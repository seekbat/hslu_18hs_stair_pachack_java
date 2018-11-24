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
import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;


import java.util.LinkedList;
import java.util.List;

public class AnalyticsUtils {
    private List<Direction> getPossibilites(int x, int y){
        List<Direction> possibilites = new LinkedList<>();
        if(y-1>=0){ //check north
            if(game[x][y-1]== FieldTypes.WALL){
                possibilites.add(Direction.NORTH);
            }
        }
        if(x+1<=game.length){ //check east
            if(game[x+1][y]){
                possibilites.add(Direction.EAST);
            }
        }
        if(y+1<=game[0].length){
            if(game[x][y+1] == FieldTypes.WALL){
                possibilites.add(Direction.SOUTH);
            }
        }
        if(x-1>=0){
            if(game[x-1][y] == FieldTypes.WALL){
                possibilites.add(Direction.WEST);
            }
        }
        return possibilites;
    }
}
