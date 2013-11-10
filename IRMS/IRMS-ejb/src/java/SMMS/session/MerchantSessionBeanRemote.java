/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.ContractEntity;
import SMMS.entity.MerchantEntity;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Timeout;
import javax.ejb.Timer;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface MerchantSessionBeanRemote {

    ContractEntity addContractInMerchant(Long contractId, String merchantId) throws ExistException;

    MerchantEntity addMerchant(MerchantEntity merchant);

    void createTimers();

    List<MerchantEntity> getAllMerchants() throws ExistException;

    MerchantEntity getMerchantById(String merchantId) throws ExistException;

    @Timeout
    void handleTimeout(Timer timer);
    //    @Override
    //    public void ejbTimeout(javax.ejb.Timer timer) {
    //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //    }
    ////

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    void persist(Object object);

    boolean updateMerchant(MerchantEntity merchant);
    
}
