package ch.stair.hackday.packhack.analytics;

import ch.stair.hackday.packhack.player.PlayerState;

public class TacticCenter {
    int bufferPoints = 4;
    int enemyDistanceRadar = 4;

    public Tactic getTactic() {
        System.out.println();

        System.out.println("================= TACTIC =================");

        Tactic newStrategy = getNewStrategy();
        System.out.println("MODE            : " + newStrategy);
        System.out.println("ENEMY-EATABLE   : " + isEatable());
        System.out.println("DEFENDER-MODE   : " + inDefenderMode());
        System.out.println("ENEMY-RADAR     : " + isEnemyNearMe());
        System.out.println();
        return newStrategy;
    }

    private Tactic getNewStrategy() {
        if(isEnemyNearMe()) {
            if(inDefenderMode()) {
                if(isEatable()) {
                    return Tactic.COUNTER_MEASURES;
                } else {
                    return Tactic.COLLECTOR_MODE;
                }
            } else {
                return Tactic.ESCAPE_MODE;
            }
        } else {
            if(AnalyticsUtils.ENEMY_POINTS > bufferPoints + AnalyticsUtils.MY_POINTS) {
                return Tactic.COUNTER_MEASURES;
            } else {
                return Tactic.COLLECTOR_MODE;
            }
        }
    }

    private boolean isEnemyNearMe() {
        return false;
    }

    private boolean isEatable() {
        return !(AnalyticsUtils.enemy.getState().getCode() == PlayerState.IMMORTAL.getCode());
    }

    private boolean inDefenderMode() {
        return (AnalyticsUtils.myself.getState().getCode() == PlayerState.GOST.getCode() &&
                AnalyticsUtils.enemy.getState().getCode() != PlayerState.IMMORTAL.getCode());
    }

}
