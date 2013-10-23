  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.menu.managedbean;

import ACMS.entity.LogBookEntity;
import ACMS.entity.OverbookingQuotaEntity;
import ACMS.entity.RoomPriceEntity;
import ACMS.entity.ReservationEntity;
import ACMS.entity.RoomServiceEntity;
import ACMS.session.LogBookSessionBean;
import ACMS.session.OverbookingSessionBean;
import ACMS.session.RoomPriceSessionBean;
import ACMS.session.ReservationSessionBean;
import ACMS.session.RoomServiceSessionBean;
import ACMS.session.RoomSessionBean;
import ATMS.entity.QuotaEntity;
import ATMS.entity.AttrTicketEntity;
import ATMS.session.TicketSessionBean;
import CEMS.entity.EventEntity;
import CEMS.session.EventSessionBean;
import CRMS.entity.MemberEntity;
import CRMS.session.MemberSessionBean;
import ERMS.entity.EmployeeEntity;
import ERMS.entity.FunctionalityEntity;
import ERMS.entity.RoleEntity;
import ERMS.session.EPasswordHashSessionBean;
import ERMS.session.EmployeeSessionBean;
import ERMS.session.FunctionalitySessionBean;
import ERMS.session.RoleSessionBean;
import ESMS.entity.ShowContractEntity;
import ESMS.entity.ShowEntity;
import ESMS.entity.ShowScheduleEntity;
import ESMS.entity.ShowTicketEntity;
import ESMS.session.ShowContractSessionBean;
import ESMS.session.ShowScheduleSessionBean;
import ESMS.session.ShowSessionBean;
import ESMS.session.ShowTicketSessionBean;
import Exception.ExistException;
import FBMS.entity.RestaurantEntity;
import SMMS.entity.ContractEntity;
import SMMS.entity.ContracteventEntity;
import SMMS.entity.MerchantEntity;
import SMMS.entity.OutletEntity;
import SMMS.entity.PushingcartEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.ContracteventSessionBean;
import SMMS.session.MerchantSessionBean;
import SMMS.session.OutletSessionBean;
import SMMS.session.PushingcartSessionBean;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@RequestScoped
public class initializationManagedBean implements Serializable {

    @EJB
    private ContracteventSessionBean contracteventSessionBean;
    @EJB
    private ContractSessionBean contractSessionBean;
    @EJB
    private LogBookSessionBean logBookSessionBean;
    @EJB
    private OutletSessionBean outletSessionBean;
    @EJB
    private MerchantSessionBean merchantSessionBean;
    @EJB
    private PushingcartSessionBean pushingcartSessionBean;
    @EJB
    private RoomPriceSessionBean priceSessionBean;
    @EJB
    private OverbookingSessionBean overbookingSessionBean;
    @EJB
    private MemberSessionBean memberSessionBean;
    @EJB
    private ReservationSessionBean reservationSessionBean;
    @EJB
    private RoomSessionBean roomSessionBean;
    @EJB
    private RoomServiceSessionBean roomServiceSessionBean;
    @EJB
    private EmployeeSessionBean employeeSessionBean = new EmployeeSessionBean();
    @EJB
    private RoleSessionBean roleSessionBean;
    @EJB
    private EPasswordHashSessionBean ePasswordHashSessionBean;
    @EJB
    private FunctionalitySessionBean functionalitySessionBean;
    @EJB
    private TicketSessionBean ticketSessionBean;
    @EJB
    private ShowSessionBean showSessionBean;
    @EJB
    private ShowTicketSessionBean showTicketSessionBean;
    @EJB
    private ShowScheduleSessionBean showScheduleSessionBean;
    @EJB
    private ShowContractSessionBean showContractSessionBean;
    @EJB
    private EventSessionBean eventSessionBean;
    
    private EmployeeEntity employee;
    private RoleEntity role;
    private ReservationEntity reservation;
    private MemberEntity member;
    private MemberEntity member2;
    private FunctionalityEntity functionality;
    private RestaurantEntity restaurant;
    private OverbookingQuotaEntity overbookingQuota;
    private RoomServiceEntity roomService;
    private RoomPriceEntity price;
    private AttrTicketEntity ticket;
    private QuotaEntity quota;
    private MerchantEntity merchant;
    private LogBookEntity log;
    private ShowEntity show;
    private ShowScheduleEntity showSchedule;
    private ShowTicketEntity showTicket;
    private ShowContractEntity showContract;
    private ContractEntity contract;
    private ContracteventEntity event;
    private EventEntity eventEntity;
//    private MemberEntity member;

