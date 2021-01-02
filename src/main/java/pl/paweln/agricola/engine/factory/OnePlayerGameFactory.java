package pl.paweln.agricola.engine.factory;

public class OnePlayerGameFactory extends AbstractGameFactory {

    public OnePlayerGameFactory(String gameName, String ... players) {
        super(gameName, players);
        validatePlayers(1);
    }

    @Override
    protected void createPlayerBasedAction() {
        if (logger.isDebugEnabled()) {
            logger.debug("No player actions for 1P game.");
        }
    }

}
