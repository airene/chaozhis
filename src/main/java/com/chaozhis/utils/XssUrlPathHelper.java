package com.chaozhis.utils;

import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * XSS 在pathvariable时使用的类
 *
 * @author airene | 2016-04-19
 */
public class XssUrlPathHelper extends UrlPathHelper {
    @Override
    public Map<String, String> decodePathVariables(HttpServletRequest request, Map<String, String> vars) {
        Map<String, String> result = super.decodePathVariables(request, vars);
        if (!CollectionUtils.isEmpty(result)) {
            for (String key : result.keySet()) {
                result.put(key, cleanXSS(result.get(key)));
            }
        }
        return result;
    }

    @Override
    public MultiValueMap<String, String> decodeMatrixVariables(HttpServletRequest request, MultiValueMap<String, String> vars) {
        MultiValueMap<String, String> mvm = super.decodeMatrixVariables(request, vars);
        if (!CollectionUtils.isEmpty(mvm)) {
            for (String key : mvm.keySet()) {
                List<String> value = mvm.get(key);
                for (int i = 0; i < value.size(); i++) {
                    value.set(i, cleanXSS(value.get(i)));
                }
            }
        }
        return mvm;
    }

    private String cleanXSS(String value) {
        return HtmlUtils.htmlEscape(value);
    }
}
