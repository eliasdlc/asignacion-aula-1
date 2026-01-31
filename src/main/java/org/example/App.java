package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App {
    public static void main(String[] args) {

        var app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(7000);

        app.before(ctx -> {
            if (!ctx.path().equals("/login.html") && !ctx.path().equals("/autenticar")
                    && !ctx.path().endsWith(".css")) {
                if (ctx.sessionAttribute("usuario") == null) {
                    ctx.redirect("/login.html");
                }
            }
        });

        app.post("/autenticar", ctx -> {

            String user = ctx.formParam("username");
            String pass = ctx.formParam("password");

            if ("admin".equals(user) && "1234".equals(pass)) {
                ctx.sessionAttribute("usuario", user);

                ctx.redirect("/home.html");
            } else {
                ctx.redirect("/login.html");
            }
        });

        app.get("/logout", ctx -> {
            ctx.sessionAttribute("usuario", null);
            ctx.redirect("/login.html");
        });

    }

}
