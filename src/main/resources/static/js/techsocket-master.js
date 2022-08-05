var stompClient = null;
var gameTemplate = null;
var resultTemplate = null;

var mode = "voting";
var sessionId = null;
var resetRequestedNotificationShown = false;


function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe("/topic/session/" + sessionId + "/stats", function (data) {
            console.log("Stats Received");
            console.log(data);
            var body = JSON.parse(data.body);
            updateGame(body);
            data.ack();
        });

        stompClient.subscribe("/topic/session/" + sessionId + "/result", function (data) {
            console.log("Result Received");
            console.log(data);
            var body = JSON.parse(data.body);
            showResult(body);
            data.ack();
        });
        updateStats();
    });
}

function init() {
    var gameSource = $("#game-template").html();
    gameTemplate = Handlebars.compile(gameSource);
    var resultSource = $("#result-template").html();
    resultTemplate = Handlebars.compile(resultSource);
    sessionId = $('body').data('game-id');
    connect();
    $(document).on('keyup', handleKeyUp);
}

function handleKeyUp(event) {
    if (event.which === 70) {// f
        finishVoting();
        event.preventDefault();
    } else if (event.which === 82 && mode === 'result') {// r
        resetVotes();
        event.preventDefault();
    }
}

function updateGame(data) {
    if (mode === 'voting') {
        data.totalVotes = data.playerVotes.length;
        data.votePercent = data.currentVotes * 100 / data.totalVotes;
        document.title = "Delegation Poker (" + data.currentVotes + "/" + data.totalVotes + ")";
        var html = gameTemplate(data);
        $('#content').html(html);
        if (data.votePercent > 99) {
            $('#progress-bar').addClass('progress-bar-success');
        } else if (data.votePercent > 74) {
            // default darker blue looks better
            //$('#progress-bar').addClass('progress-bar-info');
        } else if (data.votePercent > 49) {
            $('#progress-bar').addClass('progress-bar-warning');
        } else {
            $('#progress-bar').addClass('progress-bar-danger');
        }
        $('#finish').click(finishVoting);
        $('.kick-player').click(kickPlayer);
        $('#copyToClipboard').click(copyToClipboard);
        renderQr();
    } else if (mode === 'result') {
        document.title = "Delegation Poker";
        if (data.resetRequested) {
            showResetRequestedNotification();
        }
        $('#refresh').removeClass('btn-default').addClass('btn-success');
    }
}


function showResult(data) {
    if (mode === 'result') {
        data.averageRounded = parseFloat(data.average).toFixed(1);
        data.medianRounded = parseFloat(data.median).toFixed(1);
        var html = resultTemplate(data);
        $('#content').html(html);
        $('#reset').click(resetVotes);
        $('#refresh').click(finishVoting);
        new Morris.Bar({
            // ID of the element in which to draw the chart.
            element: 'histogram',
            // Chart data records -- each entry in this array corresponds to a point on
            // the chart.
            data: data.votes,
            // The name of the data record attribute that contains x-values.
            xkey: 'choice',
            // A list of names of data record attributes that contain y-values.
            ykeys: ['count'],
            // Labels for the ykeys -- will be displayed when you hover over the
            // chart.
            labels: ['Cards']
        });
    }
}

function showResetRequestedNotification() {
    if (resetRequestedNotificationShown) {
        return
    }
    resetRequestedNotificationShown = true;
    // Let's check if the browser supports notifications
    if (!("Notification" in window)) {
        console.log("This browser does not support desktop notification");
        return;
    }
    // Let's check whether notification permissions have already been granted
    else if (Notification.permission === "granted") {
        // If it's okay let's create a notification
        showResetRequestedNotificationInternal();
    }

    // Otherwise, we need to ask the user for permission
    else if (Notification.permission !== "denied") {
        Notification.requestPermission().then(function (permission) {
            // If the user accepts, let's create a notification
            if (permission === "granted") {
                showResetRequestedNotificationInternal();
            }
        });
    }
}

function showResetRequestedNotificationInternal() {
    var iconUrl = $('#logo').attr('src');
    var notification = new Notification("Delegation Poker: Please Reset the Voting", {
        body: "Clicking on this notification will reset the Vote",
        icon: iconUrl,
        badge: iconUrl
    });
    notification.onclick = function (ev) {
        ev.preventDefault();
        resetVotes();
    }

}

function finishVoting() {
    mode = "result";
    stompClient.send("/app/session/tally", {}, JSON.stringify({sessionId: sessionId}));
}

function kickPlayer(event) {
    var playerId = $(event.currentTarget).data('player-id');
    stompClient.send("/app/session/kick", {}, JSON.stringify({sessionId: sessionId, playerId: playerId}));
}

function updateStats() {
    stompClient.send("/app/session/stats", {}, JSON.stringify({sessionId: sessionId}));
}

function resetVotes() {
    mode = "voting";
    stompClient.send("/app/session/reset", {}, JSON.stringify({sessionId: sessionId}));
    setTimeout(updateStats, 100);
    resetRequestedNotificationShown = false;
}


function copyToClipboard() {
    var range = document.createRange();
    range.selectNodeContents(document.getElementById("joinlink"));
    var selection = window.getSelection();
    selection.removeAllRanges();
    selection.addRange(range);
    document.execCommand("copy");
    selection.removeAllRanges();
}

$(function () {
    init();
});
