package com.suc.web.jpa.dao.impl;

import java.util.Date;
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
import com.suc.web.entity.MWorkrecord;
import com.suc.web.jpa.dao.MWorkrecordDao;
import com.suc.web.utils.DataGridModel;

@Repository
public class MWorkrecordDaoImpl extends BaseDAOImpl<MWorkrecord> implements MWorkrecordDao {
    private static final Logger log = LoggerFactory.getLogger(MWorkrecordDaoImpl.class);
    @PersistenceContext
    private EntityManager       em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MWorkrecord> findByCustname(String name) {
        log.debug("name=" + name);
        Query q = getEntityManager().createNamedQuery("MWorkrecord.findByCustname", MWorkrecord.class);
        q.setParameter(1, name);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MWorkrecord> findByCustproject(String project) {
        log.debug("project=" + project);
        Query q = getEntityManager().createNamedQuery("MWorkrecord.findByCustproject", MWorkrecord.class);
        q.setParameter(1, project);
        return q.getResultList();
    }

    @Override
    public void delete(List<Long> ids) throws Exception {
        if (ids != null && ids.size() > 0) {
            for (Long id : ids) {
                log.debug("id==" + id);
                em.remove(em.find(MWorkrecord.class, id));
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Map<String, Object> getPageList(DataGridModel dgm, MWorkrecord workrecord) throws Exception {
        log.debug("dgm=" + dgm);
        log.debug("workrecord=" + workrecord);
        Map<String, Object> result = new HashMap<String, Object>(2);
        String countQuery = "select count(*) from MWorkrecord workrecord, MUser user where workrecord.mUser=user ";
        String fullQuery = "select workrecord from MWorkrecord workrecord, MUser user where workrecord.mUser=user ";
        String orderString = "";
        if (StringUtils.isNotBlank(dgm.getSort())) {
            orderString = " order by " + dgm.getSort() + " " + dgm.getOrder();
        }
        StringBuffer sb = new StringBuffer();
        Map<String, Object> params = new HashMap<String, Object>();
        if (workrecord != null) {
            if (workrecord.getCustInfo() != null) {
                if (StringUtils.isNotBlank(workrecord.getCustInfo().getCustName())) {
                    sb.append(" and workrecord.custInfo.custName like :custName");
                    params.put("custName", "%" + workrecord.getCustInfo().getCustName() + "%");
                }
                if (StringUtils.isNotBlank(workrecord.getCustInfo().getCustProject())) {
                    sb.append(" and workrecord.custInfo.custProject = :custProject");
                    params.put("custProject", workrecord.getCustInfo().getCustProject());
                }
            }
            if (workrecord.getMUser() != null) {
                if (StringUtils.isNotBlank(workrecord.getMUser().getName())) {
                    sb.append(" and user.name = :userName");
                    params.put("userName", workrecord.getMUser().getName());
                }
            }
        }
        Query queryTotal = getEntityManager().createQuery(countQuery + sb.toString());
        Query queryList = getEntityManager().createQuery(fullQuery + sb.toString() + orderString).setFirstResult((dgm.getPage() - 1) * dgm.getRows()).setMaxResults(dgm.getRows());
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

    @SuppressWarnings("rawtypes")
    @Override
    public Map<String, Object> findByDate(Date startDate, Date endDate) {
        log.debug("startDate=" + startDate + ";endDate=" + endDate);
        Map<String, Object> result = new HashMap<String, Object>(2);
        String countQuery = "select count(*) from MWorkrecord workrecord, MUser user where workrecord.mUser=user and workrecord.workDate >= ?1 and workrecord.workDate<=?2";
        String fullQuery = "select workrecord from MWorkrecord workrecord, MUser user where workrecord.mUser=user and workrecord.workDate >= ?1 and workrecord.workDate<=?2";
        Query queryTotal = getEntityManager().createQuery(countQuery);
        Query queryList = getEntityManager().createQuery(fullQuery);
        queryTotal.setParameter(1, startDate);
        queryTotal.setParameter(2, endDate);
        queryList.setParameter(1, startDate);
        queryList.setParameter(2, endDate);
        int total = ((Long) queryTotal.getSingleResult()).intValue();
        List list = queryList.getResultList();
        result.put("total", total);
        result.put("rows", list);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MWorkrecord> findByDate2(Date startDate, Date endDate) {
        log.debug("startDate=" + startDate + ";endDate=" + endDate);
        Query q = getEntityManager().createNamedQuery("MWorkrecord.findByDate", MWorkrecord.class);
        q.setParameter(1, startDate);
        q.setParameter(2, endDate);
        return q.getResultList();
    }
}
