/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.RoomEntity;
import ACMS.entity.RoomServiceEntity;
import Exception.ExistException; 
import Exception.RoomException;
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

    //member check-in
    public RoomEntity checkIn(int roomId, Date checkInDate, Date checkOutDate) throws RoomException;
    public void checkOut(int roomId) throws RoomException;
    //room include or dis-include breakfast
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    RoomEntity updateRoom(int roomId, boolean hasBreakfast) throws ExistException;
    //add new charged service
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public RoomServiceEntity addRoomService(int roomId, String roomServiceName) throws ExistException;
}
