package controllers;

import models.Dispo_medical;
import models.Effet_indesirable;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

/**
 * Created by virginie on 06/01/2015.
 */
@Security.Authenticated(Secured.class)
public class Dispo_medicaux extends Controller {
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        return ok(views.html.Dispo_medical.index.render(
                Dispo_medical.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }

    public static Result add() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Dispo_medical> dispo_medicalForm = Form.form(Dispo_medical.class).bindFromRequest();
        Dispo_medical dispo_medical = dispo_medicalForm.get();

        dispo_medical.save();

        return redirect(controllers.routes.Dispo_medicaux.index());
    }

    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Dispo_medical dispo_medical = Dispo_medical.find.where().idEq(id).findUnique();
        Form<Dispo_medical> editForm = Form.form(Dispo_medical.class).fill(dispo_medical);
        return ok(views.html.Dispo_medical.edit.render(dispo_medical, editForm, user));
    }

    public static Result update(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Dispo_medical> filledForm = Form.form(Dispo_medical.class).bindFromRequest();
        if (filledForm.hasErrors()) {
            Dispo_medical dispo_medical = Dispo_medical.find.where().idEq(id).findUnique();
            return badRequest(views.html.Dispo_medical.edit.render(dispo_medical, filledForm, user));
        } else {
            Dispo_medical dispo_medical = filledForm.get();
            dispo_medical.setId(id);
            dispo_medical.update();
            return redirect(controllers.routes.Dispo_medicaux.index());
        }
    }

    public static Result delete(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Dispo_medical dispo_medical = Dispo_medical.find.byId(id);
        if (dispo_medical != null) {
            dispo_medical.delete();
        }
        return redirect(controllers.routes.Dispo_medicaux.index());
    }
}
