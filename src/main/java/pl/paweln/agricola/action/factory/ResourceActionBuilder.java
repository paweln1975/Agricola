package pl.paweln.agricola.action.factory;

import pl.paweln.agricola.action.AccumulateEveryRound;
import pl.paweln.agricola.action.AccumulateWhenEmpty;
import pl.paweln.agricola.action.ActionType;
import pl.paweln.agricola.action.ResourceAction;
import pl.paweln.agricola.player.ResourceType;

public class ResourceActionBuilder extends ActionBuilder<ResourceActionBuilder> {
    public ResourceActionBuilder(ActionType actionType) {
        super.action = new ResourceAction(actionType);
    }

    public ResourceActionBuilder withResourceType (ResourceType resourceType) {
        ((ResourceAction)super.action).setResourceType(resourceType);
        return self();
    }

    public ResourceActionBuilder withAccumulation (int amount) {
        ((ResourceAction)super.action).setAccumulationAmount(amount);
        return self();
    }

    public ResourceActionBuilder withAccumulationEveryRound() {
        ((ResourceAction)super.action).setAccumulationLogic(
                new AccumulateEveryRound()
        );
        return self();
    }

    public ResourceActionBuilder withAccumulationWhenEmpty() {
        ((ResourceAction)super.action).setAccumulationLogic(
                new AccumulateWhenEmpty()
        );
        return self();
    }


    @Override
    public ResourceActionBuilder self() {
        return super.self();
    }

    @Override
    public ResourceAction build() {
        return (ResourceAction)super.action;
    }

}
