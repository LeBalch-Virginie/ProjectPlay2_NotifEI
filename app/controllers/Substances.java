package controllers;


import models.Substance;
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

public class Substances extends Controller {
    @Security.Authenticated(Secured.class)
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        return ok(views.html.Substance.index.render(
                Substance.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }

    public static Result list() {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }
        return ok(views.html.Substance.list.render(
                user
        ));
    }

    public static Result search(String label) {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }

        List<Substance> substances = Substance.find.where().like("label", "%" + label + "%").findList();
        Set<String> results = new HashSet<String>();
        for (Substance s : substances) {
            results.add(s.getLabel());
        }
        return ok(Json.toJson(results));
    }

    @Security.Authenticated(Secured.class)
    public static Result add() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Form<Substance> substanceForm = Form.form(Substance.class).bindFromRequest();
        final Substance substance = substanceForm.get();

        substance.save();
        return redirect(controllers.routes.Application.index());
    }

    @Security.Authenticated(Secured.class)
    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Substance substance = Substance.find.where().idEq(id).findUnique();
        Form<Substance> editForm = Form.form(Substance.class).fill(substance);
        return ok(views.html.Substance.edit.render(substance, editForm, user));
    }

    @Security.Authenticated(Secured.class)
    public static Result update(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Substance> filledForm = Form.form(Substance.class).bindFromRequest();
        if (filledForm.hasErrors()) {
            Substance substance = Substance.find.where().idEq(id).findUnique();
            return badRequest(views.html.Substance.edit.render(substance, filledForm, user));
        } else {
            Substance substance = filledForm.get();
            substance.setId(id);
            substance.update();
            return redirect(controllers.routes.Application.index());
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result delete(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Substance substance = Substance.find.byId(id);
        if (substance != null) {
            substance.delete();
        }
        return redirect(controllers.routes.Application.index());
    }

}
