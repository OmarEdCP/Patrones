package patrones.controlador;

import java.sql.SQLException;
import java.util.List;
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

    public Message insertUsuario(Usuario u) {
        Message validation = UsuarioCQRS.insert(u);

        if (!validation.isValidate()) {
            return validation;
        }

        return new Message("Usuario insertado correctamente en la base de datos.", true);
    }

    public Message updateUsuario(Usuario u) throws SQLException {
        Message validation = UsuarioCQRS.update(u);

        if (!validation.isValidate()) {
            return validation;
        }
        return new Message("Usuario actualizado correctamente en la base de datos.", true);
    }

    public UsrPublicoExternoViewModel registro(Usuario u) {
        // Aquí normalmente iría la lógica de inserción, pero solo hacemos el mapeo
        return new UsrPublicoExternoViewModel(u.getIdUsuario(), u.getNombreUsuario());
    }

    public UsrInsertExternoViewModel actualizar(Usuario u) {
        return new UsrInsertExternoViewModel(u.getNombreUsuario(), u.getContrasenia(), u.getContrasenia());
    }

    public List<Usuario> getAllUsuario() throws SQLException {
        return UsuarioDAO.getUsuarios();
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
