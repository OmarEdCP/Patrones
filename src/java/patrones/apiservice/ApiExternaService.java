package patrones.apiservice;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import jakarta.ws.rs.core.Form;
import java.lang.reflect.Type;
import patrones.modelo.Usuario;

/**
 *
 * @author Omar Eduardo Cordero Padierna
 */
public class ApiExternaService {

    private static final String BASE_URL = "http://localhost:8089/ProyectoDDD/api/user/";

    /**
     * Método 1: Consulta usuarios desde otra API (API-API)
     *
     * @return
     */
    public List<Usuario> getUsuarios() {
        Client client = ClientBuilder.newClient();
        List<Usuario> lista = new ArrayList<>();
        String URL = BASE_URL + "getAllUser";
        try {
            Response response = client
                    .target(URL)
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            if (response.getStatus() == 200) {
                String json = response.readEntity(String.class);

                Gson gson = new Gson();
                Type listType = new TypeToken<List<Usuario>>() {
                }.getType();
                lista = gson.fromJson(json, listType);
            } else {
                System.err.println("Error al consultar usuarios externos. Código: " + response.getStatus());
            }
        } catch (Exception e) {
            System.err.println("Error en getUsuarios(): " + e.getMessage());
        } finally {
            client.close();
        }

        return lista;
    }

    /**
     * Método 2: Inserta usuario en otra API (API-API)
     *
     *
     * @param u
     * @return
     */
    public String insertUsuario(Usuario u) {
        Client client = ClientBuilder.newClient();
        String URL = BASE_URL + "insertUser";
        JsonObject obj = new JsonObject();
        obj.addProperty("userName", u.getNombreUsuario());
        obj.addProperty("userPassword", u.getContrasenia());
        obj.addProperty("userEmail", u.getEmail());
        obj.addProperty("userStatus", 1);

        try {
            Response response = client
                    .target(URL)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.form(new Form().param("datosUsuario", obj.toString())));

            if (response.getStatus() == 200 || response.getStatus() == 201) {
                return "Usuario insertado correctamente en la API externa";
            } else {
                String errorMsg = response.readEntity(String.class);
                return "Error al insertar usuario. Código: " + response.getStatus() + " → " + errorMsg;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error en insertUsuario(): " + e.getMessage();
        } finally {
            client.close();
        }
    }

}
