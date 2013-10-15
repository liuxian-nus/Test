/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import ERMS.entity.EmployeeEntity;
import Exception.ExistException;
import SMMS.entity.ContractEntity;
import SMMS.entity.MerchantEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cookie
 */
@Stateless
@LocalBean
public class MerchantSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private ContractEntity contract;
  
    MerchantEntity merchant = new MerchantEntity();
    public MerchantSessionBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }

    public MerchantEntity addMerchant (MerchantEntity merchant)
    {
        em.persist(merchant);
        return merchant;
    }
    
     public boolean updateMerchant (MerchantEntity merchant)
    {
        em.merge(merchant);
        System.out.println("MerchantSessionBean: merchant " + merchant.getId() + " is successfully updated");
        return true;
    }
     
    public List<MerchantEntity> getAllMerchants() throws ExistException{
        Query q = em.createQuery("SELECT m FROM MerchantEntity m");
        List merchantList = new ArrayList<EmployeeEntity>();
         for (Object o: q.getResultList()) { 
            MerchantEntity m = (MerchantEntity) o; 
            merchantList.add(m); 
        } 
        if(merchantList.isEmpty())  throw new ExistException("Merchant database is empty!");
        return merchantList;     
    }
    
     public MerchantEntity getMerchantById(String merchantId) throws ExistException{
        merchant = em.find(MerchantEntity.class, merchantId);
        if(merchant == null)  throw new ExistException("Merchant does not exist!");
        return merchant;
    }
    
    public ContractEntity addContract(Long contractId, String merchantId) throws ExistException {
        contract = em.find(ContractEntity.class, contractId);
        merchant = em.find(MerchantEntity.class, merchantId);
        if (contract == null) {
            throw new ExistException("ContractSessionBean-->ExistException-->Invalid contract event!");
        }
        merchant.addContract(contract);
        em.merge(merchant);
        System.out.println("ContractSessionBean--> Merchant" + merchant.getMerchantEmail() + " include new contract " + contract.getContractId());
        return contract;
    }
    
}
