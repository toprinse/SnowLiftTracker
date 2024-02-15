package fr.esisar.snowlifttracker.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class NumSensor extends Sensor {

    @OneToMany(mappedBy="numSensor")
	public List<NumMeasure> numMeasureList;

    @ManyToOne
	@JoinColumn(nullable =  true, unique = false)
	public NumSensorState numSensorState;
}