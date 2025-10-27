package patrones.viewmodel;

/**
 *
 * @author Omar Eduardo Cordero Padierna
 */
public class UsrInsertExternoViewModel {
    private int Identificador;
    private String Usr;
    private String Pss;
    private String correito;
    private String PssConfirmacion;

    public UsrInsertExternoViewModel() {
    }

    public UsrInsertExternoViewModel(int Identificador, String Usr, String Pss, String correito, String PssConfirmacion) {
        this.Identificador = Identificador;
        this.Usr = Usr;
        this.Pss = Pss;
        this.correito = correito;
        this.PssConfirmacion = PssConfirmacion;
    }

    public int getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(int Identificador) {
        this.Identificador = Identificador;
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

    public String getCorreito() {
        return correito;
    }

    public void setCorreito(String correito) {
        this.correito = correito;
    }

    public String getPssConfirmacion() {
        return PssConfirmacion;
    }

    public void setPssConfirmacion(String PssConfirmacion) {
        this.PssConfirmacion = PssConfirmacion;
    }

    

    
}