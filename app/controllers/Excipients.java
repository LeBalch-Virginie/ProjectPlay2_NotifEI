package controllers;

import models.Excipient;
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

public class Excipients extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        return ok(views.html.Excipient.index.render(
                Excipient.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }

    public static Result list() {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }
        return ok(views.html.Excipient.list.render(
                user
        ));
    }

    public static Result search(String nom) {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }

        List<Excipient> excipients = Excipient.find.where().like("nom", "%" + nom + "%").findList();
        Set<String> results = new HashSet<String>();
        for (Excipient s : excipients) {
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
        Form<Excipient> excipientForm = Form.form(Excipient.class).bindFromRequest();
        Excipient excipient = excipientForm.get();

        excipient.save();

        return redirect(controllers.routes.Excipients.index());
    }

    @Security.Authenticated(Secured.class)
    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Excipient excipient = Excipient.find.where().idEq(id).findUnique();
        Form<Excipient> editForm = Form.form(Excipient.class).fill(excipient);
        return ok(views.html.Excipient.edit.render(excipient, editForm, user));
    }

    @Security.Authenticated(Secured.class)
    public static Result update(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Excipient> filledForm = Form.form(Excipient.class).bindFromRequest();
        if (filledForm.hasErrors()) {
            Excipient excipient = Excipient.find.where().idEq(id).findUnique();
            return badRequest(views.html.Excipient.edit.render(excipient, filledForm, user));
        } else {
            Excipient excipient = filledForm.get();
            excipient.setId(id);
            excipient.update();
            return redirect(controllers.routes.Excipients.index());
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result delete(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Excipient excipient = Excipient.find.byId(id);
        if (excipient != null) {
            excipient.delete();
        }
        return redirect(controllers.routes.Excipients.index());
    }
}
