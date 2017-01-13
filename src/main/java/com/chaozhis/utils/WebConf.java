package com.chaozhis.utils;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 读取文件类 fangying
 */
public class WebConf {

    private static Properties props = new Properties();
    private static String excelTemplateFolderPath;

    static {
        try {
            InputStream in = WebConf.class.getResourceAsStream("/appConfig.properties");
            // zhangjunjie 初始化excel模板文件夹路径
            URL excelResource = WebConf.class.getResource("/excel");
            if (excelResource != null) {
                excelTemplateFolderPath = excelResource.toURI().getPath();
            }
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        try {
            return props.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // zhangjunjie 获取excel模板文件夹路径
    public static String getExcelTemplateFolderPath() {
        // linux 和windows 的差异，URL 最后会多一个 "/"
        if (!excelTemplateFolderPath.contains("/excel/")) {
            excelTemplateFolderPath = excelTemplateFolderPath + "/";
        }
        return excelTemplateFolderPath;
    }

}