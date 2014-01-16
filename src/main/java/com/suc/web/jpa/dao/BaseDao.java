package com.suc.web.jpa.dao;

import java.util.List;
import javax.persistence.NoResultException;
import com.suc.web.entity.common.BaseEntity;

public interface BaseDao<T extends BaseEntity> {
    //
    public abstract void create(T t);

    public abstract T createOrUpdate(T t);

    public abstract void delete(T t);

    public abstract T findById(long id);

    public abstract List<T> findAll();

    public abstract List<T> find(String namedQuery, Object... parameters);

    public abstract T findOne(String namedQuery, Object... parameters) throws NoResultException;
}
