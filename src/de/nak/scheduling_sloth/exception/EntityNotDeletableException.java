package de.nak.scheduling_sloth.exception;

/**
 * Created by hendster on 23/11/14.
 */
public class EntityNotDeletableException extends Exception {
    private static final long serialVersionUID = 4978445776219382451L;
    public static final String DEFAULT_MESSAGE = "msg.exception.entityNotDeletable";


    public EntityNotDeletableException(String message) {
        super(message);
    }
}
