package ru.shumbasov.deal.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Position {
    @JsonProperty("engineer")
    ENGINEER,
    @JsonProperty("manager")
    MANAGER;
}
