package com.suc.web.jpa.service;

import java.util.List;
import java.util.Map;
import com.suc.web.entity.MDept;
import com.suc.web.utils.DataGridModel;

public interface MDeptService {
    MDept findById(long id) throws Exception;

    MDept findByCode(String code) throws Exception;

    MDept findByName(String name) throws Exception;

    List<MDept> findAll() throws Exception;

    void createOrUpdate(MDept dept) throws Exception;

    void delete(MDept dept) throws Exception;

    void delete(List<Long> ids) throws Exception;

    Map<String, Object> getAllJson() throws Exception;

    Map<String, Object> getPageList(DataGridModel dgm, MDept dept) throws Exception;
}
