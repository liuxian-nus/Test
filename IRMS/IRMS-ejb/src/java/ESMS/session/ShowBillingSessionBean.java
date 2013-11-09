/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowEntity;
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
public class ShowBillingSessionBean implements ShowBillingSessionBeanRemote {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private ShowEntity show;
    private ShowTicketSaleEntity showTicketSale;
    private List<ShowEntity> shows;
    private List<ShowEntity> showList;
    private List<ShowTicketSaleEntity> showTicketSales;
     private List<ShowTicketSaleEntity> showTicketSaleList;

    //Constructor
    public ShowBillingSessionBean() {
    }

    //Methods
    @Override
    public List<ShowEntity> getAllSelectedShows() throws NoResultException {
        showList = new ArrayList<ShowEntity>();
        Query q = em.createQuery("SELECT m FROM ShowEntity m");
        shows = q.getResultList();
        System.err.println("size: "+shows.size());
        Iterator<ShowEntity> itr = shows.iterator();

        while (itr.hasNext()) {
            show = itr.next();
            if (show.getShowType().equals("External") && show.isShowPaymentStatus() == true && show.isShowStatus() == true) {
                if (show != null) {
                    showList.add(show);
                    System.err.println("ShowsReadyForBill: " + show.getShowName());
                }
            }
        }
        return showList;
    }
    
    @Override
    public List<ShowTicketSaleEntity> getAllSelectedShowTicketSales(Long showId){
        showTicketSales = new ArrayList<ShowTicketSaleEntity>();
        showTicketSaleList = new ArrayList<ShowTicketSaleEntity>();
        Query q = em.createQuery("SELECT m FROM ShowTicketSaleEntity m");
        showTicketSales = q.getResultList();
        System.err.println("sales size: "+showTicketSales.size());
        Iterator<ShowTicketSaleEntity> itr = showTicketSales.iterator();
        while (itr.hasNext()) {
            showTicketSale = itr.next();
            if (showTicketSale.getShow().getShowId().equals(showId)) {
                if (showTicketSale != null) {
                    showTicketSaleList.add(showTicketSale);
                    System.err.println("ShowTicketSale ID: " + showTicketSale.getShowTicketSaleId());
                }
            }
        }
        System.err.println("sales list size: "+showTicketSaleList.size());
        return showTicketSaleList;
    }

    //Getters and Setters
    @Override
    public ShowEntity getShow() {
        return show;
    }

    @Override
    public void setShow(ShowEntity show) {
        this.show = show;
    }

    @Override
    public List<ShowEntity> getShows() {
        return shows;
    }

    @Override
    public void setShows(List<ShowEntity> shows) {
        this.shows = shows;
    }

    @Override
    public List<ShowEntity> getShowList() {
        return showList;
    }

    @Override
    public void setShowList(List<ShowEntity> showList) {
        this.showList = showList;
    }

    @Override
    public ShowTicketSaleEntity getShowTicketSale() {
        return showTicketSale;
    }

    @Override
    public void setShowTicketSale(ShowTicketSaleEntity showTicketSale) {
        this.showTicketSale = showTicketSale;
    }

    @Override
    public List<ShowTicketSaleEntity> getShowTicketSales() {
        return showTicketSales;
    }

    @Override
    public void setShowTicketSales(List<ShowTicketSaleEntity> showTicketSales) {
        this.showTicketSales = showTicketSales;
    }

    @Override
    public List<ShowTicketSaleEntity> getShowTicketSaleList() {
        return showTicketSaleList;
    }

    @Override
    public void setShowTicketSaleList(List<ShowTicketSaleEntity> showTicketSaleList) {
        this.showTicketSaleList = showTicketSaleList;
    }
}
