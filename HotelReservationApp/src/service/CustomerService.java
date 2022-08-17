package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private static CustomerService customerService = new CustomerService( );
    static Map<String,Customer> customerList = new HashMap<>();

    private CustomerService(){}

    public static CustomerService getInstance( ) {
        return customerService;
    }

    public void addCustomer(String firstName, String lastName, String email){
        customerList.put(email,new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail){
        return customerList.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers(){
        return customerList.values();
    }

}
