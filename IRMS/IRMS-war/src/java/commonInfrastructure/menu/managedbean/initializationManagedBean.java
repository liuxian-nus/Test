/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.menu.managedbean;

import ACMS.entity.ReservationEntity;
import ACMS.session.ReservationSessionBean;
import CRMS.entity.MemberEntity;
import ERMS.entity.EmployeeEntity;
import ERMS.entity.RoleEntity;
import ERMS.session.EPasswordHashSessionBean;
import ERMS.session.EmployeeSessionBean;
import ERMS.session.RoleSessionBean;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Temporal;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@RequestScoped
public class initializationManagedBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private EmployeeSessionBean employeeSessionBean = new EmployeeSessionBean();
    ;
    @EJB
    private RoleSessionBean roleSessionBean;
    @EJB
    private EPasswordHashSessionBean ePasswordHashSessionBean;
    @EJB
    private ReservationSessionBean reSessionBean;
    private EmployeeEntity employee;
    private RoleEntity role;
    private ReservationEntity reservation;
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

    public void createReservation() {
        System.out.println("go to create hotel reservation page...");
        Date cidate = new Date(2014, 10, 1);
        Date codate = new Date(2014, 10, 5);

        reservation = new ReservationEntity();
        reservation.setReservationId(Long.valueOf(1));
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
        System.out.println("Insert Reservation into database");

        addMessage("Reservation Created!");
    }

    public void createRoom() {
        try {
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
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding room to Orchard Hotel", ""));
            return;
        }
        System.out.println("Insert room into database");
        addMessage("Room Created!");
    }
        //Add new test cases below!!!!!!!!!

}