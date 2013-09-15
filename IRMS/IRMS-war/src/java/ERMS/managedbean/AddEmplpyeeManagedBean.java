/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EmployeeSessionRemote;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Cookie
 */
@ManagedBean
@RequestScoped
public class AddEmplpyeeManagedBean implements Serializable {
    @EJB
    private EmployeeSessionRemote employeeSessionRemote;
 
    private EmployeeEntity employee;

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }
    /**
     * Creates a new instance of AddEmplpyeeManagedBean
     */
    public AddEmplpyeeManagedBean() {
        employee = new EmployeeEntity();
    }
    
    public void saveNewEmployee(ActionEvent event) throws IOException {
   
    try{
               employeeSessionRemote.addEmployee(employee);
           }catch (Exception e) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new employee", ""));
                return;
           }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Employee saved.", ""));
//            emailSessionBean.emailInitialPassward(employee.getPersonalEmail(), initialPwd); //send email
            employee=new EmployeeEntity();  
    }
}
