package pl.paweln.agricola.engine.factory;

import pl.paweln.agricola.action.Action;
import pl.paweln.agricola.action.ActionType;
import pl.paweln.agricola.action.factory.ActionFactory;
import pl.paweln.agricola.engine.Game;

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

        super.game.addAction(actionSow);
        super.game.addAction(actionSheep);
        super.game.addAction(actionFences);
        super.game.addAction(actionImprovement);
        return this;
    }

    public GameActionBuilder withSecondPhaseActions() {
        Action actionFamily = ActionFactory.createAction(ActionType.FAMILY_GROW);
        Action actionStone = ActionFactory.createAction(ActionType.TAKE_STONE_1_ACC_PH2);
        Action actionReno = ActionFactory.createAction(ActionType.RENOVATION_AND_BUILD_IMPROVEMENT);

        super.game.addAction(actionFamily);
        super.game.addAction(actionStone);
        super.game.addAction(actionReno);
        return this;
    }

    public GameActionBuilder withThirdPhaseActions() {
        Action actionVege = ActionFactory.createAction(ActionType.TAKE_VEGETABLE);
        Action actionWildBoar = ActionFactory.createAction(ActionType.TAKE_WILD_BOAR);

        super.game.addAction(actionVege);
        super.game.addAction(actionWildBoar);
        return this;
    }

    public GameActionBuilder withFourthPhaseActions() {
        Action actionStone = ActionFactory.createAction(ActionType.TAKE_STONE_1_ACC_PH4);
        Action actionCow = ActionFactory.createAction(ActionType.TAKE_COW);

        super.game.addAction(actionStone);
        super.game.addAction(actionCow);
        return this;
    }

    public GameActionBuilder withFifthPhaseActions() {
        Action actionPlowAndSow = ActionFactory.createAction(ActionType.PLOW_1_FIELD_OR_SOW);
        Action actionFamily = ActionFactory.createAction(ActionType.FAMILY_GROW_DESPITE_LACK_OF_PLACE);

        super.game.addAction(actionPlowAndSow);
        super.game.addAction(actionFamily);
        return this;
    }

    public GameActionBuilder withLastPhaseAction() {
        Action action = ActionFactory.createAction(ActionType.RENOVATION_AND_BUILD_FENCES);
        super.game.addAction(action);
        return this;
    }
}
