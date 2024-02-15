package fr.esisar.snowlifttracker.model;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class SensorType extends PanacheEntity {
    
    @Column(unique = true, nullable = false)
    public String type;

    @OneToMany(mappedBy="sensorType")
	public List<Sensor> sensorList;
}