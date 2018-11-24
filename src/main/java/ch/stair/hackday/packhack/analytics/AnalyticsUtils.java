package ch.stair.hackday.packhack.analytics;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;


import java.util.LinkedList;
import java.util.List;

public class AnalyticsUtils {
    public int getPathDistance(int x1, int y1, int x2, int y2,FieldTypes[][] gamefield){

    }
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
