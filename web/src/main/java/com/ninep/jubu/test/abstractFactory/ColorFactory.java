package com.ninep.jubu.test.abstractFactory;

/**
 * @author wangjunfeng.
 * @version 1.0
 * @desc 写点注释吧.
 * @since 2019/11/15
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Color getColor(String colorType) {
        if (colorType == null|| colorType.equals("")) {
            return null;
        }
        if (colorType.equals("RED")) {
            return new Red();
        }
        if (colorType.equals("Green")) {
            return new Green();
        }
        if (colorType.equals("Blue")) {
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }

}