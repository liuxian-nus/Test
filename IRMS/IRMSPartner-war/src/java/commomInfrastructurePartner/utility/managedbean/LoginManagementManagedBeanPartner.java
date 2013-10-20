/*
 */
package commomInfrastructurePartner.utility.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.entity.RoleEntity;
import ERMS.session.EmployeeSessionBean;
import ERMS.session.EPasswordHashSessionBean;
import Exception.ExistException;
import SMMS.entity.MerchantEntity;
import SMMS.session.MerchantSessionBean;
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
public class LoginManagementManagedBeanPartner {

    @EJB
    MerchantSessionBean merchantManager;
    @EJB
    EPasswordHashSessionBean passwordHashSessionBean;
    private String merchantId;
    private String merchantPassword;

//private String message;
    public LoginManagementManagedBeanPartner() {
        merchantId = "";
        merchantPassword = "";
    }

    public LoginManagementManagedBeanPartner(MerchantSessionBean merchantManager) {
        this.merchantManager = merchantManager;
    }

    public void doLogin(ActionEvent event) throws IOException, ExistException {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        MerchantEntity systemUser = merchantManager.getMerchantById(merchantId);
        System.out.println("1");
        if (systemUser == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid MerchantId", ""));
        } else {
            setMerchantPassword(passwordHashSessionBean.hashPassword(getMerchantPassword()));//employeeId +
//            System.out.println("2"+getEmployeePassword());
//            System.out.println("3"+systemUser.getEmployeePassword());
            //Valid login
            if (systemUser.getMerchantPassword().equals(getMerchantPassword())) {
                String previousPage = "";

                if (request.getSession().getAttribute("lastAction") == null) {
                    previousPage = "/index.xhtml";

                } else {
                    previousPage = request.getSession().getAttribute("lastAction").toString();
                }
                request.getSession().setAttribute("isLogin", true);
                String systemUserId = systemUser.getMerchantEmail();
                request.getSession().setAttribute("userId", systemUserId);

                if (merchantManager.getMerchantById(systemUserId).isIsFirstTimeLogin() == true) { //first time log in

                    FacesContext.getCurrentInstance().getExternalContext().getFlash().put("merchantFirstTimeLogin", systemUser);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/IRMSPartner-war/commonInfrastructure/firstTimeLoginPwdChange.xhtml");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Welcome", "<p></p>Welcome login"));
                } else {//not first time login
                    FacesContext.getCurrentInstance().getExternalContext().getFlash().put("systemUserId", systemUserId);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/IRMSPartner-war" + previousPage);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Password.", ""));
            }
        }
    }

    public void goLogin() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("./commonInfrastructure/login.xhtml");
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }
}
