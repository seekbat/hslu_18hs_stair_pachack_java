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
    private boolean activeCapsule;


    @JsonCreator
    public PublicPlayer(@JsonProperty("position")float[] position, @JsonProperty("direction") Direction direction, @JsonProperty("isPacman") boolean isPacman, @JsonProperty("activeCapsule") boolean activeCapsule){
        this.position = position;
        this.direction = direction;
        this.isPacman = isPacman;
        this.activeCapsule = activeCapsule;
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

    public boolean isActiveCapsule() {
        return activeCapsule;
    }

    public void setActiveCapsule(boolean activeCapsule) {
        this.activeCapsule = activeCapsule;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [position = "+position+", direction = "+direction+", isPacman = "+isPacman+"]";
    }
}