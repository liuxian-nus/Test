/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowScheduleEntity;
import java.util.List;
import javax.ejb.Remote;
import javax.persistence.NoResultException;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface ShowScheduleSessionBeanRemote {

    ShowScheduleEntity addShowSchedule(ShowScheduleEntity showSchedule);

    void deleteShowSchedule(Long showScheduleId);

    List<ShowScheduleEntity> getAllShowSchedules() throws NoResultException;

    ShowScheduleEntity getShowScheduleById(Long showScheduleId);

    boolean updateShowSchedule(ShowScheduleEntity showSchedule);
    
}
