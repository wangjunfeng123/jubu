package com.ninep.jubu.manager;


import com.ninep.jubu.vo.TokenModel;

/**
 * 登录token管理接口
 *
 * @version 1.0
 */
public interface TokenManager {
    /**
     * 创建一个token关联上指定用户
     *
     * @param userId the user id
     * @return 生成的token token model
     * @since 2017.06.14
     */
    TokenModel createToken(String userId);

    /**
     * 检查token是否有效
     *
     * @param model token
     * @return 是否有效 boolean
     * @since 2017.06.14
     */
    boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析token
     *
     * @param authentication 加密后的字符串
     * @return token token
     */
    TokenModel getToken(String authentication);

    /**
     * 清除token
     *
     * @param userId the user id
     * @since 2017.06.14
     */
    void deleteToken(String userId);

    /**
     * 获取当前用户的token
     *
     * @param userId  the user id
     * @return token token
     */
    String getTokenById(String userId);
}
