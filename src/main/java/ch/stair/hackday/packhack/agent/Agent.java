package ch.stair.hackday.packhack.agent;

import ch.stair.hackday.packhack.dto.Direction;

public interface Agent {
    public String getAgentInformation();
    public Direction chooseAction();
}
