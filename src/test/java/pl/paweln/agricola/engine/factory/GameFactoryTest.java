package pl.paweln.agricola.engine.factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.paweln.agricola.engine.Game;
import pl.paweln.agricola.engine.GameType;

public class GameFactoryTest {
    String gameName;
    private static int gameCounter;
    String firstPlayer = "Player 1";
    String secondPlayer = "Player 2";
    String thirdPlayer = "Player 3";
    String fourthPlayer = "Player 4";
    String fifthPlayer = "Player 5";

    @Before
    public void setup() {
        ++gameCounter;
        this.gameName = "Game name " + gameCounter;
    }

    @Test
    public void testOnePlayerGameFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_1P, this.gameName,
                firstPlayer);
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
        Assert.assertEquals(1, game.getPlayersCount());
    }

    @Test
    public void testTwoPlayerGameFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, this.gameName,
                firstPlayer, secondPlayer);
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
        Assert.assertEquals(2, game.getPlayersCount());
    }

    @Test
    public void testThreePlayerGameFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_3P, this.gameName,
                firstPlayer, secondPlayer, thirdPlayer);
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
        Assert.assertEquals(3, game.getPlayersCount());
    }

    @Test
    public void testFourPlayerGameFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_4P, this.gameName,
                firstPlayer, secondPlayer, thirdPlayer, fourthPlayer);
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
        Assert.assertEquals(4, game.getPlayersCount());
    }

    @Test
    public void testFivePlayerGameFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_5P, this.gameName,
                firstPlayer, secondPlayer, thirdPlayer, fourthPlayer, fifthPlayer);
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
        Assert.assertEquals(5, game.getPlayersCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullGameName() {
        GameFactory gameFactory = GameFactoryManager.createFactory(
                GameType.GAME_1P, null, firstPlayer);

        Game game = gameFactory.createGame();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyGameName() {
        GameFactory gameFactory = GameFactoryManager.createFactory(
                GameType.GAME_1P, "    ", firstPlayer);

        Game game = gameFactory.createGame();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testWrongPlayerCount() {
        GameFactory gameFactory = GameFactoryManager.createFactory(
                GameType.GAME_1P, this.gameName, firstPlayer, secondPlayer);

        Game game = gameFactory.createGame();
    }
}
