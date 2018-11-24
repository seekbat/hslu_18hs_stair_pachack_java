package ch.stair.hackday.packhack.player;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;
import ch.stair.hackday.packhack.player.AStar.Node;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private int posX;
    private int posY;
    private PlayerState state;
    private PlayerColor color;

    public Player(int x, int y) {
        this.posX = x;
        this.posY = y;
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
            if (color == PlayerColor.RED){
                this.state = PlayerState.GOST;
            }else if(color == PlayerColor.BLUE){
                this.state = PlayerState.PACMAN;
            }
        }else {
            if (color == PlayerColor.RED){
                this.state = PlayerState.PACMAN;
            }else if(color == PlayerColor.BLUE){
                this.state = PlayerState.GOST;
            }
        }
    }

    public Node getNextFood(FieldTypes[][] game){
        List<Thread> threadPool = new LinkedList<>();
        int width = game.length/2;
        int height = game[0].length;
        int offsetX = 0;
        FieldTypes[][] foodArea = new FieldTypes[width][height];

        if(color == PlayerColor.BLUE){
            offsetX = game.length/2;
        }

        for(int x = offsetX; x<((game.length/2)+offsetX);x++){
            for(int y=0; y<game[0].length; y++){
                foodArea[x-offsetX][y] = game[x][y];
            }
        }
        List<Coordinate> foodNodes = getFoodNodes(foodArea);
        for (Coordinate foodNode : foodNodes) {
            Thread thread= new Thread(new CalculatorThread(this.posX,this.posY, foodNode.getX(), foodNode.getY(), game));
            threadPool.add(thread);
            thread.start();
        }

        while(!threadPool.isEmpty()){
            for (Thread thread :  threadPool) {
                if (th)
            }
        }

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

    private List<Coordinate> getFoodNodes(FieldTypes[][] foodArea){
        List<Coordinate> foodNodes = new LinkedList<>();
        for(int x = foodArea.length; i < foodArea.length;x++){
            for(int y=0; i<foodArea[0].length; y++){
                if(foodArea[x][y] == FieldTypes.FOOD){
                    foodNodes.add(new Coordinate(x,y));
                }
            }
        }
        return foodNodes;
    }
}
