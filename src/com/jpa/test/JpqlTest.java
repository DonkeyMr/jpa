package com.jpa.test;

import org.hibernate.ejb.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author liupeng
 * @date 2018/12/26
 */
public class JpqlTest {

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    private EntityTransaction entityTransaction;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("test-jpa");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
    }

    @After
    public void destroy() {
        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testJpql() {
        String jpql = "FROM Customer c WHERE c.age > ?1 ";
        List resultList = entityManager.createQuery(jpql)
                .setParameter(1, 15)
                .getResultList();
        System.out.println(resultList.size());
    }

    @Test
    public void testPartlyProperties() {
        String jpql = "SELECT new Customer(c.id, c.age) FROM Customer c WHERE c.age > ?1";
        List resultList = entityManager.createQuery(jpql)
                .setParameter(1, 15)
                .getResultList();
        System.out.println(resultList.size());
        System.out.println(resultList);
    }

    @Test
    public void testNativeQuery() {
        String sql = "SELECT c.username FROM t_customer c where c.id = ?1";
        String username = (String) entityManager.createNativeQuery(sql)
                .setParameter(1, 1)
                .getSingleResult();
        System.out.println(username);
    }

    @Test
    public void testQueryCache() {
        String jpql = "FROM Customer c WHERE c.age > ?1 ";
        List resultList = entityManager.createQuery(jpql)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter(1, 15)
                .getResultList();
        System.out.println(resultList.size());

        resultList = entityManager.createQuery(jpql)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter(1, 15)
                .getResultList();
        System.out.println(resultList.size());
    }
}
