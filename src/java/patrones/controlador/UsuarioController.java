package patrones.controlador;

import java.sql.SQLException;
import java.util.List;
import patrones.apiservice.ApiExternaService;
import patrones.cqrs.UsuarioCQRS;
import patrones.dao.UsuarioDAO;
import patrones.modelo.Message;
import patrones.modelo.Usuario;
import patrones.viewmodel.UsrInsertExternoViewModel;
import patrones.viewmodel.UsrPublicoExternoViewModel;

/**
 *
 * @author Omar Eduardo Cordero Padierna
 */
public class UsuarioController {

    private final ApiExternaService apiExterna = new ApiExternaService();

    public Message insertUsuario(Usuario u) {
        Message validation = UsuarioCQRS.insert(u);

        if (!validation.isValidate()) {
            return validation;
        }
        String result = apiExterna.insertUsuario(u);
        
        return new Message("Usuario insertado correctamente en la base de datos.", true);
    }

    public Message updateUsuario(Usuario u) throws SQLException {
        Message validation = UsuarioCQRS.update(u);

        if (!validation.isValidate()) {
            return validation;
        }
        return new Message("Usuario actualizado correctamente en la base de datos.", true);
    }

    public UsrPublicoExternoViewModel registro(UsrInsertExternoViewModel vm) throws SQLException {
        // Convertir ViewModel => Modelo
        Usuario u = new Usuario();
        u.setNombreUsuario(vm.getUsr());
        u.setEmail(vm.getCorreito());
        u.setContrasenia(vm.getPss());
        UsuarioDAO.insert(u);

        return new UsrPublicoExternoViewModel(UsuarioDAO.getByName(u.getNombreUsuario()).getIdUsuario(), u.getNombreUsuario());
    }

    public UsrPublicoExternoViewModel actualizar(UsrInsertExternoViewModel vm) throws SQLException {
        // ViewModel => Modelo
        Usuario u = new Usuario();
        u.setIdUsuario(vm.getIdentificador());
        u.setNombreUsuario(vm.getUsr());
        u.setContrasenia(vm.getPss());
        u.setEmail(vm.getCorreito());

        UsuarioDAO.update(u);

        return new UsrPublicoExternoViewModel(
                u.getIdUsuario(),
                u.getNombreUsuario()
        );
    }

    public List<Usuario> getAllUsuario() throws SQLException {
        List<Usuario> locales = UsuarioDAO.getUsuarios();
        List<Usuario> externos = apiExterna.getUsuarios();

        if (externos != null && !externos.isEmpty()) {
            for (Usuario externo : externos) {
                boolean existe = locales.stream()
                        .anyMatch(local -> local.getNombreUsuario().equalsIgnoreCase(externo.getNombreUsuario()));
                if (!existe) {
                    locales.add(externo);
                }
            }
        }

        return locales;
    }

    public Usuario getUsuarioById(int idUsuario) throws SQLException {
        return UsuarioDAO.getById(idUsuario);
    }

    public Usuario getUsuarioByName(String nombreUsuario) throws SQLException {
        return UsuarioDAO.getByName(nombreUsuario);
    }

    public void deleteUsuario(int idUsuario) throws SQLException {
        UsuarioDAO.delete(idUsuario);
    }
}
