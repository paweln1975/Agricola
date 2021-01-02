package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.action.ActionType;
import pl.paweln.agricola.engine.Game;
import pl.paweln.agricola.util.Randomizer;
import pl.paweln.agricola.player.HouseType;
import pl.paweln.agricola.player.ResourceType;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGameFactory implements GameFactory {
    protected GameBuilder gameBuilder = new GameBuilder();

    private final String gameName;
    private final List<String> players = new LinkedList<>();

    private final Randomizer<String> playerOrderRandomizer;

    protected AbstractGameFactory(String gameName, String ... players) {
        this.gameName = gameName;
        Collections.addAll(this.players, players);
        this.playerOrderRandomizer = new Randomizer<>(this.players);
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
            this.gameBuilder
                    .configurePlayer(this.playerOrderRandomizer.selectRandomElementAndRemove())
                    .withRandomizedColor()
                    .withHouseType(HouseType.WOODEN)
                    .withResource(ResourceType.FOOD, (i == 0 ) ? 2 : 3);
        }
    }

    private void setupBasicParameter() {
        this.gameBuilder
                .withName(this.gameName);
    }

    private void createPredefinedActions() {
        this.gameBuilder
                .configureActions()
                .withType(ActionType.BUILD_ROOMS_OR_BARNS)
                .withType(ActionType.STARTING_PLAYER)
                .withType(ActionType.TAKE_WOOD_3_ACC)
                .withType(ActionType.TAKE_CLAY_1_ACC)
                .withType(ActionType.TAKE_1_GRAIN)
                .withType(ActionType.PLOW_1_FIELD)
                .withType(ActionType.TAKE_REED_1_ACC)
                .withType(ActionType.CATCH_FISH)
                .withType(ActionType.SOW_OR_BAKE_BREAD)
                .withType(ActionType.DAY_LABOUR);

    }

    protected abstract void createPlayerBasedAction();

    private void createRoundActions() {
        this.gameBuilder
                .configureActions()
                .withFirstPhaseActions()
                .withSecondPhaseActions()
                .withThirdPhaseActions()
                .withFourthPhaseActions()
                .withFifthPhaseActions()
                .withLastPhaseAction();
    }

}
