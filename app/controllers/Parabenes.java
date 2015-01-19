package controllers;

import models.Parabene;
import models.User;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by virginie on 06/01/2015.
 */

public class Parabenes extends Controller {
    @Security.Authenticated(Secured.class)
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        return ok(views.html.Parabene.index.render(
                Parabene.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }

    public static Result list() {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }
        return ok(views.html.Parabene.list.render(
                user
        ));
    }

    public static Result search(String nom) {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }

        List<Parabene> Parabenes = Parabene.find.where().like("nom", "%" + nom + "%").findList();
        Set<String> results = new HashSet<String>();
        for (Parabene s : Parabenes) {
            results.add(s.getNom());
        }
        return ok(Json.toJson(results));
    }

    @Security.Authenticated(Secured.class)
    public static Result add() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Parabene> parabeneForm = Form.form(Parabene.class).bindFromRequest();
        Parabene parabene = parabeneForm.get();

        parabene.save();

        return redirect(controllers.routes.Parabenes.index());
    }

    @Security.Authenticated(Secured.class)
    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Parabene parabene = Parabene.find.where().idEq(id).findUnique();
        Form<Parabene> editForm = Form.form(Parabene.class).fill(parabene);
        return ok(views.html.Parabene.edit.render(parabene, editForm, user));
    }

    @Security.Authenticated(Secured.class)
    public static Result update(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Parabene> filledForm = Form.form(Parabene.class).bindFromRequest();
        if (filledForm.hasErrors()) {
            Parabene parabene = Parabene.find.where().idEq(id).findUnique();
            return badRequest(views.html.Parabene.edit.render(parabene, filledForm, user));
        } else {
            Parabene parabene = filledForm.get();
            parabene.setId(id);
            parabene.update();
            return redirect(controllers.routes.Parabenes.index());
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result delete(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Parabene parabene = Parabene.find.byId(id);
        if (parabene != null) {
            parabene.delete();
        }
        return redirect(controllers.routes.Parabenes.index());
    }
}
