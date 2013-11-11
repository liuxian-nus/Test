/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.RoomPriceEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface RoomPriceSessionBeanRemote {

    void createPrice(RoomPriceEntity price);

    List<RoomPriceEntity> getAllRoomPrices();

    void updatePrice(RoomPriceEntity price);
    
}
