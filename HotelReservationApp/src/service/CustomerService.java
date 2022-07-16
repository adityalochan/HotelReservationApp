package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private static CustomerService customerService = new CustomerService( );
    static Map<String,Customer> mapOfCustomer = new HashMap<>();
    static List<Customer> customerList = new ArrayList<>();

    private CustomerService(){}

    public static CustomerService getInstance( ) {
        return customerService;
    }

    public void addCustomer(String firstName, String lastName, String email){
        Customer customer = new Customer(firstName, lastName, email);
        mapOfCustomer.put(email,customer);
        customerList.add(customer);
    }

    public Customer getCustomer(String customerEmail){
        return mapOfCustomer.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers(){
        return customerList;
    }

}
