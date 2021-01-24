package pl.paweln.agricola.action;

import pl.paweln.agricola.engine.Engine;
import pl.paweln.agricola.engine.GamePhaseEventArgs;
import pl.paweln.agricola.engine.GameStatus;
import pl.paweln.agricola.engine.action.ActionParams;
import pl.paweln.agricola.engine.action.ActionParamsBuilder;
import pl.paweln.agricola.player.Player;
import pl.paweln.agricola.player.Resource;
import pl.paweln.agricola.player.ResourceType;

public class ResourceAction extends Action {

    private Resource resource;
    private int accumulationAmount;

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
    public ActionParams perform(Player player, ActionParams actionParams) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Player %s is performing the action: %s", player.getName(), this));
        }

        ActionParamsBuilder builder = new ActionParamsBuilder();
        ActionParams retActionParams = builder.withResource(
                this.resource.getType(),
                this.resource.getValue()
        ).build();

        this.player = player;
        this.player.addResourceAmount(this.resource.getType(), this.resource.getValue());
        this.resource.setValue(0);

        return retActionParams;
    }

    @Override
    public void rollback(ActionParams actionParams) {
        this.resource.setValue(actionParams.getResource().getValue());
        this.player.returnResourceAmount(
                actionParams.getResource().getType(), actionParams.getResource().getValue()
        );

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Player %s rolled back the action: %s", this.player.getName(), this));
        }

        this.player = null;
    }

    public void accumulate() {
        this.accumulationLogic.addResources(this);
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("After resource accumulation: %s", this));
        }
    }

    @Override
    public void processGamePhaseEvent(GamePhaseEventArgs<Engine> args) {
        if (args.getGameStatus() == GameStatus.WORK_PREPARE
                && args.getRound() >= this.getRoundNumberAvailable()) {
            accumulate();
        }
    }

    @Override
    public String toString() {
        return "Action{" +
                "actionType=" + super.getActionType() +
                ", name='" + super.getName() + '\'' +
                ", order=" + super.getOrder() +
                ", resource=" + this.resource +
                ", class=" + this.getClass().getSimpleName() +
                '}';
    }
}
