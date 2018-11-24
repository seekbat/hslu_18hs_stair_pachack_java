package ch.stair.hackday.packhack.analytics;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;
import ch.stair.hackday.packhack.player.Player;


public class AnalyticsUtils  {
    protected static int ENEMY_POINTS = 0;
    protected static int MY_POINTS = 0;
    private boolean isHunter = false;
    protected static FieldTypes[][] game;
    protected static FieldTypes[][] lastGameStat;
    protected static Player myself;
    protected static Player enemy;

    public AnalyticsUtils(FieldTypes[][] initialGameField) {
        game = initialGameField;
    }

    public void nextStepInformation(FieldTypes[][] actualGameField, Player enemy, Player myself) {
        AnalyticsUtils.myself = myself;
        AnalyticsUtils.enemy = enemy;
        lastGameStat = actualGameField;
        countEnemyPoints();
        countMyPoints();
        logActualStatus();
    }

    private void logActualStatus() {
        System.out.println("================= ENEMY ==================");
        System.out.println("Position X      : " + enemy.getPosX());
        System.out.println("Position Y      : " + enemy.getPosY());
        System.out.println("State           : " + enemy.getState());
        System.out.println("Points          : " + ENEMY_POINTS);
        System.out.println("Colour          : " + enemy.getColor());
        System.out.println();
        System.out.println("================= MYSELF =================");
        System.out.println("Position X      : " + myself.getPosX());
        System.out.println("Position Y      : " + myself.getPosY());
        System.out.println("State           : " + myself.getState());
        System.out.println("Points          : " + MY_POINTS);
        System.out.println("Colour          : " + myself.getColor());

    }

    public Direction getNextStep() {
        return Direction.STOP;
    }


    private void countEnemyPoints() {
        if (lastGameStat[enemy.getPosY()][enemy.getPosX()] == FieldTypes.FOOD) {
            ENEMY_POINTS++;
        }
    }

    private void countMyPoints() {
        if (lastGameStat[myself.getPosY()][myself.getPosX()] == FieldTypes.FOOD) {
            MY_POINTS++;
        }
        lastGameStat = game;
    }
}
