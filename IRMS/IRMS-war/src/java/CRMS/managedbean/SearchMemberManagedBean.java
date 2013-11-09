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
    
    public List<MemberEntity> getAllMembers() throws ExistException, IOException {
        System.err.println("in search member managed bean");
        return memberSessionBean.getAllMembers();
    }
    
    public void searchByEmail() throws IOException, ExistException{
        System.out.println("SearchMemberManagedBean: search by email: "+searchEmail);
        
  //      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
  //      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
       member=memberSessionBean.getMemberByEmail(getSearchEmail());
 //      System.out.println("before search by member email:" + member.getMemberEmail());
       if(member==null){
           System.out.println("in SearchMemberManagedBean: member doesn't exist");
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "member doesn't exist", ""));
           return;
       }
       else if(member.isVIP()){
           System.out.println("isVIP");
           setMember(member);
           System.out.println("after search by member email:" + member.getMemberEmail());
           FacesContext.getCurrentInstance().getExternalContext().getFlash().put("Member", member);
           System.out.println("after setting parameter");
           FacesContext.getCurrentInstance().getExternalContext().redirect("searchMemberResult.xhtml");         
       }
       else{
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
    
        public void onRowToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Row State " + event.getVisibility(),
                "Model:" + ((MemberEntity) event.getData()).getMemberEmail());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
        
}
