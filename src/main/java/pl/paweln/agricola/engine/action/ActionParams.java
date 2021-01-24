package pl.paweln.agricola.engine.action;

import pl.paweln.agricola.player.Resource;

public class ActionParams {
    private Resource resource;

    public static final ActionParams EMPTY_ACTION_PARAMS = new ActionParams();

    protected ActionParams() {}

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
