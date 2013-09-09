/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ERMS.entity.EmployeeEntity;
import Exception.ExistException;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class EmployeeSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    EmployeeEntity employee = new EmployeeEntity();

    public EmployeeSessionBean() {
    }
    
    
    public boolean addEmployee(String employeeId, String employeeLastName,String employeeFirstName,String employeeGender,String employeePassword, String employeeDepartment, String employeePosition, Integer employeeBirthyear, Integer employeeSchedule){

        employee.create(employeeId, employeePassword, employeeDepartment, employeePosition, employeeSchedule);
        em.persist(employee);
        return true;
    }
    
    public boolean removeEmployee(String employeeId)throws ExistException {
        employee = em.find(EmployeeEntity.class, employeeId);
        if(employee == null) {
            System.out.println("The member does not exist!");
            throw new ExistException("Member does not exist!");
        }
        em.remove(employee);
        return true;
    } 
    
    public boolean login (String employeeId, String employeePassword){
    
        employee = em.find(EmployeeEntity.class, employeeId);
        System.out.println("logging in....");
        if(employee==null) {
          System.out.println("This employee does not exist!");
          return false;
        }
        else
        {
           System.out.println("The input password is: "+ employeePassword);
           if(employee.getEmployeePassword().equals(employeePassword))  
               return true;
           else {
               System.out.println("The password is false!");
               return false;
           }
           }
        }
    
    public Set<EmployeeEntity> getEmployees(){
        Query q = em.createQuery("SELECT m FROM EmployeeEntity m");
        Set stateSet = new HashSet<EmployeeEntity>();
         for (Object o: q.getResultList()) { 
            EmployeeEntity m = (EmployeeEntity) o; 
            stateSet.add(m); 
        } 
         
        return stateSet;     
    }
    

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public boolean updateEmployee(String employeeId, String employeeLastName, String employeeFirstName, Integer employeeBirthYear, String employeeDepartment, Integer employeeSchedule, String employeePosition,String employeeGender,String employeePassword) throws ExistException {
       employee = em.find(EmployeeEntity.class, employeeId);
       if(employee == null){
           throw new ExistException("Employee does not exist!");
             }
       if(employee.getEmployeePassword().equals(employeePassword)!=true) throw new ExistException("Password is incorrect!");
            employee.setEmployeeBirthyear(employeeBirthYear);
            employee.setEmployeeDepartment(employeeDepartment);
            employee.setEmployeeFirstName(employeeFirstName);
            employee.setEmployeeGender(employeeGender);
            employee.setEmployeeLastName(employeeLastName);
            employee.setEmployeePosition(employeePosition);
            employee.setEmployeeSchedule(employeeSchedule);
            em.persist(employee);
        return true;
    }

    @Remove
    public void remove() { 
        System.out.println("EmployeeSessionBean: remove()"); 
    } 
}
