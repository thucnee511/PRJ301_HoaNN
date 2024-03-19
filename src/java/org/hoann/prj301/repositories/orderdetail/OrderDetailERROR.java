package org.hoann.prj301.repositories.orderdetail;

public class OrderDetailERROR extends Exception {

    public OrderDetailERROR(String message) {
        super(message);
    }

    public OrderDetailERROR(String message, Throwable cause) {
        super(message, cause);
    }
}
