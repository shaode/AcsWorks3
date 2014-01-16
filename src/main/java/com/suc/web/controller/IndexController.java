package com.suc.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    private static Logger logger = Logger.getLogger(IndexController.class);

    @RequestMapping
    public String getIndexPage(Model model) {
        logger.debug("======Into Home");
        model.addAttribute("message", "");
        return "login";
    }
    
    // @RequestMapping("/main")
    // public String getMainPage(Model model) {
    // logger.debug("======into Main");
    // return "main";
    // }
}
