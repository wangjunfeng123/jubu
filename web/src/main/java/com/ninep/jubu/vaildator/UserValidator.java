package com.ninep.jubu.vaildator;

import com.ninep.jubu.result.ApiResponse;
import com.ninep.jubu.utils.FarmUtils;
import com.ninep.jubu.vo.UserVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 用户校验
 * @since 2018/07/02
 */
@Component
public class UserValidator {

    public Map<String,Object> checkSave(UserVo userVo){
        if(userVo == null || StringUtils.isEmpty(userVo.getPassword())
                ||StringUtils.isEmpty(userVo.getPhoneNum())
                ||StringUtils.isEmpty(userVo.getUserName())
                || userVo.getGender() == null){
            return ApiResponse.createFailMsgResult("invalid param");
        }
        if (StringUtils.isEmpty(userVo.getEmail()) || "".equals(userVo.getEmail())){
            return ApiResponse.createFailMsgResult("邮箱为必填项");
        }
        String email = StringUtils.trimAllWhitespace(userVo.getEmail());
        if (!FarmUtils.isLegalEmail(email)){
            return ApiResponse.createFailMsgResult("邮箱格式不正确！");
        }else {
            userVo.setEmail(email);
        }
        String mobile = StringUtils.trimAllWhitespace(userVo.getPhoneNum());
        if(!FarmUtils.isLegalMobile(mobile)){
            return ApiResponse.createFailMsgResult("invalid param");
        }else{
            userVo.setPhoneNum(mobile);
        }
        return null;
    }
}