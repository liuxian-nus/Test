package ACMS.session;

import ACMS.entity.RoomPriceEntity;
import ACMS.entity.ReservationEntity;
import ACMS.entity.RoomEntity;
import ACMS.entity.RoomServiceEntity;
import ACMS.entity.RoomServiceExeEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.MemberTransactionSessionBean;
import ERMS.session.EmailSessionBean;
import Exception.ExistException;
import Exception.RoomException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.joda.time.Days;
import org.joda.time.DateMidnight;

/**
 *
 * @author liuxian
 */
@Stateless
public class RoomSessionBean {

    @EJB
    private ReservationSessionBean reservationSessionBean;
    @EJB
    private EmailSessionBean emailSessionBean;
    @EJB
    private MemberTransactionSessionBean mtSessionBean;
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    RoomEntity room = new RoomEntity();
    RoomServiceEntity roomService = new RoomServiceEntity();
    ReservationEntity reservation = new ReservationEntity();
    RoomPriceEntity price = new RoomPriceEntity();
    MemberTransactionEntity memberTransaction = new MemberTransactionEntity();
    private double bill = 0;

    public RoomSessionBean() {
    }

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
    public RoomEntity getRoomById(int id) throws ExistException {
        System.err.println("in get room by id sessionbean");
        RoomEntity thisRoom = em.find(RoomEntity.class, id);
//        if (thisRoom == null) {
//            throw new ExistException("RoomSessionBean-->ExistException-->Room doesn't exist!");
//        }
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
        System.err.println("in getAllrooms session bean");
        Query q = em.createQuery("SELECT r FROM RoomEntity r");
        List roomList = new ArrayList<RoomEntity>();
        for (Object o : q.getResultList()) {
            RoomEntity r = (RoomEntity) o;
            roomList.add(r);
            System.err.println("in get all rooms sessionbean: " + r.getRoomId());
        }
        if (roomList == null) {
            throw new ExistException("RoomEntity database is empty!");
        }
        return roomList;
    }

    public List<RoomEntity> getOccupiedRooms() throws ExistException {
        System.err.println("in getOccupiedrooms session bean");
        Query q = em.createQuery("SELECT r FROM RoomEntity r");
        List roomList = new ArrayList<RoomEntity>();
        for (Object o : q.getResultList()) {
            RoomEntity r = (RoomEntity) o;
            if (r.getRoomStatus() == "occupied") {
                roomList.add(r);
            }
        }
        if (roomList == null) {
            throw new ExistException("RoomEntity database is empty!");
        }
        System.err.println("in get all rooms sessionbean: room list size=" + roomList.size());
        return roomList;
    }

    public List<RoomEntity> getCheckInRooms(Long reservationId) throws ExistException {
        System.err.println("in getOccupiedrooms session bean");
        Query q = em.createQuery("SELECT r FROM RoomEntity r where r.roomStatus='available'");
        ReservationEntity aiyou = reservationSessionBean.getReservationById(reservationId);
        System.out.println("gett reservation" + aiyou.getReservationId() + aiyou.getReservationRoomType());

        List roomList = new ArrayList<RoomEntity>();
        for (Object o : q.getResultList()) {
            RoomEntity r = (RoomEntity) o;
            System.out.println("type" + r.getRoomType() + aiyou.getReservationRoomType() + " hotel" + r.getRoomHotel() + aiyou.getReservationHotelNo());
            if ((r.getRoomHotel()==aiyou.getReservationHotelNo()) && (r.getRoomType().equalsIgnoreCase(aiyou.getReservationRoomType()))) {
                roomList.add(r);
            }
        }
        if (roomList == null) {
            throw new ExistException("RoomEntity database is empty!");
        }
        System.err.println("in get all rooms sessionbean: room list size=" + roomList.size());
        return roomList;
    }

    //add new charged service
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public RoomServiceEntity addRoomService(int roomId, String roomServiceName, int quantity) throws ExistException {
        room = em.find(RoomEntity.class, roomId);
        roomService = em.find(RoomServiceEntity.class, roomServiceName);
        if (roomService == null) {
            throw new ExistException("RoomSessionBean-->ExistException-->Invalid room service name!");
        }
        RoomServiceExeEntity newRoomServiceExe = new RoomServiceExeEntity();
        newRoomServiceExe.setRoom(room);
        newRoomServiceExe.setRoomService(roomService);
        newRoomServiceExe.setRoomServiceQuantity(quantity);
        em.persist(newRoomServiceExe);//add new entry of room service exe
        room.addRoomService(newRoomServiceExe);
        room.addRoomServiceCharge(newRoomServiceExe.getRoomService().getRoomServicePrice() * newRoomServiceExe.getRoomServiceQuantity());
        em.merge(room);
        System.out.println("RoomSessionBean--> " + roomId + " new include new service " + roomService.getRoomServiceName());
        System.out.println("RoomSessionBean--> " + roomId + " now has total outstanding payable: " + room.getRoomServiceCharge());
        return roomService;
    }

    //clear all room service charge
    public double clearServiceCharge(int roomId) throws ExistException {
        room = em.find(RoomEntity.class, roomId);
        if (room == null) {
            throw new ExistException("RoomSessionBean-->ExistException-->Invalid room Id!");
        }
//        mtSessionBean.addMemberTransaction(room.getRoomMember(), room.getRoomServiceCharge(), room.getCheckOutDate(), "Hotel", null, false);
        room.setRoomServiceCharge(0);
        room.setRoomServiceExe(null);
        return room.getRoomServiceCharge();
    }

