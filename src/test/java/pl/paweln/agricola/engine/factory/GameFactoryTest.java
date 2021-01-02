package pl.paweln.agricola.engine.factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.paweln.agricola.engine.Game;
import pl.paweln.agricola.engine.GameType;
import pl.paweln.agricola.player.HouseType;
import pl.paweln.agricola.player.ResourceType;

public class GameFactoryTest {
    private String gameName;
    private static int gameCounter;
    private String firstPlayer = "Player 1";
    private String secondPlayer = "Player 2";
    private String thirdPlayer = "Player 3";
    private String fourthPlayer = "Player 4";
    private String fifthPlayer = "Player 5";

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
        Assert.assertEquals(23, game.getActionCount());
    }

    @Test
    public void testTwoPlayerGameFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, this.gameName,
                firstPlayer, secondPlayer);
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
        Assert.assertEquals(2, game.getPlayersCount());
        Assert.assertEquals(23, game.getActionCount());
    }

    @Test
    public void testThreePlayerGameFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_3P, this.gameName,
                firstPlayer, secondPlayer, thirdPlayer);
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
        Assert.assertEquals(3, game.getPlayersCount());
        Assert.assertEquals(27, game.getActionCount());
    }

    @Test
    public void testFourPlayerGameFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_4P, this.gameName,
                firstPlayer, secondPlayer, thirdPlayer, fourthPlayer);
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
        Assert.assertEquals(4, game.getPlayersCount());
        Assert.assertEquals(29, game.getActionCount());
    }

    @Test
    public void testFivePlayerGameFactory() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_5P, this.gameName,
                firstPlayer, secondPlayer, thirdPlayer, fourthPlayer, fifthPlayer);
        Game game = gameFactory.createGame();

        Assert.assertEquals(this.gameName, game.getName());
        Assert.assertEquals(5, game.getPlayersCount());
        Assert.assertEquals(29, game.getActionCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullGameName() {
        GameFactory gameFactory = GameFactoryManager.createFactory(
                GameType.GAME_1P, null, firstPlayer);

        gameFactory.createGame();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyGameName() {
        GameFactory gameFactory = GameFactoryManager.createFactory(
                GameType.GAME_1P, "    ", firstPlayer);

        gameFactory.createGame();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testWrongPlayerCount() {
        GameFactory gameFactory = GameFactoryManager.createFactory(
                GameType.GAME_1P, this.gameName, firstPlayer, secondPlayer);

        gameFactory.createGame();
    }

    @Test
    public void testInitialFoodDistribution() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_3P, this.gameName,
                firstPlayer, secondPlayer, thirdPlayer);
        Game game = gameFactory.createGame();
        int expectedFirstPlayerFood = 2;
        int expectedNextPlayerFood = 3;
        Assert.assertEquals(expectedFirstPlayerFood, game.getPlayer(1).getResourceAmount(ResourceType.FOOD));
        Assert.assertEquals(expectedNextPlayerFood, game.getPlayer(2).getResourceAmount(ResourceType.FOOD));
        Assert.assertEquals(expectedNextPlayerFood, game.getPlayer(3).getResourceAmount(ResourceType.FOOD));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testWrongPlayerAccess() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_3P, this.gameName,
                firstPlayer, secondPlayer, thirdPlayer);
        Game game = gameFactory.createGame();
        game.getPlayer(4);
    }

    @Test
    public void testInitialHouseType() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_3P, this.gameName,
                firstPlayer, secondPlayer, thirdPlayer);
        Game game = gameFactory.createGame();
        HouseType expectedType = HouseType.WOODEN;
        Assert.assertEquals(expectedType, game.getPlayer(1).getBoard().getHouseType());
        Assert.assertEquals(expectedType, game.getPlayer(2).getBoard().getHouseType());
        Assert.assertEquals(expectedType, game.getPlayer(3).getBoard().getHouseType());

    }


    @Test
    public void testInitialHouseSize() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, this.gameName,
                firstPlayer, secondPlayer);
        Game game = gameFactory.createGame();
        int expHouseSize = 2;
        Assert.assertEquals(expHouseSize, game.getPlayer(1).getBoard().getHouseSize());
        Assert.assertEquals(expHouseSize, game.getPlayer(2).getBoard().getHouseSize());

    }

}
