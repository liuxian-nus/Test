/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ACMS.entity.OverbookingQuotaEntity;
import ACMS.entity.ReservationEntity;
import ACMS.entity.RoomServiceEntity;
import ACMS.session.OverbookingSessionBean;
import ACMS.session.ReservationSessionBean;
import ACMS.session.RoomServiceSessionBean;
import ACMS.session.RoomSessionBean;
import CRMS.entity.MemberEntity;
import CRMS.session.CPasswordHashSessionBean;
import CRMS.session.MemberSessionBean;
import ERMS.entity.EmployeeEntity;
import ERMS.entity.FunctionalityEntity;
import ERMS.entity.RoleEntity;
import FBMS.entity.RestaurantEntity;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ser3na
 */
@Singleton
//@Startup
public class InitSessionBean {
    

    @PersistenceContext(unitName="IRMS-ejbPU")
    private EntityManager em;
    @EJB
    private EmployeeSessionBean employeeSessionBean;
    @EJB
    private EPasswordHashSessionBean ePasswordHashSessionBean;
    @EJB
    private CPasswordHashSessionBean cPasswordHashSessionBean;
    @EJB
    private ReservationSessionBean reSessionBean;
    @EJB
    private MemberSessionBean mmSessionBean;
    @EJB
    private RoomSessionBean roomSessionBean;
    @EJB
    private FunctionalitySessionBean functionalitySessionBean;
    @EJB
    private OverbookingSessionBean overbookingSessionBean;
    @EJB
    private RoomServiceSessionBean roomServiceSessionBean;
   
    
    
    
    
    
    private EmployeeEntity employee;
    private RoleEntity role;
    private ReservationEntity reservation;
    private MemberEntity member;
    private FunctionalityEntity functionality;
    private OverbookingQuotaEntity overbookingQuota;
    private RestaurantEntity restaurant;
    private RoomServiceEntity roomService1;
    private RoomServiceEntity roomService2;

    @PostConstruct
    public void init() {
        System.err.println("InitSessionBean.init");
        createSuperAdmin();
        createSystemUser();
        createReservation();
        createFBMSAdmin();
        createCRMSAdmin();
        createMember();
        createRoom();
        createFunctionalities();
        createOverbooking();
        createRmService(); 
        
    }

    public void createSuperAdmin() {
        System.err.println("go to create super admin");
        
        functionality = new FunctionalityEntity();
        functionality.setFuncName("addRole");
        functionality.setFuncDescription("access right to addRole page");
        functionalitySessionBean.addFunctionality(functionality);

        role = new RoleEntity();
        role.setRoleId(10);
        role.setRoleName("SuperAdmin");
        System.out.println("Create role :" + role.getRoleName());

        employee = new EmployeeEntity();
        employee.setEmployeeId("A0000"); //business assumption: maximum employee number 9999
        employee.setEmployeeName("SuperAdmin");
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword("A0000"));
        System.out.println("finished hashing");
        employee.setEmployeeEmail("is3102.it09@gmail.com");
        employee.addRole(role);
        employee.setIsFirstTimeLogin(false);
        System.out.println("Create employee :" + employee.getEmployeeId() + "," + employee.getEmployeeName() + "," + employee.getEmployeePassword());

