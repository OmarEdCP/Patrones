
package patrones.modelo;

/**
 *
 * @author Omar Eduardo Cordero Padierna
 */
public class Usuario {
  private int idUsuario;
  private String nombreUsuario;
  private String contrasenia;
  private String email;
  private int estatus;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String contrasenia, String email, int estatus) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.estatus = estatus;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
}
