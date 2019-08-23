package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
        return (Employee) em.createQuery("SELECT c FROM c.EMPLOYEE WHERE c.Id = :" + id).getResultList().get(0);
    }

    public Employee getEmployeeByName(String name) {
        EntityManager em = getEntityManager();
        return (Employee) em.createQuery("SELECT c FROM c.EMPLOYEE WHERE c.Name = :" + name).getResultList().get(0);
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT c FROM c.EMPLOYEE").getResultList();
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

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EmployeeFacade ef = new EmployeeFacade();

        Employee e = new Employee("Annika", "annikavej 1", 20000.0);
        ef.createEmployee(e);
    }

    /**
     * getEmployeeById getEmployeesByName getAllEmployees
     * getEmployeesWithHighestSalary
createEmployee
     */
}
