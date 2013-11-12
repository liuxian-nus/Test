/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.managedBean;


import ATMS.entity.AttrExpressPassEntity;
import ATMS.entity.ExpressPassPurchaseEntity;
import ATMS.session.AttrExpressPassSessionBean;
import ATMS.session.AttractionSessionBean;
import ATMS.session.ExpressPassPurchaseSessionBean;
import CRMS.entity.CouponEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.CouponSessionBean;
import CRMS.session.MemberSessionBean;
import CRMS.session.MemberTransactionSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
public class expressPassPurchaseManagedBean {
    @EJB
    private CouponSessionBean couponSessionBean;
    @EJB
    private MemberTransactionSessionBean memberTransactionSessionBean;
    @EJB
    private ExpressPassPurchaseSessionBean expressPassPurchaseSessionBean;
    @EJB
    private AttrExpressPassSessionBean attrExpressPassSessionBean;
    @EJB
    private AttractionSessionBean attractionSessionBean;
    @EJB
    private MemberSessionBean memberSessionBean;
    
    
    private ExpressPassPurchaseEntity epp=new ExpressPassPurchaseEntity();
    private Long epId;
    private AttrExpressPassEntity ep;
    private int quantity;
    private double fee=0.0;
    private int restQuota;
    private String memberEmail;
    private MemberEntity member;
    private MemberTransactionEntity mt;
    private Long couponCode;
    private CouponEntity coupon;
    private boolean couponCodeValid=false;
    
    @PostConstruct
    public void init()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    /**
     * Creates a new instance of expressPassPurchaseManagedBean
     */
    public expressPassPurchaseManagedBean() {
        ep=new AttrExpressPassEntity();
        epp=new ExpressPassPurchaseEntity();
    }
    
    public void purchaseEP(ActionEvent event) throws IOException, ExistException{
        try{
            System.out.println("expressPassPurchaseManagedBean : purchaseEP");
            System.out.println("epId:"+epId);
            ep=attrExpressPassSessionBean.getEPById(epId);
            System.out.println("ep set!");
            Date today=new Date();
            System.out.println("today: "+today);
            List<Integer>qty=new ArrayList<Integer>();
            System.out.println("qty instantiated");
            qty=epp.getEpQuantities();
            System.out.println("get tp quantity");
            System.out.println("qty size: "+qty.size());
            qty.add(quantity);
            System.out.println("qty size: "+qty.size());
            epp.setEpQuantities(qty);
            System.out.println("tp qty set!");
            System.out.println("quantity: "+epp.getEpQuantities().get(0));
            System.out.println("quantity successfully added");
            List<AttrExpressPassEntity> eps=new ArrayList<AttrExpressPassEntity>();
            eps=epp.getAttrEPs();
            eps.add(ep);
            epp.setAttrEPs(eps);
            System.out.println("ep: "+epp.getAttrEPs().get(0).getAttrEPName());
            System.out.println("ep successfully added");
            System.out.println("fee: "+fee);
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
            epp.setEpFee(fee);
            System.out.println("fee set!");
            epp.setEppStatus("Purchased");
            System.out.println("purchase status set to purchased");
            expressPassPurchaseSessionBean.addEPPurchase(epp);
            System.out.println("ticket purchase successful");
            if (member != null) {
                System.out.println("member exist");
                addMemberExpressPassPurchase();
                mt = new MemberTransactionEntity();
                memberTransactionSessionBean.addPoint(member, fee);
                memberTransactionSessionBean.addCoin(member, fee);
                memberTransactionSessionBean.updateVIP(member);
                mt.setMtAmount(fee);
                mt.setMtDepartment("attraction");
                mt.setMtMode(true);
                mt.setPaymentStatus(true);
                mt.setMemberEmail(memberEmail);
                String description = member.getMemberName() + ": Your purchase of attraction ticket today with a total expense of: " + fee;
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
        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when purchase express pass", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Express pass purchased.", ""));
        epp=new ExpressPassPurchaseEntity();
    }
    
