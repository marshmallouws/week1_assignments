package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class BankCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String accountNumber;
    private double balance;
    @Transient
    private int customerRanking;
    @Transient
    private String internalInfo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public BankCustomer(){}
    
    public BankCustomer(String firstname, String lastname, 
            String accountNumber, double balance, int customerRanking,
            String internalInfo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerRanking = customerRanking;
        this.internalInfo = internalInfo;      
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public int getCustomerRanking() {
        return customerRanking;
    }

    public String getInternalInfo() {
        return internalInfo;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
