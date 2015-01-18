package controllers;

import models.*;
import play.mvc.*;
import views.html.*;


public class Application extends Controller {
    public static Result index() {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }
        return ok(index.render(user));
    }

    @Security.Authenticated(Secured.class)
    public static Result administration() {
        return ok(administration.render(
                User.find.byId(request().username())
        ));
    }

    @Security.Authenticated(Secured.class)
    public static Result notifierEi() {
        return ok(notifierEi.render(
                User.find.byId(request().username())
        ));
    }
}