/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.ReservationEntity;
import ACMS.session.ReservationSessionBean;
import CRMS.entity.MemberEntity;
import CRMS.session.MemberSessionBean;
import ERMS.session.EmailSessionBean;
import Exception.ExistException;
import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class ReservationManagedBean implements Serializable {

    @EJB
    private EmailSessionBean emailSessionBean;
    @EJB
    private ReservationSessionBean reservationSessionBean;
    @EJB
    private MemberSessionBean memberSessionBean;
    private List<ReservationEntity> reservationList;
    private ReservationEntity selectReservation;
    private ReservationEntity newReservation;
    private String searchId;
    private String searchName;
    private String searchEmail;
    private List<ReservationEntity> today;
    private List<ReservationEntity> all;
    private List<ReservationEntity> overdue;
    private String memberEmail;

    @PostConstruct
    public void init() {
        today = reservationSessionBean.getTodayReservations();
        all = reservationSessionBean.getAllReservations();
        overdue = reservationSessionBean.getBeforeReservations();
    }

    /**
     * Creates a new instance of ReservationManagedBean
     */
    public ReservationManagedBean() {
        this.selectReservation = new ReservationEntity();
        this.newReservation = new ReservationEntity();
    }

    public void searchById(ActionEvent event) throws IOException, ExistException {

        System.out.println("NO6 we are in searchById function " + searchId);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            selectReservation = reservationSessionBean.getReservationById(Long.valueOf(getSearchId()));
            if (selectReservation == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reservation does not exist!", ""));
                return;
            } else {
                System.out.println("we are after search");
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectReservation", selectReservation);
                System.out.println("we are after setting parameter");
                request.getSession().setAttribute("reservationId", Long.valueOf(getSearchId()));
                System.out.println("we are after setting reservationId session attribute");
                FacesContext.getCurrentInstance().getExternalContext().redirect("ReservationSearchResult.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void cancelReservation(ActionEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
//            selectReservation = (ReservationEntity) event.getComponent().getAttributes().get("cancelReservation");
            System.out.println("cancelReservation: in displaying bean " + selectReservation.getReservationId());
//            selectReservation.setReservationStatus("cancelled");
            reservationSessionBean.cancelReservation(selectReservation);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectReservation", selectReservation);
            System.out.println("we are after setting reservation session attribute");
            FacesContext.getCurrentInstance().getExternalContext().redirect("ReservationSearchResult.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when cancelling the reservation", ""));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "This Reservation is successfully cancelled", ""));
    }

    public void payRoomFee(ActionEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
//            selectReservation = (ReservationEntity) event.getComponent().getAttributes().get("cancelReservation");
            System.out.println("pay room fee: in displaying bean " + selectReservation.getReservationId());
//            selectReservation.setReservationStatus("cancelled");
            reservationSessionBean.payRoomFee(selectReservation);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectReservation", selectReservation);
            System.out.println("we are after setting reservation session attribute");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when updating payment status", ""));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "The outstanding room fee is cleared, now you may proceed to check-in", ""));
    }

    public void selectThisReservation(ActionEvent event) throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        System.err.println("in veiwing reservation" + selectReservation.getReservationId());
        try {
            if (selectReservation == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reservation does not exist!", ""));
                return;
            } else {
                System.out.println("we are after search");
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectReservation", selectReservation);
                System.out.println("we are after setting parameter");
                request.getSession().setAttribute("reservationId", Long.valueOf(getSearchId()));
                System.out.println("we are after setting reservationId session attribute");
                FacesContext.getCurrentInstance().getExternalContext().redirect("ReservationSearchResult.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void searchByName(ActionEvent event) throws IOException, ExistException {

        System.out.println("NO6 we are in searchByName function " + searchName);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            reservationList = reservationSessionBean.getReservationByName(searchName);
            if (reservationList == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reservation does not exist!", ""));
                return;
            } else {
                System.out.println("we are after search");
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationList", reservationList);
                System.out.println("we are after setting parameter");
                request.getSession().setAttribute("rcName", searchName);
                System.out.println("we are after setting reservationId session attribute");
                FacesContext.getCurrentInstance().getExternalContext().redirect("listReservations.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void searchByEmail(ActionEvent event) throws IOException, ExistException {

        System.out.println("NO6 we are in searchByName function " + searchEmail);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            reservationList = reservationSessionBean.getReservationByEmail(searchEmail);
            if (reservationList == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reservation does not exist!", ""));
                return;
            } else {
                System.out.println("we are after search");
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationList", reservationList);
                System.out.println("we are after setting parameter");
                request.getSession().setAttribute("rcEmail", searchEmail);
                System.out.println("we are after setting reservationId session attribute");
                FacesContext.getCurrentInstance().getExternalContext().redirect("listReservations.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void viewReservation() throws IOException, ExistException {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
//            selectReservation = (ReservationEntity) request.getSession().getAttribute("viewReservation");
            System.out.println("NO6 we are in searchByName function " + selectReservation.getReservationId());

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectReservation", selectReservation);
            System.out.println("we are after setting parameter");
            request.getSession().setAttribute("rcEmail", searchEmail);
            System.out.println("we are after setting reservationId session attribute");
            FacesContext.getCurrentInstance().getExternalContext().redirect("ReservationSearchResult.xhtml");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void getTodayReservations(ActionEvent event) throws IOException, ExistException {

        System.out.println("NO6 we are in getting today reservation function ");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            reservationList = reservationSessionBean.getTodayReservations();
            System.out.println("after searching" + reservationList.size());

            System.out.println("we are after session bean");
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationList", reservationList);
            System.out.println("we are after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().redirect("listReservations.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void getBeforeReservations(ActionEvent event) throws IOException, ExistException {

        System.out.println("NO6 we are in getting before reservation function ");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            reservationList = reservationSessionBean.getBeforeReservations();

            System.out.println("we are after session bean");
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationList", reservationList);
            System.out.println("we are after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().redirect("listReservations.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void getAllReservations(ActionEvent event) throws IOException, ExistException {

        System.out.println("NO6 we are in getting all reservation function ");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            reservationList = reservationSessionBean.getAllReservations();

            System.out.println("we are after session bean");
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationList", reservationList);
            System.out.println("we are after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().redirect("listReservations.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void addReservation(ActionEvent event) throws IOException, FileNotFoundException, DocumentException {
        try {
            System.out.println("we are in addReservation in managedbean" + newReservation.getRcName());
            if (newReservation.getReservationRoomType().equals("1")) {
                newReservation.setReservationRoomType("superior");
            }
            if (newReservation.getReservationRoomType().equals("2")) {
                newReservation.setReservationRoomType("deluxe");
            }
            if (newReservation.getReservationRoomType().equals("3")) {
                newReservation.setReservationRoomType("deluxe suite");
            }
            if (newReservation.getReservationRoomType().equals("4")) {
                newReservation.setReservationRoomType("orchard suite");
            }
            if (newReservation.getReservationRoomType().equals("5")) {
                newReservation.setReservationRoomType("chairman suite");
            }
            if (memberEmail != null) {
                MemberEntity thisMember = memberSessionBean.getMemberByEmail(memberEmail);
                newReservation.setRcMember(thisMember);
            }
            reservationSessionBean.addReservation(newReservation);
            System.out.println("we are after add reservation in managedbean");
            selectReservation = reservationSessionBean.getReservationById(newReservation.getReservationId());
//            FacesContext.getCurrentInstance().getExternalContext().redirect("ReservationSearchResult.xhtml");


        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new reservation", ""));
            e.printStackTrace();
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Reservation saved.", ""));
//            emailSessionBean.emailInitialPassward(employee.getPersonalEmail(), initialPwd); //send email
//        emailSessionBean.emailReservationConfirmation(newReservation.getRcEmail(), newReservation);
        System.out.println("email already sent");
//        FacesContext.getCurrentInstance().getExternalContext().redirect("ReservationSearchResult.xhtml");
        newReservation = new ReservationEntity();
        FacesContext.getCurrentInstance().getExternalContext().redirect("checkIncheckOut.xhtml");
    }

    public List<ReservationEntity> getReservatioinList() {
        return reservationList;
    }

    public void setReservatioinList(List<ReservationEntity> reservatioinList) {
        this.reservationList = reservatioinList;
    }

    public ReservationEntity getSelectReservation() {
        return selectReservation;
    }

    public void setSelectReservation(ReservationEntity selectReservation) {
        this.selectReservation = selectReservation;
    }

    public List<String> complete(String query) throws ExistException {
        System.out.println("NO4: we are in complete bean BEFORE");
        List<String> results = new ArrayList<String>();

        reservationList = reservationSessionBean.getAllReservations();
        for (Object o : reservationList) {
            ReservationEntity rve = (ReservationEntity) o;
            if ((rve.getReservationId()).toString().startsWith(query)) {
                results.add((rve.getReservationId()).toString());
            }
        }
        System.out.println("NO5: we are in complete bean AFTER");
        return results;
    }
//     public boolean containReservation() {
//        return ("Hotel".equals(employee.getEmployeeDepartment()));
//    }

    public List<ReservationEntity> getToday() {
        return today;
    }

    public void setToday(List<ReservationEntity> today) {
        this.today = today;
    }

    public List<ReservationEntity> getAll() {
        return all;
    }

    public void setAll(List<ReservationEntity> all) {
        this.all = all;
    }

    public List<ReservationEntity> getOverdue() {
        return overdue;
    }

    public void setOverdue(List<ReservationEntity> overdue) {
        this.overdue = overdue;
    }

    public EmailSessionBean getEmailSessionBean() {
        return emailSessionBean;
    }

    public void setEmailSessionBean(EmailSessionBean emailSessionBean) {
        this.emailSessionBean = emailSessionBean;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public ReservationSessionBean getReservationSessionBean() {
        return reservationSessionBean;
    }

    public void setReservationSessionBean(ReservationSessionBean reservationSessionBean) {
        this.reservationSessionBean = reservationSessionBean;
    }

    public List<ReservationEntity> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<ReservationEntity> reservationList) {
        this.reservationList = reservationList;
    }

    public ReservationEntity getNewReservation() {
        return newReservation;
    }

    public void setNewReservation(ReservationEntity newReservation) {
        this.newReservation = newReservation;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchEmail() {
        return searchEmail;
    }

    public void setSearchEmail(String searchEmail) {
        this.searchEmail = searchEmail;
    }

    public String getSearchId() {
        System.out.println("No3: we are in setearchId" + searchId);
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }
}
