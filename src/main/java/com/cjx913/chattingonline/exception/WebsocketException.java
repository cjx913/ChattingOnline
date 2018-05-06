package com.cjx913.chattingonline.exception;

public class WebsocketException extends RuntimeException {

    public WebsocketException() {
        super();
    }

    public WebsocketException(String message) {
        super(message);
    }

    public WebsocketException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebsocketException(Throwable cause) {
        super(cause);
    }

    protected WebsocketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
