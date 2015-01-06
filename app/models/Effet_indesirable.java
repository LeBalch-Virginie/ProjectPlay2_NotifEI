package models;

import play.db.ebean.Model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Effet_indesirable extends Model {

    @Id
    private long id;
    private String label;
    public int pere;
    public int fils;

    @ManyToMany
    public List<Classe_pharmaco> Classe_pharmacos = new ArrayList<Classe_pharmaco>();

    @ManyToMany
    public List<Classe_chimique> Classe_chimiques = new ArrayList<Classe_chimique>();


    public long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public static Finder<Long, Effet_indesirable> find =
            new Finder<Long, Effet_indesirable>(Long.class, Effet_indesirable.class);

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPere() {
        return pere;
    }

    public void setPere(int pere) {
        this.pere = pere;
    }

    public int getFils() {
        return fils;
    }

    public void setFils(int fils) {
        this.fils = fils;
    }
}
