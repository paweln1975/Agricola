package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.action.ActionType;

public class ThreePlayerGameFactory extends AbstractGameFactory {

    public ThreePlayerGameFactory(String gameName, String ... players) {
        super(gameName, players);
        validatePlayers(3);
    }
    @Override
    protected void createPlayerBasedAction() {
        super.gameBuilder.configureActions()
                .withType(ActionType.TAKE_CLAY_1_ACC_3P)
                .withType(ActionType.TAKE_WOOD_2_ACC_3P_4P)
                .withType(ActionType.TAKE_1RESOURCE_1F_3P)
                .withType(ActionType.TAKE_2RESOURCES_3P_4P);
    }
}
