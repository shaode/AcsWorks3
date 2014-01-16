package com.suc.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.suc.web.entity.MOttype;
import com.suc.web.entity.MWorkrecord;
import com.suc.web.jpa.service.MOttpeService;
import com.suc.web.jpa.service.MUserService;
import com.suc.web.jpa.service.MWorkrecordService;
import com.suc.web.utils.DataGridModel;

@Controller
@RequestMapping("/workrecord")
public class WorkRecordController {
    private static final Logger logger = LoggerFactory.getLogger(WorkRecordController.class);
    @Autowired
    private MWorkrecordService  workrecordService;
    @Autowired
    private MUserService        userService;
    @Autowired
    private MOttpeService       ottypeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {
        logger.debug("workrecord.list");
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("ottypeList", ottypeService.findAll());
        return "workrecord/list";
    }

    @RequestMapping(value = "/queryAllOttpe")
    @ResponseBody
    public List<MOttype> queryAll() throws Exception {
        logger.debug("WorkRecordController.queryAllOttpe");
        return ottypeService.findAll();
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryList(DataGridModel dgm, MWorkrecord workrecord) throws Exception {
        logger.debug("dgm=" + dgm);
        logger.debug("workrecord=" + workrecord);
        return workrecordService.getPageList(dgm, workrecord);
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addOrUpdate(MWorkrecord workrecord) throws Exception {
        logger.debug("addOrUpdate,workrecord=" + workrecord);
        Map<String, String> map = new HashMap<String, String>();
        try {
            workrecordService.createOrUpdate(workrecord);
            map.put("mes", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "操作失败");
            throw e;
        }
        return map;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(@RequestParam("id") List<Long> ids) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        try {
            workrecordService.delete(ids);
            map.put("mes", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "删除失败");
            throw e;
        }
        return map;
    }

    @RequestMapping(value = "/popWindow", method = RequestMethod.GET)
    public String popWindow() throws Exception {
        logger.debug("workrecord.popWindow");
        return "workrecord/popWindow";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
