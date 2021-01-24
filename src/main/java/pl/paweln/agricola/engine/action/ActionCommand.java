package pl.paweln.agricola.engine.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.agricola.action.Action;
import pl.paweln.agricola.player.Player;

public class ActionCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(ActionCommand.class);
    private final Action action;
    private ActionParams actionParams;

    public ActionCommand(Action action, ActionParams actionParams) {
        this.action = action;
        this.actionParams = actionParams;
    }

    @Override
    public void execute(Player player) {
        if (this.action.isTakenByAnyPlayer()) {
            if (logger.isWarnEnabled()) {
                logger.warn(String.format("Action occupied by the player:%s. Exiting.", this.action.getPlayer().getName()));
            }
            throw new IllegalStateException("Action already taken by the player " + this.action.getPlayer().getName());
        }
        this.actionParams = action.perform(player, this.actionParams);
    }

    @Override
    public void rollback() {
        this.action.rollback(this.actionParams);
    }
}
