package org.hoann.prj301.repositories.feedback;

public class FeedbookERROR extends Exception {

    public FeedbookERROR(String message) {
        super(message);
    }

    public FeedbookERROR(String message, Throwable cause) {
        super(message, cause);
    }
}
