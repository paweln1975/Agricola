package pl.paweln.agricola.engine;

import org.apache.commons.lang3.tuple.Triple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhaseConfig {

    private PhaseConfig() {
    }

    private static List<Integer> harvestRounds = List.of(4, 7, 9, 11, 13, 14);

    // middle - if increase the round number
    private static Map<GamePhase, List<Triple<GameStatusTrigger, Boolean, GameStatus>>> rules =
            new HashMap<>();

    static {
        // start
        rules.put(new GamePhase(GameStatus.NEW, 0),
                Arrays.asList(
                        Triple.of(GameStatusTrigger.START, Boolean.TRUE, GameStatus.WORK_PREPARE)
                )
        );

        for (int round = GamePhase.ROUND_START + 1; round <= GamePhase.ROUND_MAX; round++) {
            // prepare (all prepared)-> work phase
            rules.put(new GamePhase(GameStatus.WORK_PREPARE, round),
                    Arrays.asList(
                            Triple.of(GameStatusTrigger.ALL_PREPARED, Boolean.FALSE, GameStatus.WORK_PHASE)
                    )
            );
            // work phase (next player / no next player)-> work phase / harvest
            rules.put(new GamePhase(GameStatus.WORK_PHASE, round),
                    Arrays.asList(
                            Triple.of(GameStatusTrigger.NEXT_PLAYER, Boolean.FALSE, GameStatus.WORK_PHASE),
                            harvestRounds.contains(round) ?
                                    Triple.of(GameStatusTrigger.NO_NEXT_PLAYER, Boolean.FALSE, GameStatus.WORK_RETURN) :
                                    Triple.of(GameStatusTrigger.NO_NEXT_PLAYER, Boolean.FALSE, GameStatus.HARVEST_FIELD_PHASE)
                    )
            );
            // return (all return)-> prepare
            rules.put(new GamePhase(GameStatus.WORK_RETURN, round),
                    Arrays.asList(
                            Triple.of(GameStatusTrigger.NO_NEXT_PLAYER, Boolean.TRUE, GameStatus.WORK_PHASE)
                    )
            );
        }

        // harvest field phase (field harvested) -> feed family
        for (Integer round : harvestRounds) {
            rules.put(new GamePhase(GameStatus.HARVEST_FIELD_PHASE, round),
                    Arrays.asList(
                            Triple.of(GameStatusTrigger.NO_NEXT_PLAYER, Boolean.FALSE, GameStatus.HARVEST_FEED_FAMILY)
                    )
            );
        }

        // harvest feed family (no next player) -> new animals
        for (Integer round : harvestRounds) {
            rules.put(new GamePhase(GameStatus.HARVEST_FEED_FAMILY, round),
                    Arrays.asList(
                            Triple.of(GameStatusTrigger.NEXT_PLAYER, Boolean.FALSE, GameStatus.HARVEST_FEED_FAMILY),
                            Triple.of(GameStatusTrigger.NO_NEXT_PLAYER, Boolean.FALSE, GameStatus.HARVEST_NEW_ANIMALS)
                    )
            );
        }

        // harvest new animals (no next player) -> prepare / end
        for (Integer round : harvestRounds) {
            if (round < GamePhase.ROUND_MAX) {
                rules.put(new GamePhase(GameStatus.HARVEST_NEW_ANIMALS, round),
                        Arrays.asList(
                                Triple.of(GameStatusTrigger.NEXT_PLAYER, Boolean.FALSE, GameStatus.HARVEST_NEW_ANIMALS),
                                Triple.of(GameStatusTrigger.NO_NEXT_PLAYER, Boolean.FALSE, GameStatus.WORK_RETURN)
                        )
                );
            } else {
                rules.put(new GamePhase(GameStatus.HARVEST_NEW_ANIMALS, round),
                        Arrays.asList(
                                Triple.of(GameStatusTrigger.NEXT_PLAYER, Boolean.FALSE, GameStatus.HARVEST_NEW_ANIMALS),
                                Triple.of(GameStatusTrigger.NO_NEXT_PLAYER, Boolean.FALSE, GameStatus.END)
                        )
                );
            }
        }
    }

    public static GamePhase executeTrigger(GameStatusTrigger gameStatusTrigger, GamePhase currentGamePhase) {
        List<Triple<GameStatusTrigger, Boolean, GameStatus>> list = rules.get(currentGamePhase);

        if (list == null) {
            throw new IllegalStateException("Wrong configuration of games phases. Game Phase not found");
        }

        GameStatus newStatus = GameStatus.NEW;
        boolean found = false;
        boolean changeRound = false;


        for (Triple<GameStatusTrigger, Boolean, GameStatus> item : list) {
            if (item.getLeft() == gameStatusTrigger) {
                newStatus = item.getRight();
                changeRound = item.getMiddle();
                found = true;
                break;
            }
        }


        if (!found) {
            throw new IllegalStateException("Wrong configuration of games phases. New game Status not found");
        }

        return new GamePhase(newStatus,
                changeRound ? currentGamePhase.getRound() + 1 : currentGamePhase.getRound());

    }

}
