package pl.paweln.agricola.action;

public class AccumulateEveryRound implements AccumulationLogic {

    @Override
    public void addResources(ResourceAction action) {
        int current = action.getResource().getValue();
        int addition = action.getAccumulationAmount();

        action.getResource().setValue(current+addition);
    }
}
