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
                Utilisateur.find.orderBy("login").findList(),
                User.find.byId(request().username())
        ));
    }
}
