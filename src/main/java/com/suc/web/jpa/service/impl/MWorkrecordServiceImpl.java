package com.suc.web.jpa.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.suc.web.entity.MWorkrecord;
import com.suc.web.jpa.dao.MWorkrecordDao;
import com.suc.web.jpa.service.MWorkrecordService;
import com.suc.web.utils.DataGridModel;

@Component
public class MWorkrecordServiceImpl implements MWorkrecordService {
    private static Logger  logger = Logger.getLogger(MWorkrecordServiceImpl.class);
    @Autowired
    private MWorkrecordDao workrecordDao;

    @Transactional(readOnly = false)
    public void createOrUpdate(MWorkrecord workrecord) {
        workrecordDao.createOrUpdate(workrecord);
        logger.debug(workrecord.toString() + ",保存成功！");
    }

    @Override
    @Transactional
    public void delete(MWorkrecord workrecord) throws Exception {
        workrecordDao.delete(workrecord);
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) throws Exception {
        workrecordDao.delete(ids);
    }

    @Override
    public MWorkrecord findById(long id) throws Exception {
        return workrecordDao.findById(id);
    }

    @Override
    public List<MWorkrecord> findByCustname(String name) {
        return workrecordDao.findByCustname(name);
    }

    @Override
    public List<MWorkrecord> findByCustproject(String project) {
        return workrecordDao.findByCustproject(project);
    }

    @Override
    public List<MWorkrecord> findAll() throws Exception {
        return workrecordDao.findAll();
    }

    @Override
    public Map<String, Object> getPageList(DataGridModel dgm, MWorkrecord workrecord) throws Exception {
        return workrecordDao.getPageList(dgm, workrecord);
    }

    @Override
    public Map<String, Object> findByDate(Date startDate, Date endDate) {
        return workrecordDao.findByDate(startDate, endDate);
    }

    @Override
    public List<MWorkrecord> findByDate2(Date startDate, Date endDate) {
        return workrecordDao.findByDate2(startDate, endDate);
    }
}
