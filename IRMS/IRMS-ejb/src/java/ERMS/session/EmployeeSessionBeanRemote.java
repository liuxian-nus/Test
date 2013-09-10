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

/**
 *
 * @author Diana Wang
 */
public interface EmployeeSessionBeanRemote {

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    boolean addEmployee(String employeeId, String employeeLastName, String employeeFirstName, String employeeGender, String employeePassword, String employeeDepartment, String employeePosition, Integer employeeBirthyear, Integer employeeSchedule);

    Set<EmployeeEntity> getEmployees();

    boolean login(String employeeId, String employeePassword);

    void persist(Object object);

    @Remove
    void remove();

    boolean removeEmployee(String employeeId) throws ExistException;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    boolean updateEmployee(String employeeId, String employeeLastName, String employeeFirstName, Integer employeeBirthYear, String employeeDepartment, Integer employeeSchedule, String employeePosition, String employeeGender, String employeePassword) throws ExistException;
    
}
