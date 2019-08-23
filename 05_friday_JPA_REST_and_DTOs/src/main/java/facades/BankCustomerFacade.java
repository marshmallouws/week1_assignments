package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private BankCustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getBCFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CustomerDTO getCustomerByID(int id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<BankCustomer> query
                    = em.createQuery("SELECT e FROM BankCustomer e WHERE e.id = :id", BankCustomer.class); 
            query.setParameter("id", id);
            return new CustomerDTO(query.getSingleResult());
        } finally {
            em.close();
        }   
    }
    
    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = getEntityManager();
        
        // Only works without middle name
        String[] arrOfStr = name.split(" ", 2); 
        try {
            TypedQuery<BankCustomer> query
                    = em.createQuery("SELECT e FROM BankCustomer e WHERE e.firstname = :first AND e.lastname = :last", BankCustomer.class);
            query.setParameter("first", arrOfStr[0]);
            query.setParameter("last", arrOfStr[1]);
            List<BankCustomer> bank = query.getResultList();
            
            List<CustomerDTO> cust = new ArrayList<>();
            
            for(BankCustomer b: bank) {
                cust.add(new CustomerDTO(b));
            }
            
            return cust;
        } finally {
            em.close();
        }
    }
    
    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }
    
    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = getEntityManager();
        
        try {
            TypedQuery<BankCustomer> query
                    = em.createQuery("SELECT b FROM BankCustomer b", BankCustomer.class);
            return query.getResultList();           
        } finally {
            em.close();
        }
    }
}
