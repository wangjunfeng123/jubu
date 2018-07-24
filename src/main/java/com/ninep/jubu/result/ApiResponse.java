package com.ninep.jubu.result;


import com.ninep.jubu.helper.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc response tools
 * @since 2018-05-30
 */
public class ApiResponse {

    /**
     * 返回成功消息
     * @param message the message
     * @return map
     * @since 2017.03.27
     */
    public static Map<String, Object> createSuccessMsgResult(String message) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("status", 1);
        ret.put("message", message);
        return ret;
    }

    /**
     * 返回成功默认消息
     * @return map
     * @since 2017.03.27
     */
    public static Map<String, Object> createSuccessMsgResult() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("status", 1);
        ret.put("message", "isSuccess");
        return ret;
    }

    public static Map<String, Object> createSuccessResult(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", data);
        result.put("status", 1);
        return result;
    }

    /**
     * 前后端的交互规范:分页返回
     * @param page  页面信息
     * @param items
     * @return
     */
    public static Map<String, Object> createSuccessWithPage(Page page, Object items) {
        Map<String, Object> pageMap =new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotalPageCount());
        data.put("totalCount", page.getTotalCount());
        data.put("pageSize", page.getPageSize());
        data.put("pageNo", page.getPageNo());
        data.put("items", items);

        pageMap.put("data", data);
        pageMap.put("status", 1);
        return pageMap;
    }

    public static Map<String, Object> createSuccessWithPage(Page page, Object items,Map<String ,Object> extend) {
        Map<String, Object> pageMap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotalPageCount());
        data.put("totalCount", page.getTotalCount());
        data.put("pageSize", page.getPageSize());
        data.put("pageNo", page.getPageNo());
        data.put("items", items);
        data.putAll(extend);

        pageMap.put("data", data);
        pageMap.put("status", 1);
        return pageMap;
    }

    /**
     * 返回成功消息和数据
     *
     * @param data    the data
     * @param message the message
     * @return map
     * @since 2017.03.27
     */
    public static Map<String, Object> createSuccessResult(Object data, String message) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("status", 1);
        ret.put("data", data);
        ret.put("message", message);
        return ret;
    }


    /**
     * 前后端的交互规范:错误返回
     *
     * @param message the message
     * @return map
     * @since 2017.06.21
     */
    public static Map<String, Object> createFailMsgResult(String message) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("status", 0);
        ret.put("message", message);
        ret.put("error", new ApiErrorResult(message, ErrorType.COMMON_ERROR));
        return ret;
    }

    public static Map<String, Object> createFailMsgResult(ApiErrorResult errorResult) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("status", 0);
        ret.put("message", errorResult.getMessage());
        ret.put("error", errorResult);
        return ret;
    }

    /**
     * 带有错误码 指定错误码
     *
     * @param errorType the error type
     * @param msg       the msg
     * @return the map
     * @since 2017.06.21
     */
    public static Map<String, Object> createFailMsgResult(ErrorType errorType, String msg) {
        Map<String, Object> result = new HashMap<>();
        ApiErrorResult error = new ApiErrorResult(msg, errorType);
        result.put("error", error);
        result.put("status", 0);
        result.put("message", msg);
        return result;
    }

    /**
     * 带有错误码 指定错误码
     *
     * @param obj the error type
     * @return the map
     * @since 2017.06.21
     */
    public static Map<String, Object> createFailMsgResult(Object obj) {
        if (obj instanceof String) {
            return createFailMsgResult(obj.toString());
        }
        Map<String, Object> result = new HashMap<>();
        ApiErrorResult error = new ApiErrorResult(ErrorType.COMMON_ERROR.getName(), ErrorType.COMMON_ERROR);
        if (obj instanceof ErrorType) {
            ErrorType errorType = (ErrorType) obj;
            error = new ApiErrorResult(errorType.getName(), errorType);
        }
        result.put("error", error);
        result.put("status", 0);
        result.put("message", error.getMessage());
        return result;
    }


    /**
     * 返回失败数据 默认错误码: 600
     *
     * @param data the data
     * @return map
     * @since 2017.03.27
     */
    public static Map<String, Object> createFailResult(Object data) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("status", 0);
        ret.put("data", data);
        ret.put("error", new ApiErrorResult(ErrorType.COMMON_ERROR.getName(), ErrorType.COMMON_ERROR));
        return ret;
    }

    public static Map<String, Object> createFailResult(Object data, ErrorType errorType) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("status", 0);
        ret.put("data", data);
        ret.put("error", new ApiErrorResult(errorType.getName(), errorType));
        return ret;
    }

    /**
     * 返回失败消息和数据 默认错误码: 600
     *
     * @param data    the data
     * @param message the message
     * @return map
     * @since 2017.03.27
     */
    public static Map<String, Object> createFailResult(Object data, String message) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("status", 0);
        ret.put("message", message);
        ret.put("data", data);
        ret.put("error", new ApiErrorResult(message, ErrorType.COMMON_ERROR));
        return ret;
    }

    /**
     * 带有错误码
     *
     * @param data      the data
     * @param message   the message
     * @param errorType the error type
     * @return the map
     * @since 2017.06.21
     */
    public static Map<String, Object> createFailResult(Object data, String message, ErrorType errorType) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("status", 0);
        ret.put("message", message);
        ret.put("data", data);
        ret.put("error", new ApiErrorResult(message, errorType));
        return ret;
    }

}