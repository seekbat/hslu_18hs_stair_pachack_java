package ch.stair.hackday.packhack.agent;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.SetupConfig;

public interface Agent {
    public SetupConfig getAgentInformation();
    public Direction chooseAction();
}