    //member check-in
    public void checkIn(int roomId, Long reservationId, String guestName) throws RoomException, ExistException {
        ReservationEntity reservation = new ReservationEntity();
        reservation = em.find(ReservationEntity.class, reservationId);
        if (reservation == null) {
            throw new ExistException("RoomSessionBean-->ExistException-->This Reservation doesn't exist!");
        }
        reservation.setReservationStatus("checkedIn");
        room = em.find(RoomEntity.class, roomId);
        if ("reserved".equals(room.getRoomStatus())) {
            System.out.println("RoomSessionBean-->Warning! the room is reserved!");
        } else if ("occupied".equals(room.getRoomStatus())) {
            throw new RoomException("RoomSessionBean-->RoomException-->The room is occupied, cannot check-in");
        }
        room.setReservation(reservation);
        room.setCheckInDate(reservation.getRcCheckInDate());
        room.setCheckOutDate(reservation.getRcCheckOutDate());
        room.setRoomCorporate(reservation.getReservationCorporate());
        room.setGuestName(guestName);
        room.setRoomStatus("occupied");
        room.setRoomCreditCardNo(reservation.getRcCreditCardNo());
        MemberEntity thisMember = reservation.getRcMember();
        if (thisMember != null) {
            room.setRoomMember(thisMember);
            System.out.println("RoomSessionBean-->Welcome! " + thisMember.getMemberName());
        }
        System.out.println("RoomSessionBean-->Room " + room.getRoomId() + " is now occupied");
        price = em.find(RoomPriceEntity.class, room.getRoomType());
        if (price == null) {
            throw new ExistException("RoomSessionBean-->ExistException-->Price Entity missing!");
        }
        //       thisMember.setMemberTransactions(null);
    }

    //individual member checkout
    public void checkOut(int roomId) throws RoomException, ExistException {
        room = em.find(RoomEntity.class, roomId);
        if (room.getRoomServiceCharge() != 0) {
            throw new RoomException("RoomSessionBean-->RoomException-->There is uncleared room service charge!");
        }
        bill = this.calculateRoomFee(room);
        System.out.println("accounts receivable: " + bill);
        String description = "Hotel Reservation from " + room.getCheckInDate() + " to " + room.getCheckOutDate() + " with a total room fee: " + bill;
        mtSessionBean.addMemberTransaction(room.getRoomMember(), bill, room.getCheckOutDate(), "Hotel", null, description, false);
        System.out.println("room check out: member transaction captured!");
        room.setCheckInDate(null);
        room.setCheckOutDate(null);
        room.setGuestName(null);
        //       room.setHasBreakfast(false);
        room.setReservation(null);
        room.setRoomCreditCardNo(null);
        room.setRoomMember(null);
        room.setRoomCorporate(null);
        room.setRoomStatus("checkedOut");
        System.out.println("RoomSessionBean-->Room " + room.getRoomId() + " is successfully checked out");
    }

    public void updateHousekeeping(int roomId) {
        room = em.find(RoomEntity.class, roomId);
        room.setRoomStatus("available");
    }

    /*public void sendBill(int roomId) throws RoomException {
     room = em.find(RoomEntity.class, roomId);
     bill = this.calculateBill(room);
     System.out.println("accounts receivable: " + bill);

     if (room.getRoomCorporate() != null) {
     emailSessionBean.emailCorporateBill("xinqi_wang@yahoo.com", room);
     }
     System.out.println("RoomSessionBean-->Room " + room.getRoomId() + " bill is successfully send");
     }*/
    public double calculateRoomFee(RoomEntity room) {
        //       double temp1 = room.getCheckInDate().get(Calendar.DAY_OF_YEAR);
        //       double temp2 = room.getCheckOutDate().get(Calendar.DAY_OF_YEAR);
        int daysBetween;
        DateMidnight start = new DateMidnight(room.getCheckInDate());
        DateMidnight end = new DateMidnight(room.getCheckOutDate());
        daysBetween = Days.daysBetween(start, end).getDays();
        double roomCharge = room.getRoomPrice().getPrice() * daysBetween; // 5 should be outDate - inDate
        return roomCharge;
    }

    public void addMembership(int roomId, MemberEntity thisMember) {
        room = em.find(RoomEntity.class, roomId);
        room.setRoomMember(thisMember);
        em.merge(room);
        System.out.println("RoomSessionBean --> welcome: " + thisMember.getMemberName());
    }

    public void createTestRoom(int roomHotel, int roomLevel, int roomNo, String roomType, String roomStatus) {
        try {
            System.out.println("come to create test room session bean");
            System.err.println("create priceEntity first");
            price = em.find(RoomPriceEntity.class, roomType);
            room.setRoomHotel(roomHotel);
            room.setRoomLevel(roomLevel);
            room.setRoomNo(roomNo);
            room.setRoomId(roomHotel, roomLevel, roomNo);
            room.setRoomType(roomType);
            room.setRoomStatus(roomStatus);
            room.setRoomPrice(price);
            room.setRoomMember(null);
            room.setReservation(null);
            room.setRoomServiceCharge(0);
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

    public MemberTransactionSessionBean getMtSessionBean() {
        return mtSessionBean;
    }

    public void setMtSessionBean(MemberTransactionSessionBean mtSessionBean) {
        this.mtSessionBean = mtSessionBean;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public RoomServiceEntity getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomServiceEntity roomService) {
        this.roomService = roomService;
    }

    public ReservationEntity getReservation() {
        return reservation;
    }

    public void setReservation(ReservationEntity reservation) {
        this.reservation = reservation;
    }

    public RoomPriceEntity getPrice() {
        return price;
    }

    public void setPrice(RoomPriceEntity price) {
        this.price = price;
    }

    public MemberTransactionEntity getMemberTransaction() {
        return memberTransaction;
    }

    public void setMemberTransaction(MemberTransactionEntity memberTransaction) {
        this.memberTransaction = memberTransaction;
    }
}
