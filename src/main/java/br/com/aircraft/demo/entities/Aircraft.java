package br.com.aircraft.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aircraft {
    
    private Long id;
    private String model;
    private String manufacturer;
    private String countryOrigin;
    private Long year;
    private Integer passager;
    private boolean commercial;
    private Airline airline;
}
