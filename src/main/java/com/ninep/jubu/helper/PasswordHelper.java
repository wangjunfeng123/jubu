package com.ninep.jubu.helper;

import com.ninep.jubu.domain.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc password 工具.
 * @since ${date}
 */
@Service
public class PasswordHelper {

    public void encryptPassword(User user) {
        // 将用户的注册密码经过散列算法替换成一个不可逆的新密码保存进数据，散列过程使用了盐
        String algorithmName = "md5";
        int hashIterations = 1024;
        String newPassword = new SimpleHash(algorithmName, user.getPassword(),
                ByteSource.Util.bytes("salt"), hashIterations).toHex();
        user.setPassword(newPassword);
    }
}