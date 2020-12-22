package pl.paweln.agricola.engine;

import org.junit.Assert;
import org.junit.Test;
import pl.paweln.agricola.engine.factory.GameFactoryManager;

public class EngineTest {
    @Test
    public void testEngineCreation() {
        Assert.assertNotNull(new Engine(GameFactoryManager.createFactory(GameType.GAME_1P, "test", "test")));
    }
}
