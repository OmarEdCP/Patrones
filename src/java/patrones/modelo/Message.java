
package patrones.modelo;

/**
 *
 * @author Omar Eduardo Cordero Padierna
 */
public class Message {
    private String message;
    private boolean validate;

    public Message() {
    }

    public Message(String message, boolean validate) {
        this.message = message;
        this.validate = validate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }
    
    
}
