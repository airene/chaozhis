package com.chaozhis.web;

import com.chaozhis.AppConstants;
import com.chaozhis.service.BaseService;
import com.chaozhis.utils.CookieUtil;
import com.chaozhis.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 首页
 */
@Controller
public class IndexController {

    @Autowired
    private BaseService baseService;

    // fangying
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(HttpServletRequest request, HttpServletResponse response) {
        String rid = WebUtils.getParamValue(request, "rid");
        if (rid != null && rid.length() != 0) {
            CookieUtil.addCookie(response, "rid", rid, AppConstants.HALF_DAY);
        }
        return "index";
    }

}
