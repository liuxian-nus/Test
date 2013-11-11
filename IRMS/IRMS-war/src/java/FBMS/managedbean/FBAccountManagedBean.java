/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.managedbean;

import FBMS.entity.AccountEntity;
import FBMS.session.BillingSessionBean;
import SMMS.entity.MerchantEntity;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author xinyu
 */
@ManagedBean
@ViewScoped
public class FBAccountManagedBean {

    @EJB
    private BillingSessionBean billingSessionBean;
    private List<AccountEntity> ar;
    private List<AccountEntity> cash;
    private AccountEntity account;
    private String name;

    /**
     * Creates a new instance of FBAccountManagedBean
     */
    public FBAccountManagedBean() {
        account = new AccountEntity();
    }

    public List<AccountEntity> getReceivables() {
        return billingSessionBean.getReceivableAccounts();
    }

    public List<AccountEntity> getCah() {
        return billingSessionBean.getCashAccounts();
    }

    public List<AccountEntity> getAllAccounts() {
        return billingSessionBean.getAllAccounts();
    }

    public void addAccount(ActionEvent event) throws IOException {
        System.out.println("add new what: " + account.getId());
        System.out.println("Account name is what?: " + name);
        if (("Cash".equalsIgnoreCase(name)) || ("Receivable".equalsIgnoreCase(name))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Account already exists", ""));
        } else {
            try {
                account.setAccountName(name);
                billingSessionBean.addAccount(account);
                System.out.println("we are after adding merchant in managedbean" + account.getId());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new account", ""));
                return;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Account saved.", ""));
//            emailSessionBean.emailInitialPassward(employee.getPersonalEmail(), initialPwd); //send emai        
            FacesContext.getCurrentInstance().getExternalContext().redirect("manageAccount.xhtml");
        }
    }

//    public void deleteAccount(ActionEvent event)
//    {
//        System.out.println("in deleting account" + account.getId());
//        try
//        {
//        }catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when deleting new account", ""));
//            return;
//        }
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Account deleted.", ""));
//    }
    public List<AccountEntity> getAr() {
        return ar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAr(List<AccountEntity> ar) {
        this.ar = ar;
    }

    public List<AccountEntity> getCash() {
        return cash;
    }

    public void setCash(List<AccountEntity> cash) {
        this.cash = cash;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
