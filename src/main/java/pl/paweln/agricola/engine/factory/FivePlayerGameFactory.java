package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.action.ActionType;

public class FivePlayerGameFactory extends AbstractGameFactory {

    public FivePlayerGameFactory(String gameName, String ... players) {
        super(gameName, players);
        validatePlayers(5);
    }
    @Override
    protected void createPlayerBasedAction() {
        super.gameBuilder.configureActions()
                .withType(ActionType.TAKE_CLAY_3_ACC_5P)
                .withType(ActionType.TAKE_WOOD_4_ACC_5P)
                .withType(ActionType.TAKE_FOOD_OR_BUILD_1_ROOM_5P)
                .withType(ActionType.TAKE_1R_1S_1W_5P)
                .withType(ActionType.TAKE_ANIMALS)
                .withType(ActionType.TAKE_2_RESOURCES_OR_FAMILY_GROW_5P);
    }
}
