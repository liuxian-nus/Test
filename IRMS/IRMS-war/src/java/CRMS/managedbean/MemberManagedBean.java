/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.session.MemberSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
    private MemberEntity member;
    private String searchEmail;
    private int birthMonth;
    private int ageYoung;
    private int ageOld;
    private String nationality;
    private String maritalStatus;
    private List<MemberEntity> memberList;
    private List<MemberEntity> filteredMember;
    private SelectItem[] nationalityOptions;

    /**
     * Creates a new instance of SearchMemberManagedBean
     */
    public MemberManagedBean() {
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

    public void onRowToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Row State " + event.getVisibility(),
                "Model:" + ((MemberEntity) event.getData()).getMemberEmail());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
