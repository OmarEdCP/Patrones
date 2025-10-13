package patrones.rest;

import com.google.gson.JsonObject;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import patrones.controlador.LoginController;

/**
 *
 * @author Omar Eduardo Cordero Padierna
 */
@Path("login")
public class LoginRest {
    
@Path("validarLogin")
@POST
@Produces(MediaType.APPLICATION_JSON)
public Response validarLogin(
        @FormParam("nombre") String nombre,
        @FormParam("contrasenia") String contrasenia
) {
    LoginController loginController = new LoginController();
    JsonObject response = new JsonObject();

    try {
        // Validamos el usuario con el método del controlador
        boolean isValidUser = loginController.validar_usuario(nombre, contrasenia);

        if (isValidUser) {
            response.addProperty("status", "success");
            response.addProperty("message", "Login exitoso");
        } else {
            response.addProperty("status", "fail");
            response.addProperty("message", "Credenciales incorrectas o usuario inactivo");
        }

    } catch (Exception e) {
        e.printStackTrace();
        response.addProperty("status", "error");
        response.addProperty("message", "Error en el servidor: " + e.getMessage());
    }

    return Response.status(Response.Status.OK)
                   .entity(response.toString())
                   .build();
}

}
