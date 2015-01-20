package controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Date;

/**
 * Created by wizard on 18/01/2015.
 */
@Security.Authenticated(Secured.class)
public class NotifierEi extends Controller {

    public static Result addEiMedicament() {
        Medicament medicament = Medicament.find.where().eq("nom", request().body().asFormUrlEncoded().get("medicament-search")[0]).findUnique();
        String eiLabel = request().body().asFormUrlEncoded().get("ei-search")[0];
        Effet_indesirable ei = Effet_indesirable.find.where().eq("label", eiLabel).findUnique();
        //initialisation de la declaration
        Declaration declaration = new Declaration();
        java.util.Date uDate = new java.util.Date();
        declaration.setDate(uDate);
        User user = User.find.byId(request().username());
        declaration.setUser(user.email);
        declaration.setRegion(user.region);
        declaration.setEffet_ind(ei.getLabel());
        declaration.setProduit(medicament.getNom());
        if(ei != null && medicament != null) {
            declaration.save();
        }

        if (ei == null) {
            ei = new Effet_indesirable();
            ei.setLabel(eiLabel);
        }
        if (!ei.medicaments.contains(medicament)) {
            ei.medicaments.add(medicament);
            ei.save();
        } else {
            flash("error", "Cette interaction a déjà été déclarée.");
        }

        return redirect(controllers.routes.Application.notifierEi());
    }

    public static Result addEiDispo() {
        Dispo_medical dispo_medical = Dispo_medical.find.where().eq("nom", request().body().asFormUrlEncoded().get("dispositif-search")[0]).findUnique();
        String eiLabel = request().body().asFormUrlEncoded().get("ei-search")[0];
        Effet_indesirable ei = Effet_indesirable.find.where().eq("label", eiLabel).findUnique();
        //initialisation de la declaration
        Declaration declaration = new Declaration();
        java.util.Date uDate = new java.util.Date();
        declaration.setDate(uDate);
        User user = User.find.byId(request().username());
        declaration.setUser(user.email);
        declaration.setRegion(user.region);
        declaration.setEffet_ind(ei.getLabel());
        declaration.setProduit(dispo_medical.getNom());
        if(ei != null && dispo_medical != null) {
            declaration.save();
        }
        if (ei == null) {
            ei = new Effet_indesirable();
            ei.setLabel(eiLabel);
        }
        if (!ei.Dispo_medicaux.contains(dispo_medical)) {
            ei.Dispo_medicaux.add(dispo_medical);
            ei.save();
        } else {
            flash("error", "Cette interaction a déjà été déclarée.");
        }

        return redirect(controllers.routes.Application.notifierEi());
    }

    public static Result addEiproduit() {
        Produit_cosmetique produit = Produit_cosmetique.find.where().eq("nom", request().body().asFormUrlEncoded().get("cosmetique-search")[0]).findUnique();
        String eiLabel = request().body().asFormUrlEncoded().get("ei-search")[0];
        Effet_indesirable ei = Effet_indesirable.find.where().eq("label", eiLabel).findUnique();
        //initialisation de la declaration
        Declaration declaration = new Declaration();
        java.util.Date uDate = new java.util.Date();
        declaration.setDate(uDate);
        User user = User.find.byId(request().username());
        declaration.setUser(user.email);
        declaration.setRegion(user.region);
        declaration.setEffet_ind(ei.getLabel());
        declaration.setProduit(produit.getNom());
        if(ei != null && produit != null) {
            declaration.save();
        }
        if (ei == null) {
            ei = new Effet_indesirable();
            ei.setLabel(eiLabel);
        }
        if (!ei.produits_cosmetiques.contains(produit)) {
            ei.produits_cosmetiques.add(produit);
            ei.save();
        } else {
            flash("error", "Cette interaction a déjà été déclarée.");
        }

        return redirect(controllers.routes.Application.notifierEi());
    }
}
