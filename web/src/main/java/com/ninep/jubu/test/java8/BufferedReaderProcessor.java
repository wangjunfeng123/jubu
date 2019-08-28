package com.ninep.jubu.test.java8;

import java.io.BufferedReader;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2019/3/9
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

    String processor(BufferedReader bufferedReade);

}