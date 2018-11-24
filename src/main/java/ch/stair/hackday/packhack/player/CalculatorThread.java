package ch.stair.hackday.packhack.player;

import ch.stair.hackday.packhack.dto.FieldTypes;
import ch.stair.hackday.packhack.player.AStar.Map;

import java.util.concurrent.Callable;

public class CalculatorThread implements Callable {
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
        this.goalY = goalY;
        this.game = game;
    }

    @Override
    public Object call() throws Exception {
        Map map = new Map(game);
        steps = map.getPathLenght(startX,startY,goalX,goalY);
        Coordinate step = new Coordinate(goalX,goalY);
        step.setSteps(steps);
        return step;
    }

    public int getSteps(){
        return steps;
    }
}
