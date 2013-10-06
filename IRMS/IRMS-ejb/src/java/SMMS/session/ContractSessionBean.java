/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.BillEntity;
import SMMS.entity.ContractEntity;
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

    public ContractSessionBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Object object) {
        em.persist(object);
    }
    ContractEntity contract = new ContractEntity();
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ContractEntity addContract(ContractEntity contract) {
        em.persist(contract);
        return contract;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeContract(ContractEntity contract) throws ExistException {
        if (contract == null) {
            throw new ExistException("contract doesn't exist!");
        }
        if ((contract.getContractStatus()!="new")||(contract.getContractStatus()!="rejected")) {
            throw new ExistException("contract cannot be removed because it has been approved!");
        }
        em.remove(contract);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean updateContract(ContractEntity contract) throws ExistException {
        if ((contract.getContractStatus()!="new")||(contract.getContractStatus()!="rejected")) {
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
    
    
}
