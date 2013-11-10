/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CEMS.entity.VenueFunctionEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.MemberSessionBean;
import CRMS.session.MemberTransactionSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class MemberManagedBean {

    @EJB
    private MemberSessionBean memberSessionBean;
    @EJB
    private MemberTransactionSessionBean transactionSessionBean;
    private MemberEntity member;
    private String searchEmail;
    private int birthMonth;
    private int ageYoung;
    private int ageOld;
    private String nationality;
    private String maritalStatus;
    private List<MemberEntity> memberList;
    private List<String> nationalityList;
    private List<MemberEntity> filteredMember;
    private SelectItem[] nationalityOptions;
    private SelectItem[] genderOptions;

    /**
     * Creates a new instance of SearchMemberManagedBean
     */
    public MemberManagedBean() {
    }

    @PostConstruct
    public void init() throws ExistException {
        memberList = memberSessionBean.getAllMembers();
        nationalityList = memberSessionBean.getAllNationalities();
        nationalityOptions = createNationalityOptions(nationalityList);
        genderOptions = createGenderOptions();
    }

    public List<String> complete(String query) {
        List<String> results = new ArrayList<String>();
        List<MemberEntity> members = memberSessionBean.getAllMembers();

        for (Object o : members) {
            MemberEntity this_member = (MemberEntity) o;
            if (this_member.getMemberEmail().startsWith(query) && this_member.isVIP()) {
                results.add(this_member.getMemberEmail());
            }
        }
        return results;
    }

    public List<MemberEntity> getAllMembers() throws ExistException, IOException {
        System.err.println("in search member managed bean");
        return memberSessionBean.getAllMembers();
    }

    //wrong method implementation:  javax.el.MethodNotFoundException
    public List<MemberTransactionEntity> getTransactionsByMemberEmail(ActionEvent event) throws ExistException, IOException {
        System.err.println("in getting member transactions: member managed bean");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        System.out.println("member is: " + member.getMemberEmail());
        try {
            List<MemberTransactionEntity> transactions = transactionSessionBean.getTransactionsByMemberEmail(member.getMemberEmail());
            System.out.println("we are after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("transactions", transactions);
            request.getSession().setAttribute("member", member);
            FacesContext.getCurrentInstance().getExternalContext().redirect("listMemberTransactions.xhtml");
            return transactions;
        } catch (Exception e) {
            System.out.println("error occured when getting transactions");
            return null;
        }
    }

    public void searchByEmail() throws IOException, ExistException {
        System.out.println("SearchMemberManagedBean: search by email: " + searchEmail);

        //      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        member = memberSessionBean.getMemberByEmail(getSearchEmail());
        //      System.out.println("before search by member email:" + member.getMemberEmail());
        if (member == null) {
            System.out.println("in SearchMemberManagedBean: member doesn't exist");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "member doesn't exist", ""));
            return;
        } else if (member.isVIP()) {
            System.out.println("isVIP");
            setMember(member);
            System.out.println("after search by member email:" + member.getMemberEmail());
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("Member", member);
            System.out.println("after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().redirect("searchMemberResult.xhtml");
        } else {
            System.out.println("member is not VIP yet");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "member is not VIP yet", ""));
            return;
        }

