package ch.stair.hackday.packhack.analytics;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;



import java.util.LinkedList;
import java.util.List;

public class AnalyticsUtils  {
    private static int ENEMY_POINTS = 0;
    private boolean isHunter = false;
    private FieldTypes[][] game;
    private FieldTypes[][] lastGameStat;
    private int posX;
    private int posY;

    public AnalyticsUtils(FieldTypes[][] initialGameField) {
        this.game = initialGameField;
    }

    public void prepareNextStep(FieldTypes[][] actualGameField, float[] enemyPosition) {
        countEnemyPoints(actualGameField, enemyPosition);
    }

    public Direction getNextStep() {
        return Direction.STOP;
    }


    private void countEnemyPoints(FieldTypes[][] actualGameField, float[] enemyPosition) {
        if (lastGameStat[(int) enemyPosition[0]][(int) enemyPosition[1]] == FieldTypes.FOOD) {
            ENEMY_POINTS++;
        }
        lastGameStat = actualGameField;
    }

    public int getEnemyPoints() {
        return ENEMY_POINTS;
    }
}
