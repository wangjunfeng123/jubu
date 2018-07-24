package com.ninep.jubu.controller;

import com.ninep.jubu.domain.User;
import com.ninep.jubu.result.ApiResponse;
import com.ninep.jubu.service.UserService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc test transactional.
 * @since 2018/06/29
 */
@RestController
@RequestMapping("testUser")
public class TestUserController {
    private static final Logger logger = LoggerFactory.getLogger(TestUserController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public Map<String, Object> saveUser(@ModelAttribute User user) {
        try {
            logger.info("TestUserController saveUser user:{}", ToStringBuilder.reflectionToString(user));
            userService.saveUser(user);
            return ApiResponse.createSuccessMsgResult("保存用户成功");
        } catch (Exception e) {
            logger.error("TestUserController saveUser user:{}", ToStringBuilder.reflectionToString(user), e);
            return ApiResponse.createFailMsgResult("保存用户信息错误");
        }
    }

}