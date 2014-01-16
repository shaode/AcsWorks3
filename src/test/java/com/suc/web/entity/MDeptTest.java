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
public class MDeptTest {
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
        List<MDept> list = em.createQuery("select t from MDept t order by t.id").getResultList();
        em.close();
        return list.size();
    }

    private MDept generateDept(String code, String name) {
        MDept obj = new MDept();
        obj.setCode(code);
        obj.setName(name);
        return obj;
    }

    // @Test
    public void test001DeleteAll() {
        System.out.println(">>>> testDeleteAll <<<<");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        int deletedCount = em.createQuery("delete from MyDept").executeUpdate();
        System.out.println(">>>>deletedCount:" + deletedCount);
        em.getTransaction().commit();
        em.close();
    }

    // @Test
    public void test002CreateDept() {
        int beforeCount = queryList();
        System.out.println(">>>> before create count = " + beforeCount);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MDept obj = generateDept("001", "ACS-OFM");
        em.persist(obj);
        em.getTransaction().commit();
        em.close();
        int afterCount = queryList();
        System.out.println(">>>> after create count = " + afterCount);
        Assert.assertEquals(beforeCount + 1, afterCount);
    }

    @Test
    public void test003FindByID() {
        System.out.println(">>>> FindByID <<<<");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MDept obj = em.find(MDept.class, 1L);
        if (null != obj) {
            System.out.println(">>>> Find By ID: = " + obj);
        } else {
            System.out.println(">>>> Find By ID not exit.");
        }
        em.getTransaction().commit();
        em.close();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test004QueryALL() {
        System.out.println(">>>> FindByID <<<<");
        EntityManager em = emf.createEntityManager();
        List<MDept> list = em.createQuery("select t from MDept t order by t.id").getResultList();
        for (MDept dept : list) {
            System.out.println("DEPT:" + dept);
        }
        em.close();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test101NamedQuery2() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("MDept.findByCode");
        q.setParameter(1, "001");
        // q.setParameter("today", new Date());
        List<MDept> results = (List<MDept>) q.getResultList();
        for (MDept m : results) {
            System.out.println(m.toString());
        }
        em.close();
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test102QueryALL() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select o from MDept o");
        List<MDept> results = (List<MDept>) q.getResultList();
        for (MDept m : results) {
            System.out.println(m.toString());
        }
        em.close();
        Assert.assertTrue(results.size() > 0);
    }

    //@Test
    public void test201Delete() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MDept obj = em.find(MDept.class, 10L);
        if (null != obj) {
            System.out.println(">>>> Find By ID: = " + obj);
        } else {
            System.out.println(">>>> Find By ID not exit.");
        }
        em.remove(obj);
        em.getTransaction().commit();
        em.close();
    }
}
