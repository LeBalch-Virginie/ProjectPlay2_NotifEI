package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import models.*;
import views.html.*;


public class Authentication extends Controller {

    public static class AuthenticatedUser {

        public String email;
        public String password;

        public String validate() {
            if(User.authenticate(email, password) == null) {
                return "oups! râté! Essaye encore une fois";
            }
            return null;
        }
    }

    public static Result register() {
        return redirect(controllers.routes.Application.index());
    }

    public static Result add() {
        Form<User> userForm = Form.form(User.class).bindFromRequest();
        User user = userForm.get();
        
        user.save();

        return redirect(controllers.routes.Application.index());
    }


    public static Result login() {
        return ok(
                login.render(Form.form(AuthenticatedUser.class))
        );
    }


    //On récupère les informations de login (quand le user se "signe")
    public static Result authenticate() {
        Form<AuthenticatedUser> loginForm =
                Form.form(AuthenticatedUser.class).bindFromRequest();
        if(loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session("email", loginForm.get().email);
            return redirect(
                    routes.Application.index()
            );
        }
    }

    //Fermer la session
    public static Result logout() {
        session().clear();
        flash("success", "Vous êtes déconnecté(e)");
        return redirect(
                routes.Authentication.login()
        );
    }

}
