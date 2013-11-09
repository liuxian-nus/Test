/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowTicketEntity;
import java.util.List;
import javax.ejb.Remote;
import javax.persistence.NoResultException;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface ShowTicketSessionBeanRemote {

    ShowTicketEntity addShowTicket(ShowTicketEntity showTicket);

    boolean deleteShowTicket(Long showTicketId);

    List<ShowTicketEntity> getAllShowTickets() throws NoResultException;

    ShowTicketEntity getShowTicketById(Long showTicketId);

    void updateQuantity(Long showTicketId, int showTicketQuota);

    boolean updateShowTicket(ShowTicketEntity showTicket);
    
}
