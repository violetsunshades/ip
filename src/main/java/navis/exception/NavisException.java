package navis.exception;

/**
 * Represents an exception specific to the Navis application.
 * This exception is thrown when an error occurs while processing
 * user commands or task operations.
 */
public class NavisException extends Exception {

    /**
     * Creates a NavisException with the specified error message.
     *
     * @param message The error message describing the problem.
     */
    public NavisException(String message) {
        super(message);
    }
}