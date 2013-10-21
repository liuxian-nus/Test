/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import ERMS.session.EPasswordHashSessionBean;
import ERMS.session.EmailSessionBean;
import Exception.ExistException;
import SMMS.entity.MerchantEntity;
import SMMS.session.MerchantSessionBean;
import java.io.IOException;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class MerchantManagedBean {

    @EJB
    private EmailSessionBean emailSessionBean;
    @EJB
    private EPasswordHashSessionBean ePasswordHashSessionBean;
    @EJB
    private MerchantSessionBean merchantSessionBean;
    private MerchantEntity merchant;

    @PostConstruct
    public void init() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public MerchantManagedBean() {
        merchant = new MerchantEntity();
    }

    /**
     * Creates a new instance of AddEmployeeManagedBean
     */
    public void saveNewMerchant(ActionEvent event) throws IOException, ExistException {
        System.out.println("add new merchant: "+merchant.getMerchantEmail());
        if (merchantSessionBean.getMerchantById(merchant.getMerchantEmail()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mercant already exists", ""));
            return;
        } else {
            String initialPwd = "";
            String uuid = UUID.randomUUID().toString();
            String[] sArray = uuid.split("-");
            initialPwd = sArray[0];
            merchant.setMerchantPassword(initialPwd);
            System.out.println("add new merchant hash password!");
            merchant.setMerchantPassword(ePasswordHashSessionBean.hashPassword(merchant.getMerchantPassword()));
            System.out.println("finished hashing");

            try {
                System.out.println("we are in save new merchant in managedbean" + merchant.getMerchantEmail());
                merchantSessionBean.addMerchant(merchant);
                System.out.println("we are after adding merchant in managedbean");
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new merchant", ""));
                return;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Merchant saved.", ""));
//            emailSessionBean.emailInitialPassward(employee.getPersonalEmail(), initialPwd); //send email
            emailSessionBean.emailInitialPassward(merchant.getMerchantEmail(), initialPwd);
            System.out.println("email already sent");
            merchant = new MerchantEntity();
            
        }
    }

    public void oneMore(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addMerchant.xhtml");
    }

    public MerchantEntity getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantEntity merchant) {
        this.merchant = merchant;
    }
    /**
     * Creates a new instance of MerchantManagedBean
     */
}