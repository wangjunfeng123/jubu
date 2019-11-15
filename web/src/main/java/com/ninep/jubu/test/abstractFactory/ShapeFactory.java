package com.ninep.jubu.test.abstractFactory;

/**
 * @author wangjunfeng.
 * @version 1.0
 * @desc 写点注释吧.
 * @since 2019/11/15
 */
public class ShapeFactory extends AbstractFactory {

    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equals("Circle")) {
            return new Circle();
        }
        if (shapeType.equals("Rectangle")) {
            return new Rectangle();
        }
        if (shapeType.equals("Square")) {
            return new Square();
        }
        return null;
    }

}