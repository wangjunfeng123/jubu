package com.ninep.jubu.test.springcxf;

import java.util.List;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2019/1/1
 */

//todo  缺少spring cxf包，
//todo 下次补全
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public List<User> getUsers() {
        System.out.println(Storage.userList);
        return Storage.userList;
    }

    @Override
    public User getDetail(Integer id) {
        System.out.println(Storage.userList.get(id));
        return Storage.userList.get(id);
    }

    @Override
    public int delete(Integer id) {
        Storage.userList.remove(id);
        System.out.println(Storage.userList);
        return 1;
    }

    @Override
    public int addUser(User user) {
        Storage.userList.add(user);
        System.out.println(Storage.userList);
        return 1;
    }

    @Override
    public int updateUser(User user) {
        Storage.userList.add(user);
        return 1;
    }

}