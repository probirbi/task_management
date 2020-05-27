package de.neasy.task.service;

import de.neasy.task.dto.WeatherAverageDTO;
import de.neasy.task.dto.WeatherMapDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {
    private final String URI="https://api.openweathermap.org/data/2.5/forecast";
    private final String API_ID="36a038d4f47d3f0187ae13f6dfb885e5";

    private final RestTemplate restTemplate;
    public WeatherService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate=restTemplateBuilder.build();
    }

    public ResponseEntity<?> weatherForecastAverage(String city){
        List<WeatherAverageDTO> result=new ArrayList<WeatherAverageDTO>();
        try{
            WeatherMapDTO weatherMapDTO=this.restTemplate.getForObject(this.url(city), WeatherMapDTO.class);

            for(LocalDate reference = LocalDate.now()
            ){}
        }
    }
}
