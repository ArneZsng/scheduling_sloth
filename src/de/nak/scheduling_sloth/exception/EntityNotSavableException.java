package de.nak.scheduling_sloth.exception;

/**
 * Exception class for not savable.
 *
 * @author      Hendrik Makait <hendrik.makait@nordakademie.de>
 * @version     1.0
 * @since       2014-11-23
 */
public class EntityNotSavableException extends Exception{
    private static final long serialVersionUID = 6324729082768626094L;
    public static final String DEFAULT_MESSAGE = "msg.exception.entityNotSavable";

    public EntityNotSavableException(String message) {
        super(message);
    }
}
