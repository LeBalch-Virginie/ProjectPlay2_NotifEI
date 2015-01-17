package models;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Medicament extends Model{



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

    @ManyToMany(mappedBy = "medicaments", cascade= CascadeType.ALL)
    public List<Substance> substances = new ArrayList<Substance>();


    public static Model.Finder<Long, Medicament> find =
            new Model.Finder<Long, Medicament>(Long.class, Medicament.class);


}