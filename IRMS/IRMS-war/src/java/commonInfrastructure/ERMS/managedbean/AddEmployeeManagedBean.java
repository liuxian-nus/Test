/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.ERMS.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EPasswordHashSessionBean;
import ERMS.session.EmailSessionBean;
import ERMS.session.EmployeeSessionBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Cookie
 */
@ManagedBean
@RequestScoped
public class AddEmployeeManagedBean implements Serializable {
    @EJB
    private EPasswordHashSessionBean ePasswordHashSessionBean;
    @EJB
    private EmailSessionBean emailSessionBean;
    @EJB
    private EmployeeSessionBean employeeSessionBean;
    
    private EmployeeEntity employee;
    
    @PostConstruct
    public void init()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    /**
     * Creates a new instance of AddEmployeeManagedBean
     */
    public AddEmployeeManagedBean() {
        employee = new EmployeeEntity();
    }

    public void saveNewEmployee(ActionEvent event) throws IOException {
        String initialPwd = "";
        String uuid = UUID.randomUUID().toString();
        String[] sArray = uuid.split("-");
        initialPwd = sArray[0];
        employee.setEmployeePassword(initialPwd);
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword(employee.getEmployeePassword())); 
        
        try {
            System.out.println("we are in SavaNewEmployee in managedbean");
            employeeSessionBean.addEmployee(employee);
            System.out.println("we are after employee in managedbean");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new employee", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Employee saved.", ""));
//            emailSessionBean.emailInitialPassward(employee.getPersonalEmail(), initialPwd); //send email
        emailSessionBean.emailInitialPassward(employee.getEmployeeEmail(), initialPwd);
        employee = new EmployeeEntity();
    }
}
