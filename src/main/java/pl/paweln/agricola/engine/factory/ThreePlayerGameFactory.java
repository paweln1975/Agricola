package pl.paweln.agricola.engine.factory;

public class ThreePlayerGameFactory extends AbstractGameFactory {

    public ThreePlayerGameFactory(String gameName, String ... players) {
        super(gameName, players);
    }
    @Override
    protected void createPlayerBasedAction() {
        logger.debug("Creating player actions for 3P game.");
    }
}
