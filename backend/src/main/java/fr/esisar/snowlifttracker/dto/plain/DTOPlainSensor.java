package fr.esisar.snowlifttracker.dto.plain;

import jakarta.validation.constraints.NotBlank;

public abstract class DTOPlainSensor {

    public Long id;

    @NotBlank
    public String reference;

    @NotBlank
    public String manufacturer;
}
