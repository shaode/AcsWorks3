package com.suc.web.jpa.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.suc.web.entity.MUser;
import com.suc.web.jpa.dao.MUserDao;
import com.suc.web.utils.DataGridModel;

@Repository
public class MUserDaoImpl extends BaseDAOImpl<MUser> implements MUserDao {
    private static final Logger log = LoggerFactory.getLogger(MUserDaoImpl.class);
    @PersistenceContext
    private EntityManager       em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public MUser findByName(String name) {
        log.debug("name=" + name);
        try {
            Query q = getEntityManager().createNamedQuery("MUser.findByName", MUser.class);
            q.setParameter(1, name);
            return (MUser) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public MUser findByMail(String mail) {
        log.debug("mail=" + mail);
        Query q = getEntityManager().createNamedQuery("MUser.findByMail", MUser.class);
        q.setParameter(1, mail);
        return (MUser) q.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<MUser> findByDeptId(long dept_id) {
        log.debug("dept_id=" + dept_id);
        String querySQL = "select user from MUser user user.dept_id := dept_id";
        Query q = getEntityManager().createQuery(querySQL);
        q.setParameter("dept_id", dept_id);
        return q.getResultList();
    }

    @Override
    public void delete(List<Long> ids) throws Exception {
        if (ids != null && ids.size() > 0) {
            for (Long id : ids) {
                em.remove(em.find(MUser.class, id));
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Map<String, Object> getPageList(DataGridModel dgm, MUser user) throws Exception {
        log.debug("dgm=" + dgm);
        log.debug("user=" + user);
        Map<String, Object> result = new HashMap<String, Object>(2);
        String countQuery = "select count(*) from MUser user,MDept dept where user.mDept=dept ";
        String fullQuery = "select user from MUser user, MDept dept where user.mDept=dept ";
        String orderString = "";
        if (StringUtils.isNotBlank(dgm.getSort())) {
            orderString = " order by " + dgm.getSort() + " " + dgm.getOrder();
        }
        StringBuffer sb = new StringBuffer();
        Map<String, Object> params = new HashMap<String, Object>();
        if (user != null) {
            if (StringUtils.isNotBlank(user.getName())) {
                sb.append(" and user.name like :userName");
                params.put("userName", "%" + user.getName() + "%");
            }
            if (StringUtils.isNotBlank(user.getMail())) {
                sb.append(" and user.mail like :mail");
                params.put("mail", "%" + user.getMail() + "%");
            }
            if (StringUtils.isNotBlank(user.getComments())) {
                sb.append(" and user.comments like :comments");
                params.put("comments", "%" + user.getComments() + "%");
            }
            if (user.getMDept() != null) {
                if (StringUtils.isNotBlank(user.getMDept().getCode())) {
                    sb.append(" and dept.code = :deptCode");
                    params.put("deptCode", user.getMDept().getCode());
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
}
