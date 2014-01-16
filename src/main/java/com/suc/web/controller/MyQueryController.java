package com.suc.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.suc.web.entity.MWorkrecord;
import com.suc.web.entity.common.QueryWorkrecordBean;
import com.suc.web.jpa.service.MOttpeService;
import com.suc.web.jpa.service.MUserService;
import com.suc.web.jpa.service.MWorkrecordService;

@Controller
@RequestMapping("/myquery")
public class MyQueryController {
    private static final Logger logger = LoggerFactory.getLogger(MyQueryController.class);
    @Autowired
    private MWorkrecordService  workrecordService;
    @Autowired
    private MUserService        userService;
    @Autowired
    private MOttpeService       ottypeService;

    @RequestMapping(value = "/workrecord", method = RequestMethod.GET)
    public String myquery(Model model) throws Exception {
        logger.debug("workrecord.myquery");
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("ottypeList", ottypeService.findAll());
        return "myquery/workrecord";
    }

    @RequestMapping(value = "/workrecordByDate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> workrecordByDate(QueryWorkrecordBean qwb) throws Exception {
        logger.debug("QueryWorkrecordBean=" + qwb);
        return workrecordService.findByDate(qwb.getStartDate(), qwb.getEndDate());
    }

    @RequestMapping(value = "/workrecordByDate2", method = RequestMethod.POST)
    @ResponseBody
    public List<MWorkrecord> workrecordByDate2(QueryWorkrecordBean qwb) throws Exception {
        logger.debug("QueryWorkrecordBean=" + qwb);
        return workrecordService.findByDate2(qwb.getStartDate(), qwb.getEndDate());
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