    public void purchaseExpressPassWithCoin(ActionEvent event) throws IOException {
        try {
            System.out.println("expressPassPurchaseManagedBean : purchaseEP");
            System.out.println("epId:"+epId);
            ep=attrExpressPassSessionBean.getEPById(epId);
            System.out.println("ep set!");  
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
                    qty = epp.getEpQuantities();
                    System.out.println("get tp quantity");
                    System.out.println("qty size: " + qty.size());
                    qty.add(quantity);
                    System.out.println("qty size: " + qty.size());
                    epp.setEpQuantities(qty);
                    System.out.println("tp qty set!");
                    System.out.println("quantity: " + epp.getEpQuantities().get(0));
                    System.out.println("quantity successfully added");
                    List<AttrExpressPassEntity> eps = new ArrayList<AttrExpressPassEntity>();
                    eps = epp.getAttrEPs();
                    eps.add(ep);
                    epp.setAttrEPs(eps);
                    System.out.println("ep: " + epp.getAttrEPs().get(0).getAttrEPName());
                    System.out.println("ep successfully added");
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
                    epp.setEpFee(fee);
                    System.out.println("fee set!");
                    epp.setEppStatus("Purchased");
                    System.out.println("purchase status set to purchased");
                    expressPassPurchaseSessionBean.addEPPurchase(epp);
                    System.out.println("ticket purchase successful");
                    addMemberExpressPassPurchase();
                    memberTransactionSessionBean.payByCoin(member,fee);
                    System.out.println("pay by coin successful");
                    mt = new MemberTransactionEntity();
                    mt.setMtAmount(fee);
                    mt.setMtDepartment("attraction");
                    mt.setMtMode(true);
                    mt.setPaymentStatus(true);
                    mt.setMemberEmail(memberEmail);
                    mt.setMtDate(today);
                    String description = member.getMemberName() + ": Your purchase of attraction ticket today with a total expense of: " + fee;
                    mt.setMtDescription(description);
                    mt = memberTransactionSessionBean.addMemberTransaction(mt);
                    System.out.println("member transaction added");
                    Set<MemberTransactionEntity> allMTs = member.getMemberTransactions();
                    allMTs.add(mt);
                    member.setMemberTransactions(allMTs);
                    memberSessionBean.updateMember(member);
                    System.out.println("member updated");
                }    
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when purchase ticket", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket purchased.", ""));
        epp = new ExpressPassPurchaseEntity();
        member=new MemberEntity();
        mt=new MemberTransactionEntity();
    }

    
    
    public void addMemberExpressPassPurchase() {
        System.out.println("epPurchaseManagedBean : addMemberTransaction");
        epp.setMember(member);
        expressPassPurchaseSessionBean.updateEPPurchase(epp);
        memberSessionBean.updateMemberExpressPassPurchase(member, epp);
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
                epp.setMember(member);
                System.out.println("member set!");
                return true;
            }
        }
        return false;
    }
    
    public void checkFee(ActionEvent event)throws IOException, ExistException{
        System.out.println("ticketPurchaseManagedBean : checkFee");
        try{
            System.out.println("quantity: "+quantity);
            ep=attrExpressPassSessionBean.getEPById(epId);
            fee=expressPassPurchaseSessionBean.calculateFee(ep, quantity);
            System.out.println("fee calculated: "+fee);
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when calculate fee", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "System Calculated fee.", ""));
    }
    
    public void checkMember(ActionEvent event){
        System.out.println("check member..");
        if (memberEmail != null) {
            System.out.println("email entered");
            member = memberSessionBean.getMemberByEmail(memberEmail);
            if (member == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Member email not valid", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Member exists", ""));
            }
        }
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please enter member email", ""));
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

    public ExpressPassPurchaseSessionBean getExpressPassPurchaseSessionBean() {
        return expressPassPurchaseSessionBean;
    }

    public void setExpressPassPurchaseSessionBean(ExpressPassPurchaseSessionBean expressPassPurchaseSessionBean) {
        this.expressPassPurchaseSessionBean = expressPassPurchaseSessionBean;
    }

    public AttrExpressPassSessionBean getAttrExpressPassSessionBean() {
        return attrExpressPassSessionBean;
    }

    public void setAttrExpressPassSessionBean(AttrExpressPassSessionBean attrExpressPassSessionBean) {
        this.attrExpressPassSessionBean = attrExpressPassSessionBean;
    }

    public AttractionSessionBean getAttractionSessionBean() {
        return attractionSessionBean;
    }

    public void setAttractionSessionBean(AttractionSessionBean attractionSessionBean) {
        this.attractionSessionBean = attractionSessionBean;
    }

    public MemberSessionBean getMemberSessionBean() {
        return memberSessionBean;
    }

    public void setMemberSessionBean(MemberSessionBean memberSessionBean) {
        this.memberSessionBean = memberSessionBean;
    }

    public ExpressPassPurchaseEntity getEpp() {
        return epp;
    }

    public void setEpp(ExpressPassPurchaseEntity epp) {
        this.epp = epp;
    }

    public Long getEpId() {
        return epId;
    }

    public void setEpId(Long epId) {
        this.epId = epId;
    }

    public AttrExpressPassEntity getEp() {
        return ep;
    }

    public void setEp(AttrExpressPassEntity ep) {
        this.ep = ep;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
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

    public MemberTransactionEntity getMt() {
        return mt;
    }

    public void setMt(MemberTransactionEntity mt) {
        this.mt = mt;
    }

    public MemberTransactionSessionBean getMemberTransactionSessionBean() {
        return memberTransactionSessionBean;
    }

    public void setMemberTransactionSessionBean(MemberTransactionSessionBean memberTransactionSessionBean) {
        this.memberTransactionSessionBean = memberTransactionSessionBean;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public Long getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(Long couponCode) {
        this.couponCode = couponCode;
    }

    public CouponSessionBean getCouponSessionBean() {
        return couponSessionBean;
    }

    public void setCouponSessionBean(CouponSessionBean couponSessionBean) {
        this.couponSessionBean = couponSessionBean;
    }

    public CouponEntity getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponEntity coupon) {
        this.coupon = coupon;
    }

    public boolean isCouponCodeValid() {
        return couponCodeValid;
    }

    public void setCouponCodeValid(boolean couponCodeValid) {
        this.couponCodeValid = couponCodeValid;
    }
       
}
