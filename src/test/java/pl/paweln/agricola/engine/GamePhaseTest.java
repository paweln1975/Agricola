package pl.paweln.agricola.engine;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GamePhaseTest {
    @Test
    public void testEquality() {
        GamePhase gamePhase1 = new GamePhase(GameStatus.NEW, 1);
        GamePhase gamePhase2 = new GamePhase(GameStatus.NEW, 1);

        Assert.assertEquals(gamePhase1, gamePhase2);
    }

    @Test
    public void testHashCode() {
        Map<GamePhase, Integer> map = new HashMap<>();
        int value = 10;

        GamePhase gamePhase1 = new GamePhase(GameStatus.NEW, 1);
        GamePhase gamePhase2 = new GamePhase(GameStatus.NEW, 1);

        map.put(gamePhase1, value);

        Assert.assertEquals(value, map.get(gamePhase2).intValue());

    }

    @Test
    public void testCreate() {
        GamePhase phase = new GamePhase(GameStatus.NEW, GamePhase.ROUND_START);
        Assert.assertEquals(GameStatus.NEW, phase.getGameStatus());
        Assert.assertEquals(GamePhase.ROUND_START, phase.getRound());
    }

    @Test (expected = IllegalStateException.class)
    public void testRoundValidationMax() {
        new GamePhase(GameStatus.NEW, GamePhase.ROUND_MAX + 1);
    }

    @Test (expected = IllegalStateException.class)
    public void testRoundValidationMin() {
        new GamePhase(GameStatus.NEW, GamePhase.ROUND_START - 1);
    }

    @Test
    public void testIncreaseRoundNumber() {
        GamePhase phase = new GamePhase(GameStatus.NEW, GamePhase.ROUND_START);
        Assert.assertEquals(GamePhase.ROUND_START +1, phase.nextRound());
    }

    @Test (expected = IllegalStateException.class)
    public void testIncreaseRoundNumberRangeExceed() {
        GamePhase phase = new GamePhase(GameStatus.NEW, GamePhase.ROUND_MAX);
        phase.nextRound();
    }


}
