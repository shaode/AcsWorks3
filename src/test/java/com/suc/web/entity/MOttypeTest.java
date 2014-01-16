package com.suc.web.entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MOttypeTest {
    EntityManagerFactory emf = null;

    @Before
    public void before() {
        emf = Persistence.createEntityManagerFactory("ACS_JPA");
    }

    @After
    public void after() {
        if (null != emf) {
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    private int queryList() {
        EntityManager em = emf.createEntityManager();
        List<MOttype> list = (List<MOttype>) em.createQuery("select t from MOttype t order by t.id").getResultList();
        em.close();
        return list.size();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test100NamedQuery1() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("MOttype.findAllNames");
        List<String> results = (List<String>) q.getResultList();
        for (String m : results) {
            System.out.println(m);
        }
        em.close();
        Assert.assertTrue(results.size() > 0);
    }
}
