package fr.esisar.snowlifttracker.dto.plain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DTOPlainSkiLift {
    
    public Long id;
    
    @NotBlank
    public String name;

    @NotBlank
    public String longitude;

    @NotBlank
    public String latitude;

    @NotNull
    public Boolean open;
}

    
    