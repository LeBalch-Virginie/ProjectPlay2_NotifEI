package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by virginie on 20/01/2015.
 */
@Entity
public class Declaration extends Model {

    @Id
    private Long id;
    private Date date;
    @ManyToOne
    private User user;
    private String effet_ind;
    private String produit;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }

    public static Model.Finder<Long, Declaration> find = new Model.Finder<Long, Declaration>(Long.class, Declaration.class);
}
