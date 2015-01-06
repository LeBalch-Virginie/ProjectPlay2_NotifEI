package controllers;

import models.Effet_indesirable;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by virginie on 18/11/2014.
 */
@Security.Authenticated(Secured.class)
public class Effet_indesirables extends Controller {

    public static Result add() {
        Form<Effet_indesirable> effet_indesirableForm = Form.form(Effet_indesirable.class).bindFromRequest();
        Effet_indesirable effet_indesirable = effet_indesirableForm.get();

        System.out.println(effet_indesirableForm.data().get("label"));
        System.out.println(effet_indesirable.getLabel());
        effet_indesirable.save();

        return redirect(controllers.routes.Application.index());
    }

    public static Result edit(Long id) {
        Effet_indesirable effet_indesirable = Effet_indesirable.find.where().idEq(id).findUnique();
        Form<Effet_indesirable> editForm = Form.form(Effet_indesirable.class).fill(effet_indesirable);
        User user = User.find.byId(request().username());
        return ok(views.html.Effet_indesirable.edit.render(effet_indesirable, editForm, user));
    }

    public static Result update(Long id) {
        Form<Effet_indesirable> filledForm = Form.form(Effet_indesirable.class).bindFromRequest();
        if (filledForm.hasErrors()) {
                User user = User.find.byId(request().username());
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
               final Effet_indesirable effet_indesirable = Effet_indesirable.find.byId(id);
                if (effet_indesirable != null) {
                    effet_indesirable.delete();
                    }
        return redirect(controllers.routes.Application.index());
    }

}