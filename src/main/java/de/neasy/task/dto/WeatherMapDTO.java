package de.neasy.task.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class WeatherMapDTO {
    private String cod;
    private BigDecimal message;
    private Integer cnt;
    private List<WeatherMapTimeDTO> list;

}
