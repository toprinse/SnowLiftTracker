package fr.esisar.snowlifttracker.dto.plain;

import jakarta.validation.constraints.NotNull;

public class DTOPlainAnalogMeasure extends DTOPlainMeasure{
   
    public Long id;

    @NotNull
    public Float analogData;
}
