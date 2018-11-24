package ch.stair.hackday.packhack.player;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;

import java.util.LinkedList;
import java.util.List;

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
    public List<Direction>findPath(int goalX, int goalY, FieldTypes[][] map){
        if(this.posX == goalX && this.posY == goalY){
            return new LinkedList<>();
        }
        return null;
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
}
