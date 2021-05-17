package com.fv.ContainerApp.Model;

/**
 * This class contains a private Customer Object and stores the costumer name
 *
 * @author Fabian Valerius
 * @version 1.0.2
 *
 */
public class Customer {

    private String CustomerName;

    public Customer(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerName() {
        return CustomerName;
    }
}
