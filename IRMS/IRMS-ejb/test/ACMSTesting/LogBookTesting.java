/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMSTesting;

import ACMS.entity.LogBookEntity;
import ACMS.session.LogBookSessionBeanRemote;
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
public class LogBookTesting {
    
    LogBookSessionBeanRemote LogBookSessionBean = lookupLogBookSessionBean();
    EmployeeSessionBeanRemote EmployeeSessionBean = lookupEmployeeSessionBean();
    
    public LogBookTesting() {
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
    public void testGetAllLogs()
    {
        System.out.println("testGetAllLogs");
        
        List<LogBookEntity> test = LogBookSessionBean.getAllLogs();
        assertNotNull(test);
    }
    
    @Test
    public void testGetLogByShift() throws ExistException
    {
        System.out.println("testGetLogByShift");
        
        int logShift = 1;
        List<LogBookEntity> test = LogBookSessionBean.getLogByShift(logShift);
        assertNotNull(test);
    }
    
    @Test
    public void testAddLog() throws ExistException
    {
        System.out.println("testAddLog");
        
        LogBookEntity test = new LogBookEntity();
        String logTitle = "test";
        String logText = "test";
        String remark="test";
        int logShift = 2;
        boolean resolved = true;
        Date publishDate = new Date();
        EmployeeEntity logEmployee = EmployeeSessionBean.getEmployeeById("F0000");
        
        test.setEmployee(logEmployee);
        test.setLogShift(logShift);
        test.setLogText(logText);
        test.setLogTitle(logTitle);
        test.setPublishDate(publishDate);
        test.setRemark(remark);
        test.setResolved(resolved);
        
        LogBookEntity added = LogBookSessionBean.addLog(test);
        assertNotNull(added);
    }
    
    @Test
    public void testMarkResolved()
    {
       System.out.println("testMarkResolved"); 
       
       LogBookEntity test = new LogBookEntity();
       LogBookSessionBean.markResolved(test);
    }
    
    @Test
    public void testRemoveLog()
    {
        System.out.println("testMarkResolved");
        
        Long logId = Long.parseLong("88888");
        LogBookEntity removed = LogBookSessionBean.removeLog(logId);
        assertNotNull(removed);
    }
    
    @Test
    public void testUpdateLog()
    {
        System.out.println("testUpdateLog");
        
        Long logId = Long.parseLong("99999");
        String title = "updated";
        String content = "updated";
        String remark = "updated";
        
        LogBookEntity updated = LogBookSessionBean.updateLog(logId, title, content, remark);
        assertNotNull(updated);
    }

    private LogBookSessionBeanRemote lookupLogBookSessionBean() {
        try {
            Context c = new InitialContext();
            return (LogBookSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/LogBookSessionBean!ACMS.session.LogBookSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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