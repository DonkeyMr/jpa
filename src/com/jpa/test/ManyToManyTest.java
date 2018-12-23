package com.jpa.test;

import com.jpa.model.Category;
import com.jpa.model.Goods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ManyToManyTest {

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
    public void testSave() {
        Category category = new Category();
        category.setName("c-1");

        Category category2 = new Category();
        category2.setName("c-2");

        Goods goods = new Goods();
        goods.setName("g-1");

        Goods goods2 = new Goods();
        goods2.setName("g-2");

        goods.getCategories().add(category);
        goods.getCategories().add(category2);
        goods2.getCategories().add(category);

        entityManager.persist(category);
        entityManager.persist(category2);
        entityManager.persist(goods);
        entityManager.persist(goods2);
    }

    @Test
    public void testFind() {
        Category category = entityManager.find(Category.class, 3);
        System.out.println(category.getName());

        System.out.println(category.getGoods().size());
    }
}
