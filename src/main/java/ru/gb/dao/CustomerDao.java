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

    public List<Customer> findAll() {
        List<Customer> customers;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            customers = session.createQuery("select c from Customer c", Customer.class).getResultList();
            session.getTransaction().commit();
        }
        return customers;
    }

    public Customer findCustomerById(Long id) {
        Customer customer;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            customer = session.get(Customer.class, id);
            session.getTransaction().commit();
        }
        return customer;
    }

    public void deleteCustomerById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            Customer customer = session.load(Customer.class, id);
            session.delete(customer);
            session.getTransaction().commit();
        }
    }

    public Customer saveOrUpdate(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            session.saveOrUpdate(customer);
            session.getTransaction().commit();
        }
        return customer;
    }
}
