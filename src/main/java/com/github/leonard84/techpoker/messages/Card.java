package com.github.leonard84.techpoker.messages;

public class Card {
    private String key;
    private String displayName;
    private String displayDescription;
    private String colour;

    public Card() {
    }

    public Card(String key, String displayName, String displayDescription, String colour) {
        this.key = key;
        this.displayName = displayName;
        this.displayDescription = displayDescription;
        this.colour = colour;
    }

    public String getKey() {
        return key;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    public String getColour() {
        return colour;
    }
}
