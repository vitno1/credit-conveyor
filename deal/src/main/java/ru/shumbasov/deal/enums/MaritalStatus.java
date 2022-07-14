package ru.shumbasov.deal.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MaritalStatus {
    @JsonProperty("isMarried")
    IS_MARRIED,
    @JsonProperty("isNotMarried")
    IS_NOT_MARRIED;
}
