package pl.paweln.agricola.action.factory;

import pl.paweln.agricola.action.Action;

public class ActionBuilder<T extends ActionBuilder<T>> {
    protected Action action;

    public Action build() {
        return this.action;
    }

    public T withName(String name) {
        this.action.setName(name);
        return self();
    }

    public T availableFromRound(int roundNumber) {
        this.action.setRoundNumberAvailable(roundNumber);
        return self();
    }

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }


}
