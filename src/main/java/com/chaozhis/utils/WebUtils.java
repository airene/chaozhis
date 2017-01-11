package com.chaozhis.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WebUtils {

    /**
     * 过滤特殊字符串
     */
    public static String dofilter(String str) {
        if (str == null) {
            str = "";
            return str;
        }
        String str_Result = "", str_OneStr;

        for (int z = 0; z < str.length(); z++) {
            str_OneStr = str.substring(z, z + 1);
            if (str_OneStr.matches("[\u4e00-\u9fa5]+") || str_OneStr.matches("[\\x00-\\x7F]+")) {
                str_Result = str_Result + str_OneStr;
            }
        }
        return str_Result;
    }

    // ip地址
    public static String getRemortIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = HtmlUtils.htmlEscape(request.getHeader("x-forwarded-for"));
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = HtmlUtils.htmlEscape(request.getHeader("Proxy-Client-IP"));
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = HtmlUtils.htmlEscape(request.getHeader("WL-Proxy-Client-IP"));
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = HtmlUtils.htmlEscape(request.getHeader("http_client_ip"));
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = HtmlUtils.htmlEscape(request.getHeader("HTTP_X_FORWARDED_FOR"));
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.contains(",")) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }
        return ip;
    }

    // 获得session的值
    @SuppressWarnings("unchecked")
    public static <T> T getSessionValue(HttpServletRequest request, String key) {
        T result = null;
        HttpSession session = request.getSession();
        if (session != null) {
            result = (T) (session.getAttribute(key) == null ? "" : session.getAttribute(key));
        }
        return result;
    }

    //  获得服务器端验证码
    public static String getValiCodeOnServer(HttpServletRequest request) {
        Object valiCodeObj = request.getSession().getAttribute("valiCode");
        return valiCodeObj == null ? "" : valiCodeObj.toString();
    }

    // 获得参数的值 airene 去掉 utf-8 看看有没有乱码 增加XSS过滤
    public static String getParamValue(HttpServletRequest request, String paramName) {
        String temp = request.getParameter(paramName);
        if (temp == null) {
            return "";
        } else {
            try {
                temp = HtmlUtils.htmlEscape(temp.trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return temp;

    }

    // 删除session的值
    public static void removeSessionValue(HttpServletRequest request, String key) {
        request.getSession().setAttribute(key, null);
    }

    // 设置session的值
    public static void setSessionValue(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

}