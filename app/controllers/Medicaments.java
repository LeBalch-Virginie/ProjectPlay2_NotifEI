package controllers;

import models.Medicament;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by virginie on 18/11/2014.
 */
public class Medicaments extends Controller {
    public static Result add() {
        final Form<Medicament> medicamentForm = Form.form(Medicament.class).bindFromRequest();
        final Medicament effet_indesirable = medicamentForm.get();

        effet_indesirable.save();
        return redirect(controllers.routes.Application.index());
    }

    public static Result update(){


        return redirect(controllers.routes.Application.index());
    }

}
