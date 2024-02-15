package fr.esisar.snowlifttracker.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class UserSnow extends PanacheEntity {
    // WARNING: No association implemented yet !!

    @Column(unique = true, nullable = false)
    public String firstname;

    public String lastname;

    public String email;

    public String login;

    public String password;

    public String permission;
}