package com.ninep.jubu.controller;

import com.ninep.jubu.domain.User;
import com.ninep.jubu.enums.PositionType;
import com.ninep.jubu.helper.PasswordHelper;
import com.ninep.jubu.helper.VoHelper;
import com.ninep.jubu.param.LoginParam;
import com.ninep.jubu.result.ApiResponse;
import com.ninep.jubu.service.UserService;
import com.ninep.jubu.vaildator.UserValidator;
import com.ninep.jubu.vo.UserVo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc user 系统.
 * @since 2018/07/02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private VoHelper voHelper;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 获取部分列表
     * @return 部门列表数据
     */
    @PostMapping("getPositionTypes")
    public Map<String, Object> getPositionTypes() {
        try {
            logger.info("UserController getPositionTypes");
            Map<Integer, String> index2Name = new HashMap<>();
            for (PositionType type:PositionType.values()) {
                index2Name.put(type.getIndex(), type.getName());
            }
            return ApiResponse.createSuccessResult(index2Name);
        } catch (Exception e) {
            logger.error("UserController getPositionTypes",e);
            return ApiResponse.createFailMsgResult("获取部门列表失败");
        }
    }

    /**
     * 保存用户
     * @param userVo 用户
     * @return 保存成功
     */
    @PostMapping("/saveUser")

    public Map<String, Object> saveUser(@RequestBody UserVo userVo) {
        try {
            logger.info("UserController saveUser user:{}", ToStringBuilder.reflectionToString(userVo));
            Map<String, Object> err = userValidator.checkSave(userVo);
            if (null != err) {
                return err;
            }
            User user = voHelper.transfer2Domain(userVo);
            passwordHelper.encryptPassword(user);
            userService.saveUser(user);
            return ApiResponse.createSuccessMsgResult("success");
        } catch (Exception e) {
            logger.info("UserController saveUser user:{}", ToStringBuilder.reflectionToString(userVo), e);
            return ApiResponse.createFailMsgResult("保存用户信息失败");
        }
    }

    @PostMapping("login")
    public Map<String, Object> login(@RequestBody LoginParam loginParam, HttpServletResponse response) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUserName(), loginParam.getPassword());
        try {
            logger.info("UserController login loginParam:{}", ToStringBuilder.reflectionToString(loginParam));

            logger.info("对用户" + loginParam.getUserName() + "开始登录验证");
            currentUser.login(token);
            logger.info("对用户" + loginParam.getUserName() + "登录验证成功  ");

            String logOperatorName = (String) currentUser.getPrincipal();

            return ApiResponse.createSuccessMsgResult("登录成功");
        } catch (UnknownAccountException uae) {
            logger.error("账户不存在", uae);
            return ApiResponse.createFailMsgResult("账户不存在");
        } catch (IncorrectCredentialsException ice) {
            logger.error("密码不正确", ice);
            return ApiResponse.createFailMsgResult("密码不正确");
        } catch (LockedAccountException lae) {
            logger.error("账号已锁定", lae);
            return ApiResponse.createFailMsgResult("账号已锁定");
        } catch (DisabledAccountException dae) {
            logger.error("账户未审核", dae);
            return ApiResponse.createFailMsgResult("账户未审核");
        } catch (ExcessiveAttemptsException eae) {
            logger.error("用户名和密码错误次数过多");
            return ApiResponse.createFailMsgResult("用户名和密码错误次数过多");
        }finally {
            if (currentUser.isAuthenticated()) {
                token.clear();
            }
        }
    }

    @GetMapping("logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        request.getSession().invalidate();
        return ApiResponse.createSuccessMsgResult("用户已安全退出");
    }


    private String changePassword(String oldPassword) {
        return new Md5Hash(oldPassword, "farm", 1024).toBase64();
    }

}