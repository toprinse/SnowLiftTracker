package fr.esisar.snowlifttracker.dto.plain;

import jakarta.validation.constraints.NotBlank;

public class DTOPlainAnalogSensorUnit {
    
    public Long id;
    
    @NotBlank
    public String unit;
}
