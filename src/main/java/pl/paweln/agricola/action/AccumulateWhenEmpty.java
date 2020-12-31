package pl.paweln.agricola.action;

public class AccumulateWhenEmpty implements AccumulationLogic {

    @Override
    public void addResources(ResourceAction action) {
        if (action.getResource().getValue() == 0) {
            action.getResource().setValue(action.getAccumulationAmount());
        }
    }
}
