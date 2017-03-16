package exception;

import static org.eclipse.persistence.config.CacheUsageIndirectionPolicy.Exception;

public class PersonException extends Exception {

    /**
     * Creates a new instance of <code>PersonNotFoundException</code> without
     * detail message.
     */
    public PersonException() {
    }

    /**
     * Constructs an instance of <code>PersonNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PersonException(String msg) {
        
        super(msg);
        
    }
    
}
