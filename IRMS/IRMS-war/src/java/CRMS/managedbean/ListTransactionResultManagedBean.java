/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.MemberTransactionSessionBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@RequestScoped
public class ListTransactionResultManagedBean {

    @EJB
    MemberTransactionSessionBean memberTransactionSessionBean;
    private MemberEntity member;
    private MemberTransactionEntity memberTransaction;
    private List<MemberTransactionEntity> memberTransactions;
    private List<MemberTransactionEntity> tempMemberTransactions;
    
    /**
     * Creates a new instance of ListTransactionResultManagedBean
     */
    public ListTransactionResultManagedBean() {
        member = new MemberEntity();
        memberTransaction = new MemberTransactionEntity();
    }
    
    public void init(PhaseEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        member = new MemberEntity();
        System.err.println("Getting Member..." );
        member = (MemberEntity) request.getSession().getAttribute("memberE");
//        member = (MemberEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("memberT");
        System.err.println("Get Member: " +member.getMemberEmail());
        doThis();
    }

    public void doThis() {
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        memberTransactions = new ArrayList<MemberTransactionEntity>();
        tempMemberTransactions = new ArrayList<MemberTransactionEntity>();
        tempMemberTransactions = memberTransactionSessionBean.getTransactionsByMemberEmail(member.getMemberEmail());
        Iterator<MemberTransactionEntity> itr = tempMemberTransactions.iterator();
        while (itr.hasNext()) {
            memberTransaction = itr.next();
            memberTransactions.add(memberTransaction);
        }
//        request.getSession().setAttribute("memberE",member);
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

    public MemberTransactionEntity getMemberTransaction() {
        return memberTransaction;
    }

    public void setMemberTransaction(MemberTransactionEntity memberTransaction) {
        this.memberTransaction = memberTransaction;
    }

    public List<MemberTransactionEntity> getMemberTransactions() {
        return memberTransactions;
    }

    public void setMemberTransactions(List<MemberTransactionEntity> memberTransactions) {
        this.memberTransactions = memberTransactions;
    }

    public List<MemberTransactionEntity> getTempMemberTransactions() {
        return tempMemberTransactions;
    }

    public void setTempMemberTransactions(List<MemberTransactionEntity> tempMemberTransactions) {
        this.tempMemberTransactions = tempMemberTransactions;
    }
}
