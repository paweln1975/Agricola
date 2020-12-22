package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.engine.Game;
import pl.paweln.agricola.engine.util.Randomizer;
import pl.paweln.agricola.player.Color;
import pl.paweln.agricola.player.Player;

import java.util.Arrays;

public class GameBuilder {
    private Game game = new Game();

    private Randomizer<Color> colorRandomizer = new Randomizer<>(
            Arrays.asList(Color.values()));

    protected GameBuilder addGameName(String gameName) {
        this.game.setName(gameName);
        return this;
    }

    protected GameBuilder addPlayer(String playerName) {
        Player player = new Player(playerName);
        player.setColor(colorRandomizer.selectRandomElementAndRemove());
        this.game.addPlayer(player);
        return this;
    }


    public Game build() {
        return this.game;
    }
}
