package pl.paweln.agricola.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.agricola.player.Player;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private String name;
    private int roundNumber;
    private GameStatus gameStatus;

    private final List<Player> playerList = new LinkedList<>();

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

    public int getPlayersCount() {
        return this.playerList.size();
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    protected int increaseRoundNumber() {
        return ++this.roundNumber;
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
