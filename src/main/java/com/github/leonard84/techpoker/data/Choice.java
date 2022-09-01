package com.github.leonard84.techpoker.data;

public enum Choice {
    D1(1, "Tell", "The leader makes a decision and announces it to the team, usually without explanation or discussion.", "#f27da9"),
    D2(2, "Sell", "The leader decides, justifies the decision and tries to convince the team.", "#ee4437"),
    D3(3, "Consult", "The leader seeks opinions from the team before taking a decision.", "#f48226"),
    D4(4, "Agree", "There is a discussion with all stakeholders and decisions are made together by consensus.", "#ddb92e"),
    D5(5, "Advise", "After seeking advice from the leader, the team will come to a decision.", "#0fb16d"),
    D6(6, "Inquire", "The team reaches a decision and the manager inquires about the result and the motives.", "#08bbe8"),
    D7(7, "Delegate", "The manager leaves autonomous decision-making to the team without any direct influence.", "#8b72b4"),
    COFFEE("☕", "I rather drink coffee", "#627c7b"),
    QUESTIONMARK("?", "I elude myself", "#4285f4");

    private final boolean hasValue;
    private final int value;
    private final String delegation, description, colour;

    Choice() {
        colour = "#627c7b";
        delegation = "Title";
        description = "Description";
        hasValue = false;
        value = 0;
    }

    Choice(String delegation, String description, String colour) {
        this.delegation = delegation;
        this.description = description;
        this.colour = colour;
        this.hasValue = false;
        this.value = 0;
    }

    Choice(int value, String title, String description, String colour) {
        this.value = value;
        this.delegation = title;
        this.description = description;
        this.colour = colour;
        this.hasValue = true;
    }

    public String getDelegation() {
        return delegation;
    }

    public String getDescription() {
        return description;
    }

    public String getColour() {
        return colour;
    }

    public boolean hasValue() {
        return hasValue;
    }

    public int getValue() {
        return value;
    }
}
