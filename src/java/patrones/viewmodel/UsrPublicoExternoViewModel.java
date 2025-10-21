package patrones.viewmodel;

/**
 *
 * @author Omar Eduardo Cordero Padierna
 */
public class UsrPublicoExternoViewModel {

    private int Identificador;
    private String Usr;

    public UsrPublicoExternoViewModel() {
    }

    public UsrPublicoExternoViewModel(int Identificador, String Usr) {
        this.Identificador = Identificador;
        this.Usr = Usr;
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
}
