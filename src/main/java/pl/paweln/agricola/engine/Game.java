package pl.paweln.agricola.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.agricola.action.Action;
import pl.paweln.agricola.action.ActionType;
import pl.paweln.agricola.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private String name;
    private int roundNumber;
    private GameStatus gameStatus;
    private int order;

    private final List<Player> playerList = new LinkedList<>();

    private final Map<ActionType, Action> actionMap = new LinkedHashMap<>();

    public Game() {
        gameStatus = GameStatus.NEW;
        roundNumber = 0;
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

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("setGameStatus: %s", this.toString()));
        }
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
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

    public int getPlayersCount() {
        return this.playerList.size();
    }

    public int getActionCount() { return this.actionMap.size(); }

    public int getRoundNumber() {
        return roundNumber;
    }

    protected int increaseRoundNumber() {
        return ++this.roundNumber;
    }

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
                ", status=" + gameStatus +
                ", roundNumber=" + roundNumber +
                '}';
    }
}
