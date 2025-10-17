package patrones.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import patrones.controlador.UsuarioController;
import patrones.modelo.Message;
import patrones.modelo.Usuario;

/**
 *
 * @author Omar Eduardo Cordero Padierna
 */
@Path("usuario")
public class UsuarioRest extends Application {

    @Path("insertUsuario")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCiudad(@FormParam("datosUsuario") @DefaultValue("") String usuario) {
        try {
            Gson gson = new Gson();
            UsuarioController uc = new UsuarioController();
            Usuario u = gson.fromJson(usuario, Usuario.class);
            Message result = uc.insertUsuario(u);
            String json = gson.toJson(result);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Error en el servidor\"}")
                    .build();
        }
    }

    @Path("getAllUsuario")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsuario(@QueryParam("id") @DefaultValue("0") int id) {
        List<Usuario> lista = null;
        Gson gson = new Gson();
        String out = null;
        UsuarioController uc = null;
        try {
            uc = new UsuarioController();
            lista = uc.getAllUsuario();

            out = gson.toJson(lista);
        } catch (Exception e) {
            e.printStackTrace();
            out = """
              {"result":"Error de servidor"}
              """;
        }
        return Response.ok(out).build();
    }

    @Path("getById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@QueryParam("idUsuario") int idUsuario) {
        Gson gson = new Gson();
        String out;
        try {
            UsuarioController uc = new UsuarioController();
            Usuario usuario = uc.getUsuarioById(idUsuario);

            if (usuario != null) {
                out = gson.toJson(usuario);
                return Response.ok(out).build();
            } else {
                out = "{\"result\":\"Usuario no encontrado\"}";
                return Response.status(Response.Status.NOT_FOUND).entity(out).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error\":\"Error en el servidor\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }

    @Path("getByName")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@QueryParam("nombreUsuario") String nombreUsuario) {
        Gson gson = new Gson();
        String out;
        try {
            UsuarioController uc = new UsuarioController();
            Usuario usuario = uc.getUsuarioByName(nombreUsuario);

            if (usuario != null) {
                out = gson.toJson(usuario);
                return Response.ok(out).build();
            } else {
                out = "{\"result\":\"Usuario no encontrado\"}";
                return Response.status(Response.Status.NOT_FOUND).entity(out).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error\":\"Error en el servidor\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }

    @Path("updateUsuario")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@FormParam("datosUsuario") @DefaultValue("") String datosUsuario) {
        String out = null;
        Usuario u = null;
        UsuarioController uc = null;
        Gson gson = new Gson();
        try {
            uc = new UsuarioController();
            u = gson.fromJson(datosUsuario, Usuario.class);
            Message result = uc.updateUsuario(u);

            String json = gson.toJson(result);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            out = """
                {"result":"Error de servidor"}
                """;
        }
        return Response.ok(out).build();
    }

    @Path("deleteUsuario")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUsuario(@FormParam("idUsuario") int idUsuario) {
        String out;
        UsuarioController uc = new UsuarioController();
        try {
            uc.deleteUsuario(idUsuario);
            out = """
                {"result":"Registro eliminado correctamente"}
                """;
        } catch (SQLException e) {
            e.printStackTrace();
            out = """
                {"result":"Error al eliminar el registro"}
                """;
        }
        return Response.ok(out).build();
    }

}
