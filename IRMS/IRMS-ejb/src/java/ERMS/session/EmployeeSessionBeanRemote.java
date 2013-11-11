/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ERMS.entity.EmployeeEntity;
import Exception.ExistException;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Remove;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface EmployeeSessionBeanRemote {

    EmployeeEntity addEmployee(EmployeeEntity employee);

    List<EmployeeEntity> getAllEmployees() throws ExistException;

    List<EmployeeEntity> getCEMSEvent() throws ExistException;

    EmployeeEntity getEmployeeById(String employeeId) throws ExistException;

    List<EmployeeEntity> getEmployeeByName(String employeeName) throws ExistException;

    List<EmployeeEntity> getHotelEmployees() throws ExistException;

    boolean login(String employeeId, String employeePassword);
    //泥煤的不要再报错了！再来！

    @Remove
    void remove();

    boolean removeEmployee(String employeeId) throws ExistException;

    boolean updateEmployee(EmployeeEntity employee);
    
}
