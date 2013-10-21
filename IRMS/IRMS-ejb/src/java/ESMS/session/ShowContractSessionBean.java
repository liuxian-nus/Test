/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowContractEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ser3na
 */
@Stateless
public class ShowContractSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private ShowContractEntity showContract;

    public ShowContractSessionBean() {
    }

    public ShowContractEntity addShowContract(ShowContractEntity showContract) {
        em.persist(showContract);
        return showContract;
    }

    public boolean deleteShowContract(Long showContractId) {
        showContract = em.find(ShowContractEntity.class, showContractId);
        if (showContract == null) {
            System.out.println("deleteShow: show Contract does not exist!");
            return false;
        }
        em.remove(showContract);
        return true;
    }

    public boolean updateShowContract(ShowContractEntity showContract) {
        em.merge(showContract);
        System.out.println("ShowContractSessionBean: show " + showContract.getShowMerchantName() + " is successfully updated");
        return true;
    }

    public List<ShowContractEntity> getAllShowContracts() throws NoResultException {
        Query q = em.createQuery("SELECT m FROM ShowContractEntity m");
        return q.getResultList();
    }

    public ShowContractEntity getShowContract() {
        return showContract;
    }

    public void setShowContract(ShowContractEntity showContract) {
        this.showContract = showContract;
    }
}
