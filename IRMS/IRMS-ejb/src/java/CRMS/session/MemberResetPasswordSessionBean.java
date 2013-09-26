/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MemberEntity;
import ERMS.session.EPasswordHashSessionBean;
import ERMS.session.EmailSessionBean;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jieqiong
 */
@Stateless
@LocalBean
public class MemberResetPasswordSessionBean {
    @EJB
    private EPasswordHashSessionBean ePasswordHashSessionBean;
    @EJB
    private EmailSessionBean emailSessionBean;
    
    @EJB
    private MemberSessionBean memberSessionBean;
    
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    
    
    MemberEntity member=new MemberEntity();
    
    

    public MemberResetPasswordSessionBean(){}
    
    public void ResetPassword(String email){
        member = em.find(MemberEntity.class, email);
        
        String initialPwd = "";
        String uuid = UUID.randomUUID().toString();
        String[] sArray = uuid.split("-");
        initialPwd = sArray[0];
        System.out.println(initialPwd);
        member.setMemberPassword(initialPwd);
        System.out.println("update member new hash password!");
        member.setMemberPassword(ePasswordHashSessionBean.hashPassword(member.getMemberPassword()));
        System.out.println("finished hashing");
        System.out.println("hashed password: "+member.getMemberPassword());
        
        try {
            System.out.println("before merge");
            memberSessionBean.updateMember(member);
            System.out.println("after merge");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when updating member", ""));
            return;
        }
        
        System.out.println("member updated!!!!");
        
        emailSessionBean.emailInitialPassward(member.getMemberEmail(), initialPwd);
        System.out.println("email already sent");
        member = new MemberEntity();
    }
    
    
    

    public void persist(Object object) {
        em.persist(object);
    }
    
    

}
