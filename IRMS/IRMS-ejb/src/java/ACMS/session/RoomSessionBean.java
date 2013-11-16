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
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
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
 * @author liuxian ///aiayayayaya
 */
@Stateless
@LocalBean
public class RoomSessionBean implements RoomSessionBeanRemote {

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
    @Override
    public RoomEntity getRoomById(int id) throws ExistException {
        System.err.println("in get room by id sessionbean");
        RoomEntity thisRoom = em.find(RoomEntity.class, id);
//        if (thisRoom == null) {
//            throw new ExistException("RoomSessionBean-->ExistException-->Room doesn't exist!");
//        }
        return thisRoom;
    }
    
    @Override
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
    @Override
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

    @Override
    public List<RoomEntity> getOccupiedRooms() throws ExistException {
        System.err.println("in getOccupiedrooms session bean");
        Query q = em.createQuery("SELECT r FROM RoomEntity r");
        List roomList = new ArrayList<RoomEntity>();
        for (Object o : q.getResultList()) {
            RoomEntity r = (RoomEntity) o;
            if (r.getRoomStatus().equals("occupied")) {
                System.out.println("one occupied room found" + r.getRoomId());
                roomList.add(r);
            }
        }
        if (roomList == null) {
            throw new ExistException("RoomEntity database is empty!");
        }
        System.err.println("in get all rooms sessionbean: room list size=" + roomList.size());
        return roomList;
    }

