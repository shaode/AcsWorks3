package com.suc.web.jpa.service;

import java.util.List;
import java.util.Map;
import com.suc.web.entity.MUser;
import com.suc.web.utils.DataGridModel;

public interface MUserService {
    MUser findById(long id) throws Exception;

    MUser findByName(String name);

    MUser findByMail(String mail) throws Exception;

    List<MUser> findAll() throws Exception;

    void createOrUpdate(MUser user) throws Exception;

    void delete(MUser user) throws Exception;

    void delete(List<Long> ids) throws Exception;

    Map<String, Object> getPageList(DataGridModel dgm, MUser user) throws Exception;
}
