package ACMS.session;

import ACMS.entity.PriceEntity;
import ACMS.entity.ReservationEntity;
import ACMS.entity.RoomEntity;
import ACMS.entity.RoomServiceEntity;
import CRMS.entity.MemberEntity;
import Exception.ExistException;
import Exception.RoomException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author liuxian
 */
@Stateless
public class RoomSessionBean {

    @PersistenceContext
    private EntityManager em;
    RoomEntity room = new RoomEntity();
    RoomServiceEntity roomService = new RoomServiceEntity();
    ReservationEntity reservation = new ReservationEntity();
    PriceEntity price = new PriceEntity();

    public RoomSessionBean() {
    }

    /*  All Attributes of a room:
     private int roomId;
     private double roomPrice;
     private String roomType;
     private String roomStatus;
     private int roomHotel;
     private int roomLevel;
     private int roomNo;
     private boolean hasBreakfast;
     private boolean isOccupied;
     @Temporal(javax.persistence.TemporalType.DATE)
     private Date checkInDate;
     @Temporal(javax.persistence.TemporalType.DATE)
     private Date checkOutDate;
     @ManyToMany(cascade={CascadeType.PERSIST})
     public Set<RoomServiceEntity> roomService = new HashSet<RoomServiceEntity> ();
     */
    //room include or dis-include breakfast 
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
    
    public RoomEntity getRoomById(int id) throws ExistException {
        System.err.println("in get room by id sessionbean");
        RoomEntity thisRoom = em.find(RoomEntity.class, id);
        if (thisRoom == null) {
            throw new ExistException("RoomSessionBean-->ExistException-->Room doesn't exist!");
        }
        return thisRoom;
    }

    public List<RoomEntity> getAvailableRooms() throws ExistException {
        //get all rooms
        System.err.println("in getallrooms sessionbean");
        Query q = em.createQuery("SELECT r FROM RoomEntity r");
        List roomList = new ArrayList<RoomEntity>();
        List selectRoom = new ArrayList<RoomEntity>();
        for (Object o : q.getResultList()) {
            RoomEntity r = (RoomEntity) o;
            roomList.add(r);
        }
        if (roomList == null) {
            throw new ExistException("RoomEntity database is empty!");
        }
        System.out.println("finish finding all room" + roomList.size());
        //get available rooms
        int i = 0;
        RoomEntity oneRoom;
        oneRoom = (RoomEntity) roomList.get(i);
        System.out.println(oneRoom.getRoomId());
        System.out.println(oneRoom.getRoomStatus());
        if (oneRoom.getRoomStatus().equals("available")) {
            selectRoom.add(oneRoom);
            System.err.println(oneRoom.getRoomId() + "added in available room list");
        }
        while (i < (roomList.size() - 1)) {
            i++;
            oneRoom = (RoomEntity) roomList.get(i);
            System.out.println(oneRoom.getRoomId());
            System.out.println(oneRoom.getRoomStatus());
            if (oneRoom.getRoomStatus().equals("available")) {
                selectRoom.add(oneRoom);
                System.err.println(oneRoom.getRoomId() + "added in available room list");
            }//end of if
        }
        System.out.println("finish the session bean method" + selectRoom.size());
        return roomList;
    }
    
    //list of all rooms -- for floor plan
    //information displayed: availability, roomSchedule,roomName, roomType, roomService, accumulated charge
    public List<RoomEntity> getAllRooms() throws ExistException {
        System.err.println("in gerallrooms sessionbean");
        Query q = em.createQuery("SELECT r FROM RoomEntity r");
        List roomList = new ArrayList<RoomEntity>();
        for (Object o : q.getResultList()) {
            RoomEntity r = (RoomEntity) o;
            roomList.add(r);
        }
        if (roomList == null) {
            throw new ExistException("RoomEntity database is empty!");
        }
        System.err.println("in gerallrooms sessionbean"+roomList.size());
        return roomList;
    }
    
    
    //add new charged service
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public RoomServiceEntity addRoomService(int roomId, String roomServiceName) throws ExistException {
        room = em.find(RoomEntity.class, roomId);
        roomService = em.find(RoomServiceEntity.class, roomServiceName);
        if (roomService == null) {
            throw new ExistException("RoomSessionBean-->ExistException-->Invalid room service name!");
        }
        room.addRoomService(roomService);
        room.addRoomServiceCharge(roomService.getRoomServicePrice());
        em.merge(room);
        System.out.println("RoomSessionBean--> " + roomId + " new include new service " + roomService.getRoomServiceName());
        System.out.println("RoomSessionBean--> " + roomId + " now has total outstanding payable: " + room.getRoomServiceCharge());
        return roomService;
    }

