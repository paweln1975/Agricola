package pl.paweln.agricola.engine;

import pl.paweln.agricola.engine.factory.GameFactory;

public class Engine {
    public static final int ROUND_MAX = 14;
    public static final  int ROUND_START = 0;

    private Game game;

    public Engine(GameFactory factory) {
        this.game = factory.createGame();
    }

    public Game getGame() {
        return this.game;
    }

    public void startGame() {
        if (game.getGameStatus() == GameStatus.NEW) {
            this.game.increaseRoundNumber();
            this.game.setGameStatus(GameStatus.STARTED);
        }
    }
}
