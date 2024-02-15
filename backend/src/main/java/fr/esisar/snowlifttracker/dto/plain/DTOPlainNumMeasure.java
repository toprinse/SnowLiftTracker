package fr.esisar.snowlifttracker.dto.plain;

import jakarta.validation.constraints.NotNull;
public class DTOPlainNumMeasure extends DTOPlainMeasure{
    
    public Long id;

    @NotNull
    public Boolean numData;
}
