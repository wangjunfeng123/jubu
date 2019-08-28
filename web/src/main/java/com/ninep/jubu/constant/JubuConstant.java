package com.ninep.jubu.constant;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 常量类
 * @since 2018/07/02
 */
public class JubuConstant {
    //手机格式
    public static final String MOBILE_PATTERN = "1[0-9]{10}|[0-9]{3,4}-[0-9]{7,8}";
    //email 格式
    public static final String EMAIL_PATTERN="^[a-zA-Z0-9_-]{1,64}@([a-zA-Z0-9_-]+.){1,63}[a-z]{1,6}$";
    //座机格式
    public static final String TEL_PATTERN="^(0(10|2[0-5789]|3\\\\d{2}|4\\\\d{2}|5\\\\d{2}|6\\\\d{2}|7\\\\d{2}|8\\\\d{2}|" +
            "9\\\\d{2})\\\\d{7,8}$)|(0(10|2[0-5789]|3\\\\d{2}|4\\\\d{2}|5\\\\d{2}|6\\\\d{2}|7\\\\d{2}|8\\\\d{2}|9\\\\d{2})" +
            "-\\\\d{7,8}$)|(\\\\d{7,8}$)\n";

    public static final String AUTHORIZATION = "farm-auth-ssid";

}