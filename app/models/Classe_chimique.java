package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Classe_chimique extends Model {


    @Id
    private Long id;
    private String code;
    private String label;


    public Long getId() {
        return id;
    }

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    @ManyToMany(mappedBy = "Classe_chimiques")
    public List<Substance> Substances = new ArrayList<Substance>();

    @ManyToMany(mappedBy = "Classe_chimiques")
    public List<Effet_indesirable> Effet_indesirables = new ArrayList<Effet_indesirable>();

}
