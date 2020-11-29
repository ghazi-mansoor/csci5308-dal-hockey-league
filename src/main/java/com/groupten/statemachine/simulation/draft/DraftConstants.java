package com.groupten.statemachine.simulation.draft;

public enum DraftConstants {
    NUMBER_OF_ROUNDS(7),
    PERCENTAGE_OF_FORWARD_PLAYERS(0.5),
    PERCENTAGE_OF_DEFENSE_PLAYERS(0.4),
    PERCENTAGE_OF_GOALIE_PLAYERS(0.1);

    private int intValue;
    private double doubleValue;

    DraftConstants(int value) {
        this.intValue = value;
    }

    DraftConstants(double value) {
        this.doubleValue = value;
    }
}
