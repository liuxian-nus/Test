/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowContractEntity;
import java.util.List;
import javax.ejb.Remote;
import javax.persistence.NoResultException;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface ShowContractSessionBeanRemote {

    ShowContractEntity addShowContract(ShowContractEntity showContract);

    boolean deleteShowContract(Long showContractId);

    List<ShowContractEntity> getAllShowContracts() throws NoResultException;

    ShowContractEntity getShowContract();

    void setShowContract(ShowContractEntity showContract);

    ShowContractEntity thisShowContract(Long showContractId);

    boolean updateShowContract(ShowContractEntity showContract);
    
}
