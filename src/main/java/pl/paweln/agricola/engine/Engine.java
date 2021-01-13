package pl.paweln.agricola.engine;

import pl.paweln.agricola.engine.factory.GameFactory;

public class Engine {

    private final Event gamePhaseChange = new Event();

    private Game game;

    public Engine(GameFactory factory) {
        this.game = factory.createGame();
        gamePhaseChange.addHandler(this.game);
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
}
