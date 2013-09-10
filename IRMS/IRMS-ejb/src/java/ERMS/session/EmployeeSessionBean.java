/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ERMS.entity.EmployeeEntity;
import Exception.ExistException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
    @PersistenceContext(unitName = "Employee")
    private EntityManager em;
    
    EmployeeEntity employee;// = new EmployeeEntity();

    public EmployeeSessionBean() {
    }
    
    
    public EmployeeEntity addEmployee(EmployeeEntity employee){
        //employee.create(employeeId, employeePassword, employeeDepartment, employeePosition, employeeSchedule);
        em.persist(employee);
        return employee;
    }
    
    public boolean removeEmployee(Long employeeId)throws ExistException {
        employee = em.find(EmployeeEntity.class, employeeId);
        if(employee == null) {
            throw new ExistException("Employee does not exist!");
        }
        em.remove(employee);
        return true;
    } 
    
    public boolean login (Long employeeId, String employeePassword){
    
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
    //泥煤的不要再报错了！
    public EmployeeEntity getEmployeeById(Long employeeId) throws ExistException{
        employee = em.find(EmployeeEntity.class, employeeId);
        if(employee == null)  throw new ExistException("Employee does not exist!");
        return employee;
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


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public boolean updateEmployee(Long employeeId, String employeeName,Date employeeDob, String employeeDepartment, Integer employeeSchedule, String employeeRole,String employeeGender,String employeePassword) throws ExistException {
       employee = em.find(EmployeeEntity.class, employeeId);
       if(employee == null){
           throw new ExistException("Employee does not exist!");
             }
       if(employee.getEmployeePassword().equals(employeePassword)!=true) throw new ExistException("Password is incorrect!");
            employee.setEmployeeDob(employeeDob);
            employee.setEmployeeDepartment(employeeDepartment);
            employee.setEmployeeGender(employeeGender);
            employee.setEmployeeName(employeeName);
            employee.setEmployeeRole(employeeRole);
            employee.setEmployeeSchedule(employeeSchedule);
            em.persist(employee);
        return true;
    }

    @Remove
    public void remove() { 
        System.out.println("EmployeeSessionBean: remove()"); 
    } 
}