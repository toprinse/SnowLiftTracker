package fr.esisar.snowlifttracker.model;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class AnalogSensorUnit extends PanacheEntity {
    
    @Column(unique = false, nullable = false)
    public String unit;

    @OneToMany(mappedBy="analogSensorUnit")
	public List<AnalogSensor> analogSensorList;
}