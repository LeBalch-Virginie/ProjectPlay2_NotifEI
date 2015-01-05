package controllers;

import models.*;
import play.*;
import play.data.Form;
import play.mvc.*;


import views.html.*;

@Security.Authenticated(Secured.class)
public class Application extends Controller {

    public static Result index() {
        return ok(index.render(
                "Vos champs sont pret a etre rempli",

                Effet_indesirable.find.orderBy("id").findList(),
                Substance.find.orderBy("id").findList(),
                Medicament.find.order("id").findList(),
                Utilisateur.find.orderBy("login").findList(),
                User.find.byId(request().username())
        ));
    }

}
