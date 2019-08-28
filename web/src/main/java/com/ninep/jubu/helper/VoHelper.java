package com.ninep.jubu.helper;

import com.ninep.jubu.domain.User;
import com.ninep.jubu.utils.Assembler;
import com.ninep.jubu.vo.UserVo;
import org.springframework.stereotype.Service;

/**
 * @author wangjunfeng
 * @date 2017-5-22
 */
@Service
public class VoHelper {

    /**
     * vo 到 domain
     */
    public User transfer2Domain(UserVo vo) {
        User user = new User();
        Assembler.assemble(vo,user);
        return user;
    }

    /**
     * domain -> vo
     */
    public UserVo transfer2Vo(User user) {
        UserVo userVo = new UserVo();
        Assembler.assemble(user,userVo);
        return userVo;
    }
}
