
package ACMS.session;

import ACMS.entity.RoomEntity;
import ACMS.entity.RoomServiceEntity;
import Exception.ExistException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author liuxian
 */
@Stateless
public class RoomSessionBean implements RoomSessionRemote {
    @PersistenceContext
    private EntityManager em ;
    
    RoomEntity room = new RoomEntity();
    RoomServiceEntity roomService = new RoomServiceEntity();
    
    public RoomSessionBean(){}
    
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
    @Override
    public RoomEntity updateRoom(int roomId,boolean hasBreakfast)throws ExistException{
        room = em.find(RoomEntity.class, roomId);
        if(room==null) throw new ExistException ("RoomSessionBean-->ExistException-->Member doesn't exist!");
        room.setHasBreakfast(hasBreakfast);
        if(hasBreakfast == true)
            System.out.println("RoomSessionBean--> "+ roomId + " now includes breakfast");
        else System.out.println("RoomSessionBean--> " + roomId + " now cancel breakfast");
        em.merge(room);
        return room;
    }
    
    //add new charged service
     @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
     public RoomServiceEntity addRoomService(int roomId, String roomServiceName)throws ExistException{
         room = em.find(RoomEntity.class, roomId);
         roomService = em.find(RoomServiceEntity.class, roomServiceName);
         if (roomServiceName == null) throw new ExistException ("RoomSessionBean-->ExistException-->Invalid room service name!");
         room.addRoomService(roomService);
         room.addRoomServiceCharge(roomService.getRoomServicePrice());
         em.merge(room);
         System.out.println("RoomSessionBean--> " + roomId + " new include new service " + roomService.getRoomServiceName());
         System.out.println("RoomSessionBean--> " + roomId + " now has total outstanding payable: " + room.getRoomServiceCharge());
         return roomService;
    }
    
    //member check-in
    @Override
    public RoomEntity checkIn(int roomId,Date checkInDate, Date checkOutDate) {
        room = em.find(RoomEntity.class, roomId);
        room.setCheckInDate(checkInDate);
        room.setCheckOutDate(checkOutDate);
        return room;
    }
}
