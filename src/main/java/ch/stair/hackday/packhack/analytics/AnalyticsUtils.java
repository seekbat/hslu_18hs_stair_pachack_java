package ch.stair.hackday.packhack.analytics;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;
import ch.stair.hackday.packhack.player.Player;
import org.springframework.util.StringUtils;


public class AnalyticsUtils  {
    protected static int ENEMY_POINTS = 0;
    protected static int MY_POINTS = 0;
    private boolean isHunter = false;
    private FieldTypes[][] game;
    private FieldTypes[][] lastGameStat;
    private Player myself;
    private Player enemy;

    public AnalyticsUtils(FieldTypes[][] initialGameField) {
        this.game = initialGameField;
    }

    public void nextStepInformation(FieldTypes[][] actualGameField, Player enemy, Player myself) {
        this.myself = myself;
        this.enemy = enemy;
        this.lastGameStat = actualGameField;
        countEnemyPoints();
        countMyPoints();
        logActualStatus();
    }

    private void logActualStatus() {
        System.out.println("================== ENEMY ==================");
        System.out.println("Position X  : " + enemy.getPosX());
        System.out.println("Position Y  : " + enemy.getPosY());
        System.out.println("State       : " + enemy.getState());
        System.out.println("Points      : " + ENEMY_POINTS);
        System.out.println("Colour      : " + enemy.getColor());
        System.out.println();
        System.out.println("================= MYSELF =================");
        System.out.println("Position X  : " + myself.getPosX());
        System.out.println("Position Y  : " + myself.getPosY());
        System.out.println("State       : " + myself.getState());
        System.out.println("Points      : " + MY_POINTS);
        System.out.println("Colour      : " + myself.getColor());

    }

    public Direction getNextStep() {
        return Direction.STOP;
    }


    private void countEnemyPoints() {
        if (lastGameStat[enemy.getPosX()][enemy.getPosY()] == FieldTypes.FOOD) {
            ENEMY_POINTS++;
        }
    }

    private void countMyPoints() {
        if (lastGameStat[myself.getPosX()][myself.getPosY()] == FieldTypes.FOOD) {
            MY_POINTS++;
        }
        lastGameStat = game;
    }
}
