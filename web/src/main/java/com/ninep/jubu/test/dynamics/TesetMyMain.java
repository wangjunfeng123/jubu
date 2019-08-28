package com.ninep.jubu.test.dynamics;

import com.ninep.jubu.test.proxy.Liming;
import com.ninep.jubu.test.proxy.Person;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/10/26
 */
public class TesetMyMain {

    public static void main(String[] args) {
        try {
            Person person = (Person)new MyMeipoProxy().getInstance(new Liming());
            person.findLove();
        } catch (NoSuchMethodException e) {

        } catch (IOException e) {

        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        } catch (ClassNotFoundException e) {

        }
    }

}