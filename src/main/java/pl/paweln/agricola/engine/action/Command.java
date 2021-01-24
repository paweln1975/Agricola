package pl.paweln.agricola.engine.action;

import pl.paweln.agricola.player.Player;

public interface Command {
    void execute (Player player);
    void rollback ();
}