    @Override
    public List<RoomEntity> getCheckInRooms(Long reservationId) throws ExistException {
        System.err.println("in getOccupiedrooms session bean");
        Query q = em.createQuery("SELECT r FROM RoomEntity r where r.roomStatus='available'");
        ReservationEntity aiyou = reservationSessionBean.getReservationById(reservationId);
        System.out.println("get reservation" + aiyou.getReservationId() + aiyou.getReservationRoomType());

        List roomList = new ArrayList<RoomEntity>();
        for (Object o : q.getResultList()) {
            RoomEntity r = (RoomEntity) o;
            System.out.println("type" + r.getRoomType() + aiyou.getReservationRoomType() + " hotel" + r.getRoomHotel() + aiyou.getReservationHotelNo());
            if ((r.getRoomHotel() == aiyou.getReservationHotelNo()) && (r.getRoomType().equalsIgnoreCase(aiyou.getReservationRoomType()))) {
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
    @Override
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
    @Override
    public double clearServiceCharge(int roomId) throws ExistException {
        room = em.find(RoomEntity.class, roomId);
        if (room == null) {
            throw new ExistException("RoomSessionBean-->ExistException-->Invalid room Id!");
        }
        String description = "Hotel Stay from " + room.getCheckInDate() + " to " + room.getCheckOutDate() + " with a total service charge: " + room.getRoomServiceCharge();
        if (room.getRoomMember()!=null)
        mtSessionBean.addMemberTransaction(room.getRoomMember(), room.getRoomServiceCharge(), room.getCheckOutDate(), "Hotel", null, description, false);
        room.setRoomServiceCharge(0);
        room.setRoomServiceExe(null);
        return room.getRoomServiceCharge();
    }

    //member check-in
    @Override
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
    @Override
    public void checkOut(int roomId) throws RoomException, ExistException {
        room = em.find(RoomEntity.class, roomId);
        if (room.getRoomServiceCharge() != 0) {
            throw new RoomException("RoomSessionBean-->RoomException-->There is uncleared room service charge!");
        }
        bill = this.calculateRoomFee(room);
        System.out.println("accounts receivable: " + bill);
//        String description = "Hotel Stay from " + room.getCheckInDate() + " to " + room.getCheckOutDate() + " with a total room fee: " + bill;
//        mtSessionBean.addMemberTransaction(room.getRoomMember(), bill, room.getCheckOutDate(), "Hotel", null, description, false);
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

    @Override
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
    @Override
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
    
    public void addIncidentalCharge(RoomEntity room,double incidentalCharge) {
        room.setRoomServiceCharge(room.getRoomServiceCharge()+incidentalCharge);
        em.merge(room);
    }

    @Override
    public void addMembership(int roomId, MemberEntity thisMember) {
        room = em.find(RoomEntity.class, roomId);
        room.setRoomMember(thisMember);
        em.merge(room);
        System.out.println("RoomSessionBean --> welcome: " + thisMember.getMemberName());
    }

    @Override
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
    
        private boolean checkAvailability(ReservationEntity data) {
        List<ReservationEntity> reservationList = reservationSessionBean.getAllReservations();
        int count = 0;
        //set for total room number
        if ((data.getReservationHotelNo() == 1) && (data.getReservationRoomType().equals("Deluxe"))) {
            count = 80;
        } else if ((data.getReservationHotelNo() == 1) && (data.getReservationRoomType().equals("Deluxe Suite"))) {
            count = 50;
        } else if ((data.getReservationHotelNo() == 1) && (data.getReservationRoomType().equals("Orchard Suite"))) {
            count = 10;
        } else if ((data.getReservationHotelNo() == 2) && (data.getReservationRoomType().equals("Deluxe"))) {
            count = 100;
        } else if ((data.getReservationHotelNo() == 2) && (data.getReservationRoomType().equals("Deluxe Suite"))) {
            count = 60;
        } else if ((data.getReservationHotelNo() == 2) && (data.getReservationRoomType().equals("Chairman Suite"))) {
            count = 8;
        } else if ((data.getReservationHotelNo() == 3) && (data.getReservationRoomType().equals("Superior"))) {
            count = 60;
        } else if ((data.getReservationHotelNo() == 3) && (data.getReservationRoomType().equals("Deluxe"))) {
            count = 60;
        } else if ((data.getReservationHotelNo() == 3) && (data.getReservationRoomType().equals("Deluxe Suite"))) {
            count = 50;
        }
        System.out.println("room information: " + data.getReservationHotelNo() + data.getReservationRoomType());
        System.err.println("Total number of rooms is: " + count);
        //while loop: deduct unavailable rooms
        Iterator<ReservationEntity> itr = reservationList.iterator();
        while (itr.hasNext()) {
            ReservationEntity re = itr.next();
            if ((re.getRcCheckOutDate().after(data.getRcCheckInDate())) && (re.getRcCheckInDate().before(data.getRcCheckOutDate()))) {
                count--;
            }
        }
        if (data.getReservationRoomCount() > count) {
            return false;
        } else {
            return true;
        }
        /*
         * check availability algorithm:
         * notation: (re.getRcCheckInDate=)rIn, rOut, (data.getCheckInDate=)in, out
         * unavailable condition 1: rIn<in and rOut>In
         * unavailabel condition 2: in<rIn<out
         * (hidden condition: rOut>rIn)
         * therefore: [(rIn<in)&&(rOut>in)]||[(in<rIn<out)&&(rOut>in)]||[(in<rIn<out)&&(rOut<in)] (the 3rd part is an empty set)
         * simplified result: rIn<out && rOut>in
         */
    }

    @Override
    public MemberTransactionSessionBean getMtSessionBean() {
        return mtSessionBean;
    }

    @Override
    public void setMtSessionBean(MemberTransactionSessionBean mtSessionBean) {
        this.mtSessionBean = mtSessionBean;
    }

    @Override
    public RoomEntity getRoom() {
        return room;
    }

    @Override
    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    @Override
    public RoomServiceEntity getRoomService() {
        return roomService;
    }

    @Override
    public void setRoomService(RoomServiceEntity roomService) {
        this.roomService = roomService;
    }

    @Override
    public ReservationEntity getReservation() {
        return reservation;
    }

    @Override
    public void setReservation(ReservationEntity reservation) {
        this.reservation = reservation;
    }

    @Override
    public RoomPriceEntity getPrice() {
        return price;
    }

    @Override
    public void setPrice(RoomPriceEntity price) {
        this.price = price;
    }

    @Override
    public MemberTransactionEntity getMemberTransaction() {
        return memberTransaction;
    }

    @Override
    public void setMemberTransaction(MemberTransactionEntity memberTransaction) {
        this.memberTransaction = memberTransaction;
    }
}
