package pl.paweln.agricola.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.agricola.engine.GamePhase;
import pl.paweln.agricola.engine.GamePhaseHandler;
import pl.paweln.agricola.engine.action.ActionParams;
import pl.paweln.agricola.player.Player;

public abstract class Action implements GamePhaseHandler {
    protected static final Logger logger = LoggerFactory.getLogger(Action.class);

    private final ActionType actionType;
    protected Player player;
    private int roundNumberAvailable;
    private String name;
    private int order;

    protected Action(ActionType actionType) {
        this.actionType = actionType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }

    public boolean isTakenByAnyPlayer() {
        return this.player != null;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract ActionParams perform(Player player, ActionParams actionParams);

    public abstract void rollback(ActionParams actionParams);

    public void setRoundNumberAvailable(int numberAvailable) {
        if (numberAvailable < 0 || numberAvailable > GamePhase.ROUND_MAX) {
            throw new IllegalArgumentException("Incorrect round number");
        }
        this.roundNumberAvailable = numberAvailable;
    }

    public int getRoundNumberAvailable() {
        return roundNumberAvailable;
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Action{" +
                "actionType=" + actionType +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", class=" + this.getClass().getSimpleName() +
                '}';
    }
}