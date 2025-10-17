package patrones.controlador;

import java.sql.SQLException;
import java.util.List;
import patrones.cqrs.UsuarioCQRS;
import patrones.dao.UsuarioDAO;
import patrones.modelo.Message;
import patrones.modelo.Usuario;

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
        try {
            UsuarioDAO.insert(u);
            return new Message("Usuario insertado correctamente en la base de datos.", true);
        } catch (Exception e) {
            return new Message("Error al insertar usuario en la base de datos: " + e.getMessage(), false);
        }
    }
  
    public Message updateUsuario(Usuario u) {
        Message validation = UsuarioCQRS.update(u);

        if (!validation.isValidate()) {
            return validation;
        }
        try {
            UsuarioDAO.update(u);
            return new Message("Usuario actualizado correctamente en la base de datos.", true);
        } catch (SQLException e) {
            return new Message("Error al actualizar usuario en la base de datos: " + e.getMessage(), false);
        }
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
