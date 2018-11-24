package ch.stair.hackday.packhack.analytics;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;
import ch.stair.hackday.packhack.player.Player;


public class AnalyticsUtils  {
    private static int ENEMY_POINTS = 0;
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
    }

    public Direction getNextStep() {
        return Direction.STOP;
    }


    private void countEnemyPoints() {
        if (lastGameStat[enemy.getPosX()][enemy.getPosY()] == FieldTypes.FOOD) {
            ENEMY_POINTS++;
        }
        lastGameStat = game;
    }

    public int getEnemyPoints() {
        return ENEMY_POINTS;
    }
}
