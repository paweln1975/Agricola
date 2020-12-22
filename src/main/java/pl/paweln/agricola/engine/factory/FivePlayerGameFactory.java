package pl.paweln.agricola.engine.factory;

public class FivePlayerGameFactory extends AbstractGameFactory {

    public FivePlayerGameFactory(String gameName, String ... players) {
        super(gameName, players);
    }
    @Override
    protected void createPlayerBasedAction() {
        logger.debug("Creating player actions for 5P game.");
    }
}