package pl.paweln.agricola.engine;

public interface GamePhaseHandler {
    void processGamePhaseEvent(GamePhaseEventArgs<Engine> args);
}
