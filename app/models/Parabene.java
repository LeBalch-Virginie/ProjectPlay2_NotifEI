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
public class Parabene extends Model {

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

    @ManyToMany
    public List<Produit_cosmetique> produit_cos = new ArrayList<Produit_cosmetique>();


    public static Model.Finder<Long, Parabene> find =
            new Finder<Long, Parabene>(Long.class, Parabene.class);


}
