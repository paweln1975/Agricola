package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.engine.GameType;

public class GameFactoryManager {
    private GameFactoryManager() {}
    public static GameFactory createFactory(GameType gameType, String gameName, String ... players) {
        GameFactory factory;
        switch (gameType) {
            case GAME_1P:
                factory = new OnePlayerGameFactory(gameName, players);
                break;
            case GAME_2P:
                factory = new TwoPlayerGameFactory(gameName, players);
                break;
            case GAME_3P:
                factory = new ThreePlayerGameFactory(gameName, players);
                break;
            case GAME_4P:
                factory = new FourPlayerGameFactory(gameName, players);
                break;
            default:
                factory = new FivePlayerGameFactory(gameName, players);
                break;

        }
        return factory;
    }
}
