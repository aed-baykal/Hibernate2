package ru.gb;

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

    List<Product> getProductsByCustomerId(Long id) {
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
}
