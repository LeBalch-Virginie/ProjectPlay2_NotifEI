package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Classe_pharmaco extends Model {

    @Id
    private String code;
    private String label;

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    @ManyToMany(mappedBy = "Classe_pharmacos")
    public List<Substance> Substances = new ArrayList<Substance>();

    @ManyToMany(mappedBy = "Classe_pharmacos")
    public List<Effet_indesirable> Effet_indesirables = new ArrayList<Effet_indesirable>();

    @ManyToMany
    @JoinTable(name="hierarchie_classe_pharmaco", joinColumns=@JoinColumn(name="pere_id"), inverseJoinColumns=@JoinColumn(name="fils_id"))
    public List<Classe_pharmaco> peres = new ArrayList<Classe_pharmaco>();

    @ManyToMany
    @JoinTable(name="hierarchie_classe_pharmaco", joinColumns=@JoinColumn(name="fils_id"), inverseJoinColumns=@JoinColumn(name="pere_id"))
    public List<Classe_pharmaco> fils = new ArrayList<Classe_pharmaco>();
}
