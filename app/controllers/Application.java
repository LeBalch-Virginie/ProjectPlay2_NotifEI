package controllers;

import models.*;

import play.mvc.*;


import views.html.*;

@Security.Authenticated(Secured.class)
public class Application extends Controller {
    public static Result index() {
        User user = null;
        if (request().username() != null) {
            user = User.find.byId(request().username());
        }
        return ok(index.render(user));
    }

    public static Result administration() {
        return ok(administration.render(
                User.find.byId(request().username())
        ));
    }

    public static Result notifierEi() {
        return ok(notifierEi.render(
                User.find.byId(request().username())
        ));
    }
}