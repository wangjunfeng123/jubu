package com.ninep.jubu.service;

import com.ninep.jubu.domain.Auth;
import com.ninep.jubu.mapper.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 权限逻辑.
 * @since 2018/07/03
 */
@Service
public class AuthService {

    @Autowired
    private AuthMapper authMapper;

    public Auth getByUrl(String url) {
        return authMapper.getByUrl(url);
    }

}