/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfacade;

import dto.CustomerDTO;
import entities.BankCustomer;
import facades.BankCustomerFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Annika
 */
public class TestBankCustomerFacade {
    EntityManagerFactory em;
    BankCustomerFacade bf;
    
    public TestBankCustomerFacade() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        em = Persistence.createEntityManagerFactory("pu");
        bf = BankCustomerFacade.getBCFacade(em);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetCustomerByID() {

        BankCustomer exp = new BankCustomer("Annika", "Ehlers", "1234", 1000.0, 1, "test");
        exp.setId(1L);
        CustomerDTO expected = new CustomerDTO(exp);
        
        CustomerDTO actual = bf.getCustomerByID(1);
        
        assertEquals(expected.getCustomerID(), actual.getCustomerID());
    }

    @Test
    public void testGetCustomerByName() {
        BankCustomer exp = new BankCustomer("Annika", "Ehlers", "1234", 1000.0, 1, "test");
        exp.setId(1L);
        CustomerDTO expected = new CustomerDTO(exp);
        
        List<CustomerDTO> actual = bf.getCustomerByName("Annika Ehlers");
        assertEquals(expected.getFullName(), actual.get(0).getFullName());
    }
    
    @Test
    public void testGetAllBankCustomers() {
        BankCustomer exp = new BankCustomer("Annika", "Ehlers", "1234", 1000.0, 1, "test");
        exp.setId(1L);
        
        List<BankCustomer> actual = bf.getAllBankCustomers();
        boolean test = false;
        for(BankCustomer b: actual) {
            if(b.getId() == exp.getId()) {
                test = true;
                break;
            }
        }       
        assertTrue(test);
    }
}
