/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.RoomServiceEntity;
import Exception.ExistException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface RoomServiceSessionBeanRemote {

    void addRoomService(RoomServiceEntity newRoomService);

    List<RoomServiceEntity> getAllRoomServices() throws ExistException;

    RoomServiceEntity getServiceByName(String roomServiceName) throws ExistException;

    boolean removeRoomService(String roomServiceName) throws ExistException;

    boolean updateRoomService(String roomServiceName, String category, double roomServicePrice) throws ExistException;
    
}
