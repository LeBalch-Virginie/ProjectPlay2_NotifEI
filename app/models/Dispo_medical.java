package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by virginie on 06/01/2015.
 */
@Entity
public class Dispo_medical extends Model {

    @Id
    private Long id;

    private String nom;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(Long id) { this.id = id; }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public static Model.Finder<Long, Dispo_medical> find =
            new Model.Finder<Long, Dispo_medical>(Long.class, Dispo_medical.class);


    @ManyToMany(mappedBy = "Dispo_medicaux")
    public List<Effet_indesirable> Effet_indesirables = new ArrayList<Effet_indesirable>();
}
