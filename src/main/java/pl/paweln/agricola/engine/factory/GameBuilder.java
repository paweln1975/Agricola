package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.engine.Game;

public class GameBuilder {
    protected Game game = new Game();

    protected GameBuilder withName(String gameName) {
        this.game.setName(gameName);
        return this;
    }

    public GamePlayerBuilder configurePlayer(String playerName) {
        return new GamePlayerBuilder(this.game, playerName);
    }

    public GameActionBuilder configureActions() {
        return new GameActionBuilder(this.game);
    }

    public Game build() {
        return this.game;
    }
}
