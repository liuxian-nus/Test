/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowTicketSaleEntity;
import java.util.List;
import javax.ejb.Remote;
import javax.persistence.NoResultException;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface ShowTicketSaleSessionBeanRemote {

    ShowTicketSaleEntity addShowTicketSale(ShowTicketSaleEntity showTicketSale);

    boolean deleteShowTicketSale(Long showTicketSaleId);

    List<ShowTicketSaleEntity> getAllShowTicketSales() throws NoResultException;

    boolean updateShowTicketSale(ShowTicketSaleEntity showTicketSale);
    
}
