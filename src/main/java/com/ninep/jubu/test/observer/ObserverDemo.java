package com.ninep.jubu.test.observer;

import java.util.Observable;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 观察者模式
 * @since 2019/8/28
 */
public class ObserverDemo {


    public static void main(String[] args) {

        MyObservable observable = new MyObservable();


        observable.addObserver((o, value) -> System.out.println(value));

        observable.setChanged();
        observable.notifyObservers("Hello world");
    }

    public static class MyObservable extends Observable{
        public void setChanged() {
            super.setChanged();
        }
    }

}