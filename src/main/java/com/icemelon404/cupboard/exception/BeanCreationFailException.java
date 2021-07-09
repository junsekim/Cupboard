package com.icemelon404.cupboard.exception;

public class BeanCreationFailException extends RuntimeException {

    private String msg;

    public BeanCreationFailException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
