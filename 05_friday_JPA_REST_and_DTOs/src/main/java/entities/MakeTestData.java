package entities;

import facades.BankCustomerFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Annika
 */
public class MakeTestData {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        BankCustomer b1 = new BankCustomer("Annika", "Ehlers", "1234", 1000.0, 10, "something");
        BankCustomer b2 = new BankCustomer("Peter", "Jakobsen", "3210", 1500.0, 1, "something");
        BankCustomer b3 = new BankCustomer("Ole", "Jakobsen", "1243", 100.0, 20, "something");
        
        try {
            em.getTransaction().begin();
            em.persist(b1);
            em.persist(b2);
            em.persist(b3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
