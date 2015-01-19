package controllers;

import models.*;
import play.*;
import play.data.DynamicForm;
import play.libs.Json;
import play.data.Form;
import play.mvc.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Search extends Controller {
    public static Result index() {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }
        return ok(views.html.Effet_indesirable.consulter.render(user));
    }

    public static Result autocompleteMedicament(String medicament) {
        List<Medicament> medicaments = Medicament.find
                .where()
                    .like("nom", "%" + medicament + "%")
                .orderBy("nom")
                .setMaxRows(10)
                .findList();

        String[] result = new String[medicaments.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = medicaments.get(i).getNom();
        }

        return ok(Json.toJson(result));
    }

    public static Result autocompleteDispositif(String dispositif) {
        List<Dispo_medical> dispositifs = Dispo_medical.find
                .where()
                .like("nom", "%" + dispositif + "%")
                .orderBy("nom")
                .setMaxRows(10)
                .findList();

        String[] result = new String[dispositifs.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = dispositifs.get(i).getNom();
        }

        return ok(Json.toJson(result));
    }

    public static Result autocompleteCosmetique(String cosmetique) {
        List<Produit_cosmetique> cosmetiques = Produit_cosmetique.find
                .where()
                .like("nom", "%" + cosmetique + "%")
                .orderBy("nom")
                .setMaxRows(10)
                .findList();

        String[] result = new String[cosmetiques.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = cosmetiques.get(i).getNom();
        }

        return ok(Json.toJson(result));
    }

    public static Result autocompleteEi(String ei) {
        List<Effet_indesirable> effet_indesirables = Effet_indesirable.find
                .where()
                .like("label", "%" + ei + "%")
                .orderBy("label")
                .setMaxRows(10)
                .findList();

        String[] result = new String[effet_indesirables.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = effet_indesirables.get(i).getLabel();
        }

        return ok(Json.toJson(result));
    }

    public static Result fromMedicament() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String medicamentName = requestData.get("medicament-search");

        Set<Effet_indesirable> effets = new HashSet<Effet_indesirable>();
        effets.addAll(Effet_indesirable.find
            .where()
                .eq("Classe_chimiques.Substances.medicaments.nom", medicamentName)
            .findList()
        );
        effets.addAll(Effet_indesirable.find
                        .where()
                        .eq("Classe_pharmacos.Substances.medicaments.nom", medicamentName)
                        .findList()
        );
        effets.addAll(Effet_indesirable.find
                        .where()
                        .eq("medicaments.nom", medicamentName)
                        .findList()
        );

        String[] result = new String[effets.size()];
        int i = 0;
        for (Effet_indesirable e : effets) {
            result[i] = e.getLabel();
            i++;
        }
        return ok(Json.toJson(result));
    }

    public static Result fromDispositif() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String dispositifName = requestData.get("dispositif-search");
        Dispo_medical dispositif = Dispo_medical.find.where().eq("nom", dispositifName).findUnique();

        if (dispositif == null) {
            return ok(Json.toJson(new String[0]));
        } else {
            String[] result = new String[dispositif.Effet_indesirables.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = dispositif.Effet_indesirables.get(i).getLabel();
            }
            return ok(Json.toJson(result));
        }
    }



    public static Result fromCosmetique() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String cosmetiqueName = requestData.get("cosmetique-search");

        Set<Effet_indesirable> effets = new HashSet<Effet_indesirable>();
        effets.addAll(Effet_indesirable.find
            .where()
            .eq("conservateurs.produit_cos.nom", cosmetiqueName)
            .findList()
        );
        effets.addAll(Effet_indesirable.find
            .where()
            .eq("excipients.produit_cos.nom", cosmetiqueName)
            .findList()
        );
        effets.addAll(Effet_indesirable.find
            .where()
            .eq("parabenes.produit_cos.nom", cosmetiqueName)
            .findList()
        );
        effets.addAll(Effet_indesirable.find
            .where()
            .eq("principes_actifs.produit_cos.nom", cosmetiqueName)
            .findList()
        );
        effets.addAll(Effet_indesirable.find
            .where()
            .eq("produits_cosmetiques.nom", cosmetiqueName)
            .findList()
        );

        String[] result = new String[effets.size()];
        int i = 0;
        for (Effet_indesirable e : effets) {
            result[i] = e.getLabel();
            i++;
        }

        return ok(Json.toJson(result));
    }
}
