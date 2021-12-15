package ru.gb;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gb.dao.CustomerDao;
import ru.gb.dao.ProductDao;
import ru.gb.model.Customer;
import ru.gb.model.Product;

import java.util.List;

public class App {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        CustomerDao customerDao = new CustomerDao(sessionFactory);
        ProductDao productDao  = new ProductDao(sessionFactory);

        List<Customer> customerList = customerDao.getCustomersByProductId(3L);
        customerList.forEach(System.out::println);

        List<Product> productList = productDao.getProductsByCustomerId(2L);
        productList.forEach(System.out::println);

        customerList = customerDao.getCustomersByProductId(1L);
        customerList.forEach(System.out::println);

        productList = productDao.getProductsByCustomerId(1L);
        productList.forEach(System.out::println);

        sessionFactory.close();
    }
}
