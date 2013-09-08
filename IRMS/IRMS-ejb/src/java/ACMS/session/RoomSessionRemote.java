/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.RoomEntity;
import ACMS.entity.RoomServiceEntity;
import Exception.ExistException;
import java.util.Date;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author liudazhi
 */
@Remote
public interface RoomSessionRemote {

    //add new charged service
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    RoomServiceEntity addRoomService(int roomId, String roomServiceName) throws ExistException;
    //member check-in
    RoomEntity checkIn(int roomId, Date checkInDate, Date checkOutDate);
    //room include or dis-include breakfast
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    RoomEntity updateRoom(int roomId, boolean hasBreakfast) throws ExistException;
}
