/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowEntity;
import ESMS.entity.ShowScheduleEntity;
import ESMS.entity.ShowTicketEntity;
import java.util.List;
import javax.ejb.Remote;
import javax.persistence.NoResultException;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface ShowSessionBeanRemote {

    ShowEntity addShow(ShowEntity show);

    void addShowSchedule(Long showId, ShowScheduleEntity showSchedule);

    void addShowTicket(Long showScheduleId, ShowTicketEntity showTicket);

    boolean deleteShow(Long showId);

    List<ShowScheduleEntity> getAllShowSchedules(Long showId) throws NoResultException;

    List<ShowEntity> getAllShows() throws NoResultException;

    ShowEntity getShowById(Long showId);

    List<ShowEntity> getShowByName(String searchName);

    boolean updateShow(ShowEntity show);

    void uploadFile(Long showId, byte[] buffer);

    void uploadImage(Long showId, String fileName);
    
}
