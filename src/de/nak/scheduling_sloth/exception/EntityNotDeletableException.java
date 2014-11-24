package de.nak.scheduling_sloth.exception;

/**
 * Exception class for not deletable.
 *
 * @author      Hendrik Makait <hendrik.makait@nordakademie.de>
 * @version     1.0
 * @since       2014-11-23
 */
public class EntityNotDeletableException extends Exception {
    private static final long serialVersionUID = 4978445776219382451L;
    public static final String DEFAULT_MESSAGE = "msg.exception.entityNotDeletable";


    public EntityNotDeletableException(String message) {
        super(message);
    }
}
