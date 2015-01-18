package controllers;

import models.Effet_indesirable;
import models.Medicament;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by wizard on 18/01/2015.
 */
@Security.Authenticated(Secured.class)
public class NotifierEi extends Controller {

    public static Result addEiMedicament() {
        Medicament medicament = Medicament.find.where().eq("nom", request().body().asFormUrlEncoded().get("medicament-search")[0]).findUnique();
        Effet_indesirable ei = Effet_indesirable.find.where().eq("label", request().body().asFormUrlEncoded().get("ei-search")[0]).findUnique();
        if (!ei.medicaments.contains(medicament)) {
            ei.medicaments.add(medicament);
            ei.save();
        }

        return redirect(controllers.routes.Application.notifierEi());
    }
}
