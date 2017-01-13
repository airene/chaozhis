package com.chaozhis.web;

import com.chaozhis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页
 */
@Controller
public class IndexController {

    @Autowired
    private BaseService baseService;

    // fangying
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index() {
        return "index";
    }

    // fangying
    @RequestMapping(value = "/u/usercenter", method = {RequestMethod.GET})
    public String a() {
        return "index";
    }
}
