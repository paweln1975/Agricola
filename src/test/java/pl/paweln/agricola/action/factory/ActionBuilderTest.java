package pl.paweln.agricola.action.factory;

import org.junit.Assert;
import org.junit.Test;
import pl.paweln.agricola.action.*;
import pl.paweln.agricola.engine.Engine;
import pl.paweln.agricola.player.ResourceType;

public class ActionBuilderTest {
    @Test
    public void testResourceActionBuilder() {
        ResourceActionBuilder builder = new ResourceActionBuilder(ActionType.TAKE_WOOD_3_ACC);
        Action action = builder.withName("Take 3 wood")
                .withResourceType(ResourceType.WOOD)
                .availableFromRound(1)
                .withAccumulation(3)
                .build();
        Assert.assertEquals(ActionType.TAKE_WOOD_3_ACC, action.getActionType());
        Assert.assertTrue(action instanceof ResourceAction);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testResourceRoundValidationMin() {
        ResourceActionBuilder builder = new ResourceActionBuilder(ActionType.TAKE_WOOD_3_ACC);
        builder.withName("Take 3 wood")
                .withResourceType(ResourceType.WOOD)
                .availableFromRound(-1)
                .withAccumulation(3)
                .build();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testResourceRoundValidationMax() {
        ResourceActionBuilder builder = new ResourceActionBuilder(ActionType.TAKE_WOOD_3_ACC);
        builder.withName("Take 3 wood")
                .withResourceType(ResourceType.WOOD)
                .availableFromRound(Engine.ROUND_MAX +1)
                .withAccumulation(3)
                .withAccumulationEveryRound()
                .build();
    }

    @Test
    public void testSpecificActionBuilder() {
        SpecificActionBuilder<PlowFieldAction> builder = new SpecificActionBuilder<>(PlowFieldAction.class);
        Action action = builder
                .availableFromRound(1)
                .build();
        Assert.assertEquals(ActionType.PLOW_1_FIELD, action.getActionType());
        Assert.assertTrue(action instanceof PlowFieldAction);
    }
}
