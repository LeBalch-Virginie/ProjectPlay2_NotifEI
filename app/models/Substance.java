package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Substance extends Model {

    @Id
    private Long id;
    private String label;

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @ManyToMany
    public List<Medicament> medicaments = new ArrayList<Medicament>();

    @ManyToMany
    public List<Classe_pharmaco> Classe_pharmacos = new ArrayList<Classe_pharmaco>();

    @ManyToMany
    public List<Classe_chimique> Classe_chimiques = new ArrayList<Classe_chimique>();

    public static Finder<Long, Substance> find =
            new Finder<Long, Substance>(Long.class, Substance.class);

}
