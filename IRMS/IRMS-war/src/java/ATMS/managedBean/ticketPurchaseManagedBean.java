/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.managedBean;

import ATMS.entity.AttrTicketEntity;
import ATMS.entity.AttractionEntity;
import ATMS.entity.QuotaEntity;
import ATMS.entity.TicketPurchaseEntity;
import ATMS.session.AttractionSessionBean;
import ATMS.session.TicketPurchaseSessionBean;
import ATMS.session.TicketSessionBean;
import CRMS.entity.CouponEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.CouponSessionBean;
import CRMS.session.GenerateBarcodeSessionBean;
import CRMS.session.MemberSessionBean;
import CRMS.session.MemberTransactionSessionBean;
import ERMS.session.EmailSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Jieqiong
 */
@ManagedBean
@ViewScoped
public class ticketPurchaseManagedBean {
    @EJB
    private GenerateBarcodeSessionBean generateBarcodeSessionBean;
    @EJB
    private EmailSessionBean emailSessionBean;
    @EJB
    private CouponSessionBean couponSessionBean;
    @EJB
    private MemberTransactionSessionBean memberTransactionSessionBean;
    @EJB
    private MemberSessionBean memberSessionBean;
    @EJB
    private AttractionSessionBean attractionSessionBean;
    @EJB
    private TicketSessionBean ticketSessionBean;
    @EJB
    private TicketPurchaseSessionBean ticketPurchaseSessionBean;
    
    
    
    private TicketPurchaseEntity tp = new TicketPurchaseEntity();
    private Long attrTicketId;
    private AttrTicketEntity attrTicket;
    private int quantity;
    private double fee = 0.0;
    private int restQuota;
    private String memberEmail;
    private MemberTransactionEntity mt;
    private MemberEntity member;
    private String tpIdString;
    private String message="Please enter ticket purchase ID";
    private Long couponCode;
    private CouponEntity coupon;
    private boolean couponCodeValid=false;

    @PostConstruct
    public void init() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    /**
     * Creates a new instance of ticketPurchaseManagedBean
     */
    public ticketPurchaseManagedBean() {
        attrTicket = new AttrTicketEntity();
        tp = new TicketPurchaseEntity();
    }

