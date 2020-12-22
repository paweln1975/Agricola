package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.engine.Game;
import pl.paweln.agricola.engine.util.Randomizer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGameFactory implements GameFactory {
    protected GameBuilder gameBuilder = new GameBuilder();

    private String gameName;
    private List<String> players = new LinkedList<>();

    private Randomizer<String> playerNamesRandomizer;

    protected AbstractGameFactory(String gameName, String ... players) {
        this.gameName = gameName;
        Collections.addAll(this.players, players);
        this.playerNamesRandomizer = new Randomizer<>(this.players);
    }

    protected void validatePlayers(int expectedPlayersCount) {
        if (this.gameName == null || this.gameName.trim().equals("")) {
            throw new IllegalArgumentException("Game name cannot be empty.");
        }

        if (this.players.size() != expectedPlayersCount) {
            throw new IllegalArgumentException("Number of players must match game type. Expected:"
                    + expectedPlayersCount + " but given:" + this.players.size());
        }
    }

    @Override
    public Game createGame() {
        setupBasicParameter();
        setupPlayers();
        createPlayerBasedAction();
        createPredefinedActions();
        createRoundActions();
        return gameBuilder.build();
    }

    public void setupPlayers() {
        for (int i = 0; i < this.players.size(); i++) {
            this.gameBuilder.addPlayer(
                this.playerNamesRandomizer.selectRandomElementAndRemove()
            );
        }
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
