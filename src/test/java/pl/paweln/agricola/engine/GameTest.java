package pl.paweln.agricola.engine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.paweln.agricola.action.Action;
import pl.paweln.agricola.action.ActionType;
import pl.paweln.agricola.action.factory.ActionFactory;
import pl.paweln.agricola.engine.factory.GameFactory;
import pl.paweln.agricola.engine.factory.GameFactoryManager;

public class GameTest {
    private String firstPlayer = "Player 1";
    private String secondPlayer = "Player 2";
    private String thirdPlayer = "Player 3";
    private String fourthPlayer = "Player 4";
    private String fifthPlayer = "Player 5";

    private String gameName;
    private static int gameCounter;

    @Before
    public void setup() {
        ++gameCounter;
        this.gameName = "Game name " + gameCounter;
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPossibilityToModifyActions() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_5P, this.gameName,
                firstPlayer, secondPlayer, thirdPlayer, fourthPlayer, fifthPlayer);
        Game game = gameFactory.createGame();

        game.getActions().add(ActionFactory.createAction(ActionType.PLOW_1_FIELD));

    }

    @Test (expected = IllegalArgumentException.class)
    public void testWrongPlayerAccess() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_3P, this.gameName,
                firstPlayer, secondPlayer, thirdPlayer);
        Game game = gameFactory.createGame();
        game.getPlayer(4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testWrongActionAccess() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_3P, this.gameName,
                firstPlayer, secondPlayer, thirdPlayer);
        Game game = gameFactory.createGame();
        game.getAction(ActionType.TAKE_1R_1S_1F_4P);
    }

    @Test
    public void testPredefinedActionAvailability() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_1P, this.gameName,
                firstPlayer);
        Game game = gameFactory.createGame();

        Action action = game.getAction(ActionType.BUILD_ROOMS_OR_BARNS);
        Assert.assertEquals(1, action.getRoundNumberAvailable());

        action = game.getAction(ActionType.STARTING_PLAYER);
        Assert.assertEquals(1, action.getRoundNumberAvailable());

        action = game.getAction(ActionType.PLOW_1_FIELD);
        Assert.assertEquals(1, action.getRoundNumberAvailable());

        action = game.getAction(ActionType.TAKE_1_GRAIN);
        Assert.assertEquals(1, action.getRoundNumberAvailable());

        action = game.getAction(ActionType.TAKE_WOOD_3_ACC);
        Assert.assertEquals(1, action.getRoundNumberAvailable());

        action = game.getAction(ActionType.TAKE_CLAY_1_ACC);
        Assert.assertEquals(1, action.getRoundNumberAvailable());

        action = game.getAction(ActionType.TAKE_REED_1_ACC);
        Assert.assertEquals(1, action.getRoundNumberAvailable());

        action = game.getAction(ActionType.DAY_LABOUR);
        Assert.assertEquals(1, action.getRoundNumberAvailable());

        action = game.getAction(ActionType.CATCH_FISH);
        Assert.assertEquals(1, action.getRoundNumberAvailable());

    }

    @Test
    public void testFirstPhaseActionsAvailability() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_1P, this.gameName,
                firstPlayer);
        Game game = gameFactory.createGame();
        int roundStart = 1;
        int roundEnd = 4;

        Action action = game.getAction(ActionType.SOW_OR_BAKE_BREAD);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));

        action = game.getAction(ActionType.TAKE_SHEEP);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));

        action = game.getAction(ActionType.BUILD_FENCES);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));

        action = game.getAction(ActionType.BUILD_MAJOR_IMPROVEMENT);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));
    }

    @Test
    public void testSecondPhaseActionsAvailability() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_1P, this.gameName,
                firstPlayer);
        Game game = gameFactory.createGame();
        int roundStart = 5;
        int roundEnd = 7;

        Action action = game.getAction(ActionType.FAMILY_GROW);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));

        action = game.getAction(ActionType.TAKE_STONE_1_ACC_PH2);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));

        action = game.getAction(ActionType.RENOVATION_AND_BUILD_IMPROVEMENT);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));

    }

    @Test
    public void testThirdPhaseActionsAvailability() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_1P, this.gameName,
                firstPlayer);
        Game game = gameFactory.createGame();
        int roundStart = 8;
        int roundEnd = 9;

        Action action = game.getAction(ActionType.TAKE_VEGETABLE);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));

        action = game.getAction(ActionType.TAKE_WILD_BOAR);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));

    }

    @Test
    public void testFourthPhaseActionsAvailability() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_1P, this.gameName,
                firstPlayer);
        Game game = gameFactory.createGame();
        int roundStart = 10;
        int roundEnd = 11;

        Action action = game.getAction(ActionType.TAKE_STONE_1_ACC_PH4);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));

        action = game.getAction(ActionType.TAKE_COW);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));

    }

    @Test
    public void testFifthPhaseActionsAvailability() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_1P, this.gameName,
                firstPlayer);
        Game game = gameFactory.createGame();
        int roundStart = 12;
        int roundEnd = 13;

        Action action = game.getAction(ActionType.FAMILY_GROW_DESPITE_LACK_OF_PLACE);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));

        action = game.getAction(ActionType.PLOW_1_FIELD_OR_SOW);
        Assert.assertTrue(isInRange(action.getRoundNumberAvailable(), roundStart, roundEnd));

    }

    @Test
    public void testSixthPhaseActionsAvailability() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_1P, this.gameName,
                firstPlayer);
        Game game = gameFactory.createGame();

        Action action = game.getAction(ActionType.RENOVATION_AND_BUILD_FENCES);
        Assert.assertEquals(14, action.getRoundNumberAvailable());

    }


    private boolean isInRange(int value, int from, int to) {
        return value >= from && value <= to;
    }

}
