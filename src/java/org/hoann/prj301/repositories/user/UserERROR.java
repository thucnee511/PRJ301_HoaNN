package org.hoann.prj301.repositories.user;

public class UserERROR extends Exception {

    public UserERROR(String message) {
        super(message);
    }

    public UserERROR(String message, Throwable cause) {
        super(message, cause);
    }
}
