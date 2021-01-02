package pl.paweln.agricola.action;

public class GrangeAction extends SpecificAction {
    public GrangeAction() {
        super(ActionType.TAKE_ANIMALS);
        super.setName("Take 1 Sheep either 1 Wild Board either 1 Cow");
    }
}
