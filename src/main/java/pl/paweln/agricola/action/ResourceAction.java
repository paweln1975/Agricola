package pl.paweln.agricola.action;

import pl.paweln.agricola.player.Resource;
import pl.paweln.agricola.player.ResourceType;

public class ResourceAction extends Action {
    private Resource resource;
    private int accumulationAmount;

    // todo use the logic when round start
    private AccumulationLogic accumulationLogic;

    public ResourceAction (ActionType actionType) {
        super(actionType);
        this.accumulationLogic = new AccumulateWhenEmpty();
    }

    public void setResourceType(ResourceType resourceType) {
        this.resource = new Resource(resourceType);
    }

    public void setAccumulationLogic(AccumulationLogic logic) {
        this.accumulationLogic = logic;
    }

    public void setAccumulationAmount(int amount) {
        this.accumulationAmount = amount;
    }

    public int getAccumulationAmount() {
        return this.accumulationAmount;
    }

    public Resource getResource() {
        return this.resource;
    }

    @Override
    public void perform() {
        throw new UnsupportedOperationException("Operation not implemented yet");
    }
}
