/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.session.EvaluationSessionBean;
import CRMS.session.MemberSessionBean;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
}
