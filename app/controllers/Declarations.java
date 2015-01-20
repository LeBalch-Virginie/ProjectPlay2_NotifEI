package controllers;

import com.avaje.ebean.Expr;
import models.Declaration;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

import java.util.List;

/**
 * Created by virginie on 20/01/2015.
 */
public class Declarations extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        User user = User.find.byId(request().username());
        if (!user.isAdmin) {
            return Results.forbidden("Need to be admin");
        }

        List<Declaration> declarations;
        if (user.getTypeUser().equals("crpv")) {
            declarations = Declaration.find.where()
                .or(
                    Expr.or(
                        Expr.or(
                                Expr.eq("user.typeUser", "patient"),
                                Expr.eq("user.typeUser", "medecin")
                        ),
                        Expr.or(
                                Expr.eq("user.typeUser", "medecin_hospitalier"),
                                Expr.eq("user.typeUser", "medecin_de_ville")
                        )
                    ),
                    Expr.eq("user.typeUser", "association_patients")
                ).eq("user.region", user.region)
                    .findList();
        } else if (user.getTypeUser().equals("cnpv")) {
            declarations = Declaration.find.where()
                .or(
                    Expr.or(
                            Expr.or(
                                    Expr.eq("user.typeUser", "patient"),
                                    Expr.eq("user.typeUser", "medecin")
                            ),
                            Expr.or(
                                    Expr.eq("user.typeUser", "medecin_hospitalier"),
                                    Expr.eq("user.typeUser", "medecin_de_ville")
                            )
                    ),
                    Expr.or(
                        Expr.eq("user.typeUser", "association_patients"),
                        Expr.eq("user.typeUser", "crpv")
                    )
                )
                .findList();
        } else {
            return Results.forbidden("Need to be CRPV or CNPV");
        }

        return ok(views.html.Declaration.index.render(
                declarations,
                User.find.byId(request().username())
        ));
    }
}
