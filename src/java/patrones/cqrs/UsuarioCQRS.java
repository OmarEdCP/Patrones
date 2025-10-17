package patrones.cqrs;

import patrones.modelo.Message;
import patrones.modelo.Usuario;

/**
 *
 * @author Omar Eduardo Cordero Padierna
 */
public class UsuarioCQRS {
    
    public static Message insert(Usuario u) {
        boolean isNameCorrect = u.getNombreUsuario().length() <= 30;
        boolean isPasswordCorrect = u.getContrasenia().length() >= 6;

        if (!isNameCorrect && !isPasswordCorrect) {
          return new Message("Error: el nombre de usuario supera los 30 caracteres y la contraseña debe tener al menos 6.", false);
        } else if (!isNameCorrect) {
            return new Message("Error: el nombre de usuario supera los 30 caracteres.", false);
        } else if (!isPasswordCorrect) {
            return new Message("Error: la contraseña debe tener al menos 6 caracteres.", false);
        } else {
            return new Message("Usuario insertado correctamente.", true);
        }
    }

    public static Message update(Usuario u) {
        boolean isNameCorrect = u.getNombreUsuario().length() <= 30;
        boolean isPasswordCorrect = u.getContrasenia().length() >= 6;

        if (!isNameCorrect && !isPasswordCorrect) {
          return new Message("Error: el nombre de usuario supera los 30 caracteres y la contraseña debe tener al menos 6.", false);
        } else if (!isNameCorrect) {
            return new Message("Error: el nombre de usuario supera los 30 caracteres.", false);
        } else if (!isPasswordCorrect) {
            return new Message("Error: la contraseña debe tener al menos 6 caracteres.", false);
        } else {
            return new Message("Usuario actualizado correctamente.", true);
        }
    }

}
