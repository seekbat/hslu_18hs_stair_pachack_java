package ch.stair.hackday.packhack.player;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;
import ch.stair.hackday.packhack.player.AStar.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Player {
    private int posX;
    private int posY;
    private PlayerState state;
    private PlayerColor color;

    public Player(int x, int y, PlayerState state, PlayerColor color) {
        this.posX = x;
        this.posY = y;
        this.state = state;
        this.color = color;
    }

    public PlayerColor getColor() {
        return color;
    }

    public void setColor(PlayerColor color) {
        this.color = color;
    }


    public List<Direction> getPossibilites(FieldTypes[][] game) {
        List<Direction> possibilites = new LinkedList<>();
        if (this.posY - 1 >= 0) { //check north
            if (game[this.posX][this.posY - 1] == FieldTypes.WALL) {
                possibilites.add(Direction.NORTH);
            }
        }
        if (this.posX + 1 <= game.length) { //check east
            if (game[this.posX + 1][this.posY] == FieldTypes.WALL) {
                possibilites.add(Direction.EAST);
            }
        }
        if (this.posY + 1 <= game[0].length) { //check south
            if (game[this.posX][this.posY + 1] == FieldTypes.WALL) {
                possibilites.add(Direction.SOUTH);
            }
        }
        if (this.posX - 1 >= 0) { //check west
            if (game[this.posX - 1][this.posY] == FieldTypes.WALL) {
                possibilites.add(Direction.WEST);
            }
        }
        return possibilites;
    }

    public void setState(PlayerColor color, FieldTypes[][] game) {
        if (this.posX <= game.length / 2) {
            if (color == PlayerColor.RED) {
                this.state = PlayerState.GOST;
            } else if (color == PlayerColor.BLUE) {
                this.state = PlayerState.PACMAN;
            }
        } else {
            if (color == PlayerColor.RED) {
                this.state = PlayerState.PACMAN;
            } else if (color == PlayerColor.BLUE) {
                this.state = PlayerState.GOST;
            }
        }
    }

    public Coordinate getNextFood(FieldTypes[][] game) {
        System.out.println("START");
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        int width = game.length / 2;
        int height = game[0].length;
        int offsetX = 0;
        FieldTypes[][] foodArea = new FieldTypes[width][height];

        if (color == PlayerColor.BLUE) {
            offsetX = game.length / 2;
        }
        System.out.println("WEEEE");
        for (int x = offsetX; x < ((game.length / 2) + offsetX); x++) {
            for (int y = 0; y < game[0].length; y++) {
                foodArea[x - offsetX][y] = game[x][y];
            }
        }
        List<Coordinate> foodNodes = getFoodNodes(foodArea);
        List<Future> futureList = new ArrayList<>();
        for (Coordinate foodNode : foodNodes) {
            System.out.println("HEREEEEEEEEE");
            futureList.add(executor.submit(new CalculatorThread(this.getPosX(), this.getPosY(), foodNode.getX(), foodNode.getY(), game)));

        }

        Coordinate minSteps = null;
        int counter = 0;
        while (futureList.size() <= counter) {
            for (Future node : futureList) {
                if (node.isDone()) {
                    Coordinate steps = null;
                    try {
                        steps = (Coordinate) node.get();
                    } catch (Exception e) {
                        System.out.print("Lukas is Tschuld!");
                    }
                    if (steps != null) {
                        if (steps.getSteps() < minSteps.getSteps()) {
                            minSteps = steps;
                        }
                    }else {
                        minSteps = steps;
                    }
                    System.out.println(counter);
                    counter++;
                }
            }
        }
        return minSteps;
    }


    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public PlayerState getState() {
        return this.state;
    }

    private List<Coordinate> getFoodNodes(FieldTypes[][] foodArea) {
        List<Coordinate> foodNodes = new LinkedList<>();
        for(int x = foodArea.length; x < foodArea.length;x++){
            for(int y=0; y<foodArea[0].length; y++){
                if(foodArea[x][y] == FieldTypes.FOOD){
                    foodNodes.add(new Coordinate(x,y));
                }
            }
        }
        return foodNodes;
    }
}
