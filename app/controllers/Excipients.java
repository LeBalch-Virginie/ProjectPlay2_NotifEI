package controllers;

import models.Excipient;
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
public class Excipients extends Controller {
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

    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Excipient excipient = Excipient.find.where().idEq(id).findUnique();
        Form<Excipient> editForm = Form.form(Excipient.class).fill(excipient);
        return ok(views.html.Excipient.edit.render(excipient, editForm, user));
    }

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