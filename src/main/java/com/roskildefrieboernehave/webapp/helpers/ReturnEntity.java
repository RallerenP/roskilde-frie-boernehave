package com.roskildefrieboernehave.webapp.helpers;

public class ReturnEntity<T> {
    private String message;
    private T body;

    public ReturnEntity(String message, T body) {
        this.message = message;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }
}
