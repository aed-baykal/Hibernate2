package ru.gb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gb.model.Customer;
import ru.gb.model.Product;

import java.util.List;

public class CustomerAndProductDao {

    List<Product> productList;
    List<Customer> customerList;
    SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    // 3 *

    List<Customer> getCustomersByProductId(Long id) {
        Product product;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            product = session.createQuery("select s from Product s left join fetch s.customers where s.id = :id", Product.class)
                    .setParameter("id", id)
                    .getSingleResult();
            customerList = product.getCustomers();
            session.getTransaction().commit();
        }
        return customerList;
    }

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
