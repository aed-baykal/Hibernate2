package ru.gb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.gb.model.Customer;
import ru.gb.model.Product;

import java.util.List;

public class CustomerDao {

    SessionFactory sessionFactory;
    List<Customer> customerList;

    public CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // 3 *

    public List<Customer> getCustomersByProductId(Long id) {
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

}
