package ru.shumbasov.deal.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CreditStatus {
    @JsonProperty("calculated")
    CALCULATED,
    @JsonProperty("issued")
    ISSUED;
}
