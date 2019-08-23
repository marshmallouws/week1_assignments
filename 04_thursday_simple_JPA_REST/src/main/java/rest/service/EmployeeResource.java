package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employee")
public class EmployeeResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployees() {
        List<Employee> emp = facade.getAllEmployees();
        List<EmployeeDTO> emdto = new ArrayList<>();
        for (Employee e : emp) {
            emdto.add(new EmployeeDTO(e));
        }
        return new Gson().toJson(emdto);

    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("id") int id) {
        Employee e = facade.getEmployeeById(id);
        EmployeeDTO d = new EmployeeDTO(e);
        return new Gson().toJson(d);
    }
    
    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("name") String name) {
        Employee e = facade.getEmployeeByName(name);
        EmployeeDTO d = new EmployeeDTO(e);
        return new Gson().toJson(d);
    }

    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHighestPaid() {
        List<Employee> emp = facade.getEmployeesWithHighestSalary();
        List<EmployeeDTO> emdto = new ArrayList<>();
        for (Employee e : emp) {
            emdto.add(new EmployeeDTO(e));
        }
        return new Gson().toJson(emdto);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Employee entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
