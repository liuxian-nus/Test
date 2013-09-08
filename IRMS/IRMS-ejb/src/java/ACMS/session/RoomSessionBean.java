
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
public class RoomSessionBean {
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
    public RoomEntity updateRoom(int roomId,boolean hasBreakfast)throws ExistException{
        room = em.find(RoomEntity.class, roomId);
        if(room==null) throw new ExistException ("Member doesn't exist!");
        room.setHasBreakfast(hasBreakfast);
        em.merge(room);
        return room;
    }
    
    //add new charged service
     @TransactionAttribute(TransactionAttributeType.REQUIRED)
     public RoomServiceEntity addRoomService(int roomId, String roomServiceName)throws ExistException{
         room = em.find(RoomEntity.class, roomId);
         roomService = em.find(RoomServiceEntity.class, roomServiceName);
         if (roomServiceName == null) throw new ExistException ("Invalid room service name!");
         room.addRoomService(roomService);
         em.merge(room);
         return roomService;
    }
    
    //member check-in
    public RoomEntity checkIn(int roomId,Date checkInDate, Date checkOutDate) {
        room = em.find(RoomEntity.class, roomId);
        room.setCheckInDate(checkInDate);
        room.setCheckOutDate(checkOutDate);
        return room;
    }
}
