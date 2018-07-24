package com.ninep.jubu.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ninep.jubu.cache.JubuThreadCache;
import com.ninep.jubu.constant.JubuConstant;
import com.ninep.jubu.domain.User;
import com.ninep.jubu.enums.Gender;
import com.ninep.jubu.enums.PositionType;
import com.ninep.jubu.enums.UserWorkStatus;
import com.ninep.jubu.helper.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 工具.
 * @since 2018/07/02
 */
public class FarmUtils {

    private final static Logger logger = LoggerFactory.getLogger(FarmUtils.class);

    /**
     * 非法的参数，赋予默认值
     */
    public static Page getPage(Integer pageNo, Integer pageSize) {
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        return new Page(pageNo, pageSize);
    }

    /**
     * 获取32位数据签名
     */
    public static String getMd5(byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     * check uuid  null&36
     */
    public static boolean checkUUID(String objectId) {
        return (StringUtils.isNotEmpty(objectId) && objectId.length() == 36);
    }

    /**
     * check mobile pattern
     */
    public static boolean isLegalMobile(String mobile) {
        return (Pattern.matches(Pattern.compile(JubuConstant.MOBILE_PATTERN).pattern(), mobile) && StringUtils.length(mobile) <= 20);
    }

    /**
     * check email
     */
    public static boolean isLegalEmail(String email) {
        return (Pattern.matches(Pattern.compile(JubuConstant.EMAIL_PATTERN).pattern(), email));
    }

    /**
     * check Legal tel
     */
    public static boolean isLegalTel(String phone) {
        return (Pattern.matches(Pattern.compile(JubuConstant.TEL_PATTERN).pattern(), phone));
    }

    public static boolean isValidExtension(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        String regEx = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 正则匹配
     *
     * @param originStr the origin str
     * @param matchStr  the match str
     * @return boolean
     * @since 2017.03.21
     */
    public static boolean regexMatch(String originStr, String matchStr) {
        if (StringUtils.isEmpty(originStr) && StringUtils.isNotEmpty(matchStr)) {
            return false;
        }

        if (StringUtils.isEmpty(matchStr)) {
            return false;
        }

        return originStr.matches(matchStr);
    }

    public static String getLocalIP() {
        InetAddress ia;
        try {
            ia = InetAddress.getLocalHost();
            return ia.getHostAddress();
        } catch (Exception e) {
            return org.apache.commons.lang3.StringUtils.EMPTY;
        }
    }


    public static void writeJsonResponse(Object o, HttpServletResponse response) {
        try {
            response.setContentType("application/json;charset=utf-8");
            response.getOutputStream().write(JSON.toJSONString(o).getBytes());
        } catch (IOException e) {
            logger.error("writeJsonResponse error object:{}", ObjectUtil.toString(o), e);
        }
    }

    public static void writeHtmlResponse(String o, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=utf-8");
            response.getOutputStream().write(o.getBytes());
        } catch (IOException e) {
            logger.error("writeJsonResponse error object:{}", ObjectUtil.toString(o), e);
        }
    }

    public static <T> List<T> calPage(List<T> target, Page page) {
        if (CollectionUtils.isEmpty(target)) {
            page.setTotalCount(0);
            return Lists.newArrayList();
        }
        int beginIndex = (page.getPageNo() - 1) * page.getPageSize();
        if (beginIndex > target.size()) {
            beginIndex = 0;
            page.setPageNo(1);
        }
        int endIndex = page.getPageNo() * page.getPageSize();
        if (endIndex > target.size()) {
            endIndex = target.size();
        }

        List<T> result = target.subList(beginIndex, endIndex);

        int size = target.size();
        page.setTotalCount(size);
        return result;
    }

    public static User handleDebug() {
        User user = new User();
        user.setUserId("9f5da5c7-6703-11e7-809d-9801a79f70e5");
        user.setUserName("系统自动");
        user.setPhoneNum("15650735910");
        user.setDepartment(PositionType.RD.getIndex());
        user.setEmail("admin@dashju.com");
        user.setStatus(UserWorkStatus.IN_WORK.getIndex());
        user.setGender(Gender.BOY.getIndex());

        JubuThreadCache.setUser(user);
        return user;
    }
}