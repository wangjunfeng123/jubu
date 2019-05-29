package com.ninep.jubu.test.concurrency;

import javax.swing.*;
import java.awt.*;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2019/5/28
 */
public class Bounce {

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new BounceFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}