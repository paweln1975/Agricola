package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.engine.Game;
import pl.paweln.agricola.player.Color;
import pl.paweln.agricola.player.HouseType;
import pl.paweln.agricola.player.Player;
import pl.paweln.agricola.player.ResourceType;
import pl.paweln.agricola.util.Randomizer;

import java.util.Arrays;

public class GamePlayerBuilder extends GameBuilder {
    protected final Player player;

    private final Randomizer<Color> colorRandomizer = new Randomizer<>(
            Arrays.asList(Color.values()));

    public GamePlayerBuilder(Game game, String playerName) {
        super(game);
        this.player = new Player(playerName);
        this.game.addPlayer(this.player);
    }

    public GamePlayerBuilder withColor (Color color) {
        this.player.setColor(color);
        return this;
    }

    public GamePlayerBuilder withRandomizedColor() {
        player.setColor(colorRandomizer.selectRandomElementAndRemove());
        return this;
    }

    public GamePlayerBuilder withHouseType(HouseType houseType) {
        player.getBoard().setHouseType(houseType);
        return this;
    }

    public GamePlayerBuilder withResource(ResourceType resourceType, int value) {
        this.player.setResourceAmount(resourceType, value);
        return this;
    }
}
