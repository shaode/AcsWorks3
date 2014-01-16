package com.suc.web.jpa.dao;

import java.util.List;
import java.util.Map;
import com.suc.web.entity.MDept;
import com.suc.web.utils.DataGridModel;

public interface MDeptDao extends BaseDao<MDept> {
    public abstract MDept findByCode(String code);

    public abstract MDept findByName(String name);
    
    public abstract void delete(List<Long> ids) throws Exception;

    public abstract Map<String, Object> getPageList(DataGridModel dgm, MDept dept) throws Exception;
}
