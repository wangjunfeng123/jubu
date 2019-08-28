package com.ninep.jubu.test.springcxf;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 代替数据库交互.
 * @since 2019/1/1
 */
public class Storage {

    //初始化数据
    public static List<User> userList = Arrays.asList(
            new User(1,"zhangsan"),
            new User(2,"lisi"),
            new User(3,"wangwu")
            );


}