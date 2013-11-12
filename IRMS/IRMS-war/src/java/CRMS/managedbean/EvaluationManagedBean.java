/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.entity.RFMModelEntity;
import CRMS.session.EvaluationSessionBean;
import CRMS.session.MemberSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class EvaluationManagedBean {

    @EJB
    MemberSessionBean memberSessionBean;
    @EJB
    EvaluationSessionBean evaluationSessionBean;
    private MemberEntity member;
    private MemberEntity selectedMember;
    private RFMModelEntity selectedRfmModel;
    private PieChartModel pieModel;
    private Double recency;
    private Double frequency;
    private Double monetary;
    private int modelNumber;
    private String memberEmail;
    private boolean calculateStatus;
    private Integer RFMValue;
    private List<MemberEntity> memberList = new ArrayList<MemberEntity>();

    /**
     * Creates a new instance of EvaluationManagedBean
     */
    public EvaluationManagedBean() {
        member = new MemberEntity();
        selectedMember = new MemberEntity();
        selectedRfmModel = new RFMModelEntity();
    }

    public List<MemberEntity> getMemberSizeOfWallet() throws ExistException {
        memberList = new ArrayList<MemberEntity>();
        List<MemberEntity> tempList = memberSessionBean.getAllMembers();
        Iterator<MemberEntity> itr = tempList.iterator();
        while (itr.hasNext()) {
            member = itr.next();
            evaluationSessionBean.calculateSizeOfWallet(member.getMemberEmail());
        }
        memberList = memberSessionBean.getAllMembers();
        return memberList;
    }

    public void memberShareOfWallet(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberS", selectedMember);
        System.err.println("1. " + selectedMember.getMemberEmail());
        FacesContext.getCurrentInstance().getExternalContext().redirect("shareOfWallet.xhtml");
    }

    public void createShareOfWallet(PhaseEvent event) {
        member = (MemberEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("memberS");
        System.err.println("2. " + member.getMemberEmail());
        pieModel = new PieChartModel();

        pieModel.set("Hotel", evaluationSessionBean.calculateShareOfWallet(member.getMemberEmail(), "hotel"));
        pieModel.set("Shopping Mall", evaluationSessionBean.calculateShareOfWallet(member.getMemberEmail(), "shopping mall"));
        pieModel.set("Entertainment Show", evaluationSessionBean.calculateShareOfWallet(member.getMemberEmail(), "entertainment show"));
        pieModel.set("Food and Beverage", evaluationSessionBean.calculateShareOfWallet(member.getMemberEmail(), "food and beverage"));
        pieModel.set("Attraction", evaluationSessionBean.calculateShareOfWallet(member.getMemberEmail(), "attraction"));
        pieModel.set("Convention Center", evaluationSessionBean.calculateShareOfWallet(member.getMemberEmail(), "convention center"));
    }

    public void addRFMParameter(ActionEvent event) throws IOException, ExistException {
        if (evaluationSessionBean.findRFMModel(selectedRfmModel.getId())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Model already exists", ""));
        } else {
            evaluationSessionBean.addRFMModel(selectedRfmModel);
            System.err.println("2. addRFMValue:" + selectedRfmModel.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New RFM Parameters saved.", ""));
            FacesContext.getCurrentInstance().getExternalContext().redirect("rfmValue.xhtml");
        }
    }

    public void saveRFMParameter(ActionEvent event) throws IOException, ExistException {
        evaluationSessionBean.setRFMParameter(recency, frequency, monetary, modelNumber);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New RFM Parameters saved.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("rfmValue.xhtml");
    }

    public void calculateRFM(ActionEvent event) throws IOException, ExistException {
        RFMValue = evaluationSessionBean.calculateRFMValue(memberEmail, modelNumber);
    }

    public MemberSessionBean getMemberSessionBean() {
        return memberSessionBean;
    }

    public void setMemberSessionBean(MemberSessionBean memberSessionBean) {
        this.memberSessionBean = memberSessionBean;
    }

    public EvaluationSessionBean getEvaluationSessionBean() {
        return evaluationSessionBean;
    }

    public void setEvaluationSessionBean(EvaluationSessionBean evaluationSessionBean) {
        this.evaluationSessionBean = evaluationSessionBean;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public MemberEntity getSelectedMember() {
        return selectedMember;
    }

    public void setSelectedMember(MemberEntity selectedMember) {
        this.selectedMember = selectedMember;
    }

    public Double getRecency() {
        return recency;
    }

    public void setRecency(Double recency) {
        this.recency = recency;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

    public Double getMonetary() {
        return monetary;
    }

    public void setMonetary(Double monetary) {
        this.monetary = monetary;
    }

    public int getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(int modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public boolean isCalculateStatus() {
        return calculateStatus;
    }

    public void setCalculateStatus(boolean calculateStatus) {
        this.calculateStatus = calculateStatus;
    }

    public Integer getRFMValue() {
        return RFMValue;
    }

    public void setRFMValue(Integer RFMValue) {
        this.RFMValue = RFMValue;
    }

    public RFMModelEntity getSelectedRfmModel() {
        return selectedRfmModel;
    }

    public void setSelectedRfmModel(RFMModelEntity selectedRfmModel) {
        this.selectedRfmModel = selectedRfmModel;
    }

    public List<MemberEntity> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberEntity> memberList) {
        this.memberList = memberList;
    }
}
