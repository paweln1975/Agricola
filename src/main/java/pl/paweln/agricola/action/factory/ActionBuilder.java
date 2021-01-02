package pl.paweln.agricola.action.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.agricola.action.Action;

public class ActionBuilder<T extends ActionBuilder<T>> {
    protected static final Logger logger = LoggerFactory.getLogger(ActionBuilder.class);
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
