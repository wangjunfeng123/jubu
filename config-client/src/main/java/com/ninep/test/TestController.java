package com.ninep.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc test controller.
 * @since 2019/9/2
 */
@RestController
public class TestController {

    @GetMapping("hello")
    public String hello() {
        return "hello world";
    }

}