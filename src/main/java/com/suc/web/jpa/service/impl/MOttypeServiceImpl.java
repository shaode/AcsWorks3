package com.suc.web.jpa.service.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.suc.web.entity.MOttype;
import com.suc.web.jpa.dao.MOttypeDao;
import com.suc.web.jpa.service.MOttpeService;

@Component
public class MOttypeServiceImpl implements MOttpeService {
    private static Logger logger = Logger.getLogger(MOttypeServiceImpl.class);
    @Autowired
    private MOttypeDao    ottypeDao;

    @Override
    public MOttype findById(long id) throws Exception {
        return ottypeDao.findById(id);
    }

    @Override
    public MOttype findByName(String name) throws Exception {
        logger.debug("name=" + name);
        return ottypeDao.findByName(name);
    }

    @Override
    public List<MOttype> findAll() throws Exception {
        return ottypeDao.findAll();
    }
}
