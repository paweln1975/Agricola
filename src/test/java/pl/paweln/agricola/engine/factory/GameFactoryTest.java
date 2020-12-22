package pl.paweln.agricola.engine.factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.paweln.agricola.engine.Game;
import pl.paweln.agricola.engine.GameType;

public class GameFactoryTest {
    String gameName;

    @Before
    public void setup() {
        gameName = "Game name 1";
    }

    @Test
    public void testOnePlayerFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_1P, this.gameName, "paweln");
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
    }

    @Test
    public void testTwoPlayerFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, this.gameName, "paweln");
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
    }

    @Test
    public void testThreePlayerFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_3P, this.gameName, "paweln");
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
    }

    @Test
    public void testFourPlayerFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_4P, this.gameName, "paweln");
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
    }

    @Test
    public void testFivePlayerFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_5P, this.gameName, "paweln");
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
    }
}
