package com.jpa.test;

import com.jpa.model.Customer;
import com.jpa.model.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class OneToManyTest {

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
    public void testMaynToOnePersist() {
        Customer customer = new Customer();
        customer.setUsername("bb");
        customer.setCreateDate(new Date());
        customer.setBirth(new Date());
        customer.setEmail("bb@163.com");
        customer.setAge(21);

        Order order = new Order();
        order.setOrderName("order-bb");
        order.setAmount(552d);
        order.setCustomer(customer);

        Order order2 = new Order();
        order2.setOrderName("order2-bb");
        order2.setAmount(34d);
        order2.setCustomer(customer);

        order.setCustomer(customer);
        order2.setCustomer(customer);

        entityManager.persist(customer);
        entityManager.persist(order);
        entityManager.persist(order2);
    }

    @Test
    public void testManyToOneFind() {
        Order order = entityManager.find(Order.class, 1);

        entityTransaction.commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Order order2 = entityManager.find(Order.class, 1);
    }

    @Test
    public void testManyToOneRemove() {
        /*Order order = entityManager.find(Order.class, 1);
        entityManager.remove(order);*/

        Customer customer = entityManager.find(Customer.class, 5);
        entityManager.remove(customer);
    }
}
