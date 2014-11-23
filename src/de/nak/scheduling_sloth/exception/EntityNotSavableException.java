package de.nak.scheduling_sloth.exception;

/**
 * Created by hendster on 23/11/14.
 */
public class EntityNotSavableException extends Exception{
    private static final long serialVersionUID = 6324729082768626094L;
    public static final String DEFAULT_MESSAGE = "msg.exception.entityNotSavable";

    public EntityNotSavableException(String message) {
        super(message);
        System.out.println(message);
    }
}
