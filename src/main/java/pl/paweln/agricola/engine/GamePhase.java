package pl.paweln.agricola.engine;

public class GamePhase {
    public static final int ROUND_START = 0;
    public static final int ROUND_MAX = 14;

    private GameStatus gameStatus;
    private int round;

    public GamePhase() {
        this.gameStatus = GameStatus.NEW;
        this.round = ROUND_START;
    }
    public GamePhase(GameStatus gameStatus, int round) {
        validateRound(round);
        this.gameStatus = gameStatus;
        this.round = round;
    }

    private void validateRound(int round) {
        if (round < ROUND_START || round > ROUND_MAX) {
            throw new IllegalStateException("Invalid round number:" + round);
        }
    }

    public int nextRound() {
        validateRound(this.round+1);
        return ++this.round;
    }

    public void setGameStatus(GameStatus status) {
        this.gameStatus = status;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public int getRound() {
        return round;
    }

    @Override
    public String toString() {
        return "GamePhase{" +
                "gameStatus=" + gameStatus +
                ", round=" + round +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GamePhase gamePhase = (GamePhase) o;

        if (round != gamePhase.round) return false;
        return gameStatus == gamePhase.gameStatus;
    }

    @Override
    public int hashCode() {
        int result = gameStatus.hashCode();
        result = 31 * result + round;
        return result;
    }
}
