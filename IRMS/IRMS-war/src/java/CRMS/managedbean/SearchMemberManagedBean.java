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
    
    public String getSearchEmail(){
        return searchEmail;
    }
    
    public void setSearchEmail(String email){
        searchEmail=email;
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
        setMember(memberSessionBean.getMemberByEmail(searchEmail));
        System.out.println("member email:"+member.getMemberEmail());
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("Member", member);
        FacesContext.getCurrentInstance().getExternalContext().redirect("searchMemberResult.xhtml");
    }
    
    public void setMember(MemberEntity member){
        this.member=member;
    }
}
