package commomInfrastructurePartner.utility.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EmployeeSessionBean;
import ERMS.session.EPasswordHashSessionBean;
import SMMS.entity.MerchantEntity;
import SMMS.session.MerchantSessionBean;
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
 * Finished!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
@ManagedBean
@ViewScoped
public class FirstTimeLoginManagedBeanPartner {

    @EJB
    private MerchantSessionBean merchantManagerSessionBean;
    @EJB
    private EPasswordHashSessionBean passowordHashSessionBean;
    private MerchantEntity merchant;
    private String passwordTemp;
    private String passwordTemp2;
    private String securityQuestion;
    private String answer;

    public FirstTimeLoginManagedBeanPartner() {
    }

    public void initView(PhaseEvent event) {
        if (merchant == null) {
            merchant = (MerchantEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("merchantFirstTimeLogin");
        }
    }

    public void doSubmit(ActionEvent event) throws IOException {

        if (getPasswordTemp().equals(getPasswordTemp2())) {
            merchant.setMerchantPassword(passowordHashSessionBean.hashPassword(getPasswordTemp()));//employee.getEmployeeName() + 

            if (merchant.isIsFirstTimeLogin() == true) {

                merchant.setIsFirstTimeLogin(false);
            }
            merchantManagerSessionBean.updateMerchant(merchant);

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().invalidate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("firstTimeLoginPwdChangeResult.xhtml");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Password Not Match!", ""));

        }
    }

    public MerchantEntity getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantEntity merchant) {
        this.merchant = merchant;
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
