package controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import models.Dispo_medical;
import models.Effet_indesirable;
import models.Medicament;
import models.Produit_cosmetique;
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
        System.out.println("valeur ei " + ei);
        if (!ei.medicaments.contains(medicament)) {
            ei.medicaments.add(medicament);
            ei.save();
        }

        return redirect(controllers.routes.Application.notifierEi());
    }

    public static Result addEiDispo() {
        Dispo_medical dispo_medical = Dispo_medical.find.where().eq("nom", request().body().asFormUrlEncoded().get("dispositif-search")[0]).findUnique();
        Effet_indesirable ei = Effet_indesirable.find.where().eq("label", request().body().asFormUrlEncoded().get("ei-search")[0]).findUnique();
        System.out.println("valeur ei " + ei);
        if (!ei.Dispo_medicaux.contains(dispo_medical)) {
            ei.Dispo_medicaux.add(dispo_medical);
            ei.save();
        }

        return redirect(controllers.routes.Application.notifierEi());
    }

    public static Result addEiproduit() {
        Produit_cosmetique produit = Produit_cosmetique.find.where().eq("nom", request().body().asFormUrlEncoded().get("cosmetique-search")[0]).findUnique();
        Effet_indesirable ei = Effet_indesirable.find.where().eq("label", request().body().asFormUrlEncoded().get("ei-search")[0]).findUnique();
        System.out.println("valeur ei " + ei);
        if (!ei.produits_cosmetiques.contains(produit)) {
            ei.produits_cosmetiques.add(produit);
            ei.save();
        }

        return redirect(controllers.routes.Application.notifierEi());
    }
}
