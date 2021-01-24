package pl.paweln.agricola.engine.action;

import org.junit.Assert;
import org.junit.Test;
import pl.paweln.agricola.action.ActionType;
import pl.paweln.agricola.action.ResourceAction;
import pl.paweln.agricola.engine.Engine;
import pl.paweln.agricola.engine.GameType;
import pl.paweln.agricola.engine.factory.GameFactory;
import pl.paweln.agricola.engine.factory.GameFactoryManager;
import pl.paweln.agricola.player.Player;
import pl.paweln.agricola.player.ResourceType;

public class ExecuteActionTest {
    private String firstPlayer = "Player 1";
    private String secondPlayer = "Player 2";

    @Test
    public void testTake3WoodAction() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, "test", firstPlayer, secondPlayer);
        Engine engine = new Engine(gameFactory);
        engine.startGame();

        Player currentPlayer = engine.getGame().getCurrentPlayer();
        engine.performAction(ActionType.TAKE_WOOD_3_ACC, ActionParams.EMPTY_ACTION_PARAMS);
        engine.commit();

        ResourceAction action = (ResourceAction) engine.getGame().getAction(ActionType.TAKE_WOOD_3_ACC);
        Assert.assertEquals(0, action.getResource().getValue());
        Assert.assertEquals(3, currentPlayer.getResourceAmount(ResourceType.WOOD));

        if (currentPlayer.getName().equals(firstPlayer)) {
            Assert.assertEquals(secondPlayer, engine.getGame().getCurrentPlayer().getName());
        } else {
            Assert.assertEquals(firstPlayer, engine.getGame().getCurrentPlayer().getName());
        }
    }

    @Test (expected = IllegalStateException.class)
    public void testDouble3WoodAction() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, "test", firstPlayer, secondPlayer);
        Engine engine = new Engine(gameFactory);
        engine.startGame();

        engine.performAction(ActionType.TAKE_WOOD_3_ACC, ActionParams.EMPTY_ACTION_PARAMS);
        engine.commit();

        engine.performAction(ActionType.TAKE_WOOD_3_ACC, ActionParams.EMPTY_ACTION_PARAMS);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testDoubleActionsInARow() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, "test", firstPlayer, secondPlayer);
        Engine engine = new Engine(gameFactory);
        engine.startGame();

        engine.performAction(ActionType.TAKE_WOOD_3_ACC, ActionParams.EMPTY_ACTION_PARAMS);
        engine.performAction(ActionType.TAKE_CLAY_1_ACC, ActionParams.EMPTY_ACTION_PARAMS);
        engine.commit();

    }

    @Test
    public void testActionRollback() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, "test", firstPlayer, secondPlayer);
        Engine engine = new Engine(gameFactory);
        engine.startGame();

        Player currentPlayer = engine.getGame().getCurrentPlayer();
        engine.performAction(ActionType.TAKE_WOOD_3_ACC, ActionParams.EMPTY_ACTION_PARAMS);
        engine.rollback();

        ResourceAction action = (ResourceAction) engine.getGame().getAction(ActionType.TAKE_WOOD_3_ACC);
        Assert.assertEquals(3, action.getResource().getValue());
        Assert.assertEquals(0, currentPlayer.getResourceAmount(ResourceType.WOOD));

        if (currentPlayer.getName().equals(firstPlayer)) {
            Assert.assertEquals(firstPlayer, engine.getGame().getCurrentPlayer().getName());
        } else {
            Assert.assertEquals(secondPlayer, engine.getGame().getCurrentPlayer().getName());
        }
    }
}
