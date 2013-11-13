/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.managedBean;

import CRMS.entity.CouponEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.CouponSessionBean;
import CRMS.session.MemberSessionBean;
import CRMS.session.MemberTransactionSessionBean;
import ERMS.entity.EmployeeEntity;
import ESMS.entity.ShowEntity;
import ESMS.entity.ShowScheduleEntity;
import ESMS.entity.ShowTicketEntity;
import ESMS.entity.ShowTicketSaleEntity;
import ESMS.session.ShowScheduleSessionBean;
import ESMS.session.ShowSessionBean;
import ESMS.session.ShowTicketSaleSessionBean;
import ESMS.session.ShowTicketSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ShowTicketingManagedBean {

    @EJB
    ShowSessionBean showSessionBean;
    @EJB
    ShowScheduleSessionBean showScheduleSessionBean;
    @EJB
    ShowTicketSessionBean showTicketSessionBean;
    @EJB
    ShowTicketSaleSessionBean showTicketSaleSessionBean;
    @EJB
    MemberTransactionSessionBean memberTransactionSessionBean;
    @EJB
    MemberSessionBean memberSessionBean;
    @EJB
    CouponSessionBean couponSessionBean;
    private ShowEntity selectedShow;
    private ShowScheduleEntity selectedShowSchedule;
    private ShowTicketEntity selectedShowTicket;
    private ShowTicketSaleEntity selectedShowTicketSale;
    private MemberTransactionEntity memberTransaction;
    private MemberEntity member;
    private CouponEntity coupon;
    private List<ShowEntity> showList;
    private List<ShowScheduleEntity> showSchedules;
    private List<ShowTicketEntity> showTickets = new ArrayList<ShowTicketEntity>();
    private String searchName;
    private Long showId;
    private Long showScheduleId;
    private Long showTicketId;
    private boolean mode;
    private int showTicketQuota;// number of tickets bought
    private boolean isMember;
    private Long couponId;

    //Constructor
    public ShowTicketingManagedBean() {
        selectedShow = new ShowEntity();
        selectedShowSchedule = new ShowScheduleEntity();
        selectedShowTicket = new ShowTicketEntity();
        selectedShowTicketSale = new ShowTicketSaleEntity();
        memberTransaction = new MemberTransactionEntity();
    }

    //Methods
    public List<String> complete(String query) throws ExistException {
        List<String> results = new ArrayList<String>();
        System.err.println("auto complete");

        showList = showSessionBean.getAllShows();
        for (Object o : showList) {
            ShowEntity se = (ShowEntity) o;
            if ((se.getShowName()).toString().startsWith(query)) {
                results.add((se.getShowName()).toString());
            }
        }
        return results;
    }

    public List<String> completeMember(String query) throws ExistException {
        List<String> results = new ArrayList<String>();

        List<MemberEntity> memberList = memberSessionBean.getAllMembers();

        for (Object o : memberList) {
            MemberEntity emp = (MemberEntity) o;
            if (emp.getMemberEmail().startsWith(query)) {
                results.add(emp.getMemberEmail());
            }
        }
        return results;
    }

    public void searchByName(ActionEvent event) throws IOException, ExistException {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            showList = showSessionBean.getShowByName(searchName);
            if (showList == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Show does not exist!", ""));
                return;
            } else {
                System.out.println(showList.size());
                System.out.println(showList.get(0).getShowSchedules().size());
                System.out.println("we are after search");
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("showList", showList);
                System.out.println("we are after setting parameter");
                request.getSession().setAttribute("showName", searchName);
                System.out.println("we are after setting reservationId session attribute");
                FacesContext.getCurrentInstance().getExternalContext().redirect("showTicketingResult.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void handleShowChanges() {
        if (showId != null) {
            selectedShow = showSessionBean.getShowById(showId);
            showSchedules = selectedShow.getShowSchedules();
        } else {
            FacesMessage msg = new FacesMessage("Error occours during ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void handleShowScheduleChanges() {
        if (showScheduleId != null) {
            selectedShowSchedule = showScheduleSessionBean.getShowScheduleById(showScheduleId);
            showTickets = selectedShowSchedule.getShowTickets();
        } else {
            FacesMessage msg = new FacesMessage("Error occours during ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void handleShowTicketChanges() {
        if (showTicketId != null) {
            selectedShowTicket = showTicketSessionBean.getShowTicketById(showTicketId);
            showTicketQuota = selectedShowTicket.getShowTicketQuota();
        } else {
            FacesMessage msg = new FacesMessage("Error occours during ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void buyTicket() throws IOException, ExistException {
        System.err.println("buying ticket from ticket office: ");
        System.out.println("showId: " + showId);
        System.out.println("showSchedule: " + showScheduleId);
        System.out.println("showTicket: " + showTicketId);
        System.out.println("coupon: " + couponId);
        //email from page
        String email1 = memberTransaction.getMemberEmail();
        //email from database
        String email2 = null;

        if (email1 != null) {
            MemberEntity me = memberSessionBean.getMemberByEmail(email1);
            if (me != null) {
                email2 = me.getMemberEmail();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid member email.", ""));
                return;
            }
        }

        selectedShow = showSessionBean.getShowById(showId);
        selectedShowSchedule = showScheduleSessionBean.getShowScheduleById(showScheduleId);
        selectedShowTicket = showTicketSessionBean.getShowTicketById(showTicketId);

        if (showTicketQuota > selectedShowTicket.getShowTicketQuota()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Not enough tickets.", ""));
        } else {

            System.out.println("selectedShow: " + selectedShow.getShowName());

            selectedShowTicketSale.setShow(selectedShow);
            selectedShowTicketSale.setShowStartDateTime(selectedShowSchedule.getStartDateTime());
            selectedShowTicketSale.setShowTicketType(selectedShowTicket.getShowTicketType());
            selectedShowTicketSale.setShowTicketQuantity(showTicketQuota);
            selectedShowTicketSale.setShowTicketPrice(selectedShowTicket.getShowTicketPrice());

            if (email2 != null) {
                selectedShowTicketSale.setMemberEmail(email2);
            }
            
            Date dt = new Date();
            double transactionAmt = selectedShowTicket.getShowTicketPrice() * showTicketQuota;
            if (couponId != null) {
                coupon = couponSessionBean.getCouponById(couponId);
                if (coupon != null && couponSessionBean.couponIsValid(coupon, dt)) {
                    couponSessionBean.useCoupon(coupon, dt, "entertainment show");
                    transactionAmt = couponSessionBean.getDiscountPrice(coupon, transactionAmt);
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid coupon code.", ""));
                    return;
                }
            }

            showTicketSessionBean.updateQuantity(showTicketId, showTicketQuota);
            showTicketSaleSessionBean.addShowTicketSale(selectedShowTicketSale);

            if (memberTransaction.getMemberEmail() != null) {

                System.err.println("Date today: " + dt);
                memberTransaction.setMtDate(dt);
                memberTransaction.setMtDepartment("entertainment show");
                memberTransaction.setMtAmount(transactionAmt);
                System.err.println("Transaction Amt: " + transactionAmt);
                memberTransaction.setMtMode(false);
                memberTransaction.setPaymentStatus(false);
                memberTransactionSessionBean.addMemberTransaction(memberTransaction);
                member = memberSessionBean.getMemberByEmail(email2);
                memberTransactionSessionBean.addCoin(member, transactionAmt);
                memberTransactionSessionBean.addPoint(member, transactionAmt);
                memberTransactionSessionBean.updateVIP(member);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful.", ""));
            FacesContext.getCurrentInstance().getExternalContext().redirect("showTicketingBuy.xhtml");

        }
    }

    public void buyTicketWithCoins() throws IOException {
        //email from page
        String email1 = memberTransaction.getMemberEmail();
        //email from database
        String email2 = null;

        //email cannot be empty
        if (email1 == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please enter member email.", ""));
            return;
        }

        //email validation
        if (email1 != null) {
            MemberEntity me = memberSessionBean.getMemberByEmail(email1);
            if (me != null) {
                email2 = me.getMemberEmail();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid member email.", ""));
                return;
            }
        }

        selectedShow = showSessionBean.getShowById(showId);
        selectedShowSchedule = showScheduleSessionBean.getShowScheduleById(showScheduleId);
        selectedShowTicket = showTicketSessionBean.getShowTicketById(showTicketId);

        if (showTicketQuota > selectedShowTicket.getShowTicketQuota()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Not enough tickets.", ""));
        } else {

            System.out.println("selectedShow: " + selectedShow.getShowName());

            selectedShowTicketSale.setShow(selectedShow);
            selectedShowTicketSale.setShowStartDateTime(selectedShowSchedule.getStartDateTime());
            selectedShowTicketSale.setShowTicketType(selectedShowTicket.getShowTicketType());
            selectedShowTicketSale.setShowTicketQuantity(showTicketQuota);
            selectedShowTicketSale.setShowTicketPrice(selectedShowTicket.getShowTicketPrice());

            if (email2 != null) {
                selectedShowTicketSale.setMemberEmail(email2);
                member = memberSessionBean.getMemberByEmail(email2);
            }

            if (member.getCoin() > selectedShowTicket.getShowTicketPrice() * showTicketQuota) {

                showTicketSessionBean.updateQuantity(showTicketId, showTicketQuota);
                member.setCoin(member.getCoin() - selectedShowTicket.getShowTicketPrice() * showTicketQuota);
                memberSessionBean.updateMember(member);
                showTicketSaleSessionBean.addShowTicketSale(selectedShowTicketSale);

                if (memberTransaction.getMemberEmail() != null) {
                    Date dt = new Date();
                    System.err.println("Date today: " + dt);
                    memberTransaction.setMtDate(dt);
                    memberTransaction.setMtDepartment("entertainment show");
                    memberTransaction.setMtAmount(selectedShowTicket.getShowTicketPrice() * showTicketQuota);
                    System.err.println("Transaction Amt: " + selectedShowTicket.getShowTicketPrice() * showTicketQuota);
                    memberTransaction.setMtMode(false);
                    memberTransaction.setPaymentStatus(false);
                    memberTransactionSessionBean.addMemberTransaction(memberTransaction);
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful.", ""));
                FacesContext.getCurrentInstance().getExternalContext().redirect("showTicketingBuy.xhtml");

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Not enough coins.", ""));
            }
        }
    }

    public void changeMode() {
        mode = true;
    }

    //Getters and Setters
    public ShowSessionBean getShowSessionBean() {
        return showSessionBean;
    }

    public void setShowSessionBean(ShowSessionBean showSessionBean) {
        this.showSessionBean = showSessionBean;
    }

    public List<ShowEntity> getShowList() {
        return showList;
    }

    public void setShowList(List<ShowEntity> showList) {
        this.showList = showList;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public ShowEntity getSelectedShow() {
        return selectedShow;
    }

    public void setSelectedShow(ShowEntity selectedShow) {
        this.selectedShow = selectedShow;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public List<ShowScheduleEntity> getShowSchedules() {
        return showSchedules;
    }

    public void setShowSchedules(List<ShowScheduleEntity> showSchedules) {
        this.showSchedules = showSchedules;
    }

    public Long getShowScheduleId() {
        return showScheduleId;
    }

    public void setShowScheduleId(Long showScheduleId) {
        this.showScheduleId = showScheduleId;
    }

    public ShowScheduleEntity getSelectedShowSchedule() {
        return selectedShowSchedule;
    }

    public void setSelectedShowSchedule(ShowScheduleEntity selectedShowSchedule) {
        this.selectedShowSchedule = selectedShowSchedule;
    }

    public ShowScheduleSessionBean getShowScheduleSessionBean() {
        return showScheduleSessionBean;
    }

    public void setShowScheduleSessionBean(ShowScheduleSessionBean showScheduleSessionBean) {
        this.showScheduleSessionBean = showScheduleSessionBean;
    }

    public List<ShowTicketEntity> getShowTickets() {
        return showTickets;
    }

    public void setShowTickets(List<ShowTicketEntity> showTickets) {
        this.showTickets = showTickets;
    }

    public Long getShowTicketId() {
        return showTicketId;
    }

    public void setShowTicketId(Long showTicketId) {
        this.showTicketId = showTicketId;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public ShowTicketEntity getSelectedShowTicket() {
        return selectedShowTicket;
    }

    public void setSelectedShowTicket(ShowTicketEntity selectedShowTicket) {
        this.selectedShowTicket = selectedShowTicket;
    }

    public ShowTicketSessionBean getShowTicketSessionBean() {
        return showTicketSessionBean;
    }

    public void setShowTicketSessionBean(ShowTicketSessionBean showTicketSessionBean) {
        this.showTicketSessionBean = showTicketSessionBean;
    }

    public int getShowTicketQuota() {
        return showTicketQuota;
    }

    public void setShowTicketQuota(int showTicketQuota) {
        this.showTicketQuota = showTicketQuota;
    }

    public ShowTicketSaleSessionBean getShowTicketSaleSessionBean() {
        return showTicketSaleSessionBean;
    }

    public void setShowTicketSaleSessionBean(ShowTicketSaleSessionBean showTicketSaleSessionBean) {
        this.showTicketSaleSessionBean = showTicketSaleSessionBean;
    }

    public ShowTicketSaleEntity getSelectedShowTicketSale() {
        return selectedShowTicketSale;
    }

    public void setSelectedShowTicketSale(ShowTicketSaleEntity selectedShowTicketSale) {
        this.selectedShowTicketSale = selectedShowTicketSale;
    }

    public boolean isIsMember() {
        return isMember;
    }

    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }

    public MemberTransactionEntity getMemberTransaction() {
        return memberTransaction;
    }

    public void setMemberTransaction(MemberTransactionEntity memberTransaction) {
        this.memberTransaction = memberTransaction;
    }

    public MemberTransactionSessionBean getMemberTransactionSessionBean() {
        return memberTransactionSessionBean;
    }

    public void setMemberTransactionSessionBean(MemberTransactionSessionBean memberTransactionSessionBean) {
        this.memberTransactionSessionBean = memberTransactionSessionBean;
    }

    public MemberSessionBean getMemberSessionBean() {
        return memberSessionBean;
    }

    public void setMemberSessionBean(MemberSessionBean memberSessionBean) {
        this.memberSessionBean = memberSessionBean;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public CouponEntity getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponEntity coupon) {
        this.coupon = coupon;
    }
}
