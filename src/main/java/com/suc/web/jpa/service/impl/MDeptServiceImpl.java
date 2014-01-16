package com.suc.web.jpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.suc.web.entity.MDept;
import com.suc.web.jpa.dao.MDeptDao;
import com.suc.web.jpa.service.MDeptService;
import com.suc.web.utils.DataGridModel;

@Component
public class MDeptServiceImpl implements MDeptService {
    private static Logger logger = Logger.getLogger(MDeptServiceImpl.class);
    @Autowired
    private MDeptDao      deptDao;

    @Override
    @Transactional(readOnly = false)
    public void createOrUpdate(MDept dept) {
        deptDao.createOrUpdate(dept);
        logger.debug(dept.getName() + ",保存成功！");
    }

    @Override
    @Transactional
    public void delete(MDept dept) throws Exception {
        deptDao.delete(dept);
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) throws Exception {
        deptDao.delete(ids);
    }

    @Override
    public MDept findById(long id) throws Exception {
        return deptDao.findById(id);
    }

    @Override
    public MDept findByCode(String code) throws Exception {
        return deptDao.findByCode(code);
    }

    @Override
    public MDept findByName(String name) throws Exception {
        return deptDao.findByName(name);
    }

    @Override
    public List<MDept> findAll() throws Exception {
        return deptDao.findAll();
    }

    @Override
    public Map<String, Object> getPageList(DataGridModel dgm, MDept dept) throws Exception {
        return deptDao.getPageList(dgm, dept);
    }

    @Override
    public Map<String, Object> getAllJson() throws Exception {
        Map<String, Object> result = new HashMap<String, Object>(2);
        List<MDept> list = findAll();
        result.put("total", list == null ? 0 : list.size());
        result.put("rows", list);
        return result;
    }
}
