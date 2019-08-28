package com.ninep.jubu.test.springcxf;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 测试.
 * @since 2019/1/1
 */
@XmlRootElement
public class User {
    private Integer id;
    private String name;

    public User() {}

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}