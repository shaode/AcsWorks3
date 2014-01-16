package com.suc.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.suc.web.entity.MUser;
import com.suc.web.jpa.service.MDeptService;
import com.suc.web.jpa.service.MUserService;
import com.suc.web.utils.DataGridModel;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private MDeptService        deptService;
    @Autowired
    private MUserService        userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {
        logger.debug("user.list");
        model.addAttribute("deptList", deptService.findAll());
        return "user/list";
    }

    @RequestMapping(value = "/queryAll")
    @ResponseBody
    public List<MUser> queryAll() throws Exception {
        logger.debug("UserController.queryAll");
        return userService.findAll();
    }
    
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryList(DataGridModel dgm, MUser user) throws Exception {
        // spring太给力了，可以自动装配两个对象 会自动的装返回的Map转换成JSON对象
        logger.debug("dgm=" + dgm);
        logger.debug("user=" + user);
        return userService.getPageList(dgm, user);
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addOrUpdate(MUser user) throws Exception {
        logger.debug("addOrUpdate,user=" + user);
        Map<String, String> map = new HashMap<String, String>();
        try {
            userService.createOrUpdate(user);
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
            userService.delete(ids);
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
        logger.debug("user.popWindow");
        return "user/popWindow";
    }
}
