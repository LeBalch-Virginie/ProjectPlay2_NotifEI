package models;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by virginie on 06/01/2015.
 */
@Entity
public class Produit_cosmetique extends Model {

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

    @ManyToMany(mappedBy = "produit_cos", cascade= CascadeType.ALL)
    public List<Principe_actif> principe_ac = new ArrayList<Principe_actif>();

    @ManyToMany(mappedBy = "produit_cos", cascade= CascadeType.ALL)
    public List<Parabene> parabenes = new ArrayList<Parabene>();

    @ManyToMany(mappedBy = "produit_cos", cascade= CascadeType.ALL)
    public List<Excipient> excipients = new ArrayList<Excipient>();

    @ManyToMany(mappedBy = "produit_cos", cascade= CascadeType.ALL)
    public List<Conservateur> conservateurs = new ArrayList<Conservateur>();

    @ManyToMany(mappedBy = "produits_cosmetiques", cascade= CascadeType.ALL)
    public List<Effet_indesirable> Effet_indesirables = new ArrayList<Effet_indesirable>();

    public static Model.Finder<Long, Produit_cosmetique> find =
            new Finder<Long, Produit_cosmetique>(Long.class, Produit_cosmetique.class);


}
