package ch.stair.hackday.packhack.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "publicPlayers",
        "gameField"
})
public class PublicPlayer
{
    private float[] position;
    private Direction direction;
    private boolean isPacman;
    private boolean weakened;


    @JsonCreator
    public PublicPlayer(@JsonProperty("position")float[] position, @JsonProperty("direction") Direction direction, @JsonProperty("isPacman") boolean isPacman, @JsonProperty("weakened") boolean weakened){
        this.position = position;
        this.direction = direction;
        this.isPacman = isPacman;
        this.weakened = weakened;
    }

    public float[] getPosition ()
    {
        return position;
    }

    public void setPosition (float[] position)
    {
        this.position = position;
    }

    public Direction getDirection ()
    {
        return direction;
    }

    public void setDirection (Direction direction)
    {
        this.direction = direction;
    }

    public boolean getIsPacman ()
    {
        return isPacman;
    }

    public void setIsPacman (boolean isPacman)
    {
        this.isPacman = isPacman;
    }

    public boolean isWeakened() {
        return weakened;
    }

    public void setWeakened(boolean weakened) {
        this.weakened = weakened;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [position = "+position+", direction = "+direction+", isPacman = "+isPacman+"]";
    }
}