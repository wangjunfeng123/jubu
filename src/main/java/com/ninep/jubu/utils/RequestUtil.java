package com.ninep.jubu.utils;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

public class RequestUtil {
	// 获取上传的文件
	public static MultipartFile getTheUploadFile(HttpServletRequest request) {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileByName = multiRequest.getFileMap();
        if (MapUtils.isNotEmpty(fileByName) && fileByName.size() == 1) {
            for (MultipartFile multipartFile : fileByName.values()) {
                return multipartFile;
            }
        }
		return null;
	}

	//是否有上传文件
	public static boolean haveFileUpload(HttpServletRequest request) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		return multipartResolver.isMultipart(request);
	}

	//获取请求参数
	public static String getRequestParameters(HttpServletRequest request) {
		StringBuilder parametersBuilder = new StringBuilder();
		@SuppressWarnings("unchecked")
		Map<String, String[]> parameterMap = request.getParameterMap();
		for (Iterator<String> iter = parameterMap.keySet().iterator(); iter.hasNext();) {
			String key = iter.next();
			if (key != null && !"".equals(key.trim())) {
				parametersBuilder.append(key).append("=").append(request.getParameter(key));
			}
			if (iter.hasNext()) {
				parametersBuilder.append("&");
			}
		}
		return parametersBuilder.toString();
	}

	//获取请求url
	public static String getRequestUrlWithParameter(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String parameters = getRequestParameters(request);
		if (StringUtils.isNotBlank(parameters)) {
			url.append("?").append(parameters);
		}
		return url.toString();
	}
}