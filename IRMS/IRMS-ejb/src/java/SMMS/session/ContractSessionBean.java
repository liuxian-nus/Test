/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.BillEntity;
import SMMS.entity.ContractEntity;
import SMMS.entity.ContracteventEntity;
import SMMS.entity.OutletEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cookie
 */
@Stateless
@LocalBean
public class ContractSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private ContractEntity contract;
    private ContracteventEntity newevent;

    public ContractSessionBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Object object) {
        em.persist(object);
    }
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ContractEntity addContract(ContractEntity contract) {
        em.persist(contract);
        System.err.println("No3: In contractsessionbean adding contract: ");
        return contract;
    }
    
    //add new contractEvent
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ContracteventEntity addContractevent(Long contractId, Long cevent) throws ExistException {
        contract = em.find(ContractEntity.class, contractId);
        newevent = em.find(ContracteventEntity.class, cevent);
        if (newevent == null) {
            throw new ExistException("ContractSessionBean-->ExistException-->Invalid contract event!");
        }
        contract.addContractEvent(newevent);
        em.merge(contract);
        System.out.println("ContractSessionBean--> " + contract.getContractId() + " new include new service " + newevent.getContracteventId());
        return newevent;
    }

    public void removeContract(ContractEntity contract) throws ExistException {
        if (contract == null) {
            throw new ExistException("contract doesn't exist!");
        }
         em.remove(contract);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean updateContract(ContractEntity contract) throws ExistException {
        if ((contract.getStatus()!="new")||(contract.getStatus()!="rejected")) {
            throw new ExistException("contract cannot be updated because it has been approved!");
        }
        em.merge(contract);
        return true;
    }
    
    public List<ContractEntity> getContractByMerchant(String merchantEmail) {
        System.err.println("in get contract by merchant session bean");
        Query q = em.createQuery("SELECT m FROM ContractEntity m");
        List OutletList = new ArrayList<ContractEntity>();
        for (Object o : q.getResultList()) {
            ContractEntity m = (ContractEntity) o;
            if (m.getMerchant().getMerchantEmail()== merchantEmail) {
                OutletList.add(m);
            }
        }
        System.err.println("in get contract by merchant sessionbean: outlet list size=" + OutletList.size());
        return OutletList;
    }
    
    public List<ContractEntity> getContractByOutlet(int outletId) {
        System.err.println("in get contract by outlet session bean");
        Query q = em.createQuery("SELECT m FROM BillEntity m");
        List TransactionList = new ArrayList<ContractEntity>();
        for (Object o : q.getResultList()) {
            ContractEntity m = (ContractEntity) o;
            if (m.getOutlet().getOutletId()== outletId) {
                TransactionList.add(m);
            }
        }
        System.err.println("in get contract by outlet sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }
    
    public ContractEntity getContractById(String contractId) throws ExistException{
        contract = em.find(ContractEntity.class, contractId);
        if(contract == null)  throw new ExistException("Contract does not exist!");
        return contract;
    }
    
     public List<ContractEntity> getAllContracts() {
        Query q = em.createQuery("SELECT m FROM ContractEntity m");
        List ContractList = new ArrayList<ContractEntity>();
        for (Object o : q.getResultList()) {
            ContractEntity m = (ContractEntity) o;
            ContractList.add(m);
        }
        return ContractList;
    }
    
     public int getSize(ContractEntity newct) {
     
        return newct.getContractEvent().size();
     }
     
     public List<ContractEntity> getNewRequestContract() {
        System.err.println("in get new request contract session bean");
        Query q = em.createQuery("SELECT m FROM ContractEntity m");
        List TransactionList = new ArrayList<ContractEntity>();
        for (Object o : q.getResultList()) {
            ContractEntity m = (ContractEntity) o;
            if (m.getLast().getEventStatus()=="newRequest") {
                TransactionList.add(m);
            }
        }
        System.err.println("in get contract by outlet sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }
    
         public List<ContractEntity> getRenewRequestContract() {
        System.err.println("in get new request contract session bean");
        Query q = em.createQuery("SELECT m FROM ContractEntity m");
        List TransactionList = new ArrayList<ContractEntity>();
        for (Object o : q.getResultList()) {
            ContractEntity m = (ContractEntity) o;
            if (m.getLast().getEventStatus()=="renewRequest") {
                TransactionList.add(m);
            }
        }
        System.err.println("in get contract by outlet sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }
         
             public List<ContractEntity> getEarlyTerminationRequestContract() {
        System.err.println("in get new request contract session bean");
        Query q = em.createQuery("SELECT m FROM ContractEntity m");
        List TransactionList = new ArrayList<ContractEntity>();
        for (Object o : q.getResultList()) {
            ContractEntity m = (ContractEntity) o;
            if (m.getLast().getEventStatus()=="earlyTerminationRequest") {
                TransactionList.add(m);
            }
        }
        System.err.println("in get contract by outlet sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }
 
    
}
