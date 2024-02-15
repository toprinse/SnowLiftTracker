package fr.esisar.snowlifttracker.model;


import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import io.quarkus.panache.common.Parameters;

@NamedQueries({
    @NamedQuery(name = "SkiLift.findByName", query = "SELECT skiLift FROM SkiLift skiLift WHERE skiLift.name = :name"),
    @NamedQuery(name = "SkiLift.listAllOpen", query = "SELECT skiLift FROM SkiLift skiLift WHERE skiLift.open = :open")
})
@Entity
public class SkiLift extends PanacheEntity {
    
    @Column(unique = false, nullable = false)
    public String name;

    public String longitude;

    public String latitude;

    @Basic // in case DataBase doesn't have a boolean type will map to BIT, TINYINT or SMALLINT 
    @Column(nullable = false)
    public boolean open; 

    @OneToMany(mappedBy="skiLift")
	public List<Sensor> sensorList;

    @ManyToOne
	@JoinColumn(nullable =  true, unique = false)
	public Station station;

    public static SkiLift findByName(String name){
        PanacheQuery<SkiLift> panacheQuery = find("#SkiLift.findByName", Parameters.with("name", name));
        return panacheQuery.singleResult();
    } 

    public static List<SkiLift> listAllOpen(){
        PanacheQuery<SkiLift> panacheQuery = find("#SkiLift.listAllOpen", Parameters.with("open", true));
        return panacheQuery.list();
    }
}