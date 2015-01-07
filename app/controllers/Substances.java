package controllers;


import models.Substance;
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
public class Substances extends Controller {
    public static Result index() {
        return ok(views.html.Substance.index.render(
                Substance.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }

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

    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Substance substance = Substance.find.where().idEq(id).findUnique();
        Form<Substance> editForm = Form.form(Substance.class).fill(substance);
        return ok(views.html.Substance.edit.render(substance, editForm, user));
    }

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