//        try{
//            setMember(memberSessionBean.getMemberByEmail(getSearchEmail()));
//            System.out.println("after search by member email:" + member.getMemberEmail());
//            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("Member", member);
//            System.out.println("after setting parameter");
//            //      request.getSession().setAttribute("memberEmail", getSearchEmail());
//       //     System.out.println("after setting memberEmail session attribute");
//            FacesContext.getCurrentInstance().getExternalContext().redirect("searchMemberResult.xhtml");
//        } catch (Exception e){
//            System.out.println("wrong email");
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "wrong Email address", ""));
//            return;
//        }
    }

    public void getMemberByBirthMonth(ActionEvent event) throws IOException, ExistException {
        System.out.println("In get member by dob month: " + birthMonth);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            memberList = memberSessionBean.getMemberByBirthMonth(birthMonth);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberList", memberList);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching by birthday month", ""));
            return;
        }
    }

    public void getMemberByNationality(ActionEvent event) throws IOException, ExistException {
        System.out.println("In get member by nationality: " + nationality);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            memberList = memberSessionBean.getMemberByNationality(nationality);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberList", memberList);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching by nationality", ""));
            return;
        }
    }

    public void getMemberByMaritalStatus(ActionEvent event) throws IOException, ExistException {
        System.out.println("In get member by maritalStatus: " + maritalStatus);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            memberList = memberSessionBean.getMemberByMaritalStatus(maritalStatus);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberList", memberList);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching by nationality", ""));
            return;
        }
    }

    public void getMemberByAgeRange(ActionEvent event) throws IOException, ExistException {
        System.out.println("In get member by age range: " + ageYoung + " " + ageOld);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            memberList = memberSessionBean.getMemberByAgeRange(ageYoung, ageOld);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberList", memberList);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching by nationality", ""));
            return;
        }
    }

    public void getMemberBySubscription(ActionEvent event) throws IOException, ExistException {
        System.out.println("In get member by subscription");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            memberList = memberSessionBean.getMemberBySubscription();
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberList", memberList);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching by nationality", ""));
            return;
        }
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

    public void setSearchEmail(String email) {
        searchEmail = email;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getAgeYoung() {
        return ageYoung;
    }

    public void setAgeYoung(int ageYoung) {
        this.ageYoung = ageYoung;
    }

    public int getAgeOld() {
        return ageOld;
    }

    public void setAgeOld(int ageOld) {
        this.ageOld = ageOld;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public List<MemberEntity> getMemberList() {
        return memberList;
    }

    public List<MemberEntity> getFilteredMember() {
        return filteredMember;
    }

    public void setFilteredMember(List<MemberEntity> filteredMember) {
        this.filteredMember = filteredMember;
    }

    public SelectItem[] getNationalityOptions() {
        return nationalityOptions;
    }

    public void setNationalityOptions(SelectItem[] nationalityOptions) {
        this.nationalityOptions = nationalityOptions;
    }

    public void setMemberList(List<MemberEntity> memberList) {
        this.memberList = memberList;
    }

    public MemberSessionBean getMemberSessionBean() {
        return memberSessionBean;
    }

    public void setMemberSessionBean(MemberSessionBean memberSessionBean) {
        this.memberSessionBean = memberSessionBean;
    }

    public List<String> getNationalityList() {
        return nationalityList;
    }

    public void setNationalityList(List<String> nationalityList) {
        this.nationalityList = nationalityList;
    }

    public SelectItem[] getGenderOptions() {
        return genderOptions;
    }

    public void setGenderOptions(SelectItem[] genderOptions) {
        this.genderOptions = genderOptions;
    }

    public MemberTransactionSessionBean getTransactionSessionBean() {
        return transactionSessionBean;
    }

    public void setTransactionSessionBean(MemberTransactionSessionBean transactionSessionBean) {
        this.transactionSessionBean = transactionSessionBean;
    }

    public void onRowToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Row State " + event.getVisibility(),
                "Model:" + ((MemberEntity) event.getData()).getMemberEmail());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private SelectItem[] createNationalityOptions(List<String> nationalityList) {
        SelectItem[] options = new SelectItem[nationalityList.size() + 1];

        System.err.println("Creating filter options");
        options[0] = new SelectItem("", "Select");
        for (int i = 0; i < nationalityList.size(); i++) {
            options[i + 1] = new SelectItem(nationalityList.get(i), nationalityList.get(i));
        }
        return options;
    }

    private SelectItem[] createGenderOptions() {
        SelectItem[] options = new SelectItem[4];
        System.out.println("Creating gender options");
        options[0] = new SelectItem("", "Select");
        options[1] = new SelectItem("Male", "Male");
        options[2] = new SelectItem("Female", "Female");
        options[3] = new SelectItem("Not Specified", "Not Specified");
        return options;

    }
}
