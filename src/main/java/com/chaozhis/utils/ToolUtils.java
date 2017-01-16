package com.chaozhis.utils;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 四位随机数、六位随机数、产生订单号
 */

public class ToolUtils {

    private final static Logger logger = Logger.getLogger(ToolUtils.class);

    // get 20080921121212 date
    public static String generateBusinessNo() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String mDateTime = formatter.format(cal.getTime());
        return mDateTime + generateFiveRandom();
    }

    // four bit int number
    public static String generateFourRandom() {
        String fourRandom;
        int randomNum = (int) (Math.random() * 10000);
        fourRandom = "" + randomNum;
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++) {
                fourRandom = "0" + fourRandom;
            }
        } else {
            fourRandom = "" + randomNum;
        }
        return fourRandom;
    }

    // zhangjunjie 生成指定范围的随机数
    public static Integer generateRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    // six bit int number
    public static String generateSixRandom() {
        String sixRandom;
        int randomNum = (int) (Math.random() * 1000000);
        sixRandom = "" + randomNum;
        int randLength = sixRandom.length();
        if (randLength < 6) {
            for (int i = 1; i <= 6 - randLength; i++) {
                sixRandom = "0" + sixRandom;
            }
        } else {
            sixRandom = "" + randomNum;
        }
        return sixRandom;
    }

    public static String getFullURL(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder("http://");
        sb.append(request.getServerName());
        sb.append(request.getContextPath());
        sb.append(request.getServletPath());
        String temp = request.getQueryString();
        if (temp != null) {
            sb.append("?");
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * listToMap(list, "activity_id");
     */
    public static <T> Map<Object, List<T>> listToMap(List<T> list, String fieldName) {
        Map<Object, List<T>> map = new LinkedHashMap<>();
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        for (T obj : list) {
            Method method;
            try {
                method = obj.getClass().getMethod(methodName);
                if (method != null) {
                    Object keyValue = method.invoke(obj);
                    List<T> newList = map.get(keyValue);
                    if (newList == null) {
                        newList = new ArrayList<>();
                    }
                    newList.add(obj);
                    map.put(keyValue, newList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return map;
    }

    public static <T> Map<String, T> listToMapDTO(List<T> list, String fieldName) {
        Map<String, T> map = new LinkedHashMap<>();
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        for (T obj : list) {
            Method method;
            try {
                method = obj.getClass().getMethod(methodName);
                if (method != null) {
                    Object keyValue = method.invoke(obj);

                    map.put(keyValue.toString(), obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return map;
    }

    public static <T> Map<String, Map<String, T>> listToMapMap(List<T> list, String fieldName, String field2Name) {
        Map<String, Map<String, T>> map = new LinkedHashMap<>();
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        String method2Name = "get" + field2Name.substring(0, 1).toUpperCase() + field2Name.substring(1);
        for (T obj : list) {
            Method method;
            Method method2;
            try {
                method = obj.getClass().getMethod(methodName);
                method2 = obj.getClass().getMethod(method2Name);
                if (method2 != null && method != null) {
                    Object keyValue = method.invoke(obj);
                    Object key2Value = method2.invoke(obj);
                    Map<String, T> mp = map.get(keyValue.toString());
                    if (mp == null) {
                        mp = new LinkedHashMap<>();
                    }
                    mp.put(key2Value.toString(), obj);
                    map.put(keyValue.toString(), mp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return map;
    }

    public static <T> Map<String, List<T>> listToMapStr(List<T> list, String fieldName) {
        Map<String, List<T>> map = new LinkedHashMap<>();
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        for (T obj : list) {
            Method method;
            try {
                method = obj.getClass().getMethod(methodName);
                if (method != null) {
                    Object keyValue = method.invoke(obj);
                    List<T> newList = map.get(keyValue.toString());
                    if (newList == null) {
                        newList = new ArrayList<>();
                    }
                    newList.add(obj);
                    map.put(keyValue.toString(), newList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return map;
    }

    // five bit int number
    private static String generateFiveRandom() {
        String sixRandom;
        int randomNum = (int) (Math.random() * 100000);
        sixRandom = "" + randomNum;
        int randLength = sixRandom.length();
        if (randLength < 5) {
            for (int i = 1; i <= 5 - randLength; i++) {
                sixRandom = "0" + sixRandom;
            }
        } else {
            sixRandom = "" + randomNum;
        }
        return sixRandom;
    }
}
