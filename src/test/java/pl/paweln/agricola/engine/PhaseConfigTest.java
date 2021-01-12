package pl.paweln.agricola.engine;

import org.junit.Assert;
import org.junit.Test;

public class PhaseConfigTest {
    @Test
    public void testStartGame() {
        GamePhase phase = new GamePhase();
        GamePhase retPhase = PhaseConfig.executeTrigger(GameStatusTrigger.START, phase);
        int expRound = 1;
        GameStatus expStatus = GameStatus.WORK_PREPARE;

        Assert.assertEquals(expRound, retPhase.getRound());
        Assert.assertEquals(expStatus, retPhase.getGameStatus());
    }

    @Test
    public void testFinishPrepare() {
        int round = 5;
        GamePhase phase = new GamePhase(GameStatus.WORK_PREPARE, round);
        GamePhase retPhase = PhaseConfig.executeTrigger(GameStatusTrigger.ALL_PREPARED, phase);

        GameStatus expStatus = GameStatus.WORK_PHASE;

        Assert.assertEquals(round, retPhase.getRound());
        Assert.assertEquals(expStatus, retPhase.getGameStatus());
    }
}
