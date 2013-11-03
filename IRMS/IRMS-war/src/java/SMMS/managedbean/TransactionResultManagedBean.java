/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import SMMS.entity.OutletEntity;
import SMMS.entity.OutletTransactionEntity;
import SMMS.session.OutletSessionBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

/**
 *
 * @author liudazhi
 */
@ManagedBean
@ViewScoped
public class TransactionResultManagedBean {
    @EJB
    private OutletSessionBean outletSessionBean;
    private OutletEntity outlet;
    /**
     * Creates a new instance of TransactionResultManagedBean
     */
    public TransactionResultManagedBean() {
    }
    
     public void initViewSelect(PhaseEvent event) {
      
        outlet = (OutletEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisOutlet");
        System.out.println("In init view select outlet" + outlet.getOutletId() + "select outlet transaction size here lala" + outletSessionBean.getTransactions(outlet.getOutletId()).size());
//        request.getSession().setAttribute("roomId", thisRoom.getRoomId());
    }
     
//     public List<OutletTransactionEntity> getTransactions()
//     {
//         System.out.println("transaction size"+outlet.getOutletTransaction().size());
//         return outlet.getOutletTransaction();
//     }
     
     public List<OutletTransactionEntity> getTransactions()
     {
         System.out.println("in getting transaction"+outlet.getId());
         System.out.println("transaction size"+outletSessionBean.getTransactions(outlet.getOutletId()).size());
         
         return outletSessionBean.getTransactions(outlet.getOutletId());
         
     }
     
     
}