    @PostConstruct
    public void init() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void createSuperAdmin() {
        System.out.println("go to create super admin");

        functionality = new FunctionalityEntity();
        functionality.setFuncName("addRole");
        functionality.setFuncDescription("access right to addRole page");
        functionalitySessionBean.addFunctionality(functionality);


        role = new RoleEntity();
        role.setRoleId(10);
        role.setRoleName("SuperAdmin");
        role.addFunctionality(functionality);
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding admin", ""));
            return;
        }
        System.out.println("Insert Employee into database");

        addMessage("Super Admin Created!");
    }

    public void createSystemUser() {
        System.out.println("go to create ACMS user");

        role = new RoleEntity();
        role.setRoleId(20);
        role.setRoleName("ACMSAdmin");
        System.out.println("Create role :" + role.getRoleName());

        employee = new EmployeeEntity();
        employee.setEmployeeId("B0000"); //business assumption: maximum employee number 9999
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding admin", ""));
            return;
        }
        System.out.println("Insert Employee into database");
        addMessage("ACMSAdmin Created!");
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

    public void createReservation() {
        System.out.println("go to create hotel reservation page...");
        Date cidate = new Date(2014, 10, 1);
        Date codate = new Date(2014, 10, 6);

        reservation = new ReservationEntity();
        reservation.setRcName("Diana");
        System.out.println("create reservation: welcome " + reservation.getRcName());
        reservation.setRcEmail("diana-wang@yahoo.com");
        reservation.setRcHP("65-81801380");
        reservation.setRcCreditCardNo("1230000045600000");
        reservation.setReservationCorporate("Credit Suisse");
        reservation.setRcCheckInDate(cidate);
        reservation.setRcCheckOutDate(codate);
        reservation.setReservationRoomType("Deluxe");
        reservation.setReservationHotelNo(1);
        reservation.setReservationRoomCount(3);
        reservation.setReservationGuestCount(6);
        reservation.setReservationStatus("guarantee");
        reservation.setRcMember(member);

        try {
            System.out.println("Saving hotel reservation....");

            reservationSessionBean.addReservation(reservation);
            System.out.println("Hotel Reservation saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding reservation", ""));
            return;
        }
        System.out.println("Insert Reservation into database");

        addMessage("Reservation Created!");
    }

    public void createCEMSAdmin() {
        System.out.println("go to create CEMS admin");

        role = new RoleEntity();
        role.setRoleId(30);
        role.setRoleName("CEMSAdmin");
        System.out.println("Create role :" + role.getRoleName());

        employee = new EmployeeEntity();
        employee.setEmployeeId("C0000"); //business assumption: maximum employee number 9999
        employee.setEmployeeName("CEMSAdmin");
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword("C0000"));
        System.out.println("finished hashing");
        employee.addRole(role);
        employee.setIsFirstTimeLogin(false);
        System.out.println("Create employee :" + employee.getEmployeeId() + "," + employee.getEmployeeName() + "," + employee.getEmployeePassword());

        try {
            System.out.println("Saving CEMSAdmin....");
            employeeSessionBean.addEmployee(employee);
            System.out.println("CEMSAdmin saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding admin", ""));
            return;
        }
        System.out.println("Insert Employee into database");
        addMessage("CEMSAdmin Created!");
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding admin", ""));
            return;
        }
        System.out.println("Insert Employee into database");
        addMessage("FBMSAdmin Created!");
    }

    public void createATMSAdmin() {
        System.out.println("go to create ATMS page");

        role = new RoleEntity();
        role.setRoleId(60);
        role.setRoleName("ATMSAdmin");
        System.out.println("Create role :" + role.getRoleName());

        employee = new EmployeeEntity();
        employee.setEmployeeId("F0000"); //business assumption: maximum employee number 9999
        employee.setEmployeeName("ATMSAdmin");
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword("F0000"));
        System.out.println("finished hashing");
        employee.addRole(role);
        employee.setIsFirstTimeLogin(false);
        System.out.println("Create employee :" + employee.getEmployeeId() + "," + employee.getEmployeeName() + "," + employee.getEmployeePassword());

        try {
            System.out.println("Saving ATMSAdmin....");
            employeeSessionBean.addEmployee(employee);
            System.out.println("ATMSAdmin saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding admin", ""));
            return;
        }
        System.out.println("Insert ATMSAdmin into database");
        addMessage("ATMSAdmin Created!");
    }

    public void createSMMSAdmin() {
        System.out.println("go to create SMMS page");
        functionality = new FunctionalityEntity();
        functionality.setFuncName("approveContract");
        functionality.setFuncDescription("manager approve contract");
        functionalitySessionBean.addFunctionality(functionality);

        FunctionalityEntity functionality2 = new FunctionalityEntity();
        functionality2.setFuncName("managerViewContract");
        functionality2.setFuncDescription("manager View Contract");
        functionalitySessionBean.addFunctionality(functionality2);

        role = new RoleEntity();
        role.setRoleId(40);
        role.setRoleName("SMMSAdmin");
        role.addFunctionality(functionality);
        role.addFunctionality(functionality2);
        System.out.println("Create role :" + role.getRoleName());

        employee = new EmployeeEntity();
        employee.setEmployeeId("D0000"); //business assumption: maximum employee number 9999
        employee.setEmployeeName("SMMSAdmin");
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword("D0000"));
        System.out.println("finished hashing");
        employee.addRole(role);
        employee.setIsFirstTimeLogin(false);
        System.out.println("Create employee :" + employee.getEmployeeId() + "," + employee.getEmployeeName() + "," + employee.getEmployeePassword());

        try {
            System.out.println("Saving SMMSAdmin....");
            employeeSessionBean.addEmployee(employee);
            System.out.println("SMMSAdmin saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding admin", ""));
            return;
        }
        System.out.println("Insert Employee into database");
        addMessage("SMMSAdmin Created!");
    }

    public void createSMMSOps() {
        System.out.println("go to create SMMS page");

        functionality = new FunctionalityEntity();
        functionality.setFuncName("addContract");
        functionality.setFuncDescription("add contract");
        functionalitySessionBean.addFunctionality(functionality);

        FunctionalityEntity functionality2 = new FunctionalityEntity();
        functionality2.setFuncName("addMerchant");
        functionality2.setFuncDescription("add  Merchant");
        functionalitySessionBean.addFunctionality(functionality2);
        
        FunctionalityEntity functionality3 = new FunctionalityEntity();
        functionality3.setFuncName("addPushingcart");
        functionality3.setFuncDescription("add pushing cart");
        functionalitySessionBean.addFunctionality(functionality3);

        FunctionalityEntity functionality4 = new FunctionalityEntity();
        functionality4.setFuncName("manageContract");
        functionality4.setFuncDescription("manager View Contract");
        functionalitySessionBean.addFunctionality(functionality4);
        
        FunctionalityEntity functionality5 = new FunctionalityEntity();
        functionality5.setFuncName("outletManagement");
        functionality5.setFuncDescription("manager approve contract");
        functionalitySessionBean.addFunctionality(functionality5);

        FunctionalityEntity functionality6 = new FunctionalityEntity();
        functionality6.setFuncName("paymentManagement");
        functionality6.setFuncDescription("manager View Contract");
        functionalitySessionBean.addFunctionality(functionality6);
        
        FunctionalityEntity functionality7 = new FunctionalityEntity();
        functionality7.setFuncName("pushingcartManagement");
        functionality7.setFuncDescription("manager approve contract");
        functionalitySessionBean.addFunctionality(functionality7);

        FunctionalityEntity functionality8 = new FunctionalityEntity();
        functionality8.setFuncName("viewContract");
        functionality8.setFuncDescription("manager View Contract");
        functionalitySessionBean.addFunctionality(functionality8);

        role = new RoleEntity();
        role.setRoleId(41);
        role.setRoleName("SMMSOps");
        role.addFunctionality(functionality);
        role.addFunctionality(functionality2);
        role.addFunctionality(functionality3);
        role.addFunctionality(functionality4);
        role.addFunctionality(functionality5);
        role.addFunctionality(functionality6);
        role.addFunctionality(functionality7);
        role.addFunctionality(functionality8);
        System.out.println("Create role :" + role.getRoleName());

        employee = new EmployeeEntity();
        employee.setEmployeeId("D0100"); //business assumption: maximum employee number 9999
        employee.setEmployeeName("SMMSOps");
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword("D0100"));
        System.out.println("finished hashing");
        employee.addRole(role);
        employee.setIsFirstTimeLogin(false);
        System.out.println("Create employee :" + employee.getEmployeeId() + "," + employee.getEmployeeName() + "," + employee.getEmployeePassword());

        try {
            System.out.println("Saving SMMSOps....");
            employeeSessionBean.addEmployee(employee);
            System.out.println("SMMSOps saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding SMMSOps", ""));
            return;
        }
        System.out.println("Insert Employee into database");
        addMessage("SMMSOps Created!");
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding admin", ""));
            return;
        }
        System.out.println("Insert Employee into database");
        addMessage("CRMSAdmin Created!");
    }

    public void createMember() {
        System.err.println("go to create member page...");
        Date qqdate = new Date(91, 02, 11);

        member = new MemberEntity();
        member.setMemberEmail("xinqi_wang@yahoo.com");
        member.setMemberPassword("ABCabc123");
        member.setMemberName("Diana");
        System.out.println("Create a new member: welcome! " + member.getMemberName());
        member.setMemberHP("92728760");
        member.setNationality("China");
        member.setMemberDob(qqdate);
        member.setGender("Female");
        member.setMaritalStatus("Single");
        member.setIsVIP(false);
        member.setIsSubscriber(true);
        member.setSecurityQuestion("What is your mother's original surname?");
        member.setAnswer("Wang");
        member.setPreferences("to be set");

        try {
            System.out.println("Creating new member....");
            memberSessionBean.addMember(member);
            System.out.println("Member created....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding member", ""));
            return;
        }
        System.err.println("Insert Dayanqi member into database");
        addMessage("Member Created!");
    }

    public void createVIP() {
        System.err.println("go to create VIP page...");
        Date bowendate = new Date(90, 10, 8);

        member2 = new MemberEntity();
        member2.setMemberEmail("bowen@nus.edu.sg");
        member2.setMemberPassword("ABCabc123");
        member2.setMemberName("Bowen");
        System.out.println("Create a new member: welcome! " + member2.getMemberName());
        member2.setMemberHP("92728760");
        member2.setNationality("China");
        member2.setMemberDob(bowendate);
        member2.setGender("Female");
        member2.setMaritalStatus("Single");
        member2.setIsVIP(true);
        member2.setIsSubscriber(true);
        member2.setSecurityQuestion("What is your mother's original surname?");
        member2.setAnswer("Zheng");
        member2.setPreferences("to be set");
        member2.setPoint(10000);
        member2.setCoin(200);

        try {
            System.out.println("Creating new member....");
            memberSessionBean.addMember(member2);
            System.out.println("Member created....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding member", ""));
            return;
        }
        System.err.println("Insert Bowen VIP into database");
        addMessage("VIP member Created!");
    }

    public void createRoom() {
        try {
            System.err.println("Insert room started.....");
            price = new RoomPriceEntity();
            price.setPriceType("deluxe");
            price.setPrice(485.3);
            priceSessionBean.createPrice(price);
            price = new RoomPriceEntity();
            price.setPriceType("superior");
            price.setPrice(380.3);
            priceSessionBean.createPrice(price);
            roomSessionBean.createTestRoom(1, 1, 1, "deluxe", "available");
            roomSessionBean.createTestRoom(1, 1, 2, "deluxe", "available");
            roomSessionBean.createTestRoom(1, 1, 3, "superior", "available");
            roomSessionBean.createTestRoom(1, 1, 4, "superior", "available");
            roomSessionBean.createTestRoom(1, 1, 5, "superior", "available");
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding room to Orchard Hotel", ""));
            return;
        }
        System.out.println("Insert room into database");
        addMessage("Room Created!");
    }

    public void createFunctionalities() {
        functionality = new FunctionalityEntity();
        functionality.setFuncName("addFunctionality");
        functionality.setFuncDescription("access right to addFunctionality page");

        try {
            System.out.println("Creating new functionality....");
            functionalitySessionBean.addFunctionality(functionality);
            System.out.println("Functionality created....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding functionality", ""));
            return;
        }
        System.err.println("Insert systemMsg functionality into database");
        addMessage("Functionality Created!");
    }

    public void createOverbooking() {
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
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when initiating overbooking", ""));
            return;
        }
        System.err.println("Initiating overbooking entity into database");
    }

    public void createRmService() {
        roomService = new RoomServiceEntity();
        System.out.println("Creating room service 1....");

        roomService.setRoomServiceName("Laundry");
        roomService.setRoomServicePrice(0);
        roomService.setCategory("free service");

        try {
            System.out.println(roomService.getRoomServiceName());
            System.out.println(roomService.getRoomServicePrice());
            roomServiceSessionBean.addRoomService(roomService);
            System.err.println("roomService added");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding room service", ""));
            return;
        }

        roomService = new RoomServiceEntity();
        System.out.println("Creating room service 2....");

        roomService.setRoomServiceName("Housekeeping");
        roomService.setRoomServicePrice(0);
        roomService.setCategory("free service");

        try {
            System.out.println(roomService.getRoomServiceName());
            System.out.println(roomService.getRoomServicePrice());
            roomServiceSessionBean.addRoomService(roomService);
            System.err.println("roomService added");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding room service", ""));
            return;
        }

        roomService = new RoomServiceEntity();
        System.out.println("Creating room service 3....");

        roomService.setRoomServiceName("TV Channel Subscription 1");
        roomService.setRoomServicePrice(19.9);
        roomService.setCategory("charged service");

        try {
            System.out.println(roomService.getRoomServiceName());
            System.out.println(roomService.getRoomServicePrice());
            roomServiceSessionBean.addRoomService(roomService);
            System.err.println("roomService added");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding room service", ""));
            return;
        }

        roomService = new RoomServiceEntity();
        System.out.println("Creating room service 4....");

        roomService.setRoomServiceName("TV Channel Subscription 2");
        roomService.setRoomServicePrice(49.9);
        roomService.setCategory("charged service");

        try {
            System.out.println(roomService.getRoomServiceName());
            System.out.println(roomService.getRoomServicePrice());
            roomServiceSessionBean.addRoomService(roomService);
            System.err.println("roomService added");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding room service", ""));
            return;
        }

        roomService = new RoomServiceEntity();
        System.out.println("Creating room service 5....");

        roomService.setRoomServiceName("Custard Puff");
        roomService.setRoomServicePrice(5.4);
        roomService.setCategory("food");

        try {
            System.out.println(roomService.getRoomServiceName());
            System.out.println(roomService.getRoomServicePrice());
            roomServiceSessionBean.addRoomService(roomService);
            System.err.println("roomService added");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding room service", ""));
            return;
        }
        roomService = new RoomServiceEntity();
        System.out.println("Creating room service 6....");

        roomService.setRoomServiceName("Chocolate Puff");
        roomService.setRoomServicePrice(5.4);
        roomService.setCategory("food");

        try {
            System.out.println(roomService.getRoomServiceName());
            System.out.println(roomService.getRoomServicePrice());
            roomServiceSessionBean.addRoomService(roomService);
            System.err.println("roomService added");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding room service", ""));
            return;
        }
        roomService = new RoomServiceEntity();
        System.out.println("Creating room service 7....");

        roomService.setRoomServiceName("Thai Pineapple Rice");
        roomService.setRoomServicePrice(10);
        roomService.setCategory("food");

        try {
            System.out.println(roomService.getRoomServiceName());
            System.out.println(roomService.getRoomServicePrice());
            roomServiceSessionBean.addRoomService(roomService);
            System.err.println("roomService added");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding room service", ""));
            return;
        }
    }

    /*  public void createTickets() {
     System.out.println("go to create Tickets page");

     quota = new QuotaEntity();
     quota.setMaxQuota(500);
     quota.setRestQuota(500);
     ticket = new AttrTicketEntity();
     ticket.setTicketName("Indoor Themepark");
     ticket.setTicketPrice(49.9);
     ticket.setQuota(quota);

     try {
     System.out.println("Saving tickets....");
     ticketSessionBean.addTicket(ticket);
     System.out.println("ticket saved...");
     } catch (Exception e) {
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding ticket", ""));
     return;
     }
     System.out.println("Insert Ticket into database");


     System.out.println("create 2nd ticket");

     quota = new QuotaEntity();
     quota.setMaxQuota(1000);
     quota.setRestQuota(1000);
     ticket = new AttrTicketEntity();
     ticket.setTicketName("Outdoor Themepark");
     ticket.setTicketPrice(79.9);
     ticket.setQuota(quota);

     try {
     System.out.println("Saving tickets....");
     ticketSessionBean.addTicket(ticket);
     System.out.println("ticket saved...");
     } catch (Exception e) {
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding ticket", ""));
     return;
     }
     System.out.println("Insert 2nd Ticket into database");

     System.out.println("create 3nd ticket");

     quota = new QuotaEntity();
     quota.setMaxQuota(400);
     quota.setRestQuota(400);
     ticket = new AttrTicketEntity();
     ticket.setTicketName("Aquarium");
     ticket.setTicketPrice(39.9);
     ticket.setQuota(quota);

     try {
     System.out.println("Saving tickets....");
     ticketSessionBean.addTicket(ticket);
     System.out.println("ticket saved...");
     } catch (Exception e) {
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding ticket", ""));
     return;
     }
     System.out.println("Insert 3nd Ticket into database");

     System.out.println("create 4th ticket");

     quota = new QuotaEntity();
     quota.setMaxQuota(200);
     quota.setRestQuota(200);
     ticket = new AttrTicketEntity();
     ticket.setTicketName("Museum");
     ticket.setTicketPrice(9.9);
     ticket.setQuota(quota);

     try {
     System.out.println("Saving tickets....");
     ticketSessionBean.addTicket(ticket);
     System.out.println("ticket saved...");
     } catch (Exception e) {
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding ticket", ""));
     return;
     }
     System.out.println("Insert 4th Ticket into database");
     addMessage("Tickets Created!");


     }*/
    public void createMerchant() {
        System.out.println("go to create merchant page...");
//        functionality = new FunctionalityEntity();
//        functionality.setFuncName("viewContractPartner");
//        functionality.setFuncDescription("View contract");
//        functionalitySessionBean.addFunctionality(functionality);
//        
//        FunctionalityEntity functionality2 = new FunctionalityEntity();
//        functionality2.setFuncName("makePaymentPartner");
//        functionality2.setFuncDescription("Make Outstanding payment");
//        functionalitySessionBean.addFunctionality(functionality2);
//        
//        FunctionalityEntity functionality3 = new FunctionalityEntity();
//        functionality3.setFuncName("viewOutletPartner");
//        functionality3.setFuncDescription("Make Outstanding payment");
//        functionalitySessionBean.addFunctionality(functionality3);

//        role = new RoleEntity();
//        role.setRoleId(41);
//        role.setRoleName("SMMSOps");
//        role.addFunctionality(functionality);
//        role.addFunctionality(functionality2);
//        role.addFunctionality(functionality3);
//        System.out.println("Create role :" + role.getRoleName());
//        
        merchant = new MerchantEntity();
//        merchant.setRoles(role);
        merchant.setMerchantEmail("cookiewxy@hotmail.com");
        merchant.setMerchantName("cookie");
        merchant.setMerchantPassword(ePasswordHashSessionBean.hashPassword("M0000"));
        merchant.setMerchantHP("81116398");
        merchant.setMerchantAddress("35 Prince George's Park");
        merchant.setSecurityQuestion("What is your mother's original surname?");
        merchant.setAnswer("Gu");
        merchant.setPartnerType("shoppingMall");


        MerchantEntity merchant2 = new MerchantEntity();
        merchant2.setMerchantEmail("lionetdd@gmail.com");
        merchant2.setMerchantName("liuyudi");
        merchant2.setMerchantPassword(ePasswordHashSessionBean.hashPassword("M1000"));
        merchant2.setMerchantHP("81116391");
        merchant2.setMerchantAddress("30 Prince George's Park");
        merchant2.setSecurityQuestion("What is your mother's original surname?");
        merchant2.setAnswer("Gu");
        merchant2.setPartnerType("shoppingMall");

        MerchantEntity merchant3 = new MerchantEntity();
        merchant3.setMerchantEmail("chrislx.nus@gmail.com");
        merchant3.setMerchantName("liuxian");
        merchant3.setMerchantPassword(ePasswordHashSessionBean.hashPassword("M2000"));
        merchant3.setMerchantHP("81113391");
        merchant3.setMerchantAddress("25 Prince George's Park");
        merchant3.setSecurityQuestion("What is your mother's original surname?");
        merchant3.setAnswer("Gu");
        merchant3.setPartnerType("shoppingMall");

        try {
            System.out.println("Saving merchant....");

            merchantSessionBean.addMerchant(merchant);
            merchantSessionBean.addMerchant(merchant2);
            merchantSessionBean.addMerchant(merchant3);
            System.out.println("Merchant saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding reservation", ""));
            return;
        }
        System.out.println("Insert merchant into database");

        addMessage("Merchant Created!");
    }

    public void createOutlet() {
        System.out.println("go to create outlet page...");

        OutletEntity outlet = new OutletEntity();
        outlet.setOutletLevel(2);
        outlet.setOutletNo(17);
        outlet.setOutletId(2, 17);
        outlet.setOutletType("appliance");
        outlet.setOutletArea(17.85);

        OutletEntity outlet2 = new OutletEntity();
        outlet2.setOutletLevel(2);
        outlet2.setOutletNo(10);
        outlet2.setOutletId(2, 10);
        outlet2.setOutletType("jewelery");
        outlet2.setOutletArea(14.07);

        OutletEntity outlet3 = new OutletEntity();
        outlet3.setOutletLevel(3);
        outlet3.setOutletNo(11);
        outlet3.setOutletId(3, 11);
        outlet3.setOutletType("appareal");
        outlet3.setOutletArea(21.33);

        OutletEntity outlet4 = new OutletEntity();
        outlet3.setOutletLevel(4);
        outlet3.setOutletNo(12);
        outlet3.setOutletId(4, 12);
        outlet3.setOutletType("cosmetics");
        outlet3.setOutletArea(19.23);

        try {
            System.out.println("Saving outlets....");

            outletSessionBean.addOutlet(outlet);
            outletSessionBean.addOutlet(outlet2);
            outletSessionBean.addOutlet(outlet3);
            outletSessionBean.addOutlet(outlet4);

            System.out.println("Outlets saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding merchant", ""));
            return;
        }
        System.out.println("Insert outlet into database");

        addMessage("Outlets! Created!");
    }

    public void createPushingcart() {
        System.out.println("go to create pushingcart page...");

        PushingcartEntity pushingcart = new PushingcartEntity();
        pushingcart.setPushingcartType("basket");
        pushingcart.setPushingcartLevel(1);
        pushingcart.setPushingcartArea("east");
        pushingcart.setPushingcartInventory(30);

        PushingcartEntity pushingcart2 = new PushingcartEntity();
        pushingcart2.setPushingcartType("trolley");
        pushingcart2.setPushingcartLevel(1);
        pushingcart2.setPushingcartArea("west");
        pushingcart2.setPushingcartInventory(28);

        PushingcartEntity pushingcart3 = new PushingcartEntity();
        pushingcart3.setPushingcartType("basket");
        pushingcart3.setPushingcartLevel(2);
        pushingcart3.setPushingcartArea("west");
        pushingcart3.setPushingcartInventory(50);


        try {
            System.out.println("Saving cart....");
            pushingcartSessionBean.addPushingcart(pushingcart);
            pushingcartSessionBean.addPushingcart(pushingcart2);
            pushingcartSessionBean.addPushingcart(pushingcart3);

            System.out.println("Cart saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding merchant", ""));
            return;
        }
        System.out.println("Insert cart into database");

        addMessage("Carts! Created!");
    }

    public void createContract() {
        System.out.println("go to create Contract Page...");

        ContractEntity contract1 = new ContractEntity();
        Date cidate = new Date(2013, 11, 1);
        Date codate = new Date(2015, 11, 1);
        Date podate = new Date(2014, 1, 1);
        try {

            System.out.println("Saving cart....");

            MerchantEntity merchanta = merchantSessionBean.getMerchantById("cookiewxy@hotmail.com");
            OutletEntity outleta = outletSessionBean.getOutletById(217);
            contract1.setMerchant(merchanta);
            contract1.setOutlet(outleta);
            contractSessionBean.addContract(contract1);
            System.out.println("Contract saved....." + contract1.getContractId());


            ContracteventEntity event1 = new ContracteventEntity();
            event1.setEventStartDate(cidate);
            event1.setEventEndDate(codate);
            event1.setEventDownDate(podate);
            event1.setEventDeposit(50000.00);
            event1.setEventMonthRate(10000.00);
            event1.setEventCommissionRate(0.32);
            event1.setEventDownPayment(20000);
            event1.setEventStatus("new");
            event1.setEventContract(contract1);
            contracteventSessionBean.addContractevent(event1);
            System.out.println("Contract saved....." + event1.getContracteventId());


            contractSessionBean.addContractevent(contract1.getContractId(), event1.getContracteventId());
            merchantSessionBean.addContractInMerchant(contract1.getContractId(), merchanta.getMerchantEmail());

            outleta.setContract(contract1);
            outletSessionBean.updateOutlet(outleta);

            System.out.println("Contract saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding merchant", ""));
            return;
        }



        ContractEntity contract2 = new ContractEntity();
        Date cidate1 = new Date(2013, 12, 1);
        Date codate1 = new Date(2015, 12, 1);
        Date podate1 = new Date(2014, 1, 1);
        try {

            System.out.println("Saving cart....");

            MerchantEntity merchantb = merchantSessionBean.getMerchantById("lionetdd@gmail.com");
            OutletEntity outletb = outletSessionBean.getOutletById(311);
            contract1.setMerchant(merchantb);
            contract1.setOutlet(outletb);
            contractSessionBean.addContract(contract2);
            System.out.println("Contract saved....." + contract2.getContractId());


            ContracteventEntity event2 = new ContracteventEntity();
            event2.setEventStartDate(cidate1);
            event2.setEventEndDate(codate1);
            event2.setEventDownDate(podate1);
            event2.setEventDeposit(55000.00);
            event2.setEventMonthRate(11000.00);
            event2.setEventCommissionRate(0.22);
            event2.setEventDownPayment(21000);
            event2.setEventStatus("new");
            event2.setEventContract(contract2);
            contracteventSessionBean.addContractevent(event2);
            System.out.println("Contract saved....." + event2.getContracteventId());


            contractSessionBean.addContractevent(contract1.getContractId(), event2.getContracteventId());
            merchantSessionBean.addContractInMerchant(contract1.getContractId(), merchantb.getMerchantEmail());

            outletb.setContract(contract1);
            outletSessionBean.updateOutlet(outletb);

            System.out.println("Contract saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding merchant", ""));
            return;
        }

        System.out.println("Insert cart into database");
        addMessage("Carts! Created!");
    }

    public void createESMSAdmin() {
        System.out.println("go to create ESMSAdmin");

        role = new RoleEntity();
        role.setRoleId(70);
        role.setRoleName("ESMSAdmin");
        System.out.println("Create role :" + role.getRoleName());

        employee = new EmployeeEntity();
        employee.setEmployeeId("G0000"); //business assumption: maximum employee number 9999
        employee.setEmployeeName("ESMSAdmin");
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword("G0000"));
        System.out.println("finished hashing");
        employee.addRole(role);
        employee.setIsFirstTimeLogin(false);
        System.out.println("Create employee :" + employee.getEmployeeId() + "," + employee.getEmployeeName() + "," + employee.getEmployeePassword());

        try {
            System.out.println("Saving ESMS Admin....");

            employeeSessionBean.addEmployee(employee);
            System.out.println("ESMS Admin saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding admin", ""));
            return;
        }
        System.out.println("Insert Employee into database");

        addMessage("ESMS Admin Created!");
    }

    public void createLogBook() throws ExistException {
        System.out.println("go to create log book");
        Date today = new Date(13, 11, 8);

        log = new LogBookEntity();
        EmployeeEntity thisEmployee = employeeSessionBean.getEmployeeById("B0000");
        log.setLogShift(1);
        log.setPublishDate(today);
        log.setLogTitle("HaHa");
        log.setLogText("HaHa,我们有一个神叫包神，好吧我必须让这个message长一点，可是我又没什么好说的，那我要怎么才能让它长一点呢？本来就是一个test case，为什么非要让它那么长呢？累觉不爱了，好吧就这样了");
        log.setRemark("test");
        log.setLogEmployee(thisEmployee);
        try {
            System.out.println("Saving Log....");
            logBookSessionBean.addLog(log);
            System.out.println("new log Saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding log", ""));
            return;
        }
        System.out.println("Insert Log into database");

        addMessage("New Log Saved!");

    }

    public void createShow() {
        Date ssDate1 = new Date(114, 2, 2, 12, 0 ,0);
        Date ssDate2 = new Time(2, 0, 0);
        Date ssDate3 = new Time(16, 0, 0);
        System.err.println("creating show...");
        show = new ShowEntity();
        showTicket = new ShowTicketEntity();
        showSchedule = new ShowScheduleEntity();
        showContract = new ShowContractEntity();
        
        show.setShowName("Harry Potter");
        show.setShowDescription("Harry Potter and the Philosopher's Stone");
        show.setShowType("External");
        showContract.setShowMerchantName("Wan Xiangyi");
        showContract.setShowMerchantEmail("cookiewxy@gmail.com");
        showContract.setShowMerchantAddress("22 Prince George's Park");
        showContract.setShowMerchantContact("12345678");
        showContract.setShowTicketCommission(0.12);
        showContract.setShowVenueDuration(3);
        showContract.setShowVenueRate(1300.00);
        showContractSessionBean.addShowContract(showContract);
        
        showTicket.setShowTicketPrice(35.00);
        showTicket.setShowTicketQuantity(100);
        showTicket.setShowTicketType("Premium");
        showTicket.setShowTicketQuota(100);
        showTicketSessionBean.addShowTicket(showTicket);
        
        showSchedule.setDuration(ssDate2);
        showSchedule.setStartDateTime(ssDate1);
//        showSchedule.setStartTime(ssDate3);
        showSchedule.addShowTicket(showTicket);
        showScheduleSessionBean.addShowSchedule(showSchedule);
        
        show.addShowSchedule(showSchedule);
        show.setShowContract(showContract);
        try {
            System.out.println("Saving show....");
            showSessionBean.addShow(show);
            System.out.println("Show saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding show", ""));
            return;
        }
        addMessage("Show Created!");
    }
    
    public void createShowContract() {
        showContract = new ShowContractEntity();
        showContract.setShowMerchantName("Zheng Bowen");
        showContract.setShowMerchantEmail("s.er3na.j@gmail.com");
        showContract.setShowMerchantContact("91772046");
        showContract.setShowMerchantAddress("21 Prince George's Park Residence 1");
        showContract.setShowTicketCommission(0.15);
        showContract.setShowVenueDuration(3);
        showContract.setShowVenueRate(1500.00);
        try {
            System.out.println("Saving show contract....");
            showContractSessionBean.addShowContract(showContract);
            System.out.println("Show contract saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding show contract", ""));
            return;
        }
        addMessage("Show Contract Created!");
    }
    
    public void createEvent(){
        eventEntity = new EventEntity();
        eventEntity.setEventName("Liu Xian");
        eventEntity.setEventType("Wedding");
        eventEntity.setName("Hao Yuan");
        eventEntity.setEmail("LX <3 HY");
        eventEntity.setEventContact("31415926");
        try {
            eventSessionBean.addEvent(eventEntity);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding show contract", ""));
            return;
        }
        addMessage("Event Created!");
    }
    //Add new test cases below!!!!!!!!!

    public void initialize() throws ExistException {
        createEvent();
        createShowContract();
        createCEMSAdmin();
        createShow();
        createSuperAdmin();
        createSystemUser();
        createFrontDesk();
        createMember();
        createVIP();
        createReservation();
        createFBMSAdmin();
        createATMSAdmin();
        createCRMSAdmin();
        createRoom();
        createFunctionalities();
        createOverbooking();
        createRmService();
        createSMMSAdmin();
        createSMMSOps();
        createLogBook();
        createMerchant();
        createOutlet();
        createPushingcart();
        createContract();
        createESMSAdmin();

        addMessage("Initialization succeed!");
    }
}
