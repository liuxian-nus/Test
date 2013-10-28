/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import CRMS.session.CPasswordHashSessionBean;
import ERMS.session.EPasswordHashSessionBean;
import SMMS.entity.ContractEntity;
import SMMS.entity.ContracteventEntity;
import SMMS.entity.MerchantEntity;
import SMMS.entity.OutletEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.ContracteventSessionBean;
import SMMS.session.MerchantSessionBean;
import SMMS.session.OutletSessionBean;
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
public class smmsInitManagedBean {
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
        merchant.setMerchantName("dayanqi");
        merchant.setMerchantPassword(ePasswordHashSessionBean.hashPassword("M3000"));
        merchant.setMerchantHP("12345678");
        merchant.setMerchantAddress("35 Prince George's Park");
        merchant.setSecurityQuestion("What is your mother's original surname?");
        merchant.setAnswer("Gu");
        merchant.setPartnerType("shoppingMall");


        MerchantEntity merchant2 = new MerchantEntity();
        merchant2.setMerchantEmail("s.er3na.j@gmail.com");
        merchant2.setMerchantName("meizi");
        merchant2.setMerchantPassword(ePasswordHashSessionBean.hashPassword("M4000"));
        merchant2.setMerchantHP("87654321");
        merchant2.setMerchantAddress("30 Prince George's Park");
        merchant2.setSecurityQuestion("What is your mother's original surname?");
        merchant2.setAnswer("Gu");
        merchant2.setPartnerType("shoppingMall");

        MerchantEntity merchant3 = new MerchantEntity();
        merchant3.setMerchantEmail("a0077867@nus.edu.sg");
        merchant3.setMerchantName("liudede");
        merchant3.setMerchantPassword(ePasswordHashSessionBean.hashPassword("M5000"));
        merchant3.setMerchantHP("09876234");
        merchant3.setMerchantAddress("25 Prince George's Park");
        merchant3.setSecurityQuestion("What is your mother's original surname?");
        merchant3.setAnswer("Gu");
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

        OutletEntity outlet = new OutletEntity();
        outlet.setOutletLevel(5);
        outlet.setOutletNo(03);
        outlet.setOutletId(5, 03);
        outlet.setOutletType("banks");
        outlet.setOutletArea(21.85);

        OutletEntity outlet2 = new OutletEntity();
        outlet2.setOutletLevel(2);
        outlet2.setOutletNo(31);
        outlet2.setOutletId(2, 31);
        outlet2.setOutletType("lifestyle");
        outlet2.setOutletArea(18.27);

        OutletEntity outlet3 = new OutletEntity();
        outlet3.setOutletLevel(5);
        outlet3.setOutletNo(16);
        outlet3.setOutletId(5, 16);
        outlet3.setOutletType("sports");
        outlet3.setOutletArea(21.33);

        OutletEntity outlet4 = new OutletEntity();
        outlet3.setOutletLevel(4);
        outlet3.setOutletNo(31);
        outlet3.setOutletId(4, 31);
        outlet3.setOutletType("celebRestaurants");
        outlet3.setOutletArea(16.93);

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
            OutletEntity outleta = outletSessionBean.getOutletById(516);
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
            event1.setEventStatus("newRequest");
            event1.setEventContract(contract1);
            contracteventSessionBean.addContractevent(event1);
            System.out.println("Contract saved....." + event1.getContracteventId());


            contractSessionBean.addContractevent(contract1.getContractId(), event1.getContracteventId());
            merchantSessionBean.addContractInMerchant(contract1.getContractId(), merchanta.getMerchantEmail());

            outleta.setContract(contract1);
            outleta.setOutletStatus("unavailable");
            outletSessionBean.updateOutlet(outleta);

            System.out.println("Contract saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding merchant", ""));
            return;
        }



        ContractEntity contract2 = new ContractEntity();
        Date cidate1 = new Date(2014, 6, 1);
        Date codate1 = new Date(2018, 6, 1);
        try {

            System.out.println("Saving cart....");

            MerchantEntity merchantb = merchantSessionBean.getMerchantById("s.er3na.j@gmail.com");
            OutletEntity outletb = outletSessionBean.getOutletById(503);
            contract1.setMerchant(merchantb);
            contract1.setOutlet(outletb);
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
            contracteventSessionBean.addContractevent(event2);
            System.out.println("Contract saved....." + event2.getContracteventId());


            contractSessionBean.addContractevent(contract1.getContractId(), event2.getContracteventId());
            merchantSessionBean.addContractInMerchant(contract1.getContractId(), merchantb.getMerchantEmail());

            outletb.setContract(contract1);
            outletSessionBean.updateOutlet(outletb);

            System.out.println("Contract saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding merchant", ""));
            return;
        }

        System.out.println("Insert cart into database");
        addMessage("Carts! Created!");
    }
    
    public void initla(ActionEvent event)
    {
        createMerchant();
        createOutlet();
        createContract();
    }
}
