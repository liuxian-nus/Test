/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ACMS.entity.ReservationEntity;
import ACMS.session.ReservationSessionBean;
import CRMS.entity.MemberEntity;
import CRMS.session.CPasswordHashSessionBean;
import CRMS.session.MemberSessionBean;
import ERMS.entity.EmployeeEntity;
import ERMS.entity.RoleEntity;
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
@Startup
public class InitSessionBean {

    @PersistenceContext
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
    
    private EmployeeEntity employee;
    private RoleEntity role;
    private ReservationEntity reservation;
    private MemberEntity member;

    @PostConstruct
    public void init() {
        System.err.println("InitSessionBean.init");
    }

    public void createSuperAdmin() {
        System.err.println("go to create super admin");

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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding admin", ""));
            return;
        }
        System.err.println("Insert Employee into database");

    }

    public void createSystemUser() {
        System.err.println("go to create ACMS user");

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
        System.err.println("Insert Employee into database");

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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding reservation", ""));
            return;
        }
        System.err.println("Insert Reservation into database");
    }

    //Add new test cases below!!!!!!!!!
    public void createMember() {
        System.err.println("go to create member page...");       
        Date qqdate = new Date(1991,03,11);
        
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
        member.setIsVIP(false);
        member.setIsSubscriber(true);
        
        try {
            System.out.println("Creating new member....");
            mmSessionBean.addMember(member);
            System.out.println("Member created....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding member", ""));
            return;
        }
        System.err.println("Insert dayan-wang member into database");
    }
}
