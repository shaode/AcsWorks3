package com.suc.web.jpa.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.suc.web.entity.MDept;
import com.suc.web.jpa.dao.MDeptDao;
import com.suc.web.utils.DataGridModel;

@Repository
public class MDeptDaoImpl extends BaseDAOImpl<MDept> implements MDeptDao {
    private static final Logger log = LoggerFactory.getLogger(MDeptDaoImpl.class);
    @PersistenceContext
    private EntityManager       em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public MDept findByCode(String code) {
        log.debug("code=" + code);
        Query q = getEntityManager().createNamedQuery("MDept.findByCode", MDept.class);
        q.setParameter(1, code);
        return (MDept) q.getSingleResult();
    }

    @Override
    public MDept findByName(String name) {
        log.debug("name=" + name);
        Query q = getEntityManager().createNamedQuery("MDept.findByName", MDept.class);
        q.setParameter("name", name);
        return (MDept) q.getSingleResult();
    }

    @Override
    public void delete(List<Long> ids) throws Exception {
        if (ids != null && ids.size() > 0) {
            for (Long id : ids) {
                em.remove(em.find(MDept.class, id));
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Map<String, Object> getPageList(DataGridModel dgm, MDept dept) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>(2);
        String countQuery = "select count(*) from MDept dept ";
        String fullQuery = "select dept from MDept dept ";
        String orderString = "";
        if (StringUtils.isNotBlank(dgm.getSort())) {
            orderString = " order by " + dgm.getSort() + " " + dgm.getOrder();
        }
        StringBuffer sb = new StringBuffer();
        Map<String, Object> params = new HashMap<String, Object>();
        if (dept != null) {
            if (StringUtils.isNotBlank(dept.getCode())) {
                sb.append(" where dept.code like :code");
                params.put("code", "%" + dept.getCode() + "%");
            }
            if (StringUtils.isNotBlank(dept.getName())) {
                if (sb.toString().indexOf("where") >= 0) {
                    sb.append(" and dept.name like :name");
                } else {
                    sb.append(" where dept.name like :name");
                }
                params.put("name", "%" + dept.getName() + "%");
            }
        }
        Query queryTotal = getEntityManager().createQuery(countQuery + sb.toString());
        Query queryList = getEntityManager().createQuery(fullQuery + sb.toString() + orderString);
        queryList.setFirstResult((dgm.getPage() - 1) * dgm.getRows()).setMaxResults(dgm.getRows());
        if (params != null && !params.isEmpty()) {
            Iterator<String> it = params.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                queryTotal.setParameter(key, params.get(key));
                queryList.setParameter(key, params.get(key));
            }
        }
        int total = ((Long) queryTotal.getSingleResult()).intValue();
        List list = queryList.getResultList();
        result.put("total", total);
        result.put("rows", list);
        return result;
    }
}
