package ch.stair.hackday.packhack.analytics;

public class TacticCenter {
    int bufferPoints = 4;
    int enemyDistanceRadar = 4;

    public Tactic getTactic() {
        return getNewStrategy();
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
        return false;
    }

    private boolean inDefenderMode() {
        return false;
    }

}