        try {
            System.out.println("Saving Super Admin....");

            employeeSessionBean.addEmployee(employee);
            System.out.println("Super Admin saved.....");
        } catch (Exception e) {
            System.out.println("Error occurs when adding Super Admin");
            return;
        }
        System.err.println("Insert Super Admin into database");

    }

    public void createSystemUser() {
        System.err.println("go to create ACMS user");

        role = new RoleEntity();
        role.setRoleId(20);
        role.setRoleName("ACMSAdmin");
        System.out.println("Create role :" + role.getRoleName());

        employee = new EmployeeEntity();
        employee.setEmployeeId("B0000"); //business assumption: maximum employee number 9999
        employee.setEmployeeDepartment("hotel");
        employee.setEmployeeName("ACMSAdmin");
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword("B0000"));
        System.out.println("finished hashing");
        employee.addRole(role);
        employee.setIsFirstTimeLogin(false);
        System.out.println("Create employee :" + employee.getEmployeeId() + "," + employee.getEmployeeName() + "," + employee.getEmployeePassword());

        try {
            System.out.println("Saving ACMSAdmin....");
            employeeSessionBean.addEmployee(employee);
            System.out.println("ACMSAdmin saved.....");
        } catch (Exception e) {
            System.out.println("Error occurs when creating system user");
            return;
        }
        System.err.println("Insert System User into database");

    }
    
        public void createFrontDesk() {
        System.err.println("go to create ACMS user");

        role = new RoleEntity();
        role.setRoleId(21);
        role.setRoleName("ACMSFrontDesk");
        System.out.println("Create role :" + role.getRoleName());

        employee = new EmployeeEntity();
        employee.setEmployeeId("B1000"); //business assumption: maximum employee number 9999
        employee.setEmployeeDepartment("hotel");
        employee.setEmployeeName("ACMSFrontDesk");
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword("B1000"));
        System.out.println("finished hashing");
        employee.addRole(role);
        employee.setIsFirstTimeLogin(false);
        System.out.println("Create employee :" + employee.getEmployeeId() + "," + employee.getEmployeeName() + "," + employee.getEmployeePassword());

        try {
            System.out.println("Saving ACMSFrontDesk....");
            employeeSessionBean.addEmployee(employee);
            System.out.println("ACMSFrontDesk saved.....");
        } catch (Exception e) {
            System.out.println("Error occurs when creating hotel front desk user");
            return;
        }
        System.err.println("Insert System User into database");

    }
    
    public void createFBMSAdmin() {
        System.out.println("go to create FBMS page");

        role = new RoleEntity();
        role.setRoleId(50);
        role.setRoleName("FBMSAdmin");
        System.out.println("Create role :" + role.getRoleName());

        employee = new EmployeeEntity();
        employee.setEmployeeId("E0000"); //business assumption: maximum employee number 9999
        employee.setEmployeeName("FBMSAdmin");
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword("E0000"));
        System.out.println("finished hashing");
        employee.addRole(role);
        employee.setIsFirstTimeLogin(false);
        System.out.println("Create employee :" + employee.getEmployeeId() + "," + employee.getEmployeeName() + "," + employee.getEmployeePassword());

        try {
            System.out.println("Saving FBMSAdmin....");
            employeeSessionBean.addEmployee(employee);
            System.out.println("FBMSAdmin saved.....");
        } catch (Exception e) {
            System.out.println("Error occurs when adding FBMSadmin");
            return;
        }
        System.out.println("Insert FBMSAdmin into database");
      
    }
    
    public void createCRMSAdmin() {
        System.out.println("go to create CRMS page");

        role = new RoleEntity();
        role.setRoleId(80);
        role.setRoleName("CRMSAdmin");
        System.out.println("Create role :" + role.getRoleName());

        employee = new EmployeeEntity();
        employee.setEmployeeId("H0000"); //business assumption: maximum employee number 9999
        employee.setEmployeeName("CRMSAdmin");
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword("H0000"));
        System.out.println("finished hashing");
        employee.addRole(role);
        employee.setIsFirstTimeLogin(false);
        System.out.println("Create employee :" + employee.getEmployeeId() + "," + employee.getEmployeeName() + "," + employee.getEmployeePassword());

        try {
            System.out.println("Saving CRMSAdmin....");
            employeeSessionBean.addEmployee(employee);
            System.out.println("CRMSAdmin saved.....");
        } catch (Exception e) {
            System.out.println("Error occurs when adding admin");
            return;
        }
        System.out.println("Insert CRMS Admin into database");
        
    }

    public void createReservation() {
        System.err.println("go to create hotel reservation page...");
        Date cidate = new Date(2014, 10, 1);
        Date codate = new Date(2014, 10, 5);

        reservation = new ReservationEntity();
        reservation.setRcName("Danny");
        System.out.println("create reservation: welcome " + reservation.getRcName());
        reservation.setRcEmail("chrislx.nus@gmail.com");
        reservation.setRcHP("65-81801380");
        reservation.setRcCreditCardNo("1230000045600000");
        reservation.setRcCheckInDate(cidate);
        reservation.setRcCheckOutDate(codate);
//         reservation.setRcMember(member);
        reservation.setReservationRoomType("Deluxe");
        reservation.setReservationHotelNo(1);
        reservation.setReservationRoomCount(3);
        reservation.setReservationGuestCount(6);

        try {
            System.out.println("Saving hotel reservation....");

            reSessionBean.addReservation(reservation);
            System.out.println("Hotel Reservation saved.....");
        } catch (Exception e) {
            System.out.println("Error occurs when adding reservation");
            return;
        }
        System.err.println("Insert Reservation into database");
    }


    public void createMember() {
        System.err.println("go to create member page...");       
        Date qqdate = new Date(91,02,11);
        
        member = new MemberEntity();
        member.setMemberEmail("xinqi-wang@yahoo.com");
        member.setMemberName("dayan-wang");
        member.setMemberPassword(cPasswordHashSessionBean.hashPassword("dayan-wang"));
        System.out.println("Create a new member: welcome! " + member.getMemberName());
        member.setMemberHP("92728760");
        member.setNationality("China");
        member.setMemberDob(qqdate);
        member.setGender("female");
        member.setMaritalStatus("single");
        member.setIsVIP(true);
        member.setIsSubscriber(true);
        member.setPreferences("to be Set");
        
        try {
            System.out.println("Creating new member....");
            mmSessionBean.addMember(member);
            System.out.println("Member created....");
        } catch (Exception e) {
            System.out.println("Error occurs when adding member");
            return;
        }
        System.err.println("Insert dayan-wang member into database");
    }
    
    public void createRoom() {
        try {
            System.err.println("Insert room started.....");
            roomSessionBean.createTestRoom(1,1,1,"deluxe","available");
            /*
            RoomEntity room1 = new RoomEntity();
            room1.setRoomId(1, 1, 1);
            room1.setRoomType("deluxe");
            room1.setRoomStatus("available");
            rmSessionBean.createTestRoom(room1);
            RoomEntity room2 = new RoomEntity();
            room2.setRoomId(1, 1, 2);
            room2.setRoomType("deluxe");
            room2.setRoomStatus("available");
            rmSessionBean.createTestRoom(room2);
            RoomEntity room3 = new RoomEntity();
            room3.setRoomId(1, 1, 3);
            room3.setRoomType("deluxe");
            room3.setRoomStatus("reserved");
            rmSessionBean.createTestRoom(room3);
            RoomEntity room4 = new RoomEntity();
            room4.setRoomId(1, 1, 4);
            room4.setRoomType("deluxe");
            room4.setRoomStatus("occupied");
            rmSessionBean.createTestRoom(room4);
            *//*
            Query query = em.createQuery("INSERT INTO roomentity(ROOMEHOTEL,ROOMLEVEL,ROOMNO,ROOMTYPE)\n"
                    + "VALUES (1,1,1,'Deluxe');");
            query = em.createQuery("INSERT INTO roomentity(ROOMEHOTEL,ROOMLEVEL,ROOMNO,ROOMTYPE)\n"
                    + "VALUES (1,1,2,'Deluxe');");
            query = em.createQuery("INSERT INTO roomentity(ROOMEHOTEL,ROOMLEVEL,ROOMNO,ROOMTYPE)\n"
                    + "VALUES (1,1,3,'Deluxe');");
            query = em.createQuery("INSERT INTO roomentity(ROOMEHOTEL,ROOMLEVEL,ROOMNO,ROOMTYPE)\n"
                    + "VALUES (1,1,4,'Deluxe');");
            query = em.createQuery("INSERT INTO roomentity(ROOMEHOTEL,ROOMLEVEL,ROOMNO,ROOMTYPE)\n"
                    + "VALUES (1,1,5,'Deluxe');");
            query = em.createQuery("INSERT INTO roomentity(ROOMEHOTEL,ROOMLEVEL,ROOMNO,ROOMTYPE)\n"
                    + "VALUES (1,1,6,'Deluxe');");
                    */
        } catch (Exception e) {
            System.out.println("Error occurs when adding room to Orchard Hotel");
            return;
        }
        System.out.println("Insert room into database");
 
    }
      
    public void createFunctionalities(){
        functionality = new FunctionalityEntity();
        functionality.setFuncName("addFunctionality");
        functionality.setFuncDescription("access right to addFunctionality page");
        
        try {
            System.out.println("Creating new functionality....");
            functionalitySessionBean.addFunctionality(functionality);
            System.out.println("Functionality created....");
        } catch (Exception e) {
            System.out.println("Error occurs when adding functionality");
            return;
        }
        System.err.println("Insert systemMsg functionality into database");
      
    }
    
    public void createOverbooking(){
        overbookingQuota = new OverbookingQuotaEntity();
        overbookingQuota.setOverbookingId(1);
        overbookingQuota.setRoomType("deluxe");
        overbookingQuota.setQuota(0);
        overbookingQuota.setCompensation1(105);
        overbookingQuota.setCompensation2(485.3);
        
        try {
            System.err.println("Initiating the overbooking entity...");
            overbookingSessionBean.initOverbooking(overbookingQuota);
            System.out.println("Overbooking record initiated");
        }catch (Exception e) {
             System.out.println("Error occurs when initiating overbooking");
            return;
        }
        System.err.println("Initiating overbookin entity into database");
    }
    
    public void createRmService(){
        roomService1 = new RoomServiceEntity();
        System.out.println("Creating room service 1....");
        
        roomService1.setRoomServiceName("Laundry");
        roomService1.setRoomServicePrice(0);
        roomService1.setCategory("free service");
        
        try {
            System.out.println(roomService1.getRoomServiceName());
            System.out.println(roomService1.getRoomServicePrice());
            roomServiceSessionBean.addRoomService(roomService1);
            System.err.println("roomService added");
        }catch (Exception e) {
            System.out.println("Error occurs when adding room service");
            return;
        }
        
        roomService2 = new RoomServiceEntity();
        System.out.println("Creating room service 2....");
        
        roomService2.setRoomServiceName("Minibar vodka");
        roomService2.setRoomServicePrice(11.8);
        roomService2.setCategory("charged service");
        
        try {
            System.out.println(roomService2.getRoomServiceName());
            System.out.println(roomService2.getRoomServicePrice());
            roomServiceSessionBean.addRoomService(roomService2);
            System.err.println("roomService added");
        }catch (Exception e) {
            System.out.println("Error occurs when adding room service");
            return;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
