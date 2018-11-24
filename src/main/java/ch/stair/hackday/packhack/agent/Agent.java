package ch.stair.hackday.packhack.agent;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.GameState;

public interface Agent {
    Direction chooseAction();
    String getAgentInformation();
    void prepareNextStep(GameState state);
}