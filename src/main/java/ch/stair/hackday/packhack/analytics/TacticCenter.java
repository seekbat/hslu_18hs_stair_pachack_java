package ch.stair.hackday.packhack.analytics;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.player.AStar.Map;
import ch.stair.hackday.packhack.player.Coordinate;
import ch.stair.hackday.packhack.player.PlayerState;

import static ch.stair.hackday.packhack.analytics.Tactic.COLLECTOR_MODE;
import static ch.stair.hackday.packhack.analytics.Tactic.COUNTER_MEASURES;
import static ch.stair.hackday.packhack.analytics.Tactic.ESCAPE_MODE;


public class TacticCenter {
    int bufferPoints = 4;
    int enemyDistanceRadar = 4;
    private int enemyDistance = 0;

    public Tactic getTactic() {
        System.out.println();
        System.out.println("================= TACTIC =================");
        Tactic newStrategy = getNewStrategy();
        System.out.println("MODE            : " + newStrategy);
        System.out.println("ENEMY-EATABLE   : " + isEatable());
        System.out.println("DEFENDER-MODE   : " + inDefenderMode());
        System.out.println("ENEMY-RADAR     : " + isEnemyNearMe());
        System.out.println("ENEMY-DISTANCE  : " + enemyDistance);
        System.out.println();
        return newStrategy;
    }

    public Direction getNextStep() {
        switch (getTactic()) {
            case ESCAPE_MODE:
                return getDirectionForEscapeMode();
            case COLLECTOR_MODE:
                return getDirectionForCollectorMode();
            case COUNTER_MEASURES:
                return getDirectionForCounterMeasures();
            default:
                return Direction.STOP;
        }
    }
    private Direction getDirectionForEscapeMode() {
        return Direction.STOP;
    }

    private Direction getDirectionForCounterMeasures() {
       Map map = new Map(AnalyticsUtils.game);
       return map.getFirstDirection(AnalyticsUtils.myself.getPosX(), AnalyticsUtils.myself.getPosY(),
               AnalyticsUtils.enemy.getPosX(), AnalyticsUtils.enemy.getPosY());
    }

    private Direction getDirectionForCollectorMode() {
        Map map = new Map(AnalyticsUtils.game);
        Coordinate c  = AnalyticsUtils.myself.getNextFood(AnalyticsUtils.game);
        return map.getFirstDirection(AnalyticsUtils.myself.getPosX(), AnalyticsUtils.myself.getPosY(),
                c.getX(), c.getY());
    }

    private Tactic getNewStrategy() {
        if(isEnemyNearMe()) {
            if(inDefenderMode()) {
                if(isEatable()) {
                    return COUNTER_MEASURES;
                } else {
                    return COLLECTOR_MODE;
                }
            } else {
                return ESCAPE_MODE;
            }
        } else {
            if(AnalyticsUtils.ENEMY_POINTS > bufferPoints + AnalyticsUtils.MY_POINTS) {
                return COUNTER_MEASURES;
            } else {
                return COLLECTOR_MODE;
            }
        }
    }

    private boolean isEnemyNearMe() {
        Map map = new Map(AnalyticsUtils.game);
        enemyDistance = map.getPathLenght(AnalyticsUtils.myself.getPosX(), AnalyticsUtils.myself.getPosY(),
                AnalyticsUtils.enemy.getPosX(), AnalyticsUtils.enemy.getPosY());
        return enemyDistance <= enemyDistanceRadar;
    }

    private boolean isEatable() {
        return !(AnalyticsUtils.enemy.getState().getCode() == PlayerState.IMMORTAL.getCode());
    }

    private boolean inDefenderMode() {
        return (AnalyticsUtils.myself.getState().getCode() == PlayerState.GOST.getCode() &&
                AnalyticsUtils.enemy.getState().getCode() != PlayerState.IMMORTAL.getCode());
    }

}
