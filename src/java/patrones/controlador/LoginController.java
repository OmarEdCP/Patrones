
package patrones.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import patrones.db.MySQL;

/**
 *
 * @author Omar Eduardo Cordero Padierna
 */
public class LoginController {
      public boolean validar_usuario(String nombre, String contrasenia) throws Exception {
    MySQL conexion = new MySQL();
    Connection conn = conexion.open();
    CallableStatement stmt = null;
    ResultSet rs = null;
    boolean valido = false;

    try {
        // Llamar al procedimiento almacenado
        stmt = conn.prepareCall("{CALL sp_ValidarUsuario(?, ?)}");
        stmt.setString(1, nombre);
        stmt.setString(2, contrasenia);

        // Ejecutar y obtener resultados
        rs = stmt.executeQuery();

        // Si el ResultSet tiene filas, el usuario existe y es válido
        if (rs.next()) {
            // Verificamos si tiene columna idUsuario (usuario válido)
            try {
                int id = rs.getInt("idUsuario");
                String user = rs.getString("nombreUsuario");
                String email = rs.getString("email");

                System.out.println("✅ Usuario válido: " + user + " (" + email + ")");
                valido = true;
            } catch (SQLException ex) {
                // Si no tiene idUsuario, probablemente devolvió el mensaje de error
                String mensaje = rs.getString("mensaje");
                System.out.println("⚠️ " + mensaje);
                valido = false;
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        throw new Exception("Error SQL: " + e.getMessage(), e);
    } catch (Exception e) {
        e.printStackTrace();
        throw new Exception("Error general: " + e.getMessage(), e);
    } finally {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        conexion.close();
    }

    return valido;
}

}
