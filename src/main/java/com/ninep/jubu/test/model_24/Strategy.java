package com.ninep.jubu.test.model_24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 策略模式
 * @since 2018/11/13
 */
public class Strategy {

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // 比较器,无论中间的策略是什么，最终的比较结果都是0,-1,1三种结果，中间的策略各不相同，有可能比较年龄，薪水。。。。。。
    public static void main(String[] args) {
        List<Strategy> list = new ArrayList<>();
        list.add(new Strategy());

        Collections.sort(list, new Comparator<Strategy>() {
            @Override
            public int compare(Strategy o1, Strategy o2) {
                return o1.getAge() - o2.getAge();
            }
        });

    }

}