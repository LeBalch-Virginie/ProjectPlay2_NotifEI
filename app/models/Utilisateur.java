package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Utilisateur extends Model {



    @Id
    private Long id;
    private String login;
    private String mdp;
    private String role;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static Finder<Long, Utilisateur> find =
            new Finder<Long, Utilisateur>(Long.class, Utilisateur.class);

}
