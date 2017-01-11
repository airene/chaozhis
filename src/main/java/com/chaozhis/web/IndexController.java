package com.chaozhis.web;

import com.chaozhis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 默认首页
 *
 * @author airene | 2017-01-11
 */
@Controller
public class IndexController {

    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.HEAD})
    public String index(Model model) {

        return "index";
    }

}
