/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.ERMS.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EmployeeSessionBean;
import ERMS.session.EPasswordHashSessionBean;
import Exception.ExistException;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@RequestScoped
public class LoginManagementManagedBean {

    @EJB
    EmployeeSessionBean employeeManager;
    @EJB
    EPasswordHashSessionBean passwordHashSessionBean;
    private String employeeId;
    private String employeePassword;

//private String message;
    public LoginManagementManagedBean() {
        employeeId = "";
        employeePassword = "";
    }

    public LoginManagementManagedBean(EmployeeSessionBean employeeManager) {
        this.employeeManager = employeeManager;
    }

    public void doLogin(ActionEvent event) throws IOException, ExistException {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        EmployeeEntity systemUser = employeeManager.getEmployeeById(getEmployeeId());
        System.out.println("HELLO,"+ employeeId+"LOGIN HERE!!!");
        if (systemUser == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid EmployeeId", ""));
        } else {
            setEmployeePassword(passwordHashSessionBean.hashPassword(employeeId + getEmployeePassword()));
            
            //Valid login
            if (systemUser.getEmployeePassword().equals(getEmployeePassword())) {
                String previousPage = "";

                if (request.getSession().getAttribute("lastAction") == null) {
                    previousPage = "/index.xhtml";
                } else {
                    previousPage = request.getSession().getAttribute("lastAction").toString();
                }

                request.getSession().setAttribute("isLogin", true);
                String systemUserId = systemUser.getEmployeeId();
                request.getSession().setAttribute("userId", systemUserId);

                if (employeeManager.getEmployeeById(systemUserId).isFirstTimeLogin() == true) { //first time log in

                    FacesContext.getCurrentInstance().getExternalContext().getFlash().put("employeeFirstTimeLogin", systemUser);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/IRMS-war/commonInfrastructure/firstTimeLoginPwdChange.xhtml");
                } else {//not first time login
                    FacesContext.getCurrentInstance().getExternalContext().getFlash().put("systemUserId", systemUserId);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/IRMS-war" + previousPage);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Password.", ""));
            }
        }
    }

    public void goLogin() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("./commonInfrastructure/login.xhtml");
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }
}
