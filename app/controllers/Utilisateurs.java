package controllers;


import models.Utilisateur;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by virginie on 24/11/2014.
 */
public class Utilisateurs extends Controller {

    public static Result add() {
        final Form<Utilisateur> UtilisateurForm = Form.form(Utilisateur.class).bindFromRequest();
        final Utilisateur utilisateur = UtilisateurForm.get();

        utilisateur.save();
        return redirect(controllers.routes.Application.index());
    }

}
