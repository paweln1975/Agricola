package pl.paweln.agricola.engine;

import org.junit.Assert;
import org.junit.Test;
import pl.paweln.agricola.engine.factory.GameFactory;
import pl.paweln.agricola.engine.factory.GameFactoryManager;

public class EngineTest {
    @Test
    public void testGameStart() {
        GameFactory gameFactory = GameFactoryManager.createFactory(GameType.GAME_1P, "test", "test");
        Engine engine = new Engine(gameFactory);
        engine.startGame();

        Assert.assertEquals(1, engine.getGame().getRoundNumber());
        Assert.assertEquals(GameStatus.STARTED, engine.getGame().getGameStatus());
    }
}
