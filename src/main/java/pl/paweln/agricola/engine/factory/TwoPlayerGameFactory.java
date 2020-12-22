package pl.paweln.agricola.engine.factory;

public class TwoPlayerGameFactory extends AbstractGameFactory {

    public TwoPlayerGameFactory(String gameName, String ... players) {
        super(gameName, players);
    }
    @Override
    protected void createPlayerBasedAction() {
        logger.debug("No player actions for 2P game.");
    }
}
