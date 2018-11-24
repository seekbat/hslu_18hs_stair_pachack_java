package ch.stair.hackday.packhack.player;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private int posX;
    private int posY;

    public Player(int x, int y){
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


    public int getPosX() {
        return this.posX;
    }
    public int getPosY() {
        return this.posY;
    }
}
