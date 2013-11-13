/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMSTesting;

import ACMS.session.RoomSessionBeanRemote;
import ERMS.entity.EmployeeEntity;
import ERMS.session.EmployeeSessionBeanRemote;
import Exception.ExistException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diana Wang
 */
public class EmployeeTesting {
    
    EmployeeSessionBeanRemote EmployeeSessionBean = lookupEmployeeSessionBean();
    
    public EmployeeTesting() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testAddEmployee()
    {
        System.out.println("testAddEmployee");
        
        String employeeName = "test";
        String employeeGender = "female";
        Date employeeDob = new Date();
        String employeePassword = "test";
        String employeeDepartment = "FBMS";
        Integer employeeSchedule =1;
        String securityQuestion = "test";
        String answer = "test";
        String employeeEmail="xinqi_wang@yahoo.com";
        
        EmployeeEntity employee = new EmployeeEntity();
        employee.setAnswer(answer);
        employee.setEmployeeDepartment(employeeDepartment);
        employee.setEmployeeDob(employeeDob);
        employee.setEmployeeEmail(employeeEmail);
        employee.setEmployeeGender(employeeGender);
        employee.setEmployeeName(employeeName);
        employee.setEmployeePassword(employeePassword);
        employee.setEmployeeSchedule(employeeSchedule);
        employee.setSecurityQuestion(securityQuestion);
        
        EmployeeEntity test = EmployeeSessionBean.addEmployee(employee);
        assertNotNull(test);
        assertSame(test.getClass(),EmployeeEntity.class);
    }
    
    @Test
    public void testRemoveEmployee() throws ExistException
    {
        System.out.println("testRemoveEmployee");
        
        boolean removed = EmployeeSessionBean.removeEmployee("H0000");
        assertTrue(removed);
    }
    
    @Test
    public void testLogin()
    {
        System.out.println("testLogin");
        
        boolean loggedIn = EmployeeSessionBean.login("H0001","0");
        assertNotNull(loggedIn);
    }
    
    @Test
    public void testGetEmployeeById() throws ExistException
    {
       System.out.println("testGetEmployeeById"); 
       
       EmployeeEntity ee = EmployeeSessionBean.getEmployeeById("H0000");
       assertNotNull(ee);
    }
    
    @Test
    public void testGetAllEmployees() throws ExistException
    {
        List<EmployeeEntity> test = EmployeeSessionBean.getAllEmployees();
        assertNotNull(test);
    }
    
    @Test
    public void testGetHotelEmployees() throws ExistException
    {
        List<EmployeeEntity> test = EmployeeSessionBean.getHotelEmployees();
        assertNotNull(test);
    }

    private EmployeeSessionBeanRemote lookupEmployeeSessionBean() {
       try {
            Context c = new InitialContext();
            return (EmployeeSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/EmployeeSessionBean!ERMS.session.EmployeeSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}