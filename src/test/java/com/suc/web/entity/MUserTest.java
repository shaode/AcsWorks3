package com.suc.web.entity;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
public class MUserTest {
    AtomicInteger        atom = new AtomicInteger();
    EntityManagerFactory emf  = null;

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

    private MUser findUser(long userID) {
        EntityManager em = emf.createEntityManager();
        MUser user = em.find(MUser.class, userID);
        em.close();
        return user;
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test100findAll() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("MUser.findAll");
        List<MUser> results = (List<MUser>) q.getResultList();
        for (MUser m : results) {
            System.out.println(m.toString());
        }
        em.close();
        Assert.assertTrue(results.size() > 0);
    }
}
