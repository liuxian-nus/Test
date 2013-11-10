/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.ContractEntity;
import SMMS.entity.ContracteventEntity;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface ContractSessionBeanRemote {

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    ContractEntity addContract(ContractEntity contract);

    //add new contractEvent
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    ContracteventEntity addContractevent(Long contractId, Long cevent) throws ExistException;

    List<ContractEntity> getAllContracts();

    ContractEntity getContractById(Long contractId) throws ExistException;

    List<ContractEntity> getContractByMerchant(String merchantEmail);

    ContractEntity getContractByOutlet(int outletId);

    List<ContractEntity> getEarlyTerminationRequestContract();

    List<ContractEntity> getNewRequestContract();

    List<ContractEntity> getRenewRequestContract();

    int getSize(ContractEntity newct);

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    void persist(Object object);

    void removeContract(ContractEntity contract) throws ExistException;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    boolean updateContract(ContractEntity contract) throws ExistException;
    
}
