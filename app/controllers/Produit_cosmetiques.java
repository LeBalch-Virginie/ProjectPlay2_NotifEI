package controllers;


import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by virginie on 18/11/2014.
 */

public class Produit_cosmetiques extends Controller {
    @Security.Authenticated(Secured.class)
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        return ok(views.html.Produit_cosmetique.index.render(
                Produit_cosmetique.find.orderBy("id").findList(),
                User.find.byId(request().username())
        ));
    }


    public static Result list() {
        User user = null;
        String email = ctx().session().get("email");
        if (email != null) {
            user = User.find.byId(email);
        }
        return ok(views.html.Produit_cosmetique.list.render(
                Produit_cosmetique.find.orderBy("id").findList(),
                user
        ));
    }

    @Security.Authenticated(Secured.class)
    public static Result add() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Form<Produit_cosmetique> produit_cosmetiqueForm = Form.form(Produit_cosmetique.class).bindFromRequest();
        final Produit_cosmetique produit_cosmetique = produit_cosmetiqueForm.get();
        Map<String, String[]> urlFormEncoded = play.mvc.Controller.request().body().asFormUrlEncoded();
        if (urlFormEncoded != null) {
            for (String key : urlFormEncoded.keySet()) {
                String[] value = urlFormEncoded.get(key);
                if (key.equals("principe_ac.id[]")) {
                    for (int i = 0; i < value.length; i++) {
                        produit_cosmetique.principe_ac.add(Principe_actif.find.byId(Long.valueOf(value[i])));
                    }
                } else if (key.equals("parabenes.id[]")) {
                    for (int i = 0; i < value.length; i++) {
                        produit_cosmetique.parabenes.add(Parabene.find.byId(Long.valueOf(value[i])));
                    }
                } else if (key.equals("excipients.id[]")) {
                    for (int i = 0; i < value.length; i++) {
                        produit_cosmetique.excipients.add(Excipient.find.byId(Long.valueOf(value[i])));
                    }
                } else if (key.equals("conservateurs.id[]")) {
                    for (int i = 0; i < value.length; i++) {
                        produit_cosmetique.conservateurs.add(Conservateur.find.byId(Long.valueOf(value[i])));
                    }
                }
            }
        }
        produit_cosmetique.save();
        return redirect(controllers.routes.Produit_cosmetiques.index());
    }

    @Security.Authenticated(Secured.class)
    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Produit_cosmetique produit_cosmetique = Produit_cosmetique.find.where().idEq(id).findUnique();
        Form<Produit_cosmetique> editForm = Form.form(Produit_cosmetique.class).fill(produit_cosmetique);
        return ok(views.html.Produit_cosmetique.edit.render(produit_cosmetique, editForm, user));
    }

    @Security.Authenticated(Secured.class)
    public static Result update(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Form<Produit_cosmetique> filledForm = Form.form(Produit_cosmetique.class).bindFromRequest();
        if (filledForm.hasErrors()) {
            Produit_cosmetique produit_cosmetique = Produit_cosmetique.find.where().idEq(id).findUnique();
           return badRequest(views.html.Produit_cosmetique.edit.render(produit_cosmetique, filledForm, user));
        } else {
            Produit_cosmetique produit_cosmetique = filledForm.get();
            produit_cosmetique.setId(id);
            Map<String, String[]> urlFormEncoded = play.mvc.Controller.request().body().asFormUrlEncoded();
            if (urlFormEncoded != null) {
                for (String key : urlFormEncoded.keySet()) {
                    String[] value = urlFormEncoded.get(key);
                    if (key.equals("principe_ac.id[]")) {
                        for (int i = 0; i < value.length; i++) {
                            produit_cosmetique.principe_ac.add(Principe_actif.find.byId(Long.valueOf(value[i])));
                        }
                    } else if (key.equals("parabenes.id[]")) {
                        for (int i = 0; i < value.length; i++) {
                            produit_cosmetique.parabenes.add(Parabene.find.byId(Long.valueOf(value[i])));
                        }
                    } else if (key.equals("excipients.id[]")) {
                        for (int i = 0; i < value.length; i++) {
                            produit_cosmetique.excipients.add(Excipient.find.byId(Long.valueOf(value[i])));
                        }
                    } else if (key.equals("conservateurs.id[]")) {
                        for (int i = 0; i < value.length; i++) {
                            produit_cosmetique.conservateurs.add(Conservateur.find.byId(Long.valueOf(value[i])));
                        }
                    }
                }
            }
            produit_cosmetique.update();
            return redirect(controllers.routes.Produit_cosmetiques.index());
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result delete(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Produit_cosmetique produit_cosmetique = Produit_cosmetique.find.byId(id);
        if (produit_cosmetique != null) {
            produit_cosmetique.delete();
        }
        return redirect(controllers.routes.Produit_cosmetiques.index());
    }
}
