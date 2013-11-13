/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import CRMS.session.CPasswordHashSessionBean;
import ERMS.session.EPasswordHashSessionBean;
import Exception.ExistException;
import SMMS.entity.BillEntity;
import SMMS.entity.ContractEntity;
import SMMS.entity.ContracteventEntity;
import SMMS.entity.MerchantEntity;
import SMMS.entity.OutletEntity;
import SMMS.entity.OutletTransactionEntity;
import SMMS.entity.SMItemEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.ContracteventSessionBean;
import SMMS.session.MerchantBillSessionBean;
import SMMS.session.MerchantSessionBean;
import SMMS.session.OutletSessionBean;
import SMMS.session.OutletTransactionSessionBean;
import SMMS.session.SMItemSessionBean;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
public class smmsInitManagedBean implements Serializable {
    @EJB
    private SMItemSessionBean sMItemSessionBean;

    @EJB
    private MerchantBillSessionBean merchantBillSessionBean;
    @EJB
    private OutletTransactionSessionBean outletTransactionSessionBean;
    @EJB
    private OutletSessionBean outletSessionBean;
    @EJB
    private MerchantSessionBean merchantSessionBean;
    @EJB
    private EPasswordHashSessionBean ePasswordHashSessionBean;
    @EJB
    private ContracteventSessionBean contracteventSessionBean;
    @EJB
    private ContractSessionBean contractSessionBean;

    /**
     * Creates a new instance of smmsInitManagedBean
     */
    public smmsInitManagedBean() {
    }
    private ContractEntity contract;
    private ContracteventEntity event;
    private MerchantEntity merchant;
    private OutletEntity outlet;
    private OutletTransactionEntity otransaction;
    private Date currentDate = new Date();
    private BillEntity bill;

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void createMerchant() {
        System.out.println("go to create merchant page...");
//        functionality = new FunctionalityEntity();
//        functionality.setFuncName("viewContractPartner");
//        functionality.setFuncDescription("View contract");
//        functionalitySessionBean.addFunctionality(functionality);
//        
//        FunctionalityEntity functionality2 = new FunctionalityEntity();
//        functionality2.setFuncName("makePaymentPartner");
//        functionality2.setFuncDescription("Make Outstanding payment");
//        functionalitySessionBean.addFunctionality(functionality2);
//        
//        FunctionalityEntity functionality3 = new FunctionalityEntity();
//        functionality3.setFuncName("viewOutletPartner");
//        functionality3.setFuncDescription("Make Outstanding payment");
//        functionalitySessionBean.addFunctionality(functionality3);

//        role = new RoleEntity();
//        role.setRoleId(41);
//        role.setRoleName("SMMSOps");
//        role.addFunctionality(functionality);
//        role.addFunctionality(functionality2);
//        role.addFunctionality(functionality3);
//        System.out.println("Create role :" + role.getRoleName());
//        
        merchant = new MerchantEntity();
//        merchant.setRoles(role);
        merchant.setMerchantEmail("xinqi_wang@yahoo.com");
        merchant.setMerchantName("Wang Dayan");
        merchant.setMerchantPassword(ePasswordHashSessionBean.hashPassword("M3000"));
        merchant.setMerchantHP("12345678");
        merchant.setMerchantAddress("35 Prince George's Park");
        merchant.setSecurityQuestion("What is your mother's original surname?");
        merchant.setAnswer("Gu");
        merchant.setIsFirstTimeLogin(false);
        merchant.setPartnerType("shoppingMall");


        MerchantEntity merchant2 = new MerchantEntity();
        merchant2.setMerchantEmail("s.er3na.j@gmail.com");
        merchant2.setMerchantName("Zheng Bowen");
        merchant2.setMerchantPassword(ePasswordHashSessionBean.hashPassword("M4000"));
        merchant2.setMerchantHP("87654321");
        merchant2.setMerchantAddress("30 Prince George's Park");
        merchant2.setSecurityQuestion("What is your mother's original surname?");
        merchant2.setAnswer("Gu");
        merchant2.setIsFirstTimeLogin(false);
        merchant2.setPartnerType("shoppingMall");

        MerchantEntity merchant3 = new MerchantEntity();
        merchant3.setMerchantEmail("a0077867@nus.edu.sg");
        merchant3.setMerchantName("Liu Yudi");
        merchant3.setMerchantPassword(ePasswordHashSessionBean.hashPassword("M5000"));
        merchant3.setMerchantHP("09876234");
        merchant3.setMerchantAddress("25 Prince George's Park");
        merchant3.setSecurityQuestion("What is your mother's original surname?");
        merchant3.setAnswer("Gu");
        merchant3.setIsFirstTimeLogin(false);
        merchant3.setPartnerType("shoppingMall");

        try {
            System.out.println("Saving merchant....");

            merchantSessionBean.addMerchant(merchant);
            merchantSessionBean.addMerchant(merchant2);
            merchantSessionBean.addMerchant(merchant3);
            System.out.println("Merchant saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding reservation", ""));
            return;
        }
        System.out.println("Insert merchant into database");

        addMessage("Merchant Created!");
    }

    public void createOutlet() {
        System.out.println("go to create outlet page...");

        outlet = new OutletEntity();
        outlet.setOutletLevel(5);
        outlet.setOutletNo(03);
        outlet.setOutletId(5, 03);
        outlet.setOutletArea(21.85);


        OutletEntity outlet2 = new OutletEntity();
        outlet2.setOutletLevel(2);
        outlet2.setOutletNo(31);
        outlet2.setOutletId(2, 31);
        outlet2.setOutletArea(18.27);

        OutletEntity outlet3 = new OutletEntity();
        outlet3.setOutletLevel(5);
        outlet3.setOutletNo(16);
        outlet3.setOutletId(5, 16);
        outlet3.setOutletArea(21.33);

        OutletEntity outlet4 = new OutletEntity();
        outlet4.setOutletLevel(4);
        outlet4.setOutletNo(31);
        outlet4.setOutletId(4, 31);
        outlet4.setOutletArea(16.93);

        try {
            System.out.println("Saving outlets....");

            outletSessionBean.addOutlet(outlet);
            outletSessionBean.addOutlet(outlet2);
            outletSessionBean.addOutlet(outlet3);
            outletSessionBean.addOutlet(outlet4);

            System.out.println("Outlets saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding merchant", ""));
            return;
        }
        System.out.println("Insert outlet into database");

        addMessage("Outlets! Created!");
    }

    public void createContract() {
        System.out.println("go to create Contract Page...");

        ContractEntity contract1 = new ContractEntity();
        Date cidate = new Date(2014, 10, 1);
        Date codate = new Date(2016, 10, 1);
        try {

            System.out.println("Saving cart....");

            MerchantEntity merchanta = merchantSessionBean.getMerchantById("xinqi_wang@yahoo.com");
            OutletEntity outleta = outletSessionBean.getOutletById(210);
            contract1.setMerchant(merchanta);
            contract1.setOutlet(outleta);
            contractSessionBean.addContract(contract1);
            System.out.println("Contract saved....." + contract1.getContractId());


            ContracteventEntity event1 = new ContracteventEntity();
            event1.setEventStartDate(cidate);
            event1.setEventEndDate(codate);
            event1.setEventDeposit(55000.00);
            event1.setEventMonthRate(9000.00);
            event1.setEventCommissionRate(0.22);
            event1.setEventStatus("newRejected");
            event1.setEventContract(contract1);
            event1.setEventTime(currentDate);
            contracteventSessionBean.addContractevent(event1);
            System.out.println("Contract saved....." + event1.getContracteventId());


            contractSessionBean.addContractevent(contract1.getContractId(), event1.getContracteventId());
            merchantSessionBean.addContractInMerchant(contract1.getContractId(), merchanta.getMerchantEmail());

            outleta.setContract(contract1);
            outleta.setOutletType("Lingerie");
            outleta.setOutletName("Victoria's Secret");
            outleta.setOutletStatus("unavailable");
            outletSessionBean.updateOutlet(outleta);

            System.out.println("Contract3 saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding conract3", ""));
            return;
        }



        ContractEntity contract2 = new ContractEntity();
        Date cidate1 = new Date(2014, 6, 1);
        Date codate1 = new Date(2018, 6, 1);
        try {

            System.out.println("Saving cart....");

            MerchantEntity merchantb = merchantSessionBean.getMerchantById("s.er3na.j@gmail.com");
            OutletEntity outletb = outletSessionBean.getOutletById(503);
            contract2.setMerchant(merchantb);
            contract2.setOutlet(outletb);
            contractSessionBean.addContract(contract2);
            System.out.println("Contract saved....." + contract2.getContractId());


            ContracteventEntity event2 = new ContracteventEntity();
            event2.setEventStartDate(cidate1);
            event2.setEventEndDate(codate1);
            event2.setEventDeposit(55000.00);
            event2.setEventMonthRate(11000.00);
            event2.setEventCommissionRate(0.22);
            event2.setEventDownPayment(21000);
            event2.setEventStatus("newPending");
            event2.setEventContract(contract2);
            event2.setEventTime(currentDate);
            contracteventSessionBean.addContractevent(event2);
            System.out.println("Contract4 saved....." + event2.getContracteventId());


            contractSessionBean.addContractevent(contract2.getContractId(), event2.getContracteventId());
            merchantSessionBean.addContractInMerchant(contract2.getContractId(), merchantb.getMerchantEmail());

            outletb.setContract(contract2);
            outletb.setOutletType("Bars and Nightclubs");
            outletb.setOutletName("Zouk");
            outletb.setOutletStatus("unavailable");
            outletSessionBean.updateOutlet(outletb);

            System.out.println("Contract4 saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding contract 4", ""));
            return;
        }



        ContractEntity contract3 = new ContractEntity();
        Date cidate3 = new Date(2010, 10, 1);
        Date codate3 = new Date(2015, 10, 1);
        try {

            System.out.println("Saving cart....");

            MerchantEntity merchant3 = merchantSessionBean.getMerchantById("cookiewxy@hotmail.com");
            OutletEntity outlet3 = outletSessionBean.getOutletById(516);
            contract3.setMerchant(merchant3);
            contract3.setOutlet(outlet3);
            contractSessionBean.addContract(contract3);
            System.out.println("Contract3 saved....." + contract3.getContractId());


            ContracteventEntity event3 = new ContracteventEntity();
            event3.setEventStartDate(cidate3);
            event3.setEventEndDate(codate3);
            event3.setEventDeposit(55000.00);
            event3.setEventMonthRate(9000.00);
            event3.setEventCommissionRate(0.22);
            event3.setEventStatus("newActive");
            event3.setEventContract(contract3);
            event3.setEventTime(currentDate);
            contracteventSessionBean.addContractevent(event3);
            System.out.println("Contract saved....." + event3.getContracteventId());


            contractSessionBean.addContractevent(contract3.getContractId(), event3.getContracteventId());
            merchantSessionBean.addContractInMerchant(contract3.getContractId(), merchant3.getMerchantEmail());

            outlet3.setContract(contract3);
            outlet3.setOutletType("Watches");
            outlet3.setOutletName("Cartier");
            outlet3.setOutletStatus("unavailable");
            outletSessionBean.updateOutlet(outlet3);
            System.err.println("start adding bill lalalallalala!!!!!!!!!!");
            addDepositBill(contract3);

            System.out.println("Contract5 saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding conract5", ""));
            return;
        }


        System.out.println("Insert contract into database");
        addMessage("Contrac4! Created!");
    }

    public void addDepositBill(ContractEntity contract) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 10);  //here expire after 2 minutes
        Date dueDate = cal.getTime();
        System.out.println("in setting due date" + dueDate);
        System.out.println("getting contract meh" + contract.getId());
//        System.out.println("getting contract meh" + contract.getLast().getEventDeposit());

        BillEntity bill2 = new BillEntity();
        bill2.setBillAmount(55000.00);
        System.out.println("NO1: getting contract meh" + contract.getId());
        bill2.setBillStatus("unpaid");
        System.out.println("NO 2: getting contract meh" + contract.getId());
        bill2.setBillType("deposit");
        System.out.println("NO3: getting contract meh" + contract.getId());
        bill2.setBillDate(currentDate);
        System.out.println("NO4: getting contract meh" + contract.getId());
        bill2.setDueDate(dueDate);
        System.out.println("NO5: getting contract meh" + contract.getId());
        bill2.setContract(contract);
        System.out.println("NO6: getting contract meh" + contract.getId());

        merchantBillSessionBean.addBill(bill2); // persisting bill
        System.out.println("NO7: getting contract meh" + contract.getId());

        merchantBillSessionBean.setBill(bill2); //setting bill to pass variable
        System.err.println("before creating timer" + dueDate);
        merchantBillSessionBean.createOverDueTimers(dueDate);

    }

    public void createTransaction() throws ExistException {
        System.out.println("go to create outlet transaction page...");
        Date date1 = new Date(2013, 6, 1);
        Date date2 = new Date(2013, 2, 1);
        Date date3 = new Date(2013, 1, 1);
        Date date4 = new Date(2013, 6, 11);
        Date date5 = new Date(2013, 5, 13);
        Date date6 = new Date(2013, 8, 21);
        Date date7 = new Date(2013, 11, 1);
        Date date8 = new Date(2012, 2, 17);
        Date date9 = new Date(2013, 10, 1);
        Date date10 = new Date(2013, 9, 18);

        try {
            System.out.println("Saving outlet Transactions....");

            otransaction = new OutletTransactionEntity();
            otransaction.setTransactionDate(date1);
            otransaction.setTransactionAmount(13.45);
            otransaction.setOutlet(outletSessionBean.getOutletById(217));
            outletTransactionSessionBean.addTransaction(otransaction); //persisting transaction
            outletSessionBean.getOutletById(217).addTransaction(otransaction); //add transaction to outlet
            outletSessionBean.updateOutlet(outletSessionBean.getOutletById(217)); //update outlet
            System.out.println("in saving transaction1:" + otransaction.getTransactionId() + "size now of 217 =" + outletSessionBean.getOutletById(217).getOutletTransaction().size());

            OutletTransactionEntity otransaction1 = new OutletTransactionEntity();
            otransaction1.setTransactionDate(date2);
            otransaction1.setTransactionAmount(22.98);
            otransaction1.setOutlet(outletSessionBean.getOutletById(217));
            outletTransactionSessionBean.addTransaction(otransaction1); //persisting transaction
            outletSessionBean.getOutletById(217).addTransaction(otransaction1);
            outletSessionBean.updateOutlet(outletSessionBean.getOutletById(217));

            OutletTransactionEntity otransaction2 = new OutletTransactionEntity();
            otransaction2.setTransactionDate(date3);
            otransaction2.setTransactionAmount(102.98);
            otransaction2.setOutlet(outletSessionBean.getOutletById(217));
            outletTransactionSessionBean.addTransaction(otransaction2); //persisting transaction
            outletSessionBean.getOutletById(217).addTransaction(otransaction2);
            outletSessionBean.updateOutlet(outletSessionBean.getOutletById(217));

            OutletTransactionEntity otransaction3 = new OutletTransactionEntity();
            otransaction3.setTransactionDate(date4);
            otransaction3.setTransactionAmount(345.98);
            otransaction3.setOutlet(outletSessionBean.getOutletById(217));
            outletTransactionSessionBean.addTransaction(otransaction3); //persisting transaction
            outletSessionBean.getOutletById(217).addTransaction(otransaction3);
            outletSessionBean.updateOutlet(outletSessionBean.getOutletById(217));

            OutletTransactionEntity otransaction4 = new OutletTransactionEntity();
            otransaction4.setTransactionDate(date5);
            otransaction4.setTransactionAmount(11.98);
            otransaction4.setOutlet(outletSessionBean.getOutletById(412));
            outletTransactionSessionBean.addTransaction(otransaction4); //persisting transaction
            outletSessionBean.getOutletById(412).addTransaction(otransaction4);
            outletSessionBean.updateOutlet(outletSessionBean.getOutletById(412));

            OutletTransactionEntity otransaction5 = new OutletTransactionEntity();
            otransaction5.setTransactionDate(date6);
            otransaction5.setTransactionAmount(22.01);
            otransaction5.setOutlet(outletSessionBean.getOutletById(412));
            outletTransactionSessionBean.addTransaction(otransaction5); //persisting transaction
            outletSessionBean.getOutletById(412).addTransaction(otransaction5);
            outletSessionBean.updateOutlet(outletSessionBean.getOutletById(412));


            OutletTransactionEntity otransaction6 = new OutletTransactionEntity();
            otransaction6.setTransactionDate(date6);
            otransaction6.setTransactionAmount(18.01);
            otransaction6.setOutlet(outletSessionBean.getOutletById(516));
            outletTransactionSessionBean.addTransaction(otransaction6); //persisting transaction
            outletSessionBean.getOutletById(516).addTransaction(otransaction6);
            outletSessionBean.updateOutlet(outletSessionBean.getOutletById(516));



            OutletTransactionEntity otransaction7 = new OutletTransactionEntity();
            otransaction7.setTransactionDate(date5);
            otransaction7.setTransactionAmount(78.01);
            otransaction7.setOutlet(outletSessionBean.getOutletById(516));
            outletTransactionSessionBean.addTransaction(otransaction7); //persisting transaction
            outletSessionBean.getOutletById(516).addTransaction(otransaction7);
            outletSessionBean.updateOutlet(outletSessionBean.getOutletById(516));


            OutletTransactionEntity otransaction8 = new OutletTransactionEntity();
            otransaction8.setTransactionDate(date2);
            otransaction8.setTransactionAmount(189.32);
            otransaction8.setOutlet(outletSessionBean.getOutletById(516));
            outletTransactionSessionBean.addTransaction(otransaction8); //persisting transaction
            outletSessionBean.getOutletById(516).addTransaction(otransaction8);
            outletSessionBean.updateOutlet(outletSessionBean.getOutletById(516));


            OutletTransactionEntity otransaction9 = new OutletTransactionEntity();
            otransaction9.setTransactionDate(date3);
            otransaction9.setTransactionAmount(3264.01);
            otransaction9.setOutlet(outletSessionBean.getOutletById(516));
            outletTransactionSessionBean.addTransaction(otransaction9); //persisting transaction
            outletSessionBean.getOutletById(516).addTransaction(otransaction9);
            outletSessionBean.updateOutlet(outletSessionBean.getOutletById(516));


            OutletTransactionEntity otransaction10 = new OutletTransactionEntity();
            otransaction10.setTransactionDate(date5);
            otransaction10.setTransactionAmount(9253.01);
            otransaction10.setOutlet(outletSessionBean.getOutletById(516));
            outletTransactionSessionBean.addTransaction(otransaction10); //persisting transaction
            outletSessionBean.getOutletById(516).addTransaction(otransaction10);
            outletSessionBean.updateOutlet(outletSessionBean.getOutletById(516));

            System.out.println("transactions saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding transaction", ""));
            return;
        }
        System.out.println("Insert transaction into database");

        addMessage("Transaction1! Created!");
    }
    
    public void createItem()
    {
        
        SMItemEntity item = new SMItemEntity();
        item.setItemName("Coke Zero");
        item.setItemPrice(3.30);
        
        SMItemEntity item2 = new SMItemEntity();
        item2.setItemName("IPhone 5S");
        item2.setItemPrice(5888.00);
        
        SMItemEntity item3 = new SMItemEntity();
        item3.setItemName("Logic Mouse");
        item3.setItemPrice(23.09);
        
        SMItemEntity item4 = new SMItemEntity();
        item4.setItemName("MacPro");
        item4.setItemPrice(1788.99);
        
        SMItemEntity item5 = new SMItemEntity();
        item5.setItemName("Prada Wallet");
        item5.setItemPrice(568.00);
        
        SMItemEntity item6 = new SMItemEntity();
        item6.setItemName("Return to Tiffany");
        item6.setItemPrice(458.00);
        
        try {
            System.out.println("Saving merchant....");

            sMItemSessionBean.addItem(item);
            sMItemSessionBean.addItem(item2);
            sMItemSessionBean.addItem(item3);
            sMItemSessionBean.addItem(item4);
            sMItemSessionBean.addItem(item5);
            sMItemSessionBean.addItem(item6);
            
            System.out.println("Items saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding items", ""));
            return;
        }
        System.out.println("Insert merchant into database");

        addMessage("Items Created!");
    }

    public void initla(ActionEvent event) throws ExistException {
        createMerchant();
        createOutlet();
        createContract();
        createTransaction();
        createItem();
    }
}
