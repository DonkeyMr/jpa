package com.jpa.test;

import com.jpa.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class JpaTest {

    public static void main(String[] args) {
        // 1、创建EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test-jpa");

        // 2、创建EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 3、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 4、进行持久化操作
        Customer customer = new Customer();
        customer.setUsername("dd");
        customer.setAge(12);
        customer.setEmail("dd@163.com");
        customer.setBirth(new Date());
        customer.setCreateDate(new Date());

        entityManager.persist(customer);

        // 5、提交事务
        transaction.commit();

        // 6、关闭EntityManager
        entityManager.close();

        // 7、关闭EntityManagerFactory
        entityManagerFactory.close();
    }
}
