package ch.stair.hackday.packhack.agent;

import ch.stair.hackday.packhack.dto.Direction;

public interface Agent {
    public Direction chooseAction();
    public String getAgentInformation();
}