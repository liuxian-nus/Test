/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.MemberSessionBean;
import CRMS.session.MemberTransactionSessionBean;
import Exception.ExistException;
import FBMS.managedbean.AddGroupCateringManagedBean;
import FBMS.session.OrderSessionBean;
import SMMS.entity.ItemTransactionEntity;
import SMMS.entity.OutletEntity;
import SMMS.entity.OutletTransactionEntity;
import SMMS.entity.SMItemEntity;
import SMMS.session.OutletSessionBean;
import SMMS.session.OutletTransactionSessionBean;
import SMMS.session.SMItemSessionBean;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author xinyu
 */
@ManagedBean
@ViewScoped
public class AddOutletTransactionManagedBean {

    @EJB
    private MemberTransactionSessionBean memberTransactionSessionBean;
    @EJB
    private SMItemSessionBean sMItemSessionBean;
    @EJB
    private OutletSessionBean outletSessionBean;
    @EJB
    private MemberSessionBean memberSessionBean;
    @EJB
    private OrderSessionBean orderSessionBean;
    @EJB
    private OutletTransactionSessionBean outletTransactionSessionBean;
    private SMItemEntity item;
    private OutletEntity outlet;
    private List<SMItemEntity> items;
    private List<ItemTransactionEntity> itemtransactions;
    private ItemTransactionEntity itemtransaction;
    private double amount; //not useful at all lalala
    private double total;
    private int quantity;
    private int outletId;
    private String memberId;
    private MemberEntity member;
    private Long itemId;
    private static final Logger logger = Logger.getLogger(AddOutletTransactionManagedBean.class.getName());
    private MemberTransactionEntity mtransaction;

    /**
     * Creates a new instance of AddOutletTransactionManagedBean
     */
    public AddOutletTransactionManagedBean() {
        item = new SMItemEntity();
        items = new ArrayList<SMItemEntity>();
        outlet = new OutletEntity();
        member = new MemberEntity();
        itemtransactions = new ArrayList<ItemTransactionEntity>();
        itemtransaction = new ItemTransactionEntity();
        mtransaction = new MemberTransactionEntity();
    }

    public String reinit() {
        itemtransaction = new ItemTransactionEntity();
        return null;
    }

    public String onFlowProcess(FlowEvent event) {
        logger.info("Current wizard step:" + event.getOldStep());
        logger.info("Next step:" + event.getNewStep());
        return event.getNewStep();

    }

    public void handleItemChanges() throws ExistException {
        if (itemId != null) {
            item = sMItemSessionBean.getItemById(itemId);
            itemtransaction.setItem(item);
            sMItemSessionBean.addItemTransaction(itemtransaction);
            System.err.println("after setting transaction" + itemtransaction.getId());
        } else {
            FacesMessage msg = new FacesMessage("Error occours during ajaxing");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void handleAmountChanges() throws ExistException {
        System.out.println("in handling changes now");
        if (itemtransaction != null) {
            {
                System.out.println("NO1: what is the quantity?" + itemtransaction.getQuantity());
                System.out.println("NO1: what is the price?" + itemtransaction.getItem().getItemPrice());
                itemtransaction.setTotal(itemtransaction.getQuantity() * itemtransaction.getItem().getItemPrice());
                System.out.println("NO2: what is the amount and quantity?" + itemtransaction.getTotal());
                sMItemSessionBean.updateTransactionItem(itemtransaction);
            }
        }


    }

    public void addTransaction(ActionEvent event) throws ExistException {
        System.out.println(" in adding transaction");

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime(); //set today
        System.out.println("what is the date today????" + today);
        try {

            outlet = outletSessionBean.getOutletById(outletId); //prepare outlet
            OutletTransactionEntity otransaction = new OutletTransactionEntity(); //prepare transaction

            Iterator itr = itemtransactions.iterator();
            while (itr.hasNext()) {
                ItemTransactionEntity current = (ItemTransactionEntity) itr.next();
                total = current.getTotal() + total;
                System.out.println("what is the total total????" + total);
            }
            System.out.println("what is the total now?" + total);
            otransaction.setOutlet(outlet);
            otransaction.setTransactionAmount(total);
            otransaction.setTransactionDate(today);
            outletTransactionSessionBean.addTransaction(otransaction);


            System.err.println("after setting outlet transaction" + otransaction.getId());


            if (memberId != null) {
                System.out.println("in member session");
                member = memberSessionBean.getMemberByEmail(memberId);

                mtransaction.setMemberEmail(memberId);
                mtransaction.setMtAmount(total);
                mtransaction.setMtDepartment("shopping mall");
                mtransaction.setMtDate(today);
                memberTransactionSessionBean.addMemberTransaction(memberId, mtransaction, true); //persisting member transaction
                System.err.println("after setting member transaction " + mtransaction.getMtId());
                memberTransactionSessionBean.addPoint(member, total);
                memberTransactionSessionBean.addCoin(member, total);
                memberTransactionSessionBean.updateVIP(member);
                member.addMemberTransaction(mtransaction);
            }

            FacesMessage msg = new FacesMessage("Your transaction has been paid");
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Error occours during adding transaction");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        System.out.println("in adding transaction" + quantity + outletId + memberId);
    }

    public List<String> completeItems() throws ExistException {
        System.out.println("NO4: we are in ALL contracts bean BEFORE");
        List<String> results = new ArrayList<String>();

        List<SMItemEntity> merchantList = sMItemSessionBean.getAllItems();
        for (Object o : merchantList) {
            SMItemEntity rve = (SMItemEntity) o;
            results.add((rve.getId()).toString());
        }
        System.out.println("NO5: we are in complete bean AFTER");
        return results;
    }

    public List<SMItemEntity> getAllItems() {
        return sMItemSessionBean.getAllItems();
    }

    public List<ItemTransactionEntity> getItemtransactions() {
        return itemtransactions;
    }

    public void setItemtransactions(List<ItemTransactionEntity> itemtransactions) {
        this.itemtransactions = itemtransactions;
    }

    public ItemTransactionEntity getItemtransaction() {
        return itemtransaction;
    }

    public void setItemtransaction(ItemTransactionEntity itemtransaction) {
        this.itemtransaction = itemtransaction;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getOutletId() {
        return outletId;
    }

    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SMItemEntity getItem() {
        return item;
    }

    public void setItem(SMItemEntity item) {
        this.item = item;
    }

    public OutletEntity getOutlet() {
        return outlet;
    }

    public void setOutlet(OutletEntity outlet) {
        this.outlet = outlet;
    }

    public List<SMItemEntity> getItems() {
        return items;
    }

    public void setItems(List<SMItemEntity> items) {
        this.items = items;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public MemberTransactionEntity getMtransaction() {
        return mtransaction;
    }

    public void setMtransaction(MemberTransactionEntity mtransaction) {
        this.mtransaction = mtransaction;
    }
}
