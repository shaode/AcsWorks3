package com.suc.web.jpa.dao;

import java.util.List;
import java.util.Map;
import com.suc.web.entity.MUser;
import com.suc.web.utils.DataGridModel;

public interface MUserDao extends BaseDao<MUser> {
    public abstract MUser findByName(String name);

    public abstract MUser findByMail(String mail);

    public abstract List<MUser> findByDeptId(long dept_id);
    
    public abstract void delete(List<Long> ids) throws Exception;

    public abstract Map<String, Object> getPageList(DataGridModel dgm, MUser user) throws Exception;
}
