package ru.shumbasov.conveyor.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public enum Gender {
            @JsonProperty("isMale")
    IS_MALE,
            @JsonProperty("isFeMale")
    IS_FEMALE;
}
