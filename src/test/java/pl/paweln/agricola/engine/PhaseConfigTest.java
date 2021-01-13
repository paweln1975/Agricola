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

    @Test
    public void testWorkPhaseNoNextPlayerHarvestRound() {
        int round = 4;
        GamePhase phase = new GamePhase(GameStatus.WORK_PHASE, round);
        GamePhase retPhase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);

        GameStatus expStatus = GameStatus.HARVEST_FIELD_PHASE;

        Assert.assertEquals(round, retPhase.getRound());
        Assert.assertEquals(expStatus, retPhase.getGameStatus());
    }

    @Test
    public void testWorkPhaseNoNextPlayerNoHarvestRound() {
        int round = 5;
        GamePhase phase = new GamePhase(GameStatus.WORK_PHASE, round);
        GamePhase retPhase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);

        GameStatus expStatus = GameStatus.WORK_RETURN;

        Assert.assertEquals(round, retPhase.getRound());
        Assert.assertEquals(expStatus, retPhase.getGameStatus());
    }


    @Test
    public void testWorkReturnNoNextPlayer() {
        int round = 5;
        GamePhase phase = new GamePhase(GameStatus.WORK_RETURN, round);

        GamePhase retPhase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);

        GameStatus expStatus = GameStatus.WORK_PREPARE;
        int expRound = round+1;

        Assert.assertEquals(expRound, retPhase.getRound());
        Assert.assertEquals(expStatus, retPhase.getGameStatus());
    }

    @Test
    public void testHarvestFieldPhaseNoNextPlayer() {
        int round = 7;
        GamePhase phase = new GamePhase(GameStatus.HARVEST_FIELD_PHASE, round);

        GamePhase retPhase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);

        GameStatus expStatus = GameStatus.HARVEST_FEED_FAMILY;

        Assert.assertEquals(round, retPhase.getRound());
        Assert.assertEquals(expStatus, retPhase.getGameStatus());
    }

    @Test
    public void testHarvestFeedFamilyNoNextPlayer() {
        int round = 7;
        GamePhase phase = new GamePhase(GameStatus.HARVEST_FEED_FAMILY, round);

        GamePhase retPhase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);

        GameStatus expStatus = GameStatus.HARVEST_NEW_ANIMALS;

        Assert.assertEquals(round, retPhase.getRound());
        Assert.assertEquals(expStatus, retPhase.getGameStatus());
    }

    @Test
    public void testHarvestNewAnimalsNoNextPlayer() {
        int round = 7;
        GamePhase phase = new GamePhase(GameStatus.HARVEST_NEW_ANIMALS, round);

        GamePhase retPhase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);

        GameStatus expStatus = GameStatus.WORK_RETURN;

        Assert.assertEquals(round, retPhase.getRound());
        Assert.assertEquals(expStatus, retPhase.getGameStatus());
    }

    @Test
    public void testHarvestEndGame() {
        int round = 14;
        GamePhase phase = new GamePhase(GameStatus.HARVEST_NEW_ANIMALS, round);

        GamePhase retPhase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);

        GameStatus expStatus = GameStatus.END;

        Assert.assertEquals(round, retPhase.getRound());
        Assert.assertEquals(expStatus, retPhase.getGameStatus());
    }

    @Test
    public void testFullGameFlow() {
        GamePhase phase = new GamePhase();
        phase = PhaseConfig.executeTrigger(GameStatusTrigger.START, phase);
        //1
        phase = simulateRound(phase, false);

        //2
        phase = simulateRound(phase, false);

        //3
        phase = simulateRound(phase, false);

        //4 with harvest
        phase = simulateRound(phase, true);

        //5
        phase = simulateRound(phase, false);

        //6
        phase = simulateRound(phase, false);

        //7 with harvest
        phase = simulateRound(phase, true);

        //8
        phase = simulateRound(phase, false);

        //9 with harvest
        phase = simulateRound(phase, true);

        //10
        phase = simulateRound(phase, false);

        //11 with harvest
        phase = simulateRound(phase, true);

        //12
        phase = simulateRound(phase, false);

        //13 with harvest
        phase = simulateRound(phase, true);

        //14 with harvest
        phase = PhaseConfig.executeTrigger(GameStatusTrigger.ALL_PREPARED, phase);
        phase = PhaseConfig.executeTrigger(GameStatusTrigger.NEXT_PLAYER, phase);
        phase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);
        phase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);
        phase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);
        phase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);

        Assert.assertEquals(GamePhase.ROUND_MAX, phase.getRound());
        Assert.assertEquals(GameStatus.END, phase.getGameStatus());
        System.out.println(phase.getGameStatus() + " " + phase.getRound());

    }

    private GamePhase simulateRound(GamePhase phase, boolean harvest) {
        phase = PhaseConfig.executeTrigger(GameStatusTrigger.ALL_PREPARED, phase);
        phase = PhaseConfig.executeTrigger(GameStatusTrigger.NEXT_PLAYER, phase);
        phase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);
        phase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);

        if (harvest) {
            phase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);
            phase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);
            phase = PhaseConfig.executeTrigger(GameStatusTrigger.NO_NEXT_PLAYER, phase);
        }
        return phase;
    }

}
