package ru.shumbasov.deal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shumbasov.deal.enums.ApplicationStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationStatusHistoryDTO {

    private ApplicationStatus status;

    private LocalDate time;

    private ApplicationStatus changeType;

}
