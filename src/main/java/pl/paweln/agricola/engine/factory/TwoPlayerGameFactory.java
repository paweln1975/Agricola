package pl.paweln.agricola.engine.factory;

public class TwoPlayerGameFactory extends AbstractGameFactory {

    public TwoPlayerGameFactory(String gameName, String ... players) {
        super(gameName, players);
        validatePlayers(2);
    }
    @Override
    protected void createPlayerBasedAction() {
        if (logger.isDebugEnabled()) {
            logger.debug("No player actions for 2P game.");
        }
    }
}
