package ch.stair.hackday.packhack.agent;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.SetupConfig;

public class Pacman implements Agent {
    @Override
    public SetupConfig getAgentInformation() {
        //TODO: Implement the Information for your agent here.
        return new SetupConfig("a");
    }

    @Override
    public Direction chooseAction() {
        //TODO: Implement the agentlogik in here.
        float randomBetween0AndSize = (float)Math.random() * (Direction.values().length-1);
        int randomIndex = Math.round(randomBetween0AndSize);
        return Direction.values()[randomIndex];
    }
}
