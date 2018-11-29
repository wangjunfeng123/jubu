package com.ninep.jubu.test.serialize;

import java.io.*;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/11/29
 */
public class TestMain {

    public static void main(String[] args) {
//        deSerialize();
        //反序列化
        Person person = unDeSerialize();
        System.out.println(person);
    }

    public static void deSerialize() {
        Person person = new Person();
        person.setAge(18);
        person.setName("张三");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("person")));
            oos.writeObject(person);
            oos.close();
        } catch (IOException e) {
        }
    }

    private static Person unDeSerialize() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("person")));
            return (Person) ois.readObject();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        return null;
    }

}