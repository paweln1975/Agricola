package pl.paweln.agricola.engine.action;

import pl.paweln.agricola.player.Resource;
import pl.paweln.agricola.player.ResourceType;

public class ActionParamsBuilder {
    private final ActionParams actionParams = new ActionParams();

    public ActionParamsBuilder withResource(ResourceType resourceType, int value) {
        Resource resource = new Resource(resourceType);
        resource.setValue(value);
        this.actionParams.setResource(resource);
        return this;
    }

    public ActionParams build() {
        return this.actionParams;
    }
}
