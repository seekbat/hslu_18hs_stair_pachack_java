package ch.stair.hackday.packhack.analytics;

import ch.stair.hackday.packhack.agent.Agent;
import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;

public class AnalyticsUtils implements Agent{
    private static int ENEMY_POINTS = 0;
    private boolean isHunter = false;
    private FieldTypes[][] lastGameFieldState;

    public AnalyticsUtils(FieldTypes[][] initialGameField) {
        this.lastGameFieldState = initialGameField;
    }

    public void prepareNextStep(FieldTypes[][] actualGameField, float [] enemyPosition) {
        countEnemyPoints(actualGameField, enemyPosition);
    }

    public Direction getNextStep() {
        return Direction.STOP;
    }


    private void countEnemyPoints(FieldTypes[][] actualGameField, float [] enemyPosition) {
       if (lastGameFieldState[(int) enemyPosition[0]][(int) enemyPosition[1]] == FieldTypes.FOOD) {
           ENEMY_POINTS++;
       }
       lastGameFieldState = actualGameField;
    }

    public int getEnemyPoints() {
        return ENEMY_POINTS;
    }

    @Override
    public Direction chooseAction() {
        return getNextStep();
    }

    @Override
    public String getAgentInformation() {
        return null;
    }
}
