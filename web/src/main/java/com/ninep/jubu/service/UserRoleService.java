package com.ninep.jubu.service;

import com.ninep.jubu.domain.UserRole;
import com.ninep.jubu.enums.EntityStatus;
import com.ninep.jubu.mapper.UserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since ${date}
 */
@Service
public class UserRoleService {
    public static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 根据用户id获取角色id集合
     * @param userId 用户id
     * @return RoleIds
     */
    public List<Integer> getRoleIds(String userId) {
        List<UserRole> userRoles = this.getUserRole(userId);
        if (CollectionUtils.isEmpty(userRoles)) {
            return new ArrayList<>();
        }
        return userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
    }

    private List<UserRole> getUserRole(String userId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setStatus(EntityStatus.NORMAL.getIndex());
        return userRoleMapper.getList(userRole);
    }

}