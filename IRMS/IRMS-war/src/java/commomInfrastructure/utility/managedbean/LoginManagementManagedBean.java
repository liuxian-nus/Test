/*
 */
package commomInfrastructure.utility.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.entity.RoleEntity;
import ERMS.session.EmployeeSessionBean;
import ERMS.session.EPasswordHashSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.List;
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

        EmployeeEntity systemUser = employeeManager.getEmployeeById(employeeId);
        System.out.println("1");
        if (systemUser == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid EmployeeId", ""));
        } else {
            setEmployeePassword(passwordHashSessionBean.hashPassword(getEmployeePassword()));//employeeId +
//            System.out.println("2"+getEmployeePassword());
//            System.out.println("3"+systemUser.getEmployeePassword());
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
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Welcome", "<p></p>Welcome login"));
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
