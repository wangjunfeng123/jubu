package com.ninep.jubu.test.abstFactory;

import com.ninep.jubu.test.factory.Car;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/11/4
 */
public class DefaultFactory extends AbstFactory {

    //默认情况下工厂生产奥迪车
    @Override
    protected Car getCar() {
        return new AudiFactory().getCar();
    }
}