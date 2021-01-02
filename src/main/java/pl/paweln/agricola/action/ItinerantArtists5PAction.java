package pl.paweln.agricola.action;

import pl.paweln.agricola.player.Resource;
import pl.paweln.agricola.player.ResourceType;

public class ItinerantArtists5PAction extends SpecificAction {
    Resource resource = new Resource(ResourceType.FOOD);

    public ItinerantArtists5PAction() {
        super(ActionType.TAKE_FOOD_OR_BUILD_1_ROOM_5P);
        super.setName("ItinerantArtists Or Build 1 Room");
    }
}
