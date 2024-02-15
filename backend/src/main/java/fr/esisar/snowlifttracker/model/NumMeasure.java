package fr.esisar.snowlifttracker.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class NumMeasure extends Measure {
    
    @Basic
    @Column(nullable = false)
    public Boolean numData;

    @ManyToOne
	@JoinColumn(nullable =  true, unique = false)
	public NumSensor numSensor;
}