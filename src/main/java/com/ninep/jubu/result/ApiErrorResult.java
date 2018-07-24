package com.ninep.jubu.result;

public class ApiErrorResult {

    private int code;
    private String type;
    private String message;

    public ApiErrorResult() {
    }

    public ApiErrorResult(String message, ErrorType errorType) {
        this.code = errorType.getIndex();
        this.message = message;
        this.type = errorType.getName();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
