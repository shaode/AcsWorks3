package com.suc.web.jpa.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.suc.web.entity.MWorkrecord;
import com.suc.web.utils.DataGridModel;

public interface MWorkrecordService {
    MWorkrecord findById(long id) throws Exception;

    List<MWorkrecord> findByCustname(String name);

    List<MWorkrecord> findByCustproject(String project);

    List<MWorkrecord> findAll() throws Exception;

    void createOrUpdate(MWorkrecord workrecord) throws Exception;

    void delete(MWorkrecord workrecord) throws Exception;

    void delete(List<Long> ids) throws Exception;

    Map<String, Object> getPageList(DataGridModel dgm, MWorkrecord workrecord) throws Exception;

    Map<String, Object> findByDate(Date startDate, Date endDate);
    List<MWorkrecord> findByDate2(Date startDate, Date endDate);
}
