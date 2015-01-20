package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.Model;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class User extends Model {

    @Id
    @Constraints.Required
    @Formats.NonEmpty
    public String email;

    //@Constraints.Required
    public String name;

    @Constraints.Required
    public String password;

    //@Constraints.Required
    public Boolean isAdmin = false;

    public String typeUser;

    public String region;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public static Model.Finder<String,User> find =
            new Model.Finder(String.class, User.class);

    public static List<User> findAll() {
        return find.all();
    }

    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }

    //TODO : expliquer ce que fait le code
    public static User authenticate(String email, String password) {
        return find.where()
                .eq("email", email)
                .eq("password", password)
                .findUnique();
    }


    public String toString() {
        return "User(" + email + ")";
    }

}
