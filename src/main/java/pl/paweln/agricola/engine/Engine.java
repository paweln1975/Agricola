package pl.paweln.agricola.engine;

import pl.paweln.agricola.engine.factory.GameFactory;

public class Engine {
    private Game game;

    public Engine(GameFactory factory) {
        this.game = factory.createGame();
    }

    public Game getGame() {
        return this.game; // test 1
    }
}
