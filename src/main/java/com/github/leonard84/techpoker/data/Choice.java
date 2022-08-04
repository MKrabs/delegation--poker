package com.github.leonard84.techpoker.data;

public enum Choice {
    Proclaim(1, "Die Führungskraft trifft eine Entscheidung und verkündet sie. Meist ohne Begründung oder Diskussion."),
    Sell(2, "Die Führungskraft entscheidet, begründet die Entscheidung und versucht das Team davon zu überzeugen."),
    Consult(3, "Die Führungskraft holt Meinungen des Teams ein, bevor sie eine Entscheidung trifft."),
    Agree(4, "Es gibt eine Diskussion mit allen Beteiligten und gemeinsam wird im Konsens entschieden."),
    Advised(5, "Nachdem sich das Team Rat bei der Führungskraft einholt, trifft das Team die Entscheidung."),
    Inquire(6, "Das Team trifft die Entscheidung und die Führungskraft erkundigt sich nach dem Ergebnis und den Beweggründen."),
    Delegate(7, "Die Führungskraft überlässt dem Team die autonome Entscheidung."),
    COFFEE(),
    QUESTIONMARK();

    private final boolean hasValue;
    private final int value;
    private final String description;

    Choice() {
        description = "Description";
        hasValue = false;
        value = 0;
    }

    Choice(int value, String description) {
        this.value = value;
        this.description = description;
        hasValue = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasValue() {
        return hasValue;
    }

    public int getValue() {
        return value;
    }
}
