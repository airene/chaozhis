package com.chaozhis.web;

import com.chaozhis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页
 */
@Controller
public class PostController {

    @Autowired
    private BaseService baseService;

    // fangying
    @RequestMapping(value = "/u/post-add", method = {RequestMethod.GET})
    public String index(HttpServletRequest request) {
        return "post";
    }

}
