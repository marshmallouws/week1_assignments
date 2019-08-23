package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee getEmployeeById(int id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query 
                    = em.createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class);
            query.setParameter("id", id);
            return query.getResultList().get(0);
        } finally {
            em.close();
        }
    }

    public Employee getEmployeeByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query 
                    = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
            query.setParameter("name", name);
            return query.getResultList().get(0);
        } finally {
            em.close();
        }
        
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query 
                    = em.createQuery("SELECT e FROM Employee e", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee createEmployee(Employee e) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            return e;
        } finally {
            em.close();
        }

    }
    
    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = getEntityManager();
        
        try {
            TypedQuery<Employee> query 
                    = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(x.salary) FROM Employee x)", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
   
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EmployeeFacade ef = getEmployeeFacade(emf);

        /*Employee e = new Employee("Annika", "annikavej 1", 2000.0);
        Employee e1 = new Employee("Peter", "petervej 1", 100.0);
        ef.createEmployee(e1);
        ef.createEmployee(e);*/
        List<Employee> e = ef.getAllEmployees();
        for(Employee em: e) {
            System.out.println(em.getName());
        }
        
        //ef.getEmployeeById(1);
        //ef.getEmployeeByName("Peter");
        List<Employee> empl = ef.getEmployeesWithHighestSalary();
        for(Employee em: empl) {
            System.out.println(em.getSalary());
        }
        
    }
}
