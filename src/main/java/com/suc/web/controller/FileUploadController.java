package com.suc.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) throws Exception {
        logger.debug("upload.main");
        // model.addAttribute("deptList", deptService.findAll());
        return "upload/main";
    }

    @RequestMapping(value = "excel", method = RequestMethod.POST)
    public String upload(HttpServletRequest request) throws IllegalStateException, IOException {
        logger.debug("request" + request.toString());
        // 设置上下方文
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 检查form是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 由CommonsMultipartFile继承而来,拥有上面的方法.
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String fileName = "demoUpload" + file.getOriginalFilename();
                    //String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
                    String path = "/tmp/" + fileName;
                    File localFile = new File(path);
                    file.transferTo(localFile);
                }
            }
        }
        return "dataSuccess";
    }
}