    public void purchaseTicket(ActionEvent event) throws IOException {
        try {
            System.out.println("ticketPurchaseManagedBean : purchaseTicket");
            System.out.println("attrTicketId:" + attrTicketId);
            attrTicket = ticketSessionBean.getTicketById(attrTicketId);
            System.out.println("attrTicket set!");
            if (!ticketAvailable(attrTicket, quantity)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No enough ticket", ""));
                return;
            }
            Date today=new Date();
            System.out.println("today: "+today);
            System.out.println("ticket available");
            List<Integer> qty = new ArrayList<Integer>();
            System.out.println("qty instantiated");
            qty = tp.getAttrTicketQuantities();
            System.out.println("get tp quantity");
            System.out.println("qty size: " + qty.size());
            qty.add(quantity);
            System.out.println("qty size: " + qty.size());
            tp.setAttrTicketQuantities(qty);
            System.out.println("tp qty set!");
            System.out.println("quantity: " + tp.getAttrTicketQuantities().get(0));
            System.out.println("quantity successfully added");
            List<AttrTicketEntity> tickets = tp.getAttrTickets();
            tickets.add(attrTicket);
            tp.setAttrTickets(tickets);
            System.out.println("ticket: " + tp.getAttrTickets().get(0).getAttrTicketName());
            System.out.println("ticket successfully added");
            System.out.println("fee: " + fee);
            System.out.println("start to work on coupon");
            if (coupon != null) {
                System.out.println("coupon code entered");
                if (couponCodeValid) {
                    System.out.println("valid coupon code");
                    System.out.println("coupon: " + coupon.getStatus());
                    fee *= coupon.getCouponType().getDiscount();
                    System.out.println("fee after using coupon: " + fee);
                    coupon.setStatus("Used");
                    coupon.setDepartment("attraction");
                    coupon.setCouponUsedDate(today);
                    couponSessionBean.updateCoupon(coupon);
                    System.out.println("coupon updated");
                }
            }
            tp.setAttrTicketFee(fee);
            System.out.println("fee set!");
            tp.setAttrTPStatus("Purchased");
            System.out.println("purchase status set to purchased");
            ticketPurchaseSessionBean.addTicketPurchase(tp);
            System.out.println("ticket purchase successful");
            decreaseRestQuota(attrTicket, quantity);
            System.out.println("available quota decreased");
            //member=memberSessionBean.getMemberByEmail(memberEmail);
            if(member!=null){
                System.out.println("member: " + member.getMemberName());
                System.out.println("get member");
                if (member != null) {
                    System.out.println("member exist");
                    addMemberTicketPurchase();
                    mt = new MemberTransactionEntity();
                    memberTransactionSessionBean.addPoint(member, fee);
                    memberTransactionSessionBean.addCoin(member, fee);
                    memberTransactionSessionBean.updateVIP(member);
                    mt.setMtAmount(fee);
                    mt.setMtDepartment("attraction");
                    mt.setMtMode(true);
                    mt.setPaymentStatus(true);
                    mt.setMemberEmail(memberEmail);
                    String description = member.getMemberName() + ": Your purchase of attraction ticket at this date with a total expense of: " + fee;
                    mt.setMtDescription(description);
                    mt.setMtDate(today);
                    mt = memberTransactionSessionBean.addMemberTransaction(mt);
                    System.out.println("member transaction added");
                    Set<MemberTransactionEntity> allMTs = member.getMemberTransactions();
                    allMTs.add(mt);
                    member.setMemberTransactions(allMTs);
                    memberSessionBean.updateMember(member);
                    System.out.println("member updated");
                }
            }
            else{
                    System.out.println("member email is not entered");
                }
            
            generateBarcodeSessionBean.generate(String.valueOf(tp.getTpId()));
            String inputfile=emailSessionBean.createTicket(tp);
            System.out.println("ticket purchase success!");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when purchase ticket", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket purchased.", ""));
        tp = new TicketPurchaseEntity();
    }
    
        public void purchaseTicketWithCoin(ActionEvent event) throws IOException {
        try {
            System.out.println("ticketPurchaseManagedBean : purchaseTicketWithCoin");
            System.out.println("attrTicketId:" + attrTicketId);
            attrTicket = ticketSessionBean.getTicketById(attrTicketId);
            System.out.println("attrTicket set!");
            if (!ticketAvailable(attrTicket, quantity)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No enough ticket", ""));
                return;
            }
            System.out.println("ticket available");  
            if(member==null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You must be a member to pay by coins", ""));
                return;
            }
            else{
                Boolean enoughCoin=memberTransactionSessionBean.checkCoinAmount(member,fee);
                if(!enoughCoin){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Coin is not enough to make this purchase", ""));
                    return;
                }
                else{
                    Date today=new Date();
                    System.out.println("today: "+today);
                    List<Integer> qty = new ArrayList<Integer>();
                    System.out.println("qty instantiated");
                    qty = tp.getAttrTicketQuantities();
                    System.out.println("get tp quantity");
                    System.out.println("qty size: " + qty.size());
                    qty.add(quantity);
                    System.out.println("qty size: " + qty.size());
                    tp.setAttrTicketQuantities(qty);
                    System.out.println("tp qty set!");
                    System.out.println("quantity: " + tp.getAttrTicketQuantities().get(0));
                    System.out.println("quantity successfully added");
                    List<AttrTicketEntity> tickets = tp.getAttrTickets();
                    tickets.add(attrTicket);
                    tp.setAttrTickets(tickets);
                    System.out.println("ticket: " + tp.getAttrTickets().get(0).getAttrTicketName());
                    System.out.println("ticket successfully added");
                    System.out.println("fee: " + fee);
                    System.out.println("start to work on coupon");
                    if (coupon != null) {
                        System.out.println("coupon code entered");
                        if (couponCodeValid) {
                            System.out.println("valid coupon code");
                            System.out.println("coupon: " + coupon.getStatus());
                            fee *= coupon.getCouponType().getDiscount();
                            System.out.println("fee after using coupon: " + fee);
                            coupon.setStatus("Used");
                            coupon.setDepartment("attraction");
                            coupon.setCouponUsedDate(today);
                            couponSessionBean.updateCoupon(coupon);
                            System.out.println("coupon updated");
                        }
                    }
                    tp.setAttrTicketFee(fee);
                    System.out.println("fee set!");
                    tp.setAttrTPStatus("Purchased");
                    System.out.println("purchase status set to purchased");
                    ticketPurchaseSessionBean.addTicketPurchase(tp);
                    System.out.println("ticket purchase successful");
                    decreaseRestQuota(attrTicket, quantity);
                    System.out.println("available quota decreased");
                    addMemberTicketPurchase();
                    memberTransactionSessionBean.payByCoin(member,fee);
                    System.out.println("pay by coin successful");
                    mt = new MemberTransactionEntity();
                    mt.setMtAmount(fee);
                    mt.setMtDepartment("attraction");
                    mt.setMtMode(true);
                    mt.setPaymentStatus(true);
                    mt.setMemberEmail(memberEmail);
                    String description = member.getMemberName() + ": Your purchase of attraction ticket at this date with a total expense of: " + fee;
                    mt.setMtDescription(description);
                    mt.setMtDate(today);
                    mt = memberTransactionSessionBean.addMemberTransaction(mt);
                    System.out.println("member transaction added");
                    Set<MemberTransactionEntity> allMTs = member.getMemberTransactions();
                    allMTs.add(mt);
                    member.setMemberTransactions(allMTs);
                    memberSessionBean.updateMember(member);
                    System.out.println("member updated");
                }    
            }
            generateBarcodeSessionBean.generate(String.valueOf(tp.getTpId()));
            String inputfile=emailSessionBean.createTicket(tp);
            System.out.println("ticket purchase success!");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when purchase ticket", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket purchased.", ""));
        tp = new TicketPurchaseEntity();
        member=new MemberEntity();
        mt=new MemberTransactionEntity();
    }

    public boolean ticketAvailable(AttrTicketEntity ticket, int quantity) {
        AttractionEntity attr = ticket.getAttr();
        int this_restQuota = attr.getAttrQuota().getRestQuota();
        if (this_restQuota >= quantity) {
            System.out.println("enough ticket");
            return true;
        } else {
            System.out.println("ticket sold out");
            return false;
        }
    }

    public void addMemberTicketPurchase() {
        System.out.println("ticketPurchaseManagedBean : addMemberTransaction");
        tp.setMember(member);
        ticketPurchaseSessionBean.updateTicketPurchase(tp);
        memberSessionBean.updateMemberTicketPurchase(member, tp);
        System.out.println("ticket purchase and member cross set!");
    }

    public boolean addMember() {
        System.out.println("addMember...");
        if (memberEmail != null) {
            System.out.println("email entered");
            MemberEntity member = memberSessionBean.getMemberByEmail(memberEmail);
            if (member == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Member email not valid", ""));
                return false;
            } else {
                System.out.println("valid email");
                tp.setMember(member);
                ticketPurchaseSessionBean.updateTicketPurchase(tp);
                memberSessionBean.updateMemberTicketPurchase(member, tp);
                System.out.println("ticket purchase and member cross set!");
                return true;
            }
        }
        return false;
    }

    public void addMemberTransaction() {
        System.out.println("ticketPurchaseManagedBean: addMemberTransaction");
        MemberEntity member = memberSessionBean.getMemberByEmail(memberEmail);
        Date date = null;
        /*   mt=new MemberTransactionEntity();
        
         mt.setMemberEmail(memberEmail);
         mt.setMtAmount(fee);
         mt.setMtDepartment("Attraction");
         mt.setMtMode(false); //assume use cash now
         mt.setPaymentStatus(true);
         System.out.println("mt set!");

         memberTransactionSessionBean.addMemberTransaction(memberEmail, mt); //assume pay by cash
         System.out.println("transaction add successful!");*/
        String description = member.getMemberName() + ": Your purchase of attraction ticket at " + date + " with a total expense of: " + fee;
        memberTransactionSessionBean.addMemberTransaction(member, fee, date, "Attraction", "", description, false);
        System.out.println("transaction add successful!");
    }

    public void decreaseRestQuota(AttrTicketEntity ticket, int quantity) {
        AttractionEntity attr = ticket.getAttr();
        QuotaEntity quota = attr.getAttrQuota();
        int this_restQuota = quota.getRestQuota();
        this_restQuota -= quantity;
        quota.setRestQuota(this_restQuota);
        attr.setAttrQuota(quota);
        attractionSessionBean.updateAttractionAfterDecreaseQuota(attr);
        System.out.println("quota updated");

    }

    public void checkFee(ActionEvent event) throws IOException, ExistException {
        System.out.println("ticketPurchaseManagedBean : checkFee");
        try {
            System.out.println("quantity: " + quantity);
            attrTicket = ticketSessionBean.getTicketById(attrTicketId);
            fee = ticketPurchaseSessionBean.calculateFee(attrTicket, quantity);
            System.out.println("fee calculated: " + fee);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when calculate fee", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "System Calculated fee.", ""));
    }

    public void checkQuota(ActionEvent event) throws IOException, ExistException {
        System.out.println("ticketPurchaseManagedBean : checkQuota");
        try {
            System.out.println("quantity: " + quantity);
            attrTicket = ticketSessionBean.getTicketById(attrTicketId);
            AttractionEntity attr = attrTicket.getAttr();
            QuotaEntity quota = attr.getAttrQuota();
            restQuota = quota.getRestQuota();
            System.out.println("rest quota: " + restQuota);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when retrieving available quota", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Available quota.", ""));
    }

    public void checkMember(ActionEvent event) {
        System.out.println("check member..");
        if (memberEmail != null) {
            System.out.println("email entered");
            member = memberSessionBean.getMemberByEmail(memberEmail);
            if (member == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Member email not valid", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Member exists", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please enter member email", ""));
        }
    }
    
    public void checkTicket(ActionEvent event){
        System.out.println("check ticket..");
        boolean isValidTicket=ticketPurchaseSessionBean.checkTicketValidity(tpIdString);
        if(isValidTicket){
            message="Ticket is valid";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket is valid", ""));
        }
        else{
            message="Ticket is not valid";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket is not valid", ""));
        }
    }
    
    public void checkCouponCode(ActionEvent event) throws ExistException{
        System.out.println("check coupon code..");
        if(couponCode!=null){
            System.out.println("couponCode entered");
            coupon=couponSessionBean.getCouponById(couponCode);
            if(coupon==null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Coupon code is not valid", ""));
            }
            else{
                String couponStatus=coupon.getStatus();
                if(couponStatus.equals("New")){
                    couponCodeValid=true;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Coupon code is good to use", ""));
                }
                else if(couponStatus.equals("Used")){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Coupon code has been used", ""));
                }
                else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Coupon code has expired", ""));
                }
            }
            
        }
        else{
            System.out.println("coupon code not entered");
            coupon=null;
        }
    }

    public void oneMore(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("adventureWorldTicketPurchase.xhtml");
    }

    public TicketPurchaseSessionBean getTicketPurchaseSessionBean() {
        return ticketPurchaseSessionBean;
    }

    public void setTicketPurchaseSessionBean(TicketPurchaseSessionBean ticketPurchaseSessionBean) {
        this.ticketPurchaseSessionBean = ticketPurchaseSessionBean;
    }

    public TicketSessionBean getTicketSessionBean() {
        return ticketSessionBean;
    }

    public void setTicketSessionBean(TicketSessionBean ticketSessionBean) {
        this.ticketSessionBean = ticketSessionBean;
    }

    public TicketPurchaseEntity getTp() {
        return tp;
    }

    public void setTp(TicketPurchaseEntity tp) {
        this.tp = tp;
    }

    public Long getAttrTicketId() {
        return attrTicketId;
    }

    public void setAttrTicketId(Long attrTicketId) {
        this.attrTicketId = attrTicketId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public AttrTicketEntity getAttrTicket() {
        return attrTicket;
    }

    public void setAttrTicket(AttrTicketEntity attrTicket) {
        this.attrTicket = attrTicket;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public AttractionSessionBean getAttractionSessionBean() {
        return attractionSessionBean;
    }

    public void setAttractionSessionBean(AttractionSessionBean attractionSessionBean) {
        this.attractionSessionBean = attractionSessionBean;
    }

    public int getRestQuota() {
        return restQuota;
    }

    public void setRestQuota(int restQuota) {
        this.restQuota = restQuota;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
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

    public MemberTransactionEntity getMt() {
        return mt;
    }

    public void setMt(MemberTransactionEntity mt) {
        this.mt = mt;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public String getTpIdString() {
        return tpIdString;
    }

    public void setTpIdString(String tpIdString) {
        this.tpIdString = tpIdString;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CouponSessionBean getCouponSessionBean() {
        return couponSessionBean;
    }

    public void setCouponSessionBean(CouponSessionBean couponSessionBean) {
        this.couponSessionBean = couponSessionBean;
    }

    public Long getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(Long couponCode) {
        this.couponCode = couponCode;
    }
      

}
