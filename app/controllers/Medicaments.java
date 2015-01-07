package controllers;

import models.Medicament;
import models.User;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

/**
 * Created by virginie on 18/11/2014.
 */
@Security.Authenticated(Secured.class)
public class Medicaments extends Controller {
    public static Result index() {
        return ok(views.html.Medicament.index.render(
                Medicament.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }

    public static Result add() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Form<Medicament> medicamentForm = Form.form(Medicament.class).bindFromRequest();
        //final Medicament effet_indesirable = medicamentForm.get();
        final Medicament medicament = medicamentForm.get();

        //effet_indesirable.save();
        medicament.save();
        return redirect(controllers.routes.Application.index());
    }

    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Medicament medicament = Medicament.find.where().idEq(id).findUnique();
        Form<Medicament> editForm = Form.form(Medicament.class).fill(medicament);
        return ok(views.html.Medicament.edit.render(medicament, editForm, user));
    }

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
