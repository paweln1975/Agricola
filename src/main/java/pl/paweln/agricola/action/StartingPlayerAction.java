package pl.paweln.agricola.action;

import pl.paweln.agricola.player.Resource;
import pl.paweln.agricola.player.ResourceType;

public class StartingPlayerAction extends SpecificAction {
    Resource resource = new Resource(ResourceType.FOOD);
    public StartingPlayerAction() {
        super(ActionType.STARTING_PLAYER);
        super.setName("Starting Player");
    }
}
