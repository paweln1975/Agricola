package pl.paweln.agricola.engine;

import pl.paweln.agricola.engine.factory.GameFactory;

public class Engine {
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
