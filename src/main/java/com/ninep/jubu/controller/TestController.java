package com.ninep.jubu.controller;

import com.ninep.jubu.domain.User;
import com.ninep.jubu.result.ApiResponse;
import com.ninep.jubu.service.UserService;
import com.ninep.jubu.utils.XlsxToTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/06/26
 */
@RestController
@RequestMapping("test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    /**
     * 获取用户信息详情
     * @param id
     * @return json
     */
    @RequestMapping(value = "getDetail", method = RequestMethod.GET)
    public Map<String, Object> getDetail(String id) {
        try {

            User user = userService.getDetail(id);
            System.out.println(user);
            return ApiResponse.createSuccessResult(user);
        } catch (Exception e) {
            logger.error("获取详情错误",e);
            return ApiResponse.createFailResult("获取详情错误");
        }
    }

    /**
     * 测试redis
     * @return json
     */
    @PostMapping("/testRedis")
    public Map<String, Object> testRedis() {
        try {
            redisTemplate.opsForValue().set("sss", "lisi");
            redisTemplate.opsForHash().put("mm", "zhangsan", 29);
            redisTemplate.opsForList().range("list", 0, -1);
            redisTemplate.delete("sss");

            String ss = (String) redisTemplate.opsForValue().get("sss");
            System.out.println(ss);
            return ApiResponse.createSuccessMsgResult("redis test");
        } catch (Exception e) {
            return ApiResponse.createFailMsgResult("redis error");
        }
    }


    @PostMapping("testUpload")
    public Map<String, Object> uploadExcel(@RequestParam("file") MultipartFile multipartFile) {
        try {
            logger.info("TestController uploadExcel 上传excel文件并保存");
            if (multipartFile.isEmpty()) {
                return ApiResponse.createFailMsgResult("文件上传错误");
            }
            List<Map<String, String>> maps = XlsxToTable.readFileExcelMap(multipartFile);
            return ApiResponse.createSuccessMsgResult("保存成功");
        } catch (Exception e) {
            logger.error("TestController uploadExcel 上传excel文件并保存错误",e);
            return ApiResponse.createFailMsgResult("保存数据失败");
        }
    }

}