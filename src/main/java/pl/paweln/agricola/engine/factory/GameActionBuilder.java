package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.action.Action;
import pl.paweln.agricola.action.ActionType;
import pl.paweln.agricola.action.factory.ActionFactory;
import pl.paweln.agricola.engine.Game;
import pl.paweln.agricola.util.Randomizer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameActionBuilder extends GameBuilder {

    public GameActionBuilder(Game game) {
        super.game = game;
    }

    public GameActionBuilder withType(ActionType actionType) {
        Action action = ActionFactory.createAction(actionType);
        super.game.addAction(action);
        return this;
    }

    public GameActionBuilder withFirstPhaseActions() {
        Action actionSow = ActionFactory.createAction(ActionType.SOW_OR_BAKE_BREAD);
        Action actionSheep = ActionFactory.createAction(ActionType.TAKE_SHEEP);
        Action actionFences = ActionFactory.createAction(ActionType.BUILD_FENCES);
        Action actionImprovement = ActionFactory.createAction(ActionType.BUILD_MAJOR_IMPROVEMENT);

        addRandomizedActions(1, 4, actionSow, actionFences, actionImprovement, actionSheep);

        return this;
    }

    public GameActionBuilder withSecondPhaseActions() {
        Action actionFamily = ActionFactory.createAction(ActionType.FAMILY_GROW);
        Action actionStone = ActionFactory.createAction(ActionType.TAKE_STONE_1_ACC_PH2);
        Action actionReno = ActionFactory.createAction(ActionType.RENOVATION_AND_BUILD_IMPROVEMENT);

        addRandomizedActions(5, 7, actionFamily, actionStone, actionReno);

        return this;
    }

    public GameActionBuilder withThirdPhaseActions() {
        Action actionVegetable = ActionFactory.createAction(ActionType.TAKE_VEGETABLE);
        Action actionWildBoar = ActionFactory.createAction(ActionType.TAKE_WILD_BOAR);

        addRandomizedActions(8, 9, actionVegetable, actionWildBoar);
        return this;
    }

    public GameActionBuilder withFourthPhaseActions() {
        Action actionStone = ActionFactory.createAction(ActionType.TAKE_STONE_1_ACC_PH4);
        Action actionCow = ActionFactory.createAction(ActionType.TAKE_COW);

        addRandomizedActions(10, 11, actionStone, actionCow);
        return this;
    }

    public GameActionBuilder withFifthPhaseActions() {
        Action actionPlowAndSow = ActionFactory.createAction(ActionType.PLOW_1_FIELD_OR_SOW);
        Action actionFamily = ActionFactory.createAction(ActionType.FAMILY_GROW_DESPITE_LACK_OF_PLACE);

        addRandomizedActions(12, 13, actionPlowAndSow, actionFamily);
        return this;
    }

    public GameActionBuilder withLastPhaseAction() {
        Action action = ActionFactory.createAction(ActionType.RENOVATION_AND_BUILD_FENCES);
        super.game.addAction(action);
        return this;
    }



    private void addRandomizedActions(int fromRound, int toRound, Action ... actions) {
        List<Action> actionList = Arrays.stream(actions).collect(Collectors.toList());
        Randomizer<Action> randomizer = new Randomizer<>(actionList);

        for (int i = fromRound; i <= toRound; i++) {
            Action action = randomizer.selectRandomElementAndRemove();
            action.setRoundNumberAvailable(i);
            super.game.addAction(action);
        }
    }
}
