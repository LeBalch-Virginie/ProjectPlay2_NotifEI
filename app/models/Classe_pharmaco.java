package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Classe_pharmaco extends Model {



    @Id
    private Long id;
    private String label;
    private int pere;
    private int fils;

    public Long getId() {
        return id;
    }

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

    @ManyToMany(mappedBy = "Classe_pharmacos")
    public List<Substance> Substances = new ArrayList<Substance>();

    @ManyToMany(mappedBy = "Classe_pharmacos")
    public List<Effet_indesirable> Effet_indesirables = new ArrayList<Effet_indesirable>();

}
