package com.icemelon404.cupboard.exception;

public class BeanNotFoundException extends RuntimeException {

    private String msg;

    public BeanNotFoundException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
