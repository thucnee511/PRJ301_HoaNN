package org.hoann.prj301.repositories.service;

public class ServiceERROR extends Exception {

    public ServiceERROR(String message) {
        super(message);
    }

    public ServiceERROR(String message, Throwable cause) {
        super(message, cause);
    }
}
