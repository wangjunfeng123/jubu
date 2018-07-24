package com.ninep.jubu.param;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 登录参数.
 * @since 2018/07/10
 */
public class LoginParam {
    private String userName;
    private String password;
    private String code;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}