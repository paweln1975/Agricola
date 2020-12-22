package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.engine.Game;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGameFactory implements GameFactory {
    protected GameBuilder gameBuilder = new GameBuilder();

    private String gameName;
    private List<String> players = new LinkedList<>();

    protected AbstractGameFactory(String gameName, String ... players) {
        this.gameName = gameName;
        Collections.addAll(this.players, players);
    }

    @Override
    public Game createGame() {
        setupBasicParameter();
        createPlayerBasedAction();
        createPredefinedActions();
        createRoundActions();
        return gameBuilder.build();
    }

    private void setupBasicParameter() {
        this.gameBuilder
                .addGameName(this.gameName);
    }

    private void createPredefinedActions() {

    }

    protected abstract void createPlayerBasedAction();

    private void createRoundActions() {

    }

}
