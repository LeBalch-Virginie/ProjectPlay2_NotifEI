package controllers;

import models.Conservateur;
import models.Dispo_medical;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

/**
 * Created by virginie on 06/01/2015.
 */

public class Conservateurs extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        return ok(views.html.Conservateur.index.render(
                Conservateur.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }

    public static Result list() {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }
        return ok(views.html.Conservateur.list.render(
                Conservateur.find.orderBy("nom").findList(),
                user
        ));
    }

    @Security.Authenticated(Secured.class)
    public static Result add() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Conservateur> conservateurForm = Form.form(Conservateur.class).bindFromRequest();
        Conservateur conservateur = conservateurForm.get();

        conservateur.save();

        return redirect(controllers.routes.Conservateurs.index());
    }

    @Security.Authenticated(Secured.class)
    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Conservateur conservateur = Conservateur.find.where().idEq(id).findUnique();
        Form<Conservateur> editForm = Form.form(Conservateur.class).fill(conservateur);
        return ok(views.html.Conservateur.edit.render(conservateur, editForm, user));
    }

    @Security.Authenticated(Secured.class)
    public static Result update(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Conservateur> filledForm = Form.form(Conservateur.class).bindFromRequest();
        if (filledForm.hasErrors()) {
            Conservateur conservateur = Conservateur.find.where().idEq(id).findUnique();
            return badRequest(views.html.Conservateur.edit.render(conservateur, filledForm, user));
        } else {
            Conservateur conservateur = filledForm.get();
            conservateur.setId(id);
            conservateur.update();
            return redirect(controllers.routes.Conservateurs.index());
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result delete(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Conservateur conservateur = Conservateur.find.byId(id);
        if (conservateur != null) {
            conservateur.delete();
        }
        return redirect(controllers.routes.Conservateurs.index());
    }
}
