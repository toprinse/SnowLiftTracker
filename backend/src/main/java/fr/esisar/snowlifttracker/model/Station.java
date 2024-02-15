package fr.esisar.snowlifttracker.model;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import io.quarkus.panache.common.Parameters;

@NamedQueries(
    @NamedQuery(name = "Station.findByName",query = "SELECT station FROM Station station WHERE station.name = :name")
)
@Entity
public class Station extends PanacheEntity {
    
    @Column(unique = true, nullable = false)
    public String name;

    public String longitude;

    public String latitude;

    @OneToMany(mappedBy="station")
	public List<SkiLift> skiLiftList;

    public static Station findByName(String name){
        PanacheQuery<Station> panacheQuery = find("#Station.findByName", Parameters.with("name", name));
        return panacheQuery.singleResult();
    }
}
