package com.suc.web.jpa.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.suc.web.entity.MWorkrecord;
import com.suc.web.utils.DataGridModel;

public interface MWorkrecordDao extends BaseDao<MWorkrecord> {
    public abstract List<MWorkrecord> findByCustname(String name);

    public abstract List<MWorkrecord> findByCustproject(String project);

    public abstract void delete(List<Long> ids) throws Exception;

    public abstract Map<String, Object> getPageList(DataGridModel dgm, MWorkrecord workrecord) throws Exception;

    public abstract Map<String, Object> findByDate(Date startDate, Date endDate);
    
    public abstract List<MWorkrecord> findByDate2(Date startDate, Date endDate);
}
