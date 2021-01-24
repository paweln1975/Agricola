package pl.paweln.agricola.engine;

import org.junit.Assert;
import org.junit.Test;
import pl.paweln.agricola.action.Action;
import pl.paweln.agricola.action.ActionType;
import pl.paweln.agricola.action.ResourceAction;
import pl.paweln.agricola.engine.factory.GameFactory;
import pl.paweln.agricola.engine.factory.GameFactoryManager;
import pl.paweln.agricola.player.ResourceType;

public class EngineTest {
    @Test
    public void testGameStart() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_1P, "test", "test");
        Engine engine = new Engine(gameFactory);
        engine.startGame();

        Assert.assertEquals(1, engine.getGame().getRoundNumber());
        Assert.assertEquals(GameStatus.WORK_PREPARE, engine.getGame().getGameStatus());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNegativeResourceAssignment() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_1P, "test", "test");
        Engine engine = new Engine(gameFactory);
        engine.startGame();
        engine.getGame().getPlayer(0).setResourceAmount(ResourceType.FOOD, -1);
    }

    @Test
    public void startingPlayerTest() {
        String firstPlayer = "Player 1";
        String secondPlayer = "Player 2";
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P,
                "test", firstPlayer, secondPlayer);
        Engine engine = new Engine(gameFactory);
        engine.startGame();

        Assert.assertNotNull(engine.getGame().getCurrentPlayer());
        String playerName = engine.getGame().getCurrentPlayer().getName();
        Assert.assertTrue(playerName.equals(firstPlayer) || playerName.equals(secondPlayer) );
    }

    @Test
    public void prepareFirstRoundTest() {
        String firstPlayer = "Player 1";
        String secondPlayer = "Player 2";
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P,
                "test", firstPlayer, secondPlayer);
        Engine engine = new Engine(gameFactory);
        engine.startGame();

        Action action = engine.getGame().getAction(ActionType.TAKE_WOOD_3_ACC);
        Assert.assertNull(action.getPlayer());

        Assert.assertEquals(3, ((ResourceAction)action).getResource().getValue());
        Assert.assertEquals(ResourceType.WOOD, ((ResourceAction)action).getResource().getType() );
    }
}
