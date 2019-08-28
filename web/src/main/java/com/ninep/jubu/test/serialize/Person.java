package com.ninep.jubu.test.serialize;

import java.io.Serializable;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc serializable 存储大小测试.
 * @since 2018/11/29
 */
public class Person implements Serializable {
    private static final long serialVersionUID = -4573864018880472625L;
    private transient String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}