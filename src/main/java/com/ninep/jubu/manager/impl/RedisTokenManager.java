package com.ninep.jubu.manager.impl;

import com.ninep.jubu.manager.TokenManager;
import com.ninep.jubu.vo.TokenModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 基于redis实现token缓存
 * @author Administrator
 * @version 1.0
 * @since 2017.06.14
 */
@Service
public class RedisTokenManager implements TokenManager {

    @Resource
    private RedisTemplate redisTemplate;

    private static final String TOKEN_PREFIX = "user_login_token_prefix_";
    // 缓存超时时间，单位：秒 ,默认7天
    private int defaultExpireTime = 60 * 60 * 24 * 7;
    private Logger logger = LoggerFactory.getLogger(getClass());

    //根据用户id创建token
    @Override
    public TokenModel createToken(String userId) {
        //使用uuid作为源token
        String token;
        token = this.getExistToken(userId);
        if (StringUtils.isEmpty(token)) {
            token = UUID.randomUUID().toString().replace("-", "");
        }
        TokenModel model = new TokenModel(userId, token);

        //存储到redis并设置过期时间 一周
        redisTemplate.opsForValue().set(TOKEN_PREFIX + userId, token, defaultExpireTime, TimeUnit.SECONDS);
        logger.info("createToken userId:{} key:{},token:{}", userId, TOKEN_PREFIX + userId, token);
        return model;
    }

    //根据用户id查询缓存中是否有token
    private String getExistToken(String userId) {
        return (String) redisTemplate.opsForValue().get(TOKEN_PREFIX + userId);
    }

    //根据用户登录信息获取tokenModel
    @Override
    public TokenModel getToken(String authentication) {
        if (StringUtils.isEmpty(authentication)) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        String userId = param[0];
        String token = param[1];
        logger.info("getToken authentication:{} userId:{},token:{}", authentication, userId, token);
        return new TokenModel(userId, token);
    }

    //校验token值是否有效
    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = (String) redisTemplate.opsForValue().get(TOKEN_PREFIX + model.getUserId());
        logger.info("getToken key:{} modelToken:{},token:{}", TOKEN_PREFIX + model.getUserId(), model.getToken(), token);
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redisTemplate.opsForValue().set(TOKEN_PREFIX + model.getUserId(), token, defaultExpireTime,TimeUnit.SECONDS);
        return true;
    }

    //删除缓存中的token
    @Override
    public void deleteToken(String userId) {
        redisTemplate.delete(TOKEN_PREFIX + userId);
    }

    //根据用户id，获取缓存中的token值
    @Override
    public String getTokenById(String userId) {
        return (String) redisTemplate.opsForValue().get(TOKEN_PREFIX + userId);
    }
}