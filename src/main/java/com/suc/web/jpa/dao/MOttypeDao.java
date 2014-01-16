package com.suc.web.jpa.dao;

import com.suc.web.entity.MOttype;

public interface MOttypeDao extends BaseDao<MOttype> {
    public abstract MOttype findByName(String name);
}
