package pl.paweln.agricola.engine;

import java.util.HashSet;
import java.util.Set;

public class Event {
    private Set<GamePhaseHandler> processors = new HashSet<>();

    public void addHandler(GamePhaseHandler handler) {
        this.processors.add(handler);
    }

    public void fire(GamePhaseEventArgs<Engine> args) {
        for (GamePhaseHandler p : this.processors) {
            p.processGamePhaseEvent(args);
        }
    }
}
