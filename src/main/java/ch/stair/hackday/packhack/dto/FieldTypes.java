package ch.stair.hackday.packhack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FieldTypes {
    @JsonProperty(" ")EMPTY,
    @JsonProperty("%")WALL,
    @JsonProperty("°")FOOD,
    @JsonProperty("o")CAPSULE
}
