package com.suc.web.jpa.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.suc.web.entity.MOttype;
import com.suc.web.jpa.dao.MOttypeDao;

@Repository
public class MOttypeDaoImpl extends BaseDAOImpl<MOttype> implements MOttypeDao {
    private static final Logger log = LoggerFactory.getLogger(MOttypeDaoImpl.class);
    @PersistenceContext
    private EntityManager       em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public MOttype findByName(String name) {
        log.debug("name=" + name);
        Query q = getEntityManager().createNamedQuery("MOttype.findByName", MOttype.class);
        q.setParameter("name", name);
        return (MOttype) q.getSingleResult();
    }
}
