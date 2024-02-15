package fr.esisar.snowlifttracker.model;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class NumSensorState extends PanacheEntity {
    
    @Column(unique = false, nullable = false)
    public Boolean high;

    @Column(unique = false, nullable = false)
    public Boolean low;

    @OneToMany(mappedBy="numSensorState")
	public List<NumSensor> numSensorList;
}