package ru.gb;

import ru.gb.model.Customer;
import ru.gb.model.Product;

import java.util.List;

public class App {
    public static void main(String[] args) {
        CustomerAndProductDao customerAndProductDao  = new CustomerAndProductDao();

        List<Customer> customerList = customerAndProductDao.getCustomersByProductId(3L);
        customerList.forEach(System.out::println);

        List<Product> productList = customerAndProductDao.getProductsByCustomerId(2L);
        productList.forEach(System.out::println);
    }
}
