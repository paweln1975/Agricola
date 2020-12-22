package pl.paweln.agricola.engine.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.agricola.engine.Game;

public interface GameFactory {
    Logger logger = LoggerFactory.getLogger(GameFactory.class);
    Game createGame();
}
