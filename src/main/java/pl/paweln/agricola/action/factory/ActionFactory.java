package pl.paweln.agricola.action.factory;

import pl.paweln.agricola.action.*;
import pl.paweln.agricola.player.ResourceType;

public class ActionFactory {
    private ActionFactory() {}

    public static Action createAction(ActionType actionType) {
        Action action;
        switch (actionType) {
            case BUILD_ROOMS_OR_BARNS:
                action = (new SpecificActionBuilder<>(BuildRoomsOrBarnsAction.class))
                        .availableFromRound(1)
                        .build();
                break;
            case STARTING_PLAYER:
                action = (new SpecificActionBuilder<>(StartingPlayerAction.class))
                        .availableFromRound(1)
                        .build();
                break;
            case TAKE_1_GRAIN:
                action = createResourceAction(
                        "Take 1 Grain",
                        ResourceType.GRAIN,
                        actionType,
                        1,
                        true
                        );
                break;
            case PLOW_1_FIELD:
                action = (new SpecificActionBuilder<>(PlowFieldAction.class))
                        .availableFromRound(1)
                        .build();
                break;
            case BUILD_1_BARN_OR_BAKE_BREAD:
                action = (new SpecificActionBuilder<>(Build1BarnOrBakeBreadAction.class))
                        .availableFromRound(1)
                        .build();
                break;
            case DAY_LABOUR:
                action = createResourceAction(
                        "Day Labour",
                        ResourceType.FOOD,
                        actionType,
                        2,
                        true
                );
                break;
            case CATCH_FISH:
                action = createResourceAction(
                        "Catch Fish",
                        ResourceType.FOOD,
                        actionType,
                        1,
                        false
                );
                break;
            case TAKE_WOOD_3_ACC:
                action = createResourceAction(
                        "3 Wood",
                        ResourceType.WOOD,
                        actionType,
                        3,
                        false
                );
                break;
            case TAKE_CLAY_1_ACC:
                action = createResourceAction(
                        "1 Clay",
                        ResourceType.CLAY,
                        actionType,
                        1,
                        false
                );
                break;
            case TAKE_REED_1_ACC:
                action = createResourceAction(
                        "1 Reed",
                        ResourceType.REED,
                        actionType,
                        1,
                        false
                );
                break;
            case TAKE_WOOD_2_ACC_3P_4P:
                action = createResourceAction(
                        "2 Wood",
                        ResourceType.WOOD,
                        actionType,
                        2,
                        false
                );
                break;
            case TAKE_1RESOURCE_1F_3P:
                action = (new SpecificActionBuilder<>(Take1ResourcePlus1FAction.class))
                        .availableFromRound(1)
                        .build();
                break;
            case TAKE_2RESOURCES_3P_4P:
                action = (new SpecificActionBuilder<>(Take2ResourcesAction.class))
                        .availableFromRound(1)
                        .build();
                break;
            case TAKE_CLAY_1_ACC_3P:
                action = createResourceAction(
                        "1 Clay 3P",
                        ResourceType.CLAY,
                        actionType,
                        1,
                        false
                );
                break;
            case TAKE_CLAY_2_ACC_4P:
                action = createResourceAction(
                        "2 Clay 4P",
                        ResourceType.CLAY,
                        actionType,
                        2,
                        false
                );
                break;
            case TAKE_WOOD_1_ACC_4P:
                action = createResourceAction(
                        "1 Wood",
                        ResourceType.WOOD,
                        actionType,
                        1,
                        false
                );
                break;
            case TAKE_1R_1S_1F_4P:
                action = (new SpecificActionBuilder<>(Take1R1S1FAction.class))
                        .availableFromRound(1)
                        .build();
                break;
            case TAKE_WOOD_4_ACC_5P:
                action = createResourceAction(
                        "4 Wood",
                        ResourceType.WOOD,
                        actionType,
                        4,
                        false
                );
                break;
            case TAKE_CLAY_3_ACC_5P:
                action = createResourceAction(
                        "3 Clay",
                        ResourceType.CLAY,
                        actionType,
                        3,
                        false
                );
                break;
            case TAKE_FOOD_OR_BUILD_1_ROOM_5P:
                action = (new SpecificActionBuilder<>(ItinerantArtists5PAction.class))
                        .availableFromRound(1)
                        .build();
                break;
            case TAKE_FOOD_4P:
                action = createResourceAction(
                        "Itinerant Artists",
                        ResourceType.FOOD,
                        actionType,
                        1,
                        false
                );
                break;
            case TAKE_ANIMALS:
                action = (new SpecificActionBuilder<>(GrangeAction.class))
                        .availableFromRound(1)
                        .build();
                break;
            case TAKE_1R_1S_1W_5P:
                action = (new SpecificActionBuilder<>(Take1R1S1WAction.class))
                        .availableFromRound(1)
                        .build();
                break;
            case TAKE_2_RESOURCES_OR_FAMILY_GROW_5P:
                action = (new SpecificActionBuilder<>(FamilyGrow5PAction.class))
                        .availableFromRound(1)
                        .build();
                break;
            case BUILD_FENCES:
                action = (new SpecificActionBuilder<>(BuildFencesAction.class))
                        .build();
                break;
            case BUILD_MAJOR_IMPROVEMENT:
                action = (new SpecificActionBuilder<>(BuildImprovementAction.class))
                        .build();
                break;
            case TAKE_SHEEP:
                action = createResourceAction(
                        "1 Sheep",
                        ResourceType.SHEEP,
                        actionType,
                        1,
                        false
                );
                break;
            case SOW_OR_BAKE_BREAD:
                action = (new SpecificActionBuilder<>(SowOrBakeBreadAction.class))
                        .build();
                break;
            case RENOVATION_AND_BUILD_IMPROVEMENT:
                action = (new SpecificActionBuilder<>(AfterRenovationBuildImprovementAction.class))
                        .build();
                break;
            case FAMILY_GROW:
                action = (new SpecificActionBuilder<>(FamilyGrowAction.class))
                        .build();
                break;
            case TAKE_STONE_1_ACC_PH2:
                action = createResourceAction(
                        "1 Stone 2 Phase",
                        ResourceType.STONE,
                        actionType,
                        1,
                        false
                );
                break;
            case TAKE_VEGETABLE:
                action = createResourceAction(
                        "1 Vegetable",
                        ResourceType.VEGETABLE,
                        actionType,
                        1,
                        true
                );
                break;
            case TAKE_WILD_BOAR:
                action = createResourceAction(
                        "1 Wild Boar",
                        ResourceType.WILD_BOAR,
                        actionType,
                        1,
                        false
                );
                break;
            case TAKE_COW:
                action = createResourceAction(
                        "1 Cow",
                        ResourceType.COW,
                        actionType,
                        1,
                        false
                );
                break;
            case TAKE_STONE_1_ACC_PH4:
                action = createResourceAction(
                        "1 Stone 3 Phase",
                        ResourceType.STONE,
                        actionType,
                        1,
                        false
                );
                break;
            case PLOW_1_FIELD_OR_SOW:
                action = (new SpecificActionBuilder<>(PlowOrSowAction.class))
                        .build();
                break;
            case FAMILY_GROW_DESPITE_LACK_OF_PLACE:
                action = (new SpecificActionBuilder<>(FamilyGrowDespiteLackOfPlaceAction.class))
                        .build();
                break;
            case RENOVATION_AND_BUILD_FENCES:
                action = (new SpecificActionBuilder<>(AfterRenovationBuildFences.class))
                        .availableFromRound(14)
                        .build();
                break;
            default:
                throw new UnsupportedOperationException("There is possibility to create only well-known actions");
        }
        return action;
    }

    private static ResourceAction createResourceAction(String name,
                                                        ResourceType resourceType,
                                                        ActionType actionType,
                                                        int accumulationAmount,
                                                        boolean accumulateOnlyWhenEmpty) {
        ResourceActionBuilder builder = new ResourceActionBuilder(actionType);
        builder.withName(name)
                .withResourceType(resourceType)
                .availableFromRound(1)
                .withAccumulation(accumulationAmount);
        if (accumulateOnlyWhenEmpty) {
            builder.withAccumulationWhenEmpty();
        } else {
            builder.withAccumulationEveryRound();
        }
        return builder.build();

    }
}
