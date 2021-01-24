package pl.paweln.agricola.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.agricola.action.Action;
import pl.paweln.agricola.action.ActionType;
import pl.paweln.agricola.engine.action.ActionCommand;
import pl.paweln.agricola.engine.action.Command;
import pl.paweln.agricola.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Game implements GamePhaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private String name;
    private final GamePhase gamePhase;
    private int order;
    private Player currentPlayer;

    private final List<Player> playerList = new LinkedList<>();

    private final Map<ActionType, Action> actionMap = new LinkedHashMap<>();

    private final List<Command> commandList = new LinkedList<>();

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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    protected void executeCommand(Command command) {
        if (command instanceof ActionCommand) {
            for (Command c : this.commandList) {
                if (c instanceof ActionCommand) {
                    throw new IllegalArgumentException("Only one Action Command is allow for the current player turn.");
                }
            }
        }

        this.commandList.add(command);
        command.execute(this.getCurrentPlayer());
    }

    protected void commit() {
        this.commandList.clear();
        nextPlayer();
    }

    protected void rollback() {
        ListIterator<Command> iterator = this.commandList.listIterator(this.commandList.size());
        while (iterator.hasPrevious()) {
            Command command = iterator.previous();
            command.rollback();
        }
        this.commandList.clear();
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

    public int getActionCount() {
        return this.actionMap.size();
    }


    public Player getPlayer(int number) {
        if (number <= 0 || number > this.playerList.size())
            throw new IllegalArgumentException("This game is configured for " + this.playerList.size() + " player(s), but requested is:" + number);
        return this.playerList.get(number - 1);
    }

    public Action getAction(ActionType actionType) {
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
                ", gamePhase=" + gamePhase +
                '}';
    }

    private boolean nextPlayer() {
        boolean playerChanged = false;

        if (this.playerList.isEmpty()) {
            throw new IllegalStateException("Number of players is equal to 0.");
        }
        if (currentPlayer == null) {
            this.currentPlayer = this.playerList.get(0);
            playerChanged = true;
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("Setting starting player to the first player in the list:%s", this.currentPlayer.getName()));
            }
        } else {
            boolean currentFound = false;
            for (Player player : this.playerList) {
                if (player.equals(this.currentPlayer)) {
                    currentFound = true;
                    continue;
                }
                // logic only for work phase - extend for other phases
                if (currentFound && player.getAvailableActionCount() > 0) {
                    this.currentPlayer = player;
                    playerChanged = true;
                    break;
                }
            }

            // last in the list reached and still not found - start from the beginning
            if (!playerChanged) {
                for (Player player : this.playerList) {
                    if (player.getAvailableActionCount() > 0) {
                        this.currentPlayer = player;
                        playerChanged = true;
                    }
                    if (player.equals(this.currentPlayer)) {
                        break;
                    }
                }
            }
        }

        if (logger.isDebugEnabled()) {
            if (playerChanged) {
                logger.debug(String.format("Successfully set next player to the next player in the loop:%s", this.currentPlayer.getName()));
            } else {
                logger.debug("Current player has not been changed.");
            }
        }

        return playerChanged;
    }

    @Override
    public void processGamePhaseEvent(GamePhaseEventArgs<Engine> args) {
        this.gamePhase.setGameStatus(args.getGameStatus());
        if (args.getRound() != this.gamePhase.getRound()) {
            this.gamePhase.nextRound();
        }

        if (args.getGameStatus() == GameStatus.WORK_PREPARE) {
            nextPlayer();
        }
    }
}
