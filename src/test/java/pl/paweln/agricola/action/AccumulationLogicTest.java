package pl.paweln.agricola.action;

import org.junit.Assert;
import org.junit.Test;
import pl.paweln.agricola.action.factory.ResourceActionBuilder;
import pl.paweln.agricola.player.ResourceType;

public class AccumulationLogicTest {
    @Test
    public void testAccumulationWhenEmpty() {
        ResourceActionBuilder builder = new ResourceActionBuilder(ActionType.TAKE_1_GRAIN);
        ResourceAction action = builder
                .withResourceType(ResourceType.GRAIN)
                .withAccumulation(1)
                .withAccumulationWhenEmpty()
                .build();

        action.accumulate();
        Assert.assertEquals(1, action.getResource().getValue());

        action.accumulate();
        Assert.assertEquals(1, action.getResource().getValue());

    }

    @Test
    public void testAccumulationEveryRound() {
        ResourceActionBuilder builder = new ResourceActionBuilder(ActionType.TAKE_WOOD_3_ACC);
        ResourceAction action = builder
                .withResourceType(ResourceType.WOOD)
                .withAccumulation(3)
                .withAccumulationEveryRound()
                .build();

        action.accumulate();
        Assert.assertEquals(3, action.getResource().getValue());

        action.accumulate();
        Assert.assertEquals(6, action.getResource().getValue());

    }
}
