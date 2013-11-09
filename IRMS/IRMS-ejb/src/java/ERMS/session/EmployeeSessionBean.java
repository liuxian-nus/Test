package ERMS.session;

import ERMS.entity.EmployeeEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.Remove;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Diana Wang
 */
@Stateless
public class EmployeeSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    EmployeeEntity employee;

    public EmployeeSessionBean() {
        //insert test case
        //   Query q1 = em.createQuery("INSERT INTO employeeentity(EMPLOYEEID,ANSWER,EMPLOYEEDEPARTMENT,EMPLOYEEDOB,EMPLOYEEGENDER,EMPLOYEENAME,EMPLOYEEPASSWORD,EMPLOYEESCHEDULE,ISFIRSTTIMELOGIN,SECURITYQUESTION)VALUES (100000,'Coral Island Resort','Admin',01/01/1990,'Male','Tan Wk','0000','0',false,'Where do you have your first job?')");
        //   Query q2 = em.createQuery("INSERT INTO employeeentity(EMPLOYEEID,ANSWER,EMPLOYEEDEPARTMENT,EMPLOYEEDOB,EMPLOYEEGENDER,EMPLOYEENAME,EMPLOYEEPASSWORD,EMPLOYEESCHEDULE,ISFIRSTTIMELOGIN,SECURITYQUESTION)VALUES (201001, 'John','Hotel',01/01/1990,'Male','Tester','0000','1',false,'Who is your best friend?')");
    }

    public EmployeeEntity addEmployee(EmployeeEntity employee) {
        //employee.create(employeeId, employeePassword, employeeDepartment, employeePosition, employeeSchedule);
        em.persist(employee);
        return employee;
    }

    public boolean removeEmployee(String employeeId) throws ExistException {
        employee = em.find(EmployeeEntity.class, employeeId);
        System.err.println("remove employee: " + employee.getEmployeeId());
        if (employee == null) {
            throw new ExistException("Employee does not exist!");
        }
        em.remove(employee);
        return true;
    }

    public boolean login(String employeeId, String employeePassword) {

        employee = em.find(EmployeeEntity.class, employeeId);
        System.out.println("logging in....");
        if (employee == null) {
            System.out.println("This employee does not exist!");
            return false;
        } else {
            System.out.println("The input password is: " + employeePassword);
            if (employee.getEmployeePassword().equals(employeePassword)) {
                return true;
            } else {
                System.out.println("The password is false!");
                return false;
            }
        }
    }
    //泥煤的不要再报错了！再来！

    public EmployeeEntity getEmployeeById(String employeeId) throws ExistException {
        employee = em.find(EmployeeEntity.class, employeeId);
        if (employee == null) {
            throw new ExistException("Employee does not exist!");
        }
        return employee;
    }

    public List<EmployeeEntity> getAllEmployees() throws ExistException {
        Query q = em.createQuery("SELECT m FROM EmployeeEntity m");
        List employeeList = new ArrayList<EmployeeEntity>();
        for (Object o : q.getResultList()) {
            EmployeeEntity m = (EmployeeEntity) o;
            employeeList.add(m);
        }
        if (employeeList.isEmpty()) {
            throw new ExistException("Employee database is empty!");
        }
        return employeeList;
    }
    
     public List<EmployeeEntity> getHotelEmployees() throws ExistException {
        Query q = em.createQuery("SELECT m FROM EmployeeEntity m where m.employeeDepartment='Hotel'");
        List employeeList = new ArrayList<EmployeeEntity>();
        for (Object o : q.getResultList()) {
            EmployeeEntity m = (EmployeeEntity) o;
            employeeList.add(m);
        }
        if (employeeList.isEmpty()) {
            throw new ExistException("Employee database is empty!");
        }
        return employeeList;
    }

    public List<EmployeeEntity> getCEMSEvent() throws ExistException {
        Query q = em.createQuery("SELECT m FROM EmployeeEntity m");
        List employeeList = new ArrayList<EmployeeEntity>();
        for (Object o : q.getResultList()) {
            EmployeeEntity m = (EmployeeEntity) o;
            List<String> userType = new ArrayList<String>();
            for (int i = 0; i < m.getRoles().size(); i++) {
                userType.add(m.getRoles().get(i).getRoleName());
            }
            if (userType.contains("CEMSEvent")) {
                employeeList.add(m);
            }
        }
        if (employeeList.isEmpty()) {
            throw new ExistException("Employee database is empty!");
        }
        return employeeList;
    }

    public List<EmployeeEntity> getEmployeeByName(String employeeName) throws ExistException {
        Query query = em.createQuery("SELECT r FROM EmployeeEntity r WHERE r.employeeName ='" + employeeName + "'");
        System.err.println("getEmployeeByName: " + employeeName);
        //query.setParameter("employeeName", employeeName);
        List<EmployeeEntity> employee = null;
        employee = query.getResultList();
        if (employee == null) {
            throw new ExistException("Employee does not exist!");
        }
        return employee;
    }

    public boolean updateEmployee(EmployeeEntity employee) {
        em.merge(employee);
        System.out.println("EmployeeSessionBean: employee " + employee.getEmployeeId() + " is successfully updated");
        return true;
    }

    @Remove
    public void remove() {
        System.out.println("EmployeeSessionBean: remove()");
    }
}