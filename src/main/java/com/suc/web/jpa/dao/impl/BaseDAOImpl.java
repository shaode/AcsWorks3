package com.suc.web.jpa.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.suc.web.entity.common.BaseEntity;

@Repository
public abstract class BaseDAOImpl<T extends BaseEntity> {
    private static final Logger logger = LoggerFactory.getLogger(BaseDAOImpl.class);
    private final Class<T>      persistentClass;

    protected abstract EntityManager getEntityManager();

    public void create(T t) {
        logger.debug("T=" + t.toString());
        getEntityManager().persist(t);
    }

    public T createOrUpdate(T t) {
        logger.debug("T=" + t.toString());
        return getEntityManager().merge(t);
    }

    public void delete(T t) {
        logger.debug("T=" + t.toString());
        // getEntityManager().remove(getEntityManager().contains(t) ? t : getEntityManager().merge(t));
        getEntityManager().remove(getEntityManager().find(persistentClass, t.getId()));
    }

    @SuppressWarnings("unchecked")
    public BaseDAOImpl() {
        // get the BaseDAOImpl from its subclass, and then return the BaseDAOImpl's actual Generic Type T.
        persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        logger.debug("persistentClass=" + persistentClass);
    }

    public T findById(long id) {
        logger.debug("id=="+id);
        return getEntityManager().find(persistentClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        final String nameQueryString = persistentClass.getSimpleName() + ".findAll";
        logger.debug("findAll=" + nameQueryString);
        return (List<T>) getEntityManager().createNamedQuery(nameQueryString).getResultList();
    }

    @SuppressWarnings("unchecked")
    public T findOne(String namedQuery, Object... parameters) throws NoResultException {
        try {
            logger.debug("findOne=" + namedQuery);
            final Query query = getEntityManager().createNamedQuery(namedQuery);
            int i = 0;
            for (Object p : parameters) {
                query.setParameter(++i, p);
            }
            return (T) query.getSingleResult();
        } catch (NoResultException e) {
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> find(String namedQuery, Object... parameters) {
        logger.debug("find=" + namedQuery);
        final Query query = getEntityManager().createNamedQuery(namedQuery);
        int i = 0;
        for (Object p : parameters) {
            query.setParameter(++i, p);
        }
        return (List<T>) query.getResultList();
    }
}
