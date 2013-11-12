/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.entity.PromotionEntity;
import CRMS.session.EvaluationSessionBean;
import CRMS.session.MemberSessionBean;
import CRMS.session.PromotionSessionBean;
import CRMS.session.VIPSessionBean;
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
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author liuxian
 */
@ManagedBean
@ViewScoped
public class promotionManagedBean {

    @EJB
    private PromotionSessionBean promotionSessionBean;
    @EJB
    private MemberSessionBean memberSessionBean;
    @EJB
    private VIPSessionBean vipSessionBean;
    @EJB
    private EvaluationSessionBean evaluationSessionBean;
    private List<PromotionEntity> promotions = new ArrayList<PromotionEntity>();
    private List<PromotionEntity> exclusivePromotions = new ArrayList<PromotionEntity>();
    private PromotionEntity newPromotion;
    private Long promotionId;
    private List<MemberEntity> participateMembers = new ArrayList<MemberEntity>();
    private MemberEntity member;
    private boolean isMemberExclusive;
    private String targetGroup;

    public promotionManagedBean() {
        newPromotion = new PromotionEntity();
    }

    @PostConstruct
    public void init() throws ExistException {
        promotions = promotionSessionBean.getAllPromotions();
        exclusivePromotions = promotionSessionBean.getMemberExclusivePromotions();
    }

    public void initViewSelect(PhaseEvent event) {
        System.err.println("in initial view function");
        this.member = (MemberEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("member");
        System.err.println("select transaction with this member: " + member.getMemberName());
    }

    public void saveNewPromotion(ActionEvent event) throws IOException, ExistException {
        System.err.println("targetGroup: " + targetGroup);
        System.err.println("Saving New promotion...");
        if(targetGroup.equalsIgnoreCase("customers")){
            setNotMemberExclusive();
        }if(targetGroup.equalsIgnoreCase("members")){
            setMemberExclusive();
        }if(targetGroup.equalsIgnoreCase("VIPs")){
            setVIPExclusive();
        }if(targetGroup.equalsIgnoreCase("RFM")){
            setRFMExclusive();
        }if(targetGroup.equalsIgnoreCase("lifeValue")){
            setLifeValueExclusive();
        }if(targetGroup.equalsIgnoreCase("sizeOW")){
            setSizeOWExclusive();
        }
        try {
            promotionSessionBean.createPromotion(newPromotion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New promotion saved.", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new promotion", ""));
            e.printStackTrace();
        }
    }

    public List<MemberEntity> getPromotionMembers(ActionEvent event) throws IOException, ExistException {
        System.err.println("looking for participating members of given promotion id");
        try {
            participateMembers = promotionSessionBean.getPromotionMembers(promotionId);
            FacesContext.getCurrentInstance().getExternalContext().redirect("listAllMembers.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new promotion", ""));
            e.printStackTrace();
        }
        return participateMembers;
    }
    
    public void onRowToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Row State " + event.getVisibility(),
                "Model:" + ((PromotionEntity) event.getData()).getPromotionTitle());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void setMemberExclusive() throws IOException, ExistException {
        try {
            this.isMemberExclusive = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion set to target to all members.", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void setNotMemberExclusive() throws IOException, ExistException {
        try {
            this.isMemberExclusive = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion set to target to all customers.", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void setVIPExclusive() throws IOException, ExistException {
        try {
            this.isMemberExclusive = true;
            newPromotion.setMcMemberTargets(vipSessionBean.getVIPs());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion is set to target to VIP only.", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs setting target customer", ""));
            return;
        }
    }
    
    public void setRFMExclusive() throws IOException, ExistException {
        try {
            this.isMemberExclusive = true;
            newPromotion.setMcMemberTargets(evaluationSessionBean.getTieredBasedOnRFM());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion is set to target to RFM only.", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs setting target customer", ""));
            return;
        }
    }
    
    public void setLifeValueExclusive() throws IOException, ExistException {
        try {
            this.isMemberExclusive = true;
            newPromotion.setMcMemberTargets(evaluationSessionBean.getTieredBasedOnCustLifeValue());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion is set to target to life value only.", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs setting target customer", ""));
            return;
        }
    }
    
     public void setSizeOWExclusive() throws IOException, ExistException {
        try {
            this.isMemberExclusive = true;
            newPromotion.setMcMemberTargets(evaluationSessionBean.getTieredBasedOnSizeOfWallet());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion is set to target to size of wallet only.", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs setting target customer", ""));
            return;
        }
    }

    public MemberSessionBean getMemberSessionBean() {
        return memberSessionBean;
    }

    public void setMemberSessionBean(MemberSessionBean memberSessionBean) {
        this.memberSessionBean = memberSessionBean;
    }

    public VIPSessionBean getVipSessionBean() {
        return vipSessionBean;
    }

    public void setVipSessionBean(VIPSessionBean vipSessionBean) {
        this.vipSessionBean = vipSessionBean;
    }

    public boolean isIsMemberExclusive() {
        return isMemberExclusive;
    }

    public void setIsMemberExclusive(boolean isMemberExclusive) {
        this.isMemberExclusive = isMemberExclusive;
    }

    public String getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(String targetGroup) {
        this.targetGroup = targetGroup;
    }
    
        public PromotionSessionBean getPromotionSessionBean() {
        return promotionSessionBean;
    }

    public void setPromotionSessionBean(PromotionSessionBean promotionSessionBean) {
        this.promotionSessionBean = promotionSessionBean;
    }

    public List<PromotionEntity> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<PromotionEntity> promotions) {
        this.promotions = promotions;
    }

    public PromotionEntity getNewPromotion() {
        return newPromotion;
    }

    public void setNewPromotion(PromotionEntity newPromotion) {
        this.newPromotion = newPromotion;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public List<MemberEntity> getParticipateMembers() {
        return participateMembers;
    }

    public void setParticipateMembers(List<MemberEntity> participateMembers) {
        this.participateMembers = participateMembers;
    }

    public List<PromotionEntity> getExclusivePromotions() {
        return exclusivePromotions;
    }

    public void setExclusivePromotions(List<PromotionEntity> exclusivePromotions) {
        this.exclusivePromotions = exclusivePromotions;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }
    
    public boolean isMemberExclusive() {
        return isMemberExclusive;
    }
}
