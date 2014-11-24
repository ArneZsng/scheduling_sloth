package de.nak.scheduling_sloth.exception;


/**
 * Exception class for not found.
 *
 * @author      Hendrik Makait <hendrik.makait@nordakademie.de>
 * @version     1.0
 * @since       2014-11-23
 */
public class EntityNotFoundException extends Exception {
    private static final long serialVersionUID = 5524946781470109353L;
    public static final String DEFAULT_MESSAGE = "msg.exception.entityNotFound";

    public EntityNotFoundException(String message) {
        super(message);
    }
}
