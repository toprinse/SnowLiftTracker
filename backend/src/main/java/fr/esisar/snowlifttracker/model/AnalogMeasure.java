package fr.esisar.snowlifttracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AnalogMeasure extends Measure {

    @Column(unique = false, nullable = false)
    public Float analogData;

    @ManyToOne
	@JoinColumn(nullable =  true, unique = false)
	public AnalogSensor analogSensor;
}