package ch.stair.hackday.packhack.player;

import ch.stair.hackday.packhack.dto.FieldTypes;
import ch.stair.hackday.packhack.player.AStar.Map;

public class CalculatorThread implements Runnable {
    private int startX;
    private int startY;
    private int goalX;
    private int goalY;
    private int steps;
    private FieldTypes[][] game;

    public CalculatorThread(int startX, int startY, int goalX, int goalY, FieldTypes[][] game){
        this.startX = startX;
        this.startY = startY;
        this.goalX = goalX;
        this.startY = goalY;
        this.game = game;
    }

    @Override
    public void run() {
        Map map = new Map(game);
        steps = map.getPathLenght(startX,startY,goalX,goalY);
    }

    public int getSteps(){
        return steps;
    }
}
