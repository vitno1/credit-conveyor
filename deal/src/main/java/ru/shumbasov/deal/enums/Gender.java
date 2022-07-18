package ru.shumbasov.deal.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {
    @JsonProperty("isMale")
    IS_MALE,
    @JsonProperty("isFeMale")
    IS_FEMALE;
}
