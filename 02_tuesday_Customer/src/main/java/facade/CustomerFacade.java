/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Annika
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;

    private static CustomerFacade instance;

    //Default constructor
    private CustomerFacade() {
    }

    /**
     * The purpose is to create a singleton so that we don't have more than one
     * instance of BookFacade.
     *
     * @param _emf
     * @return instance of BookFacade
     */
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Customer findByID(int id) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT c FROM Customer c WHERE c.id = :id", Customer.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public int getNumberOfCustomers() {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Long> query
                    = em.createQuery("SELECT COUNT(c.id) FROM Customer c", Long.class);
            return query.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers() {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Customer addCustomer(String fName, String lName) {
        Customer c = new Customer(fName, lName);
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }

    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade f = getCustomerFacade(emf);
//        f.addCustomer("Anina", "Kiel");
//        Customer c = f.findByID(1);
//        System.out.println(c.getFirstName());
//        System.out.println(f.getNumberOfCustomers());
//        
//        List<Customer> all = f.allCustomers();
//        for(Customer m: all) {
//            System.out.println(m.getFirstName());
    }
}
