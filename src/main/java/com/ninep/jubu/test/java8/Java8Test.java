package com.ninep.jubu.test.java8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc java8 test
 * @since 2019/3/9
 */
public class Java8Test {

    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "4");
        list.stream().filter((String i) -> !i.isEmpty()).collect(Collectors.toList());
        System.out.println(list);
    }

}