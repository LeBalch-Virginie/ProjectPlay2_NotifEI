package controllers;

import models.Medicament;
import models.Produit_cosmetique;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

/**
 * Created by virginie on 18/11/2014.
 */
@Security.Authenticated(Secured.class)
public class Produit_cosmetiques extends Controller {
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


    public static Result add() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Form<Produit_cosmetique> produit_cosmetiqueForm = Form.form(Produit_cosmetique.class).bindFromRequest();
        final Produit_cosmetique produit_cosmetique = produit_cosmetiqueForm.get();

        produit_cosmetique.save();
        return redirect(controllers.routes.Application.index());
    }

    public static Result edit(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        Produit_cosmetique produit_cosmetique = Produit_cosmetique.find.where().idEq(id).findUnique();
        Form<Produit_cosmetique> editForm = Form.form(Produit_cosmetique.class).fill(produit_cosmetique);
        return ok(views.html.Produit_cosmetique.edit.render(produit_cosmetique, editForm, user));
    }

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
            produit_cosmetique.update();
                return redirect(controllers.routes.Application.index());
        }
    }

    public static Result delete(Long id) {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }
        final Produit_cosmetique produit_cosmetique = Produit_cosmetique.find.byId(id);
        if (produit_cosmetique != null) {
            produit_cosmetique.delete();
        }
        return redirect(controllers.routes.Application.index());
    }

}
