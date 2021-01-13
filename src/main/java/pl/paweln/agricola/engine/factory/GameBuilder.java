package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.engine.Game;
import pl.paweln.agricola.engine.GameStatus;

public class GameBuilder {
    protected final Game game;

    public GameBuilder(int roundNumber) {
        this.game = new Game(roundNumber);
        this.game.getGamePhase().setGameStatus(GameStatus.NEW);
    }

    protected GameBuilder(Game game) {
        this.game = game;
    }

    public GameBuilder withName(String gameName) {
        this.game.setName(gameName);
        return this;
    }

    public GameBuilder withGameStatus(GameStatus gameStatus) {
        this.game.getGamePhase().setGameStatus(gameStatus);
        return this;
    }

    public GameBuilder withGameRound(int round) {
        this.game.getGamePhase().setRound(round);
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
