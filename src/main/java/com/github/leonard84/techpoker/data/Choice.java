package com.github.leonard84.techpoker.data;

public enum Choice {
    D1(1, "Verkünden", "Die Führungskraft trifft eine Entscheidung und verkündet sie. Meist ohne Begründung oder Diskussion.", "#f27da9"),
    D2(2, "Verkaufen", "Die Führungskraft entscheidet, begründet die Entscheidung und versucht das Team davon zu überzeugen.", "#ee4437"),
    D3(3, "Befragen", "Die Führungskraft holt Meinungen des Teams ein, bevor sie eine Entscheidung trifft.", "#f48226"),
    D4(4, "Einigen", "Es gibt eine Diskussion mit allen Beteiligten und gemeinsam wird im Konsens entschieden.", "#ddb92e"),
    D5(5, "Beraten", "Nachdem sich das Team Rat bei der Führungskraft einholt, trifft das Team die Entscheidung.", "#0fb16d"),
    D6(6, "Erkundigen", "Das Team trifft die Entscheidung und die Führungskraft erkundigt sich nach dem Ergebnis und den Beweggründen.", "#08bbe8"),
    D7(7, "Delegieren", "Die Führungskraft überlässt dem Team die autonome Entscheidung.", "#8b72b4"),
    COFFEE("☕", "Ich trinke lieber Kaffee", "#627c7b"),
    QUESTIONMARK("?", "Ich entziehe mich", "#4285f4");

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
