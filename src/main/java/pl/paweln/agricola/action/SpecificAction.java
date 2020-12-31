package pl.paweln.agricola.action;

public abstract class SpecificAction extends Action {
    protected SpecificAction(ActionType actionType) {
        super(actionType);
    }

    @Override
    public void perform() {
        throw new UnsupportedOperationException("Action not implemented yet");
    }
}
