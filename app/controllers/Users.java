package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

/**
 * Created by virginie on 20/01/2015.
 */
public class Users extends Controller {

    /*
    @Security.Authenticated(Secured.class)
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        return ok(views.html.User.index.render(
                User.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }

    @Security.Authenticated(Secured.class)
    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        User user2 = User.find.where().idEq(id).findUnique();
        Form<User> editForm = Form.form(User.class).fill(user2);
        return ok(views.html.User.edit.render(user2, editForm, user));
    }

    @Security.Authenticated(Secured.class)
    public static Result update(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<User> filledForm = Form.form(User.class).bindFromRequest();
        if (filledForm.hasErrors()) {
            User user2 = User.find.where().idEq(id).findUnique();
            return badRequest(views.html.Substance.edit.render(user2, filledForm, user));
        } else {
            User user2 = filledForm.get();
            user2.setId(id);
            user2.update();
            return redirect(controllers.routes.Application.index());
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result delete(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final User user2 = User.find.byId(id);
        if (user2 != null) {
            user2.delete();
        }
        return redirect(controllers.routes.Application.index());
    }
    */
}
