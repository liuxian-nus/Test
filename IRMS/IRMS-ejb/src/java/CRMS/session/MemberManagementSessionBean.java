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
public class MemberManagementSessionBean {
    
    @EJB
    private EPasswordHashSessionBean ePasswordHashSessionBean;
    @EJB
    private EmailSessionBean emailSessionBean;
    
    @EJB
    private MemberSessionBean memberSessionBean;
    
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    
    
    MemberEntity member=new MemberEntity();
    
    

    public MemberManagementSessionBean(){}
    
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
    
 /*   public boolean login(String memberEmail, String memberPassword){
        System.out.println("memberEmail: "+memberEmail);
        member = em.find(MemberEntity.class, memberEmail);
        System.out.println(member.getMemberEmail());
        System.out.println("logging in....");
        if (member==null) System.out.println("MemberEmail is wrong!");
        else
            System.out.println("password is "+member.getMemberPassword());
        if (member == null || !(member.getMemberPassword().equals(memberPassword)))
            return false;
        else return true;
    }*/
    
    public boolean login(String memberEmail, String memberPassword){
        System.out.println("MemberManagementSessionBean, login function");
        System.out.println("memberEmail: "+memberEmail);
        member = em.find(MemberEntity.class, memberEmail);
        if(member==null) return false;
        System.out.println("member email stored: "+member.getMemberEmail());
        
        System.out.println("logging in....");
        System.out.println("key in password: "+memberPassword);
        System.out.println("stored password: "+member.getMemberPassword());
        System.out.println("key in password hash: "+ePasswordHashSessionBean.hashPassword(memberPassword));
        
        if(member!=null&&member.getMemberPassword().equals(ePasswordHashSessionBean.hashPassword(memberPassword)))
            return true;
        else return false;
    }
    
    public boolean checkAnswer(String email, String answer){
        member=em.find(MemberEntity.class, email);
        String correctAnswer=member.getAnswer();
        System.out.println(correctAnswer);
        if(correctAnswer.equals(answer)){
            System.out.println("true");
            return true;
        }
        else{
            System.out.println(answer);
            return false;
        }
    }
    
    public boolean checkPassword(String email, String pwd){
        System.out.println("MemberManagementSessionBean, checkPassword");
        System.out.println("passed in email:"+email);
        member=em.find(MemberEntity.class, email);
        String password=member.getMemberPassword();
        if(ePasswordHashSessionBean.hashPassword(pwd).equals(member.getMemberPassword())){
            System.out.println("pwd:"+pwd);
            return true;
        }
        else return false;
    }
    
    public void resetPasswordWithNewPassword(String email, String newPwd){
         member=em.find(MemberEntity.class, email);
         member.setMemberPassword(ePasswordHashSessionBean.hashPassword(newPwd));
         System.out.println("hashed password: "+member.getMemberPassword());
         
         try {
            System.out.println("before merge");
            memberSessionBean.updateMember(member);
            System.out.println("after merge");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when updating member password", ""));
            return;
        }
         member = new MemberEntity();       
    }
    
    
    

    public void persist(Object object) {
        em.persist(object);
    }
    
    

}
