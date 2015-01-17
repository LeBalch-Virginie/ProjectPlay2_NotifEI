package models;

import play.db.ebean.Model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Effet_indesirable extends Model {

    @Id
    private Long id;

    private String label;

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public static Model.Finder<Long, Effet_indesirable> find =
            new Finder<Long, Effet_indesirable>(Long.class, Effet_indesirable.class);

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @ManyToMany
    public List<Classe_pharmaco> Classe_pharmacos = new ArrayList<Classe_pharmaco>();

    @ManyToMany
    public List<Classe_chimique> Classe_chimiques = new ArrayList<Classe_chimique>();

    @ManyToMany
    public List<Dispo_medical> Dispo_medicaux = new ArrayList<Dispo_medical>();

    @ManyToMany
    @JoinTable(name="hierarchie_effet_indesirable", joinColumns=@JoinColumn(name="pere_id"), inverseJoinColumns=@JoinColumn(name="fils_id"))
    public List<Effet_indesirable> peres = new ArrayList<Effet_indesirable>();

    @ManyToMany
    @JoinTable(name="hierarchie_effet_indesirable", joinColumns=@JoinColumn(name="fils_id"), inverseJoinColumns=@JoinColumn(name="pere_id"))
    public List<Effet_indesirable> fils = new ArrayList<Effet_indesirable>();
}
