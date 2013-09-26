/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.session.MemberSessionBean;
import ERMS.entity.EmployeeEntity;
import Exception.ExistException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

/**
 *
 * @author Jieqiong
 */
@ManagedBean
@ViewScoped
public class SearchMemberResultManagedBean {
    @EJB
    private MemberSessionBean memberSessionBean;
    
    MemberEntity member;

    

    /**
     * Creates a new instance of SearchMemberResultManagedBean
     */
    public SearchMemberResultManagedBean() {
    }
    
    public MemberEntity getMember(){
        return member;
    }
    
    public void initView(PhaseEvent event) {
        member = (MemberEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("Member");
        System.out.println("initView: "+member.getPreferences());
    }
    
    public void saveChanges(ActionEvent event) throws ExistException{
        System.out.println("into SearchMemberResultManagedBean");
        System.out.println("member Email: "+member.getMemberEmail());
        System.out.println("member preferences: "+member.getPreferences());
        
        memberSessionBean.updateMember(getMember());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));  
    }
    
    
    public void saveMemberChanges(ActionEvent event) throws ExistException{
        System.out.println("into SearchMemberResultManagedBean");
        System.out.println("member Email: "+member.getMemberEmail());
        System.out.println("member preferences: "+member.getPreferences());
        
        memberSessionBean.updateMember(getMember());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));  
    }
            
     
    
    
}
