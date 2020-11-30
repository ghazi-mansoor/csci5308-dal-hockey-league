package com.groupten.statemachine.simulation.draft.strategy;

public class DraftContext implements IDraftContext {
    private IDraftStrategy strategy;

    @Override
    public void setStrategy(IDraftStrategy draftStrategy) {
        this.strategy = draftStrategy;
    }

    @Override
    public void executeStrategy() {
        strategy.execute();
    }
}
