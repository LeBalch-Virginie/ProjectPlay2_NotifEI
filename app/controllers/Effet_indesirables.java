package controllers;

import models.Effet_indesirable;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

/**
 * Created by virginie on 18/11/2014.
 */
@Security.Authenticated(Secured.class)
public class Effet_indesirables extends Controller {
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

    public static Result add() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Effet_indesirable> effet_indesirableForm = Form.form(Effet_indesirable.class).bindFromRequest();
        Effet_indesirable effet_indesirable = effet_indesirableForm.get();

        System.out.println(effet_indesirableForm.data().get("label"));
        System.out.println(effet_indesirable.getLabel());
        effet_indesirable.save();

        return redirect(controllers.routes.Application.index());
    }

    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Effet_indesirable effet_indesirable = Effet_indesirable.find.where().idEq(id).findUnique();
        Form<Effet_indesirable> editForm = Form.form(Effet_indesirable.class).fill(effet_indesirable);
        return ok(views.html.Effet_indesirable.edit.render(effet_indesirable, editForm, user));
    }

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