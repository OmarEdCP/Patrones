package patrones.controlador;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import patrones.db.MySQL;
import patrones.modelo.Usuario;

/**
 *
 * @author Omar Eduardo Cordero Padierna
 */
public class UsuarioController {
     public Usuario insertUsuario(Usuario u) {

        String query = "call sp_AgregarUsuario(?,?,?)";
        try {
            MySQL connMysql = new MySQL();
            Connection conn = connMysql.open();
            CallableStatement pstm = (CallableStatement) conn.prepareCall(query);
            pstm.setString(1, u.getNombreUsuario());
            pstm.setString(2, u.getContrasenia());
            pstm.setString(3, u.getEmail());
            pstm.execute();
            System.out.println("Insert correcto");
            pstm.close();
            connMysql.close();

        } catch (SQLException em) {
            em.getStackTrace();
        }
        return u;
    }

    public List<Usuario> getAllUsuario() throws SQLException {
        String sql = "select * from vw_UsuariosActivos";
        MySQL connMysql = new MySQL();
        Connection conn = connMysql.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Usuario> listaUsuario = new ArrayList<>();
        while (rs.next()) {
            listaUsuario.add(fill(rs));
        }
        rs.close();
        connMysql.close();
        return listaUsuario;
    }

    public Usuario fill(ResultSet rs) throws SQLException {

        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("idUsuario"));
        u.setNombreUsuario(rs.getString("nombreUsuario"));
        u.setContrasenia(rs.getString("contrasenia"));
        u.setEmail(rs.getString("email"));
        u.setEstatus(rs.getInt("estatus"));
        return u;
    }

    public void updateUsuario(Usuario u) throws SQLException {
        String query = "CALL sp_ActualizarUsuario(?,?,?,?)";
        Connection conn = null;
        CallableStatement cstm = null;
        MySQL connMysql = new MySQL();
        conn = connMysql.open();
        cstm = (CallableStatement) conn.prepareCall(query);
        cstm.setInt(1, u.getIdUsuario());
        cstm.setString(2, u.getNombreUsuario());
        cstm.setString(3, u.getContrasenia());
        cstm.setString(4, u.getEmail());
        cstm.execute();
        cstm.close();
        connMysql.close();
        conn.close();
    }

    public void deleteUsuario(int idUsuario) throws SQLException {
        String query = "{CALL sp_EliminarUsuario(?)}";
        MySQL connMysql = new MySQL();
        Connection conn = connMysql.open();
        CallableStatement cstm = (CallableStatement) conn.prepareCall(query);
        cstm.setInt(1, idUsuario);
        cstm.execute();
        cstm.close();
        connMysql.close();
        conn.close();
    }
}
