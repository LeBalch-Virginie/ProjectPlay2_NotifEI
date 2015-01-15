package controllers;

import models.Parabene;
import models.Principe_actif;
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
public class Principe_actifs extends Controller {
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        return ok(views.html.Principe_actif.index.render(
                Principe_actif.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }

    public static Result add() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Principe_actif> principe_actifForm = Form.form(Principe_actif.class).bindFromRequest();
        Principe_actif principe_actif = principe_actifForm.get();

        principe_actif.save();

        return redirect(controllers.routes.Principe_actifs.index());
    }

    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Principe_actif principe_actif = Principe_actif.find.where().idEq(id).findUnique();
        Form<Principe_actif> editForm = Form.form(Principe_actif.class).fill(principe_actif);
        return ok(views.html.Principe_actif.edit.render(principe_actif, editForm, user));
    }

    public static Result update(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Principe_actif> filledForm = Form.form(Principe_actif.class).bindFromRequest();
        if (filledForm.hasErrors()) {
            Principe_actif principe_actif = Principe_actif.find.where().idEq(id).findUnique();
            return badRequest(views.html.Principe_actif.edit.render(principe_actif, filledForm, user));
        } else {
            Principe_actif principe_actif = filledForm.get();
            principe_actif.setId(id);
            principe_actif.update();
            return redirect(controllers.routes.Principe_actifs.index());
        }
    }

    public static Result delete(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Principe_actif principe_actif = Principe_actif.find.byId(id);
        if (principe_actif != null) {
            principe_actif.delete();
        }
        return redirect(controllers.routes.Principe_actifs.index());
    }
}
