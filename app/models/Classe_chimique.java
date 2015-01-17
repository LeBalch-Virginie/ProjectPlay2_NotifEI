package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Classe_chimique extends Model {

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


    @ManyToMany(mappedBy = "Classe_chimiques", cascade= CascadeType.ALL)
    public List<Substance> Substances = new ArrayList<Substance>();

    @ManyToMany(mappedBy = "Classe_chimiques", cascade= CascadeType.ALL)
    public List<Effet_indesirable> Effet_indesirables = new ArrayList<Effet_indesirable>();

    @ManyToMany
    @JoinTable(name="hierarchie_classe_chimique", joinColumns=@JoinColumn(name="pere_id"), inverseJoinColumns=@JoinColumn(name="fils_id"))
    public List<Classe_chimique> peres = new ArrayList<Classe_chimique>();

    @ManyToMany
    @JoinTable(name="hierarchie_classe_chimique", joinColumns=@JoinColumn(name="fils_id"), inverseJoinColumns=@JoinColumn(name="pere_id"))
    public List<Classe_chimique> fils = new ArrayList<Classe_chimique>();
}
