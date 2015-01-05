package controllers;

import models.Effet_indesirable;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by virginie on 18/11/2014.
 */

public class Effet_indesirables extends Controller {

    public static Result add() {
        Form<Effet_indesirable> effet_indesirableForm = Form.form(Effet_indesirable.class).bindFromRequest();
        Effet_indesirable effet_indesirable = effet_indesirableForm.get();

        System.out.println(effet_indesirableForm.data().get("label"));
        System.out.println(effet_indesirable.getLabel());
        effet_indesirable.save();

        return redirect(controllers.routes.Application.index());
    }


}