package com.ninep.jubu.service;

import com.ninep.jubu.cache.JubuThreadCache;
import com.ninep.jubu.domain.User;
import com.ninep.jubu.enums.UserWorkStatus;
import com.ninep.jubu.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc user service.
 * @since 2018/07/02
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 获取用户详情
     * @param userId 用户Id
     * @return user
     */
    public User getDetail(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 保存用户 和 修改用户
     * @param user 用户
     */
    public void saveUser(User user) {
        if (user.getUserId() == null) {
            user.setUserId(UUID.randomUUID().toString());
            Date now = new Date();
            user.setAddTime(now);
            //todo 用户系统 cache
            user.setAddUser(JubuThreadCache.getUser().getUserId());
            user.setModTime(now);
            user.setModUser(JubuThreadCache.getUser().getUserId());
            user.setStatus(UserWorkStatus.IN_WORK.getIndex());
            userMapper.insert(user);
        } else {
            user.setModTime(new Date());
            user.setModUser(JubuThreadCache.getUser().getUserId());
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 用户
     */
    public User getUserByName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        return Optional.of(userMapper.getUserByName(userName)).orElse(new User());
    }

}