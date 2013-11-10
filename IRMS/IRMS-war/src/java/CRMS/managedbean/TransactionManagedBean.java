/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.MemberTransactionSessionBean;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author liuxian
 */
@ManagedBean
@ViewScoped
public class TransactionManagedBean {

    @EJB
    private MemberTransactionSessionBean transactionSessionBean;
    private MemberEntity member;
    private String searchEmail;
    private SelectItem[] option1;
    private SelectItem[] option2;
    private List<MemberTransactionEntity> transactions = new ArrayList<MemberTransactionEntity>();

    public TransactionManagedBean() {
    }

    @PostConstruct
    public void init() throws ExistException {
        transactions = transactionSessionBean.getAllTransactions();
        option1 = createOption1();
        option2 = createOption2();

    }
    public void initViewSelect(PhaseEvent event) {
        System.err.println("in initial view function");
        this.member = (MemberEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("member");
        System.err.println("select transaction with this member: " + member.getMemberName());
    }

    private SelectItem[] createOption1() {
        SelectItem[] options = new SelectItem[3];
        System.out.println("Creating boolean options");
        options[0] = new SelectItem("", "Select");
        options[1] = new SelectItem(true, "Cash/Card Payment");
        options[2] = new SelectItem(false, "Coin Payment");
        return options;
    }

    private SelectItem[] createOption2() {
        SelectItem[] options = new SelectItem[3];
        System.out.println("Creating boolean options");
        options[0] = new SelectItem("", "Select");
        options[1] = new SelectItem(true, "Cash Payment");
        options[2] = new SelectItem(false, "Credit Card Payment");
        return options;
    }

    public SelectItem[] getOption1() {
        return option1;
    }

    public void setOption1(SelectItem[] option1) {
        this.option1 = option1;
    }

    public SelectItem[] getOption2() {
        return option2;
    }

    public void setOption2(SelectItem[] option2) {
        this.option2 = option2;
    }

    public MemberTransactionSessionBean getTransactionSessionBean() {
        return transactionSessionBean;
    }

    public void setTransactionSessionBean(MemberTransactionSessionBean transactionSessionBean) {
        this.transactionSessionBean = transactionSessionBean;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public String getSearchEmail() {
        return searchEmail;
    }

    public void setSearchEmail(String searchEmail) {
        this.searchEmail = searchEmail;
    }

    public List<MemberTransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<MemberTransactionEntity> transactions) {
        this.transactions = transactions;
    }

    public void onRowToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Row State " + event.getVisibility(),
                "Model:" + ((MemberTransactionEntity) event.getData()).getMemberEmail());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
