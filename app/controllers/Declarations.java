package controllers;

import models.Declaration;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

/**
 * Created by virginie on 20/01/2015.
 */
public class Declarations extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        return ok(views.html.Declaration.index.render(
                Declaration.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }
}
