package controllers;

import models.Substance;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by virginie on 18/11/2014.
 */
public class Substances extends Controller {

    public static Result add() {
        final Form<Substance> substanceForm = Form.form(Substance.class).bindFromRequest();
        final Substance substance = substanceForm.get();

        substance.save();
        return redirect(controllers.routes.Application.index());
    }

}
