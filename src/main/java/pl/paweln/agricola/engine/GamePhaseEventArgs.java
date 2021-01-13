package pl.paweln.agricola.engine;

public class GamePhaseEventArgs<T> {
    private final T source;
    private final GamePhase newGamePhase;

    public GamePhaseEventArgs(T source, GamePhase newGamePhase) {
        this.source = source;
        this.newGamePhase = newGamePhase;
    }

    public T getSource() {
        return source;
    }

    public GamePhase getNewGamePhase() {
        return newGamePhase;
    }

    public int getRound() {
        return newGamePhase.getRound();
    }

    public GameStatus getGameStatus() {
        return newGamePhase.getGameStatus();
    }
}
