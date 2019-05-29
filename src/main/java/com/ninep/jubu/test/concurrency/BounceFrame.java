package com.ninep.jubu.test.concurrency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2019/5/28
 */
public class BounceFrame extends JFrame{
    private BallComponent comp;
    public static final int STEPS = 2000;
    public static final int DELAY = 3;


    public BounceFrame() {
        setTitle("Bounce");
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", event -> addBall());
        addButton(buttonPanel, "Close", event -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }


    public void addBall () {
        try {
            Ball ball = new Ball();
            comp.add(ball);

            for (int i = 1; i < STEPS; i++) {
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }

        } catch (InterruptedException e) {

        }
    }

}
