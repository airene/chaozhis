package com.chaozhis.utils;

import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 具有XSS过滤功能的上传视图解析
 *
 * @author airene | 2016-04-19
 */
public class XssCommonsMultipartResolver extends CommonsMultipartResolver {
    public XssCommonsMultipartResolver() {
    }

    @Override
    public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
        MultipartParsingResult parsingResult = parseRequest(request);
        return new DefaultMultipartHttpServletRequest(request, parsingResult.getMultipartFiles(),
                parsingResult.getMultipartParameters(), parsingResult.getMultipartParameterContentTypes()) {

            @Override
            public String getParameter(String name) {
                String value = super.getParameter(name);
                return value == null ? null : cleanXSS(value);
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values = super.getParameterValues(name);
                if (values == null)
                    return null;

                String[] result = new String[values.length];
                for (int i = 0; i < result.length; i++) {
                    result[i] = cleanXSS(values[i]);
                }

                return result;
            }

            @Override
            public String getHeader(String name) {
                String value = super.getHeader(name);
                return value == null ? null : cleanXSS(value);
            }

        };
    }

    private String cleanXSS(String value) {
        return HtmlUtils.htmlEscape(value);
    }
}
