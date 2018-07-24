package com.ninep.jubu.exception;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc farm 基本异常类.
 * @since 2018/07/06
 */
public class JubuException extends RuntimeException {
    public JubuException() {
    }

    public JubuException(String message) {
        super(message);
    }

    public JubuException(String message, Throwable cause) {
        super(message, cause);
    }

    public JubuException(Throwable cause) {
        super(cause);
    }

    public JubuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}