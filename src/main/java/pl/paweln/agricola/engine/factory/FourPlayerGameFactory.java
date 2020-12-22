package pl.paweln.agricola.engine.factory;

public class FourPlayerGameFactory extends AbstractGameFactory {

    public FourPlayerGameFactory(String gameName, String ... players) {
        super(gameName, players);
    }

    @Override
    protected void createPlayerBasedAction() {
        logger.debug("Creating player actions for 4P game.");
    }

}
