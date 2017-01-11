package com.chaozhis.web;

import com.chaozhis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/mobile")
public class MobileController {
    @Autowired
    private BaseService baseService;

    @RequestMapping(method = {RequestMethod.GET})
    public String index(Model model) {

        return "mobile/index";
    }

}
