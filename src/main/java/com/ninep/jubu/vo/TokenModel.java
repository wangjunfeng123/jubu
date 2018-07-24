package com.ninep.jubu.vo;

import java.io.Serializable;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc token manager
 * @since 2018/07/02
 */
public class TokenModel implements Serializable {

    private String userId;//userid
    private String token;//随机生成的uuid

    public TokenModel(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}