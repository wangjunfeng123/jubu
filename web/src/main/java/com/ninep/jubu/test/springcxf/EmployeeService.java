package com.ninep.jubu.test.springcxf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebService;
import java.util.List;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 用户的数据处理 curd.
 * @since 2019/1/1
 */
@WebService
@RequestMapping("users")
public interface EmployeeService {

    //http://ip:port/users
    @RequestMapping(value = "",method = RequestMethod.GET)
    List<User> getUsers();

    //http://ip:port/users/id
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    User getDetail(Integer id);

    //http://ip:port/users/id
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    int delete(Integer id);

    //http://ip:port/users/add
    @RequestMapping(value = "add",method = RequestMethod.POST)
    int addUser(User user);

    //http://ip:port/users/update
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    int updateUser(User user);

}