/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.ReservationEntity;
import ACMS.entity.RoomEntity;
import ACMS.entity.RoomPriceEntity;
import ACMS.entity.RoomServiceEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.MemberTransactionSessionBean;
import Exception.ExistException;
import Exception.RoomException;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface RoomSessionBeanRemote {

    void addMembership(int roomId, MemberEntity thisMember);

    //add new charged service
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    RoomServiceEntity addRoomService(int roomId, String roomServiceName, int quantity) throws ExistException;

    /*public void sendBill(int roomId) throws RoomException {
    room = em.find(RoomEntity.class, roomId);
    bill = this.calculateBill(room);
    System.out.println("accounts receivable: " + bill);
    if (room.getRoomCorporate() != null) {
    emailSessionBean.emailCorporateBill("xinqi_wang@yahoo.com", room);
    }
    System.out.println("RoomSessionBean-->Room " + room.getRoomId() + " bill is successfully send");
    }*/
    double calculateRoomFee(RoomEntity room);

    //member check-in
    void checkIn(int roomId, Long reservationId, String guestName) throws RoomException, ExistException;

    //individual member checkout
    void checkOut(int roomId) throws RoomException, ExistException;

    //clear all room service charge
    double clearServiceCharge(int roomId) throws ExistException;

    void createTestRoom(int roomHotel, int roomLevel, int roomNo, String roomType, String roomStatus);

    //list of all rooms -- for floor plan
    //information displayed: availability, roomSchedule,roomName, roomType, roomService, accumulated charge
    List<RoomEntity> getAllRooms() throws ExistException;

    List<RoomEntity> getAvailableRooms() throws ExistException;

    List<RoomEntity> getCheckInRooms(Long reservationId) throws ExistException;

    MemberTransactionEntity getMemberTransaction();

    MemberTransactionSessionBean getMtSessionBean();

    List<RoomEntity> getOccupiedRooms() throws ExistException;

    RoomPriceEntity getPrice();

    ReservationEntity getReservation();

    RoomEntity getRoom();

    //room include or dis-include breakfast
    /*
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public RoomEntity updateRoom(int roomId, boolean hasBreakfast) throws ExistException {
    room = em.find(RoomEntity.class, roomId);
    if (room == null) {
    throw new ExistException("RoomSessionBean-->ExistException-->Room doesn't exist!");
    }
    room.setHasBreakfast(hasBreakfast);
    if (hasBreakfast == true) {
    System.out.println("RoomSessionBean--> " + roomId + " now includes breakfast");
    } else {
    System.out.println("RoomSessionBean--> " + roomId + " now cancel breakfast");
    }
    em.merge(room);
    return room;
    }
     */
    RoomEntity getRoomById(int id) throws ExistException;

    RoomServiceEntity getRoomService();

    void setMemberTransaction(MemberTransactionEntity memberTransaction);

    void setMtSessionBean(MemberTransactionSessionBean mtSessionBean);

    void setPrice(RoomPriceEntity price);

    void setReservation(ReservationEntity reservation);

    void setRoom(RoomEntity room);

    void setRoomService(RoomServiceEntity roomService);

    void updateHousekeeping(int roomId);
    
}
