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
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.MemberSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private MemberTransactionEntity mt;
    
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
            epp.setEpFee(fee);
            System.out.println("fee set!");
            boolean isMember=addMember();
            epp.setEppStatus("Purchased");
            System.out.println("purchase status set to purchased");
            expressPassPurchaseSessionBean.addEPPurchase(epp);
            System.out.println("ticket purchase successful");
        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when purchase express pass", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Express pass purchased.", ""));
        epp=new ExpressPassPurchaseEntity();
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
            MemberEntity member = memberSessionBean.getMemberByEmail(memberEmail);
            if (member == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Member email not valid", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Member exists", ""));
            }
        }
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please enter member email", ""));
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

    
    
}
