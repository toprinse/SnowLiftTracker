package fr.esisar.snowlifttracker.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class AnalogSensor extends Sensor {
    
    @OneToMany(mappedBy="analogSensor")
	public List<AnalogMeasure> analogMeasureList;

    @ManyToOne
	@JoinColumn(nullable =  true, unique = false)
	public AnalogSensorUnit analogSensorUnit;
}