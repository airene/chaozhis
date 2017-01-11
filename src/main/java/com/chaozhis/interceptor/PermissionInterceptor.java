package com.chaozhis.interceptor;

import com.chaozhis.dto.UserDTO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器 ：是否登录
 *
 * @author airene | 2016-02-01
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    private final UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        String url = urlPathHelper.getLookupPathForRequest(request);
        int flag = url.indexOf("/adm/");
        if (user == null && flag != -1) {
            ModelAndView mav = new ModelAndView("redirect:/user/login");
            mav.addObject("error", "nologin");
            throw new ModelAndViewDefiningException(mav);
        }
        return true;
    }

}
