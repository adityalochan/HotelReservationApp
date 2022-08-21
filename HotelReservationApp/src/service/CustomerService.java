package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private static CustomerService customerService = new CustomerService( );
    static Map<String,Customer> mapOfCustomer = new HashMap<>();

    private CustomerService(){}

    public static CustomerService getInstance( ) {
        return customerService;
    }

    public void addCustomer(String firstName, String lastName, String email){
        mapOfCustomer.put(email,new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail){
        return mapOfCustomer.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers(){
        return mapOfCustomer.values();
    }

}
