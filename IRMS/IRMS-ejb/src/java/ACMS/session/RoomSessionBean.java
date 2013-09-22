package ACMS.session;

import ACMS.entity.RoomEntity;
import ACMS.entity.RoomServiceEntity;
import CRMS.entity.MemberEntity;
import Exception.ExistException;
import Exception.RoomException;
import java.util.ArrayList;
import java.util.Date;
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
    MemberEntity thisMember = new MemberEntity();
    RoomServiceEntity roomService = new RoomServiceEntity();

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
            throw new ExistException("RoomSessionBean-->ExistException-->Member doesn't exist!");
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

    //add new charged service
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public RoomServiceEntity addRoomService(int roomId, String roomServiceName) throws ExistException {
        room = em.find(RoomEntity.class, roomId);
        roomService = em.find(RoomServiceEntity.class, roomServiceName);
        if (roomServiceName == null) {
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
    public void checkIn(int roomId, Date checkInDate, Date checkOutDate, String thisEmail) throws RoomException {
        room = em.find(RoomEntity.class, roomId);
        if ("reserved".equals(room.getRoomStatus())) {
            System.out.println("RoomSessionBean-->Warning! the room is reserved!");
        } else if ("occupied".equals(room.getRoomStatus())) {
            throw new RoomException("RoomSessionBean-->RoomException-->The room is occupied, cannot check-in");
        }
        room.setCheckInDate(checkInDate);
        room.setCheckOutDate(checkOutDate);
        room.setRoomStatus("occupied");
        if (thisEmail != null) {
            thisMember = em.find(MemberEntity.class, thisEmail);
            room.setMembership(thisMember);
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

    //list of all rooms -- for floor plan
    //information displayed: availability, roomSchedule,roomName, roomType, roomService, accumulated charge
    public List<RoomEntity> getAllRooms() throws ExistException {
        Query q = em.createQuery("SELECT r FROM RoomEntity r");
        List roomList = new ArrayList<RoomEntity>();
        for (Object o : q.getResultList()) {
            RoomEntity r = (RoomEntity) o;
            roomList.add(r);
        }
        if (roomList == null) {
            throw new ExistException("RoomEntity database is empty!");
        }
        return roomList;
    }

    public void addMembership(int roomId, MemberEntity thisMember) {
        room = em.find(RoomEntity.class, roomId);
        room.setMembership(thisMember);
        em.merge(room);
        System.out.println("RoomSessionBean --> welcome: " + thisMember.getMemberName());
    }
}
