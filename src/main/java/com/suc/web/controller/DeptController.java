package com.suc.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.suc.web.entity.MDept;
import com.suc.web.jpa.service.MDeptService;
import com.suc.web.utils.DataGridModel;

@Controller
@RequestMapping("/dept")
public class DeptController {
    private static final Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private MDeptService        deptService;

    @RequestMapping(value = "/list")
    public String list(Model model) {
        return "dept/list";
    }

    @RequestMapping(value = "/queryAll")
    @ResponseBody
    public List<MDept> queryAll() throws Exception {
        log.debug("DeptController.queryAll");
        return deptService.findAll();
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryList(@RequestBody DeptMap deptMap) throws Exception {
        System.out.println("dgm=" + deptMap.getDgm());
        System.out.println("dept=" + deptMap.getDept());
        return deptService.getPageList(deptMap.getDgm(), deptMap.getDept());
    }

    @RequestMapping(value = "/createOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> update(@RequestBody MDept dept) throws Exception {
        // public Map<String, String> addOrUpdate( @RequestBody String json) throws Exception {
        log.debug(dept.toString());
        Map<String, String> map = new HashMap<String, String>();
        try {
            deptService.createOrUpdate(dept);
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
        log.debug("dept ids=" + ids.toString());
        Map<String, String> map = new HashMap<String, String>();
        try {
            deptService.delete(ids);
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
        return "dept/popWindow";
    }
}

class DeptMap {
    private DataGridModel dgm;
    private MDept         dept;

    public DeptMap() {
    }

    public DataGridModel getDgm() {
        return dgm;
    }

    public void setDgm(DataGridModel dgm) {
        this.dgm = dgm;
    }

    public MDept getDept() {
        return dept;
    }

    public void setDept(MDept dept) {
        this.dept = dept;
    };
}
