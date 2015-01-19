package controllers;

import models.Medicament;
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
 * Created by virginie on 18/11/2014.
 */

public class Medicaments extends Controller {
    @Security.Authenticated(Secured.class)
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        return ok(views.html.Medicament.index.render(
                Medicament.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }

    public static Result list() {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }
        return ok(views.html.Medicament.list.render(
                user
        ));
    }

    public static Result search(String nom) {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }

        List<Medicament> medicaments = Medicament.find.where().like("nom", "%" + nom + "%").findList();
        Set<String> results = new HashSet<String>();
        for (Medicament m : medicaments) {
            results.add(m.getNom());
        }
        return ok(Json.toJson(results));
    }

    @Security.Authenticated(Secured.class)
    public static Result add() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Form<Medicament> medicamentForm = Form.form(Medicament.class).bindFromRequest();
        final Medicament medicament = medicamentForm.get();

        medicament.save();
        return redirect(controllers.routes.Application.index());
    }

    @Security.Authenticated(Secured.class)
    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Medicament medicament = Medicament.find.where().idEq(id).findUnique();
        Form<Medicament> editForm = Form.form(Medicament.class).fill(medicament);
        return ok(views.html.Medicament.edit.render(medicament, editForm, user));
    }

    @Security.Authenticated(Secured.class)
    public static Result update(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Medicament> filledForm = Form.form(Medicament.class).bindFromRequest();
        if (filledForm.hasErrors()) {
                Medicament medicament = Medicament.find.where().idEq(id).findUnique();
               return badRequest(views.html.Medicament.edit.render(medicament, filledForm, user));
        } else {
                Medicament medicament = filledForm.get();
                medicament.setId(id);
                medicament.update();
                return redirect(controllers.routes.Application.index());
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result delete(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Medicament medicament = Medicament.find.byId(id);
        if (medicament != null) {
            medicament.delete();
        }
        return redirect(controllers.routes.Application.index());
    }
}
