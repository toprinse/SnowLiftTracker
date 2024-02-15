package fr.esisar.snowlifttracker.model;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Measure extends PanacheEntity {
    
    /* Hibernate doc :
     * Hibernate recommends the use of the java.time
     *
     * LocalDate is mapped to the DATE JDBC type.
     * // mapped as DATE
     * private LocalDate localDate;
     * 
     * LocalDateTime is mapped to the TIMESTAMP JDBC type.
     * // mapped as TIMESTAMP
     * private LocalDateTime localDateTime;
     * 
     * May be we should switch to LocalDateTime
     */
    @Column(unique = true, nullable = false)
    public LocalDateTime timestamp;
}