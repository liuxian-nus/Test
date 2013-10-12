/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowMerchantEntity;
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
public class ShowMerchantSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private ShowMerchantEntity showMerchant;

    public ShowMerchantSessionBean() {
    }

    public void addShowMerchant(ShowMerchantEntity showMerchant) {
        em.persist(showMerchant);
    }
    
    public void updateShowMerchant(ShowMerchantEntity showMerchant){
        em.merge(showMerchant);
    }
    
    public boolean deleteShowMerchant(String showMerchantEmail){
        showMerchant = em.find(ShowMerchantEntity.class, showMerchantEmail);
        if (showMerchant == null) {
            return false;
        }
        em.remove(showMerchant);
        return true;
    }
    
    public List<ShowMerchantEntity> getAllShowMerchants() throws NoResultException {
        Query q = em.createQuery("SELECT m FROM ShowMerchantEntity m");
        return q.getResultList();
    }

    public ShowMerchantEntity getShowMerchant() {
        return showMerchant;
    }

    public void setShowMerchant(ShowMerchantEntity showMerchant) {
        this.showMerchant = showMerchant;
    }
}
