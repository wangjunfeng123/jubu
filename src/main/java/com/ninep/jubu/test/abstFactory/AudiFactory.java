package com.ninep.jubu.test.abstFactory;

import com.ninep.jubu.test.factory.Audi;
import com.ninep.jubu.test.factory.Car;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/11/4
 */
public class AudiFactory extends AbstFactory {

    @Override
    protected Car getCar() {
        return new Audi();
    }
}