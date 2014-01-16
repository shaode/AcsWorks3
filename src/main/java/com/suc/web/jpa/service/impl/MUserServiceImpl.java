package com.suc.web.jpa.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.suc.web.entity.MUser;
import com.suc.web.jpa.dao.MUserDao;
import com.suc.web.jpa.service.MUserService;
import com.suc.web.utils.DataGridModel;

@Component
public class MUserServiceImpl implements MUserService {
    private static Logger logger = Logger.getLogger(MUserServiceImpl.class);
    @Autowired
    private MUserDao      userDao;

    @Transactional(readOnly = false)
    public void createOrUpdate(MUser user) {
        userDao.createOrUpdate(user);
        logger.debug(user.getName() + ",保存成功！");
    }

    @Override
    @Transactional
    public void delete(MUser user) throws Exception {
        userDao.delete(user);
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) throws Exception {
        userDao.delete(ids);
    }

    @Override
    public MUser findById(long id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public MUser findByName(String name){
        return userDao.findByName(name);
    }
    
    @Override
    public MUser findByMail(String mail) throws Exception {
        return userDao.findByMail(mail);
    }

    @Override
    public List<MUser> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public Map<String, Object> getPageList(DataGridModel dgm, MUser user) throws Exception {
        return userDao.getPageList(dgm, user);
    }
}
