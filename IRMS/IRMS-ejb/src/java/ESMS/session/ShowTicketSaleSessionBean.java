/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowTicketSaleEntity;
import java.util.ArrayList;
import java.util.Iterator;
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
public class ShowTicketSaleSessionBean implements ShowTicketSaleSessionBeanRemote {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private ShowTicketSaleEntity showTicketSale;
    private List<ShowTicketSaleEntity> showTicketSales = new ArrayList<ShowTicketSaleEntity>();
    private List<ShowTicketSaleEntity> temp = new ArrayList<ShowTicketSaleEntity>();
    
    public ShowTicketSaleSessionBean(){}
    
    public List<ShowTicketSaleEntity> getShowTicketSalesByEmail(String memberEmail){
        temp = getAllShowTicketSales();
        Iterator<ShowTicketSaleEntity> itr = temp.iterator();
        while (itr.hasNext()) {
            showTicketSale = itr.next();
            if (showTicketSale.getMemberEmail().equalsIgnoreCase(memberEmail)) {
               showTicketSales.add(showTicketSale);
            }
        }
        return showTicketSales;
    }
    
    @Override
    public ShowTicketSaleEntity addShowTicketSale(ShowTicketSaleEntity showTicketSale) {
        em.persist(showTicketSale);
        return showTicketSale;
    }
    
    @Override
    public boolean deleteShowTicketSale(Long showTicketSaleId) {
        showTicketSale = em.find(ShowTicketSaleEntity.class, showTicketSaleId);
        if (showTicketSale == null) {
            return false;
        }
        em.remove(showTicketSale);
        return true;
    }
    
    @Override
    public boolean updateShowTicketSale(ShowTicketSaleEntity showTicketSale) {
        em.merge(showTicketSale);
        System.out.println("ShowTicketSaleSessionBean: " + showTicketSale.getShowTicketSaleId() + " is successfully updated");
        return true;
    }
    
    @Override
    public List<ShowTicketSaleEntity> getAllShowTicketSales() throws NoResultException {
        Query q = em.createQuery("SELECT m FROM ShowTicketSaleEntity m");
        return q.getResultList();
    }
}
