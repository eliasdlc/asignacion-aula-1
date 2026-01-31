package org.example;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App {
     public static void main(String[] args) {


        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
         }).start(7000);

        // Filtro

         app.before(ctx -> {
            if (!ctx.path().equals("/login.html")){

            }
         });
    }

}
