/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowEntity;
import ESMS.entity.ShowTicketSaleEntity;
import java.util.List;
import javax.ejb.Remote;
import javax.persistence.NoResultException;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface ShowBillingSessionBeanRemote {

    List<ShowTicketSaleEntity> getAllSelectedShowTicketSales(Long showId);

    //Methods
    List<ShowEntity> getAllSelectedShows() throws NoResultException;

    //Getters and Setters
    ShowEntity getShow();

    List<ShowEntity> getShowList();

    ShowTicketSaleEntity getShowTicketSale();

    List<ShowTicketSaleEntity> getShowTicketSaleList();

    List<ShowTicketSaleEntity> getShowTicketSales();

    List<ShowEntity> getShows();

    void setShow(ShowEntity show);

    void setShowList(List<ShowEntity> showList);

    void setShowTicketSale(ShowTicketSaleEntity showTicketSale);

    void setShowTicketSaleList(List<ShowTicketSaleEntity> showTicketSaleList);

    void setShowTicketSales(List<ShowTicketSaleEntity> showTicketSales);

    void setShows(List<ShowEntity> shows);
    
}
