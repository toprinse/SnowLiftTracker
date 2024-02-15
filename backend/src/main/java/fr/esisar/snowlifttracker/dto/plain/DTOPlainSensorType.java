package fr.esisar.snowlifttracker.dto.plain;

import jakarta.validation.constraints.NotBlank;

public class DTOPlainSensorType {
    
    public Long id;
    
    @NotBlank
    public String type;
}

