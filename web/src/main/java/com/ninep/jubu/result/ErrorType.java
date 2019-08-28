package com.ninep.jubu.result;

/**
 * error type
 */
public enum ErrorType {
    
    /**
     * 客户端请求有语法错误，不能被服务器所理解
     */
    BAD_REQUEST("参数异常", 400),
    
    /**
     * 请求未经授权
     */
    UNAUTHORIZED("Unauthorized", 401),
    
    /**
     * 服务器收到请求，但是拒绝提供服务
     */
    FORBIDDEN("Forbidden", 403),
    
    /**
     * 请求资源不存在
     */
    NOT_FOUND("Not Found", 404),
    
    /**
     * 服务器发生不可预期的错误
     */
    INTERNAL_SERVER_ERROR("Internal Server Error", 500),
    
    /**
     * 服务不可用
     */
    SERVICE_UNAVAILABLE("Service Unavailable", 503),

    COMMON_ERROR("请求异常!", 600),

    LOGIN_INFO_ERROR("登录信息已失效,请登录后访问!", 700),
    ;


    private String name;

    private int index;

    public static ErrorType fromHttpStatus(int httpStatus) {
        for(ErrorType errorCode : values()) {
            if(errorCode.getIndex() == httpStatus) {
                return errorCode;
            }
        }
        return INTERNAL_SERVER_ERROR;
    }

    ErrorType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (ErrorType type : ErrorType.values()) {
            if (type.getIndex() == index) {
                return type.name;
            }
        }
        return null;
    }

    public static Integer getIndex(String name) {
        for (ErrorType type : ErrorType.values()) {
            if (type.getName().equals(name)) {
                return type.index;
            }
        }
        return null;
    }

    public static ErrorType getErrorCode(int index) {
        for (ErrorType type : ErrorType.values()) {
            if (type.getIndex() == index) {
                return type;
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
