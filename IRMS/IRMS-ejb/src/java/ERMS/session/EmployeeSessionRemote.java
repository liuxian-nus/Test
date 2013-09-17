/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ERMS.entity.EmployeeEntity;
import Exception.ExistException;
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
    
    boolean login(String employeeId, String employeePassword);
    
    @Remove
    void remove();
    
    boolean removeEmployee(String employeeId) throws ExistException;
    
    public EmployeeEntity getEmployeeById(String employeeId) throws ExistException;
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    boolean updateEmployee(EmployeeEntity employee) throws ExistException;
    
}
