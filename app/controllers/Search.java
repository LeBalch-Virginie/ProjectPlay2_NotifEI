package controllers;

import models.*;
import play.*;
import play.data.DynamicForm;
import play.libs.Json;
import play.data.Form;
import play.mvc.*;

import java.util.List;

@Security.Authenticated(Secured.class)
public class Search extends Controller {
    public static Result index() {
        return ok(views.html.Search.index.render(User.find.byId(request().username())));
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

    public static Result fromMedicament() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String medicamentName = requestData.get("medicament-search");
        Medicament medicament = Medicament.find.where().eq("nom", medicamentName).findUnique();


        return ok(medicament.getId().toString());
    }

    public static Result fromDispositif() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String dispositifName = requestData.get("dispositif-search");
        Dispo_medical dispositif = Dispo_medical.find.where().eq("nom", dispositifName).findUnique();

        String[] result = new String[dispositif.Effet_indesirables.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = dispositif.Effet_indesirables.get(i).getLabel();
        }

        return ok(Json.toJson(result));
    }
}
