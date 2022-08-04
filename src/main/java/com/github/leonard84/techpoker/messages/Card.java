package com.github.leonard84.techpoker.messages;

public class Card {
    private String key;
    private String displayName;
    private String displayDescription;

    public Card() {
    }

    public Card(String key, String displayName, String displayDescription) {
        this.key = key;
        this.displayName = displayName;
        this.displayDescription = displayDescription;
    }

    public String getKey() {
        return key;
    }

    public String getDisplayName() {
        return displayName;
    }
}
