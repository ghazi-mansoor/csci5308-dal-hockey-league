package com.groupten.statemachine.simulation.draft.strategy;

public interface IDraftContext {
    void setStrategy(IDraftStrategy draftStrategy);
    void executeStrategy();
}
