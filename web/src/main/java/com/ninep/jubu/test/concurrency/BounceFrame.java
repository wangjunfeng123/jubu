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
public class BounceFrame extends JFrame {
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


    public void addBall() {
        Ball ball = new Ball();
        comp.add(ball);

        Runnable task = () -> {
            try {
                for (int i = 1; i < STEPS; i++) {
                    ball.move(comp.getBounds());
                    comp.paint(comp.getGraphics());
                    Thread.sleep(5);
                    System.out.println("current thread status: " + Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    boolean interrupted = Thread.interrupted();
                    System.out.println("current thread status: " + Thread.currentThread().isInterrupted());
                }
            }catch (InterruptedException e) {
                System.out.println("thread interrupted");
                boolean interrupted = Thread.currentThread().isInterrupted();
                System.out.println("current thread status: " + interrupted);
                Thread.currentThread().interrupt();
                System.out.println("current thread status: " + interrupted);
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

}
