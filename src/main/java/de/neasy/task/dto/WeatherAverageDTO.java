package de.neasy.task.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class WeatherAverageDTO {
    private LocalDate date;
    private BigDecimal daily;
    private BigDecimal nightly;
    private BigDecimal pressure;


}
