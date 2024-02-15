package fr.esisar.snowlifttracker.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Sensor extends PanacheEntity {
    
    @Column(unique = true, nullable = false)
    public String reference;

    public String manufacturer;

    @ManyToOne
	@JoinColumn(nullable =  true, unique = false)
	public SensorType sensorType;

    @ManyToOne
	@JoinColumn(nullable =  true, unique = false)
	public SkiLift skiLift;
}