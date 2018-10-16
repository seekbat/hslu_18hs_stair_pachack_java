package ch.stair.hackday.packhack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Direction {
    @JsonProperty("North") NORTH,
    @JsonProperty("South") SOUTH,
    @JsonProperty("West") WEST,
    @JsonProperty("East") EAST,
    @JsonProperty("Stop") STOP
}
