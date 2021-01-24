package pl.paweln.agricola.engine;

import pl.paweln.agricola.action.Action;
import pl.paweln.agricola.action.ActionType;
import pl.paweln.agricola.engine.action.ActionCommand;
import pl.paweln.agricola.engine.action.ActionParams;
import pl.paweln.agricola.engine.action.Command;
import pl.paweln.agricola.engine.factory.GameFactory;

public class Engine {

    private final Event gamePhaseChange = new Event();

    private Game game;

    public Engine(GameFactory factory) {
        this.game = factory.createGame();
        gamePhaseChange.addHandler(this.game);
        for (Action action : this.game.getActions()) {
            gamePhaseChange.addHandler(action);
        }
    }

    public Game getGame() {
        return this.game;
    }

    public void startGame() {
        if (game.getGameStatus() == GameStatus.NEW) {
            changeGamePhase(GameStatusTrigger.START);
        }
    }

    private void changeGamePhase(GameStatusTrigger trigger) {
        GamePhase gamePhase = PhaseConfig.executeTrigger(trigger, this.getGame().getGamePhase());
        gamePhaseChange.fire(new GamePhaseEventArgs<>(this, gamePhase));
    }

    public void performAction (ActionType actionType, ActionParams actionParams) {
        Action action = this.game.getAction(actionType);
        Command command = new ActionCommand(action, actionParams);
        this.game.executeCommand(command);
    }

    public void commit() {
        this.game.commit();
    }

    public void rollback() {
        this.game.rollback();
    }
}
