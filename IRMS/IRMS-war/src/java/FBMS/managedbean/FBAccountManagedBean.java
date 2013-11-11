/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.managedbean;

import FBMS.entity.AccountEntity;
import FBMS.session.BillingSessionBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
    /**
     * Creates a new instance of FBAccountManagedBean
     */
    public FBAccountManagedBean() {
    }
    
    public List<AccountEntity> getReceivables()
    {
        return billingSessionBean.getReceivableAccounts();
    }
    
    public List<AccountEntity> getCah()
    {
        return billingSessionBean.getCashAccounts();
    }
    
    public List<AccountEntity> getAllAccounts()
    {
        return billingSessionBean.getAllAccounts();
    }
}
