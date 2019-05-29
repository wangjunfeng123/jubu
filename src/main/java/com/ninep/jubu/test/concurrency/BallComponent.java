package com.ninep.jubu.test.concurrency;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2019/5/29
 */
public class BallComponent extends JPanel {
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGH = 350;

    private List<Ball> balls = new ArrayList();

    public void add(Ball ball) {
        balls.add(ball);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        for (Ball ball : balls) {
            g.fill(ball.getShape());
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGH);
    }

}