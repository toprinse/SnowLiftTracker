package fr.esisar.snowlifttracker.dto.plain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


public abstract class DTOPlainMeasure {

    public Long id;
    
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime timestamp;
}
