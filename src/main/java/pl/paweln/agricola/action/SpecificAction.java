package pl.paweln.agricola.action;

import pl.paweln.agricola.engine.Engine;
import pl.paweln.agricola.engine.GamePhaseEventArgs;
import pl.paweln.agricola.engine.action.ActionParams;
import pl.paweln.agricola.player.Player;

public abstract class SpecificAction extends Action {
    protected SpecificAction(ActionType actionType) {
        super(actionType);
    }

    @Override
    public ActionParams perform(Player player, ActionParams actionParams) {
        throw new UnsupportedOperationException("Action not implemented yet");
    }

    @Override
    public void rollback(ActionParams actionParams) {
        throw new UnsupportedOperationException("Action not implemented yet");
    }

    @Override
    public void processGamePhaseEvent(GamePhaseEventArgs<Engine> args) {

    }
}
