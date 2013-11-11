/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.session.EvaluationSessionBean;
import CRMS.session.MemberSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
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
    private PieChartModel pieModel;

    /**
     * Creates a new instance of EvaluationManagedBean
     */
    public EvaluationManagedBean() {
        member = new MemberEntity();
    }
    
    public List<MemberEntity> getMemberSizeOfWallet() throws ExistException {
        List<MemberEntity> memberList = new ArrayList<MemberEntity>();
        List<MemberEntity> tempList = memberSessionBean.getAllMembers();
        Iterator<MemberEntity> itr = tempList.iterator();
        while (itr.hasNext()) {
            member = itr.next();
            evaluationSessionBean.calculateSizeOfWallet(member.getMemberEmail());
        }
        memberList = tempList;
        return memberList;
    }

    public void memberShareOfWallet(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberS", selectedMember);
        System.err.println("1. "+selectedMember.getMemberEmail());
        FacesContext.getCurrentInstance().getExternalContext().redirect("shareOfWallet.xhtml");
    }
    
     public void createShareOfWallet(PhaseEvent event) {
        member = (MemberEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("memberS");
        System.err.println("2. "+member.getMemberEmail());
        pieModel = new PieChartModel();

        pieModel.set("Hotel", evaluationSessionBean.calculateShareOfWallet(member.getMemberEmail(),"hotel"));
        pieModel.set("Shopping Mall", evaluationSessionBean.calculateShareOfWallet(member.getMemberEmail(),"shopping mall"));
        pieModel.set("Entertainment Show", evaluationSessionBean.calculateShareOfWallet(member.getMemberEmail(),"entertainment show"));
        pieModel.set("Food and Beverage", evaluationSessionBean.calculateShareOfWallet(member.getMemberEmail(),"food and beverage"));
        pieModel.set("Attraction", evaluationSessionBean.calculateShareOfWallet(member.getMemberEmail(),"attraction"));
        pieModel.set("Convention Center", evaluationSessionBean.calculateShareOfWallet(member.getMemberEmail(),"convention center"));
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
}
