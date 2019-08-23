/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;

/**
 *
 * @author Annika
 */
public class CustomerDTO {
    private int customerID;
    private String fullName;
    private String accountNumber;
    private double balance;
    
    public CustomerDTO(BankCustomer c) {
        customerID = c.getId().intValue();
        fullName = c.getFirstname() + " " + c.getLastName();
        accountNumber = c.getAccountNumber();
        balance = c.getBalance();
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}
