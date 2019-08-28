package com.ninep.jubu.test.abstFactory;

import com.ninep.jubu.test.factory.Audi;
import com.ninep.jubu.test.factory.Benz;
import com.ninep.jubu.test.factory.Car;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/11/4
 */
public abstract class AbstFactory {

    //
    protected abstract Car getCar();

    //
    public Car getCar(String name) {
        if ("Benz".equalsIgnoreCase(name)) {
            return new DefaultFactory().getCar();
        } else if ("Audi".equalsIgnoreCase(name)) {
            return new Audi();
        } else {
            return null;
        }
    }

}