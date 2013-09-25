package commomInfrastructure.utility.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EmployeeSessionBean;
import ERMS.session.EPasswordHashSessionBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author liuxian
 *
 * Not Finished!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
@ManagedBean
@ViewScoped
public class FirstTimeLoginManagedBean {

    @EJB
    private EmployeeSessionBean employeeManagerSessionBean;
    @EJB
    private EPasswordHashSessionBean passowordHashSessionBean;
    private EmployeeEntity employee;
    private String passwordTemp;
    private String passwordTemp2;
    private String securityQuestion;
    private String answer;

    public FirstTimeLoginManagedBean() {
    }

    public void initView(PhaseEvent event) {
        if (employee == null) {
            employee = (EmployeeEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("employeeFirstTimeLogin");
        }
    }

    public void doSubmit(ActionEvent event) throws IOException {

        if (getPasswordTemp().equals(getPasswordTemp2())) {
            employee.setEmployeePassword(passowordHashSessionBean.hashPassword(getPasswordTemp()));//employee.getEmployeeName() + 

            if (employee.isFirstTimeLogin() == true) {

                employee.setIsFirstTimeLogin(false);
            }
            employeeManagerSessionBean.updateEmployee(employee);

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().invalidate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("firstTimeLoginPwdChangeResult.xhtml");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Password Not Match!", ""));

        }
    }

    public EmployeeSessionBean getEmployeeManagerSessionBean() {
        return employeeManagerSessionBean;
    }

    public void setEmployeeManagerSessionBean(EmployeeSessionBean employeeManagerSessionBean) {
        this.employeeManagerSessionBean = employeeManagerSessionBean;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public String getPasswordTemp() {
        return passwordTemp;
    }

    public void setPasswordTemp(String passwordTemp) {
        this.passwordTemp = passwordTemp;
    }

    public String getPasswordTemp2() {
        return passwordTemp2;
    }

    public void setPasswordTemp2(String passwordTemp2) {
        this.passwordTemp2 = passwordTemp2;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
