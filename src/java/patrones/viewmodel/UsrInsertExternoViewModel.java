package patrones.viewmodel;

/**
 *
 * @author Omar Eduardo Cordero Padierna
 */
public class UsrInsertExternoViewModel {

    private String Usr;
    private String Pss;
    private String PssConfirmacion;

    public UsrInsertExternoViewModel() {
    }

    public UsrInsertExternoViewModel(String Usr, String Pss, String PssConfirmacion) {
        this.Usr = Usr;
        this.Pss = Pss;
        this.PssConfirmacion = PssConfirmacion;
    }

    public String getUsr() {
        return Usr;
    }

    public void setUsr(String Usr) {
        this.Usr = Usr;
    }

    public String getPss() {
        return Pss;
    }

    public void setPss(String Pss) {
        this.Pss = Pss;
    }

    public String getPssConfirmacion() {
        return PssConfirmacion;
    }

    public void setPssConfirmacion(String PssConfirmacion) {
        this.PssConfirmacion = PssConfirmacion;
    }

}
