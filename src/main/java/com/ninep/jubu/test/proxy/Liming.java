package com.ninep.jubu.test.proxy;

/**
 * @author wangjunfeng
 * @version 1.0
 * @since 2018/10/25
 */
public class Liming implements Person {

    private String name = "liming";
    private String sex = "男";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public void findLove() {
        System.out.println("____________________");
        System.out.println("我是"+name+"；性别"+sex);
        System.out.println("寻找真爱");
        System.out.println("____________________");
    }

}