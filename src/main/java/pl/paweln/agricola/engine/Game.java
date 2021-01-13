package pl.paweln.agricola.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.agricola.action.Action;
import pl.paweln.agricola.action.ActionType;
import pl.paweln.agricola.player.Player;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game implements GamePhaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private String name;
    private final GamePhase gamePhase;
    private int order;

    private final List<Player> playerList = new LinkedList<>();

    private final Map<ActionType, Action> actionMap = new LinkedHashMap<>();

    public Game() {
        this.gamePhase = new GamePhase();
    }

    public Game(int roundNumber) {
        this.gamePhase = new GamePhase(GameStatus.NEW, roundNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("setName: %s", this.toString()));
        }
    }

    public void addPlayer(Player player) {
        this.playerList.add(player);
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("addPlayer: %s", player.toString()));
        }
    }

    public void addAction(Action action) {
        action.setOrder(++order);
        this.actionMap.put(action.getActionType(), action);
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("addAction: %s", action.toString()));
        }
    }

    public GameStatus getGameStatus() {
        return this.gamePhase.getGameStatus();
    }

    public GamePhase getGamePhase() {
        return gamePhase;
    }

    public int getRoundNumber() {
        return this.gamePhase.getRound();
    }

    public int getPlayersCount() {
        return this.playerList.size();
    }

    public int getActionCount() { return this.actionMap.size(); }



    public Player getPlayer(int number) {
        if (number <= 0 || number > this.playerList.size())
            throw new IllegalArgumentException("This game is configured for " + this.playerList.size() + " player(s), but requested is:" + number);
        return this.playerList.get(number-1);
    }

    public Action getAction (ActionType actionType) {
        Action action = this.actionMap.get(actionType);
        if (action == null) {
            throw new IllegalArgumentException("This game does not contain action " + actionType);
        }
        return action;
    }

    /*
    Returns the view of actions. No possibility to modify the view.
     */
    public List<Action> getActions() {
        return this.actionMap.values().stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", gamePhase=" + gamePhase+
                '}';
    }


    @Override
    public void processGamePhaseEvent(GamePhaseEventArgs<Engine> args) {
        this.gamePhase.setGameStatus(args.getGameStatus());
        if (args.getRound() != this.gamePhase.getRound()) {
            this.gamePhase.nextRound();
        }

    }
}
