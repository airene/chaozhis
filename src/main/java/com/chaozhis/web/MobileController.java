package com.chaozhis.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/mobile")
public class MobileController {

    @RequestMapping(method = {RequestMethod.GET})
    public String index() {

        return "mobile/index";
    }

}
