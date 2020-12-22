package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.engine.Game;

public class GameBuilder {
    private Game game = new Game();

    public GameBuilder addGameName(String gameName) {
        this.game.setName(gameName);
        return this;
    }

    public Game build() {
        return this.game;
    }
}
