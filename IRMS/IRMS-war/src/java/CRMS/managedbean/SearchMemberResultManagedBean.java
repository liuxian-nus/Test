/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.session.MemberSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jieqiong
 */
@ManagedBean
@ViewScoped
public class SearchMemberResultManagedBean implements Serializable{
    @EJB
    private MemberSessionBean memberSessionBean;
    
    MemberEntity member;
    String email;

    

    /**
     * Creates a new instance of SearchMemberResultManagedBean
     */
    public SearchMemberResultManagedBean() {
    }
    
    public MemberEntity getMember(){
        return member;
    }
    
    public void setMember(MemberEntity member){
        this.member=member;
    }
    
    public String getMemberEmail(){
        return email;
    }
    
    public void setMemberEmail(String email){
        this.email=email;
    }
    
    public void initView(PhaseEvent event) {
        System.out.println("***into initView***");
        member = (MemberEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("Member");
        System.out.println("initView: "+member.getPreferences());
    }
    
    public void saveChanges(ActionEvent event) throws ExistException{
        System.out.println("into SearchMemberResultManagedBean: saveChanges");
        
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        email = (String) request.getSession().getAttribute("memberEmail");
//        System.out.println("after getting email:" +email);
//        memberSessionBean.getMemberByEmail(email);
        
        System.out.println("member Email: "+member.getMemberEmail());
        System.out.println("member preferences: "+member.getPreferences());
        
       
 
        
        memberSessionBean.updateMember(getMember());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));  
    }
    
    /*public void saveChangesTry(ActionEvent event) throws IOException {

        System.out.println("saveChanges");

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int roomId = (Integer) request.getSession().getAttribute("roomId");
        String preferences=(String)request.getSession().getAttribute("preferences");
        System.out.println("preferences: "+preferences);
 
    }*/
    
    
    
    
   
            
     
    
    
}
