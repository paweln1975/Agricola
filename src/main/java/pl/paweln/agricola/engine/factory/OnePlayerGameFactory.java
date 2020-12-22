package pl.paweln.agricola.engine.factory;

public class OnePlayerGameFactory extends AbstractGameFactory {

    public OnePlayerGameFactory(String gameName, String ... players) {
        super(gameName, players);
    }

    @Override
    protected void createPlayerBasedAction() {
        logger.debug("No player actions for 1P game.");
    }

}
