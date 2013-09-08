/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS;

import exception.ExistException;

import javax.ejb.Stateless;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Remove;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author liuxian
 */
@Stateless
public class MemberManagerBean {
 @PersistenceContext
    private EntityManager em ;
 
    MemberEntity member = new MemberEntity();

    //create new instance of managerBean
    public MemberManagerBean() { }

    //member registration
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addMember(String memberEmail,String memberPassword, String memberName,String memberHP, String gender, String nationality, Date memberDob, boolean maritalStatus, boolean isSubscriber) {
        member.create(memberEmail,memberPassword,memberName,memberHP,gender,nationality,memberDob,maritalStatus,isSubscriber);
        em.persist(member);
    }

    public boolean login(String memberEmail, String memberPassword){
        member = em.find(MemberEntity.class, memberEmail);
        System.out.println("logging in....");
        if (member==null) System.out.println("MemberEmail is wrong!");
        else
            System.out.println("password is "+member.getMemberPassword());
        if (member == null || !(member.getMemberPassword().equals(memberPassword)))
            return false;
        else return true;
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
    public void updateMember(String memberEmail,String memberPassword, String memberName,String memberHP, String gender, String nationality, Date memberDob, boolean maritalStatus) throws ExistException{
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
}


