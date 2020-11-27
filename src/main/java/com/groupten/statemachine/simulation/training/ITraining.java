package com.groupten.statemachine.simulation.training;

import com.groupten.statemachine.simulation.trophy.IObserver;

public interface ITraining {
    void trainPlayers();
    void subscribe(IObserver observer);
    void unsubscribe(IObserver observer);
    void notifyObserver();
}
