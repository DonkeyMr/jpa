package com.jpa.test;

import com.jpa.model.Department;
import com.jpa.model.Manager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OneToOneTest {

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
    public void testPeresist() {
        Manager manager = new Manager();
        manager.setManagerName("aa");

        Department department = new Department();
        department.setDepartmentName("d-aa");

        department.setManager(manager);

        entityManager.persist(manager);
        entityManager.persist(department);
    }

    @Test
    public void testFind() {
        Department department = entityManager.find(Department.class, 1);
        System.out.println(department.getDepartmentName());

        System.out.println(department.getManager().getManagerName());
    }

    @Test
    public void testFind2() {
        Manager manager = entityManager.find(Manager.class, 1);
        System.out.println(manager.getManagerName());

        System.out.println(manager.getDepartment().getDepartmentName());
    }
}
