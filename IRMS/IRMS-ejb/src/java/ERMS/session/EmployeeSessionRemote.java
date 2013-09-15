/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ERMS.entity.EmployeeEntity;
import Exception.ExistException;
import java.util.Date;
import java.util.Set;
import javax.ejb.Remove;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;

/**
 *
 * @author Diana Wang
 */
public interface EmployeeSessionRemote {

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    boolean addEmployee(EmployeeEntity employee);
    
    Set<EmployeeEntity> getAllEmployees() throws NoResultException;
    
    boolean login(Long employeeId, String employeePassword);
    
    @Remove
    void remove();
    
    boolean removeEmployee(Long employeeId) throws ExistException;
    
    public EmployeeEntity getEmployeeById(Long employeeId) throws ExistException;
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    boolean updateEmployee(Long employeeId, String employeeName,Date employeeDob, String employeeDepartment, Integer employeeSchedule, String employeeRole,String employeeGender,String employeePassword) throws ExistException;
    
}
