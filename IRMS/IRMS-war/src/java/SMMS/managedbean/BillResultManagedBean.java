/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import SMMS.entity.BillEntity;
import SMMS.entity.BillItemEntity;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class BillResultManagedBean {

    private BillEntity bill;
    private List<BillEntity> bills;
    private List<BillItemEntity> selectedBills;

    public List<BillItemEntity> getSelectedBills() {
        return selectedBills;
    }

    public void setSelectedBills(List<BillItemEntity> selectedBills) {
        this.selectedBills = selectedBills;
    }

    /**
     * Creates a new instance of BillResultManagedBean
     */
    public BillResultManagedBean() {
        bill = new BillEntity();
        bills = new ArrayList<BillEntity>();
        selectedBills = new ArrayList<BillItemEntity>();
    }

    public void initViewSelect(PhaseEvent event) {

        bill = (BillEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisBill");
        System.out.println("In init view select bill" + bill.getBillId() + "select outlet transaction size here lala");
//        request.getSession().setAttribute("roomId", thisRoom.getRoomId());
    }

    public void initViewSelect2(PhaseEvent event) {
        bills = (List<BillEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisBills");
        System.out.println("In init view select bill" + bills.size() + "select outlet transaction size here lala");
//     
    }

    public void initViewSelect3(PhaseEvent event) {

        selectedBills = (List<BillItemEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisBills");
        System.out.println("in init bean" + selectedBills.size());
        //        request.getSession().setAttribute("roomId", thisRoom.getRoomId());
    }

    public BillEntity getBill() {
        return bill;
    }

    public void setBill(BillEntity bill) {
        this.bill = bill;
    }

    public List<BillEntity> getBills() {
        return bills;
    }

    public void setBills(List<BillEntity> bills) {
        this.bills = bills;
    }
}
