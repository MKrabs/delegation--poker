package com.github.leonard84.techpoker.data;

import java.util.ArrayList;
import java.util.Collection;

public class ChoiceResult {
    private Choice choice;
    private int count;
    private Collection<String> players = new ArrayList<>();
    private String name, colour;

    public ChoiceResult(Choice choice, int count, Collection<String> players) {
        this.choice = choice;
        this.count = count;
        this.players.addAll(players);
        this.name = choice.getDelegation();
        this.colour = choice.getColour();
    }

    public Choice getChoice() {
        return choice;
    }

    public int getCount() {
        return count;
    }

    public Collection<String> getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }
}
