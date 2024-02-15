package fr.esisar.snowlifttracker.dto.plain;

import jakarta.validation.constraints.NotBlank;

public class DTOPlainStation {

    public Long id;
    
    @NotBlank
    public String name;

    @NotBlank
    public String longitude;

    @NotBlank
    public String latitude;
}
