package com.konantech.spring.controller.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ApiController {

    @RequestMapping(value = "/docs", method = RequestMethod.GET)
    public String docs(ModelMap modelMap, HttpServletRequest request) throws Exception {
        return "api/index";
    }

}
