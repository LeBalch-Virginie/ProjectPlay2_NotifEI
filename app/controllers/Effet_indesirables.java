package controllers;

import models.Effet_indesirable;
import models.Produit_cosmetique;
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

public class Effet_indesirables extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        return ok(views.html.Effet_indesirable.index.render(
                Effet_indesirable.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }

    public static Result list() {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }
        return ok(views.html.Effet_indesirable.list.render(
                user
        ));
    }

    public static Result search(String label) {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }

        List<Effet_indesirable> effets = Effet_indesirable.find.where().like("label", "%" + label + "%").findList();
        Set<String> results = new HashSet<String>();
        for (Effet_indesirable e : effets) {
            results.add(e.getLabel());
        }
        return ok(Json.toJson(results));
    }

    @Security.Authenticated(Secured.class)
    public static Result add() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Effet_indesirable> effet_indesirableForm = Form.form(Effet_indesirable.class).bindFromRequest();
        Effet_indesirable effet_indesirable = effet_indesirableForm.get();

        effet_indesirable.save();

        return redirect(controllers.routes.Application.index());
    }

    @Security.Authenticated(Secured.class)
    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Effet_indesirable effet_indesirable = Effet_indesirable.find.where().idEq(id).findUnique();
        Form<Effet_indesirable> editForm = Form.form(Effet_indesirable.class).fill(effet_indesirable);
        return ok(views.html.Effet_indesirable.edit.render(effet_indesirable, editForm, user));
    }

    @Security.Authenticated(Secured.class)
    public static Result update(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Effet_indesirable> filledForm = Form.form(Effet_indesirable.class).bindFromRequest();
        if (filledForm.hasErrors()) {
                Effet_indesirable effet_indesirable = Effet_indesirable.find.where().idEq(id).findUnique();
                return badRequest(views.html.Effet_indesirable.edit.render(effet_indesirable, filledForm, user));
        } else {
            Effet_indesirable effet_indesirable = filledForm.get();
            effet_indesirable.setId(id);
            effet_indesirable.update();
            return redirect(controllers.routes.Application.index());
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result delete(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Effet_indesirable effet_indesirable = Effet_indesirable.find.byId(id);
        if (effet_indesirable != null) {
            effet_indesirable.delete();
            }
        return redirect(controllers.routes.Application.index());
    }

}