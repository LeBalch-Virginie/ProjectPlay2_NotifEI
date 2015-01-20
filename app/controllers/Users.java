package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

import java.util.HashMap;

/**
 * Created by virginie on 20/01/2015.
 */
public class Users extends Controller {
    @Security.Authenticated(Secured.class)
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        return ok(views.html.User.index.render(
                User.find.byId(request().username()),
                User.find.orderBy("email").findList()
        ));
    }

    @Security.Authenticated(Secured.class)
    public static Result edit(String email) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }

        User user2 = User.find.where().idEq(email).findUnique();
        Form<User> editForm = Form.form(User.class).fill(user2);
        return ok(views.html.User.edit.render(user, editForm, user2));
    }

    @Security.Authenticated(Secured.class)
    public static Result update(String email) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        User oldUser = User.find.byId(email);
        Form<User> filledForm = Form.form(User.class).fill(oldUser).bindFromRequest();
        oldUser.setName(filledForm.data().get("name"));
        oldUser.setTypeUser(filledForm.data().get("typeUser"));
        oldUser.setRegion(filledForm.data().get("region"));
        oldUser.update();
        return redirect(controllers.routes.Users.index());
    }

    @Security.Authenticated(Secured.class)
    public static Result delete(String email) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final User user2 = User.find.byId(email);
        if (user2 != null) {
            user2.delete();
        }
        return redirect(controllers.routes.Users.index());
    }
}
