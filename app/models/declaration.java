package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by virginie on 20/01/2015.
 */
@Entity
public class Declaration extends Model {

    @Id
    private Long id;
    private Date date;
    private String user;

    public String getEffet_ind() {
        return effet_ind;
    }

    public void setEffet_ind(String effet_ind) {
        this.effet_ind = effet_ind;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    private String effet_ind;
    private String produit;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private String region;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }

    public static Model.Finder<Long, Declaration> find =
            new Model.Finder<Long, Declaration>(Long.class, Declaration.class);

}
