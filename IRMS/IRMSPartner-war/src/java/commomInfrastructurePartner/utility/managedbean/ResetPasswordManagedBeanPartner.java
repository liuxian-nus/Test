/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commomInfrastructurePartner.utility.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EPasswordHashSessionBean;
import ERMS.session.EmailSessionBean;
import ERMS.session.EmployeeSessionBean;
import Exception.ExistException;
import SMMS.entity.MerchantEntity;
import SMMS.session.MerchantSessionBean;
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
public class ResetPasswordManagedBeanPartner {

    @EJB
    private MerchantSessionBean merchantSessionBean;
    @EJB
    private EPasswordHashSessionBean passowordHashSessionBean;
    @EJB
    private EmailSessionBean emailSessionBean;
    private String merchantId;
    private MerchantEntity merchant;
    private String securityQuestion;
    private String answer;

    public ResetPasswordManagedBeanPartner() {
    }
//action listener

    public void doVerify(ActionEvent event) throws IOException, ExistException {
        merchant = merchantSessionBean.getMerchantById(merchantId);
        System.out.println(merchant.getMerchantEmail());
        if (merchant == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid UserName", ""));
            return;
        } else {
            if (merchant.getSecurityQuestion().equals(securityQuestion) && merchant.getAnswer().equals(answer)) {//send email to private email
            String uuid = UUID.randomUUID().toString();
            String[] sArray = uuid.split("-");
            String initialPwd = sArray[0];
            merchant.setMerchantPassword(initialPwd);
            merchant.setMerchantPassword(passowordHashSessionBean.hashPassword(merchant.getMerchantPassword()));
            merchant.setIsFirstTimeLogin(true);
            merchantSessionBean.updateMerchant(merchant);
            emailSessionBean.emailInitialPassward(merchant.getMerchantEmail(), initialPwd); //send email
            FacesContext.getCurrentInstance().getExternalContext().redirect("resetPasswordResultPartner.xhtml");
        }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Verification Failed.", ""));
    }
        }
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

    public MerchantSessionBean getMerchantSessionBean() {
        return merchantSessionBean;
    }

    public void setMerchantSessionBean(MerchantSessionBean merchantSessionBean) {
        this.merchantSessionBean = merchantSessionBean;
    }

    public EPasswordHashSessionBean getPassowordHashSessionBean() {
        return passowordHashSessionBean;
    }

    public void setPassowordHashSessionBean(EPasswordHashSessionBean passowordHashSessionBean) {
        this.passowordHashSessionBean = passowordHashSessionBean;
    }

    public EmailSessionBean getEmailSessionBean() {
        return emailSessionBean;
    }

    public void setEmailSessionBean(EmailSessionBean emailSessionBean) {
        this.emailSessionBean = emailSessionBean;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public MerchantEntity getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantEntity merchant) {
        this.merchant = merchant;
    }
}
