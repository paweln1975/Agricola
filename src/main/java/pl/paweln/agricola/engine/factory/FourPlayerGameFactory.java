package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.action.ActionType;

public class FourPlayerGameFactory extends AbstractGameFactory {

    public FourPlayerGameFactory(String gameName, String ... players) {
        super(gameName, players);
        validatePlayers(4);
    }

    @Override
    protected void createPlayerBasedAction() {
        super.gameBuilder.configureActions()
                .withType(ActionType.TAKE_CLAY_2_ACC_4P)
                .withType(ActionType.TAKE_WOOD_1_ACC_4P)
                .withType(ActionType.TAKE_WOOD_2_ACC_3P_4P)
                .withType(ActionType.TAKE_1R_1S_1F_4P)
                .withType(ActionType.TAKE_FOOD_4P)
                .withType(ActionType.TAKE_2RESOURCES_3P_4P);
    }

}
