/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.ERMS.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EPasswordHashSessionBean;
import ERMS.session.EmployeeSessionRemote;
import Exception.ExistException;
import java.io.IOException;
import java.util.UUID;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ResetPasswordManagedBean {

    @EJB
    private EmployeeSessionRemote employeeSessionRemote;
    @EJB
    private EPasswordHashSessionBean passowordHashSessionBean;
//    @EJB
//    private EmailSessionBean emailSessionBean;
    private Long employeeId;
    private String employeeDob;
    private EmployeeEntity employee;
    private String securityQuestion;
    private String ans;

    public ResetPasswordManagedBean() {
    }

    public void doVerify(ActionEvent event) throws IOException, ExistException {
        employee = employeeSessionRemote.getEmployeeById(employeeId);
        if (employee == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid UserName", ""));

        } else {

            if (employee.getEmployeeDob().toString().equals(employeeDob) && employee.getSecurityQuestion().equals(securityQuestion) && employee.getAnswer().equals(ans)) {
                //send email to private email
                String uuid = UUID.randomUUID().toString();
                String[] sArray = uuid.split("-");
                String initialPwd = sArray[0];
                employee.setEmployeePassword(initialPwd);
                employee.setEmployeePassword(passowordHashSessionBean.hashPassword(employee.getEmployeePassword()));
                employee.setIsFirstTimeLogin(true);
//                employeeSessionRemote.updateEmployee(employee);
//                emailSessionBean.emailInitialPassward(employee.getPersonalEmail(), initialPwd); //send email
                  FacesContext.getCurrentInstance().getExternalContext().redirect("resetPasswordResult.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Verification Failed.", ""));
            }
        }
    }

    public EmployeeSessionRemote getEmployeeManagerSessionRemote() {
        return employeeSessionRemote;
    }

    public void setEmployeeManagerSessionRemote(EmployeeSessionRemote employeeManagerSessionRemote) {
        this.employeeSessionRemote = employeeManagerSessionRemote;
    }

    public void retrieveEmployee() throws ExistException {
        employee = employeeSessionRemote.getEmployeeById(employeeId);
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeDob() {
        return employeeDob;
    }

    public void setEmployeeDob(String employeeDob) {
        this.employeeDob = employeeDob;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}

