package fr.esisar.snowlifttracker.dto;

import java.util.List;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainStation;

public class DTOStation extends DTOPlainStation{
    
    public List<DTOPlainSkiLift> skiLiftList;
}
