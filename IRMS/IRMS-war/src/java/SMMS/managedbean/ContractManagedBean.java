/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import Exception.ExistException;
import SMMS.entity.ContractEntity;
import SMMS.entity.ContracteventEntity;
import SMMS.entity.MerchantEntity;
import SMMS.entity.OutletEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.ContracteventSessionBean;
import SMMS.session.MerchantSessionBean;
import SMMS.session.OutletSessionBean;
import java.util.ArrayList;
import java.util.List;
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
public class ContractManagedBean {

    @EJB
    private ContracteventSessionBean contracteventSessionBean;
    @EJB
    private OutletSessionBean outletSessionBean;
    @EJB
    private MerchantSessionBean merchantSessionBean;
    @EJB
    private ContractSessionBean contractSessionBean;

    public ContracteventSessionBean getContracteventSessionBean() {
        return contracteventSessionBean;
    }

    public void setContracteventSessionBean(ContracteventSessionBean contracteventSessionBean) {
        this.contracteventSessionBean = contracteventSessionBean;
    }

    public OutletSessionBean getOutletSessionBean() {
        return outletSessionBean;
    }

    public void setOutletSessionBean(OutletSessionBean outletSessionBean) {
        this.outletSessionBean = outletSessionBean;
    }

    public MerchantSessionBean getMerchantSessionBean() {
        return merchantSessionBean;
    }

    public void setMerchantSessionBean(MerchantSessionBean merchantSessionBean) {
        this.merchantSessionBean = merchantSessionBean;
    }

    public ContractSessionBean getContractSessionBean() {
        return contractSessionBean;
    }

    public void setContractSessionBean(ContractSessionBean contractSessionBean) {
        this.contractSessionBean = contractSessionBean;
    }
    private ContractEntity contract;
    private List<MerchantEntity> merchants;
    private MerchantEntity merchant;
    private OutletEntity outlet;
    private List<OutletEntity> outlets;
    private String merchantId; //merchant name unique
    private int outletId;
    private ContracteventEntity newevent;

    public List<MerchantEntity> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<MerchantEntity> merchants) {
        this.merchants = merchants;
    }

    public MerchantEntity getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantEntity merchant) {
        this.merchant = merchant;
    }

    public OutletEntity getOutlet() {
        return outlet;
    }

    public void setOutlet(OutletEntity outlet) {
        this.outlet = outlet;
    }

    public List<OutletEntity> getOutlets() {
        return outlets;
    }

    public void setOutlets(List<OutletEntity> outlets) {
        this.outlets = outlets;
    }

    public ContracteventEntity getNewevent() {
        return newevent;
    }

    public void setNewevent(ContracteventEntity newevent) {
        this.newevent = newevent;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public int getOutletId() {
        return outletId;
    }

    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    /**
     * Creates a new instance of ContractManagedBean
     */
    public ContractManagedBean() {
        contract = new ContractEntity();
        newevent = new ContracteventEntity();
    }

    public void addContract(ActionEvent event) throws ExistException {
        System.out.println("in adding contract" + merchantId + "and outlet ID:" + outletId);
        try {
            newevent.setEventStatus("new");
            contracteventSessionBean.addContractevent(newevent);
            System.out.println("after creating contract event" + newevent.getContracteventId());

            contract.setMerchant(merchantSessionBean.getMerchantById(merchantId)); //adding new merchant
            contract.setOutlet(outletSessionBean.getOutletById(outletId)); //adding new outlet
            contract.addContractEvent(newevent);// adding new event to contract
            contractSessionBean.addContract(contract);
            System.out.println("after persisting contract" + contract.getContractId());

            merchant = merchantSessionBean.getMerchantById(merchantId);
            merchant.addContract(contract);
            merchantSessionBean.updateMerchant(merchant);
            System.out.println("after adding contract to merchant" + merchant.getMerchantEmail());

            outlet = outletSessionBean.getOutletById(outletId);
            outlet.setContract(contract);
            outletSessionBean.updateOutlet(outlet);
            System.out.println("after adding contract to outlet" + outlet.getOutletId());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new contract", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contract has been added.", ""));
    }

    public List<String> getAllMerchants() throws ExistException {
        System.out.println("NO4: we are in ALL merchants bean BEFORE");
        List<String> results = new ArrayList<String>();

        List<MerchantEntity> merchantList = merchantSessionBean.getAllMerchants();
        for (Object o : merchantList) {
            MerchantEntity rve = (MerchantEntity) o;
            results.add((rve.getMerchantEmail()).toString());
        }
        System.out.println("NO5: we are in complete bean AFTER");
        return results;
    }

    public List<Integer> getAvailableOutlets() throws ExistException {
        System.out.println("NO4: we are in ALL outlets bean BEFORE");
        List<Integer> results = new ArrayList<Integer>();

        List<OutletEntity> merchantList = outletSessionBean.getAvailableOutlets();
        for (Object o : merchantList) {
            OutletEntity rve = (OutletEntity) o;
            results.add((rve.getOutletId()));
        }
        System.out.println("NO5: we are in complete bean AFTER");
        return results;
    }
}
//public class Main {
// 
//	public static void main(String[] args) {
//		Timer timer = new Timer();
//		TimerTask tt = new TimerTask(){
//			public void run(){
//				Calendar cal = Calendar.getInstance(); //this is the method you should use, not the Date(), because it is desperated.
// 
//				int hour = cal.get(Calendar.HOUR_OF_DAY);//get the hour number of the day, from 0 to 23
// 
//				if(hour == 14){
//					System.out.println("doing the scheduled task");
//				}
//			}
//		};
//		timer.schedule(tt, 1000, 1000*5);//	delay the task 1 second, and then run task every five seconds
//	}
//}