package ru.gb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.gb.model.Customer;
import ru.gb.model.Product;

import java.util.List;

public class ProductDao {

    SessionFactory sessionFactory;
    List<Product> productList;

    public ProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // 3 *

    public List<Product> getProductsByCustomerId(Long id) {
        Customer customer;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            customer = session.createQuery("select s from Customer s left join fetch s.products where s.id = :id", Customer.class)
                    .setParameter("id", id)
                    .getSingleResult();
            productList = customer.getProducts();
            session.getTransaction().commit();
        }
        return productList;
    }

    public List<Product> findAll() {
        List<Product> products;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            products = session.createQuery("select p from Product p", Product.class).getResultList();
            session.getTransaction().commit();
        }
        return products;
    }

    public Product findProductById(Long id) {
        Product product;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    public void deleteProductById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            Product product = session.load(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
        return product;
    }
}
