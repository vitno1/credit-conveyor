package ru.shumbasov.deal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shumbasov.deal.enums.EmploymentStatus;
import ru.shumbasov.deal.enums.Position;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private EmploymentStatus employmentStatus;

    private String employer;

    private String employerINN;

    private BigDecimal salary;

    private Position position;

    private Integer workExperienceCurrent;

    private Integer workExperienceTotal;
}
