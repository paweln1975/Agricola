package pl.paweln.agricola.player;

import org.junit.Assert;
import org.junit.Test;
import pl.paweln.agricola.engine.Game;
import pl.paweln.agricola.engine.GameType;
import pl.paweln.agricola.engine.factory.GameFactory;
import pl.paweln.agricola.engine.factory.GameFactoryManager;

public class PlayerTest {
    @Test (expected = IllegalArgumentException.class)
    public void testBoardFieldReferenceXNegative() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, "test",
                "First Player", "Second Player");
        Game game = gameFactory.createGame();
        game.getPlayer(1).getBoard().getField(-1, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBoardFieldReferenceXTooBig() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, "test",
                "First Player", "Second Player");
        Game game = gameFactory.createGame();
        game.getPlayer(1).getBoard().getField(6, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBoardFieldReferenceYNegative() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, "test",
                "First Player", "Second Player");
        Game game = gameFactory.createGame();
        game.getPlayer(1).getBoard().getField(1, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBoardFieldReferenceYTooBig() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, "test",
                "First Player", "Second Player");
        Game game = gameFactory.createGame();
        game.getPlayer(1).getBoard().getField(1, 4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBoardNegativeFieldReference() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, "test",
                "First Player", "Second Player");
        Game game = gameFactory.createGame();
        game.getPlayer(1).getBoard().getField(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBoardTooBigFieldReference() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, "test",
                "First Player", "Second Player");
        Game game = gameFactory.createGame();
        game.getPlayer(1).getBoard().getField(16);
    }

    @Test
    public void testFirstPlayerFirstField() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_2P, "test",
                "First Player", "Second Player");
        Game game = gameFactory.createGame();
        Field f = game.getPlayer(1).getBoard().getField(1);
        Assert.assertEquals(1, f.getX());
        Assert.assertEquals(1, f.getY());
        Assert.assertFalse(f.hasTopFence());
        Assert.assertFalse(f.hasBottomFence());
        Assert.assertFalse(f.hasLeftFence());
        Assert.assertFalse(f.hasRightFence());
    }

}
