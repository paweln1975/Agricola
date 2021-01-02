package pl.paweln.agricola.action;

import pl.paweln.agricola.engine.Engine;
import pl.paweln.agricola.player.Player;

public abstract class Action {
    private final ActionType actionType;
    protected Player player;
    // todo implement round number action availability
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

    // todo this interface will be changed with Player and Command parameter
    public abstract void perform();

    public void setRoundNumberAvailable(int numberAvailable) {
        if (numberAvailable < 0 || numberAvailable > Engine.ROUND_MAX) {
            throw new IllegalArgumentException("Incorrect round number");
        }
        this.roundNumberAvailable = numberAvailable;
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