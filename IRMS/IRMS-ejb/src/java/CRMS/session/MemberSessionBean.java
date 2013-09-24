
package CRMS.session;

import CRMS.entity.MemberEntity;
import ERMS.session.EPasswordHashSessionBean;
import Exception.ExistException;
import java.io.IOException;

import javax.ejb.Stateless;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author liuxian, Jieqiong
 */
@Stateless
public class MemberSessionBean {
    @EJB
    private EPasswordHashSessionBean ePasswordHashSessionBean;
    @PersistenceContext
    private EntityManager em ;
    
 
    MemberEntity member = new MemberEntity();

    //create new instance of managerBean
    public MemberSessionBean() { }

    //member registration
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public MemberEntity addMember(MemberEntity member) {
        //member.create(memberEmail,memberPassword,memberName,memberHP,gender,nationality,memberDob,maritalStatus,isSubscriber);
        em.persist(member);
        return member;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public MemberEntity addMember(String memberEmail, String memberName, String memberPassword, String memberPassword2,
    String memberHP, String gender, String nationality, Date memberDob, String maritalStatus, 
    boolean isSubscriber) {
        
        System.out.println("into MemberSessionBean: addMember");
        member = em.find(MemberEntity.class, memberEmail);
        if(member!=null) {
            System.out.println(member.getMemberEmail());
            System.out.println("You have registered already. Please log in.");
            return member;
        }
        if(!memberPassword.equals(memberPassword2)){
            System.out.println("Two passwords are not the same. Please register again.");
            return member;
        }
        
        System.out.println(memberEmail);
        member.setMemberEmail(memberEmail);
        
        member.setMemberName(memberName);
        member.setMemberPassword(memberPassword);
        member.setMemberHP(memberHP);
        member.setGender(gender);
        member.setNationality(nationality);
        member.setMemberDob(memberDob);
        member.setMaritalStatus(maritalStatus);
        member.setIsVIP(false);
        member.setIsSubscriber(isSubscriber);
        member.setPoint(0);
        member.setCoin(0);
        member.setPreferences(null);      
        //member.create(memberEmail,memberPassword,memberName,memberHP,gender,nationality,memberDob,maritalStatus,isSubscriber);
        em.persist(member);
        System.out.println("new member add successfully!");
        return member;
    }

    public boolean login(String memberEmail, String memberPassword){
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
    }
    
    public MemberEntity getMemberByEmail(String email){
        member = em.find(MemberEntity.class, email);
        System.out.println("member name: "+member.getMemberName());
        return member;
    }
    
    //cancel membership
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeMember(String memberEmail) throws ExistException {
        member = em.find(MemberEntity.class, memberEmail);
        if(member==null) throw new ExistException ("Member doesn't exist!");
        em.remove(member);
    }
    
    //update member profile & password
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public MemberEntity updateMember(String memberEmail,String memberPassword, String memberName,String memberHP, String gender, String nationality, Date memberDob, String maritalStatus) throws ExistException{
        member = em.find(MemberEntity.class, memberEmail);
        if(member==null) throw new ExistException ("Member doesn't exist!");
        if (!(member.getMemberPassword().equals(memberPassword))) throw new ExistException("Wrong ID or password");
        member.setMemberName(memberName);
        member.setMemberPassword(memberPassword);
        member.setMemberHP(memberHP);
        member.setGender(gender);
        member.setNationality(nationality);
        member.setMemberDob(memberDob);
        member.setMaritalStatus(maritalStatus);
        em.merge(member);
        return member;
    }
    
    public boolean updateMember(MemberEntity member)
    {
        em.merge(member);
        member=em.find(MemberEntity.class, "leijq369@gmail.com");
        System.out.println(member.getMemberPassword());
        System.out.println("MemberSessionBean: member " + member.getMemberEmail() + " is successfully updated");
        System.out.println("MemberSessionBean: member updated password:"+member.getMemberPassword());
        return true;
    }
    
    //member subscribe/unsubscribe from email list
     @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateSubscription(String memberEmail,boolean isSubscriber) throws ExistException{
        member = em.find(MemberEntity.class, memberEmail);
        if(member==null) throw new ExistException ("Member doesn't exist!");
        member.setIsSubscriber(isSubscriber);
        em.merge(member);
     }
   
    public Set<MemberEntity> getMember(){
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        Set stateSet = new HashSet<MemberEntity>();
        for (Object o: q.getResultList()) {
            MemberEntity m = (MemberEntity) o;
            stateSet.add(m);
        }
        return stateSet;
    }
    
    

    @Remove
    public void remove() {
        System.out.println("MemberManagerBean: remove()");
    }

    public void persist(Object object) {
        em.persist(object);
    }
}