    //member check-in
    public void checkIn(int roomId, Long reservationId) throws RoomException, ExistException {
        ReservationEntity reservation = new ReservationEntity();
        reservation = em.find(ReservationEntity.class, reservationId);
        if (reservation == null) {
            throw new ExistException("RoomSessionBean-->ExistException-->This Reservation doesn't exist!");
        }
        room = em.find(RoomEntity.class, roomId);
        if ("reserved".equals(room.getRoomStatus())) {
            System.out.println("RoomSessionBean-->Warning! the room is reserved!");
        } else if ("occupied".equals(room.getRoomStatus())) {
            throw new RoomException("RoomSessionBean-->RoomException-->The room is occupied, cannot check-in");
        }
        room.setReservation(reservation);
        room.setCheckInDate(reservation.getRcCheckInDate());
        room.setCheckOutDate(reservation.getRcCheckOutDate());
        //room.setGuestName(reservation.getRcName());
        room.setRoomStatus("occupied");
        MemberEntity thisMember = reservation.getRcMember();
        if (thisMember != null) {
            room.setRoomMember(thisMember);
            System.out.println("RoomSessionBean-->Welcome! " + thisMember.getMemberName());
        }
        System.out.println("RoomSessionBean-->Room " + room.getRoomId() + " is now occupied");
    }

    //individual member checkout
    public void checkOut(int roomId) throws RoomException {
        room = em.find(RoomEntity.class, roomId);
        if (room.getRoomServiceCharge() != 0) {
            throw new RoomException("RoomSessionBean-->RoomException-->There is uncleared room service charge!");
        }
        room.setCheckInDate(null);
        room.setCheckOutDate(null);
        room.setRoomStatus("available");
        System.out.println("RoomSessionBean-->Room " + room.getRoomId() + " is successfully checked out");
    }


    public void addMembership(int roomId, MemberEntity thisMember) {
        room = em.find(RoomEntity.class, roomId);
        room.setRoomMember(thisMember);
        em.merge(room);
        System.out.println("RoomSessionBean --> welcome: " + thisMember.getMemberName());
    }

    public void createTestRoom(int roomHotel, int roomLevel, int roomNo, String roomType, String roomStatus, String guestName) {
        try {
            System.out.println("come to create test room session bean");
            System.err.println("create priceEntity first");
            price.setPriceType(roomType);
            price.setPrice(485.3);
            em.persist(price);
            room.setRoomHotel(roomHotel);
            room.setRoomLevel(roomLevel);
            room.setRoomNo(roomNo);
            room.setRoomId(roomHotel, roomLevel, roomNo);
            room.setRoomType(roomType);
            room.setRoomStatus(roomStatus);
            room.setRoomPrice(price);
            room.setRoomMember(null);
            room.setReservation(null);
            room.setGuestName(guestName);
            System.out.println(room.getRoomId());
            System.out.println(room.getRoomType());
            System.out.println(room.getRoomStatus());
            System.out.println(room.getRoomPrice().getPrice());
            System.out.println(room.getRoomHotel());
            System.out.println(room.getRoomLevel());
            System.out.println(room.getRoomNo());
            System.out.println("prepare to create");
            em.persist(room);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("create test room session bean failed");
            return;
        }
        System.out.println("Insert room into database");
    }
}
