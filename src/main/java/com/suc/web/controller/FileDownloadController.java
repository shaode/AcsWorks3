package com.suc.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/download")
public class FileDownloadController {
    private static final Logger logger = LoggerFactory.getLogger(FileDownloadController.class);

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) throws Exception {
        logger.debug("upload.main");
        // model.addAttribute("deptList", deptService.findAll());
        return "upload/main";
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public static void download(HttpServletRequest request, HttpServletResponse response, String fileName) throws Exception {
    }
}
