package de.nak.scheduling_sloth.exception;


/**
 * Created by hendster on 23/11/14.
 */
public class EntityNotFoundException extends Exception {
    private static final long serialVersionUID = 5524946781470109353L;
    public static final String DEFAULT_MESSAGE = "msg.exception.entityNotFound";

    public EntityNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }
}
