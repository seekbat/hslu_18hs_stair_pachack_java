package ch.stair.hackday.packhack.analytics;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.player.AStar.Map;
import ch.stair.hackday.packhack.player.Coordinate;
import ch.stair.hackday.packhack.player.PlayerState;

import java.util.ArrayDeque;
import java.util.Deque;

import static ch.stair.hackday.packhack.analytics.Tactic.COLLECTOR_MODE;
import static ch.stair.hackday.packhack.analytics.Tactic.COUNTER_MEASURES;
import static ch.stair.hackday.packhack.analytics.Tactic.ESCAPE_MODE;


public class TacticCenter {
    int bufferPoints = 4;
    int enemyDistanceRadar = 4;
    private int enemyDistance = 0;
    private Coordinate initialPoint = null;
    private int oldLength = 0;
    private boolean reverse = false;
    private boolean back = false;
    private Deque directionHistory = new ArrayDeque<Direction>();

    public Tactic getTactic() {
        System.out.println();
        System.out.println("================= TACTIC =================");
       /* Tactic newStrategy = getNewStrategy();
        System.out.println("MODE            : " + newStrategy);
        System.out.println("ENEMY-EATABLE   : " + isEatable());
        System.out.println("DEFENDER-MODE   : " + inDefenderMode());
        System.out.println("ENEMY-RADAR     : " + isEnemyNearMe());
        System.out.println("ENEMY-DISTANCE  : " + enemyDistance);
        System.out.println();
        return newStrategy;
        */
       return null;
    }

    private boolean isInLoop() {
       return directionHistory.size() - oldLength > 2;
    }

    public Direction getNextStep() {
        if(initialPoint == null) {
            initialPoint = new Coordinate(AnalyticsUtils.myself.getPosX(), AnalyticsUtils.myself.getPosY());
        }
        return getDirectionForCounterMeasures();
        /*switch (getTactic()) {
            case ESCAPE_MODE:
                return getDirectionForEscapeMode();
            case COLLECTOR_MODE:
                return getDirectionForCollectorMode();
            case COUNTER_MEASURES:
                return getDirectionForCounterMeasures();
            default:
                return Direction.STOP;
        }*/
    }
    private Direction getDirectionForEscapeMode() {
        return Direction.STOP;
    }

    private Direction getDirectionForCounterMeasures() {
       Map map = new Map(AnalyticsUtils.game);
        if(isEnemyNearMe() && (AnalyticsUtils.myself.getState() == PlayerState.PACMAN || AnalyticsUtils.enemy.getState() == PlayerState.IMMORTAL)) {
            return inverseMove((Direction) directionHistory.pollLast());
        }

       Direction direction =  map.getFirstDirection(AnalyticsUtils.myself.getPosX(), AnalyticsUtils.myself.getPosY(),
               AnalyticsUtils.enemy.getPosX(), AnalyticsUtils.enemy.getPosY());
        directionHistory.add(direction);
        return direction;
    }

    private Direction inverseMove(Direction direction) {
        if(direction == Direction.NORTH) return Direction.SOUTH;
        if(direction == Direction.SOUTH) return Direction.NORTH;
        if(direction == Direction.EAST) return Direction.WEST;
        if(direction == Direction.WEST) return Direction.EAST;
        return Direction.STOP;
    }

    private Direction getDirectionForCollectorMode() {
        Map map = new Map(AnalyticsUtils.game);
        Coordinate c  = AnalyticsUtils.myself.getNextFood(AnalyticsUtils.game);
        return map.getFirstDirection(AnalyticsUtils.myself.getPosX(), AnalyticsUtils.myself.getPosY(),
                c.getX(), c.getY());
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
