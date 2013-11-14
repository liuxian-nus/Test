/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowContractEntity;
import java.util.List;
import javax.ejb.LocalBean;
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
@LocalBean
public class ShowContractSessionBean implements ShowContractSessionBeanRemote {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private ShowContractEntity showContract;

    public ShowContractSessionBean() {
    }

    @Override
    public ShowContractEntity addShowContract(ShowContractEntity showContract) {
        em.persist(showContract);
        return showContract;
    }

    @Override
    public boolean deleteShowContract(Long showContractId) {
        showContract = em.find(ShowContractEntity.class, showContractId);
        if (showContract.getShow() != null) {
            return false;
        }
        if (showContract == null) {
            System.out.println("deleteShow: show Contract does not exist!");
            return false;
        }
        em.remove(showContract);
        return true;
    }

    @Override
    public boolean updateShowContract(ShowContractEntity showContract) {
        em.merge(showContract);
        System.out.println("ShowContractSessionBean: show " + showContract.getShowMerchantName() + " is successfully updated");
        return true;
    }

    @Override
    public List<ShowContractEntity> getAllShowContracts() throws NoResultException {
        Query q = em.createQuery("SELECT m FROM ShowContractEntity m");
        return q.getResultList();
    }

    @Override
    public ShowContractEntity thisShowContract(Long showContractId) {
        showContract = em.find(ShowContractEntity.class, showContractId);
        return showContract;
    }

    @Override
    public ShowContractEntity getShowContract() {
        return showContract;
    }

    @Override
    public void setShowContract(ShowContractEntity showContract) {
        this.showContract = showContract;
    }
}
