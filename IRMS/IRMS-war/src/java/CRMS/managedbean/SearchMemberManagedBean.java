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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class SearchMemberManagedBean {
    @EJB
    private MemberSessionBean memberSessionBean;
    
    private MemberEntity member;
    private String searchEmail;
    
    

    /**
     * Creates a new instance of SearchMemberManagedBean
     */
    public SearchMemberManagedBean() {
    }
    
    
    public List<String> complete (String query){
        List<String> results = new ArrayList<String>();  
        List<MemberEntity> members=memberSessionBean.getAllMembers();
        
        for(Object o:members){
            MemberEntity this_member=(MemberEntity)o;
            if(this_member.getMemberEmail().startsWith(query)&&this_member.isVIP()){
                results.add(this_member.getMemberEmail());
            }
        }       
        return results;     
    }
    
    public void searchByEmail() throws IOException, ExistException{
        System.out.println("search by email: "+searchEmail);
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        
        setMember(memberSessionBean.getMemberByEmail(getSearchEmail()));
        System.out.println("after search by member email:"+member.getMemberEmail());
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("Member", member);
        System.out.println("after setting parameter");
        request.getSession().setAttribute("memberEmail", getSearchEmail());
        System.out.println("after setting memberEmail session attribute");
        FacesContext.getCurrentInstance().getExternalContext().redirect("searchMemberResult.xhtml");
    }
    
    public MemberEntity getMember(){
        return member;
    }
    
    public void setMember(MemberEntity member){
        this.member=member;
    }
    
     public String getSearchEmail(){
        return searchEmail;
    }
    
    public void setSearchEmail(String email){
        searchEmail=email;
    }
    
    public MemberSessionBean getMemberSessionBean(){
        return memberSessionBean;
    }
    
    public void setMemberSessionBean(MemberSessionBean memberSessionBean){
        this.memberSessionBean=memberSessionBean;
    }
}
