package com.suc.web.entity;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
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
public class MWorkrecordTest {
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

    @Test
    @SuppressWarnings("unchecked")
    public void test100NamedQuery1() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("MWorkrecord.findByCustname");
        q.setParameter(1, "SDCMCC");
        List<MWorkrecord> results = (List<MWorkrecord>) q.getResultList();
        for (MWorkrecord m : results) {
            System.out.println("aaaaaaaa=" + m.getCustInfo());
        }
        em.close();
        Assert.assertTrue(results.size() > 0);
    }

    //@Test
    public void test001CreateWorkRecord() {
        System.out.println(">>>> testCreateWorkRecord <<<<");
        int beforeCount = queryList();
        System.out.println(">>>> before create count = " + beforeCount);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MWorkrecord wr = generateWorkRecord();
        em.merge(wr);
        em.getTransaction().commit();
        em.close();
        int afterCount = queryList();
        System.out.println(">>>> after create count = " + afterCount);
        Assert.assertEquals(beforeCount + 1, afterCount);
    }

    @SuppressWarnings("unchecked")
    private int queryList() {
        EntityManager em = emf.createEntityManager();
        List<MWorkrecord> list = (List<MWorkrecord>) em.createQuery("select t from MWorkrecord t order by t.id").getResultList();
        em.close();
        return list.size();
    }

    private MWorkrecord generateWorkRecord() {
        MWorkrecord wr = new MWorkrecord();
        wr.setMUser(findUser(1L));
        wr.setMOttype(findOTType(1L));
        wr.setOtHours(new BigDecimal(0));
        wr.setWorkHours(new BigDecimal(8));
        wr.setWorkDate(new Date());
        wr.setWorkStart("09:00:00");
        //wr.setWorkEnd(new Time(new Date().getTime()));
        wr.setWorkEnd("18:00:00");
        CustInfo ci = new CustInfo();
        ci.setCustName("SDCMCC");
        ci.setCustProject("12345");
        wr.setCustInfo(ci);
        return wr;
    }

    private MUser findUser(long id) {
        EntityManager em = emf.createEntityManager();
        MUser engineer = em.find(MUser.class, id);
        em.close();
        return engineer;
    }

    public MOttype findOTType(long id) {
        EntityManager em = emf.createEntityManager();
        MOttype ot = em.find(MOttype.class, id);
        em.close();
        return ot;
    }
}
