package fr.esisar.snowlifttracker.dto.plain;

import jakarta.validation.constraints.NotNull;

public class DTOPlainNumSensorState {

    public Long id;

    @NotNull
    public Boolean high;
    
    @NotNull
    public Boolean low; 
}
