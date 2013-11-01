package CRMS.session;

import ATMS.entity.TicketPurchaseEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import ERMS.session.EPasswordHashSessionBean;
import Exception.ExistException;
import java.util.ArrayList;

import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
    @PersistenceContext(unitName="IRMS-ejbPU")
    private EntityManager em;
    MemberEntity member = new MemberEntity();

    //create new instance of managerBean
    public MemberSessionBean() {
    }

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
            boolean isSubscriber, String question, String answer) {

        System.out.println("into MemberSessionBean: addMember");
        /*    member = em.find(MemberEntity.class, memberEmail);
         if(member!=null) {
         System.out.println(member.getMemberEmail());
         System.out.println("You have registered already. Please log in.");
         return member;
         }
         if(!memberPassword.equals(memberPassword2)){
         System.out.println("Two passwords are not the same. Please register again.");
         return member;
         }*/

        //      System.out.println(memberEmail);
        member.setMemberEmail(memberEmail);

        //       System.out.println(memberName);
        member.setMemberName(memberName);

//        System.out.println(memberPassword);
        member.setMemberPassword(ePasswordHashSessionBean.hashPassword(memberPassword));

        //      System.out.println(memberHP);
        member.setMemberHP(memberHP);

        //       System.out.println(gender);
        member.setGender(gender);

        //      System.out.println(nationality);
        member.setNationality(nationality);

        //      System.out.println(memberDob);
        member.setMemberDob(memberDob);

        //      System.out.println(maritalStatus);
        member.setMaritalStatus(maritalStatus);

        member.setIsVIP(false);

        //       System.out.println(isSubscriber);
        member.setIsSubscriber(isSubscriber);

        member.setPoint(0);
        member.setCoin(0);
        member.setPreferences("to be set");

        System.out.println(question);
        member.setSecurityQuestion(question);

        System.out.println(answer);
        member.setAnswer(answer);
        //member.create(memberEmail,memberPassword,memberName,memberHP,gender,nationality,memberDob,maritalStatus,isSubscriber);
        em.persist(member);
        System.out.println("new member add successfully!");
        return member;
    }

    public MemberEntity getMemberByEmail(String email) {
        if(em == null)
            System.err.println("EM IS NULL");
        member = em.find(MemberEntity.class, email);
        if (member == null) {
            System.out.println("in MemberSessionBean: member doesn't exist");
        }
        //       System.out.println("member name: "+member.getMemberName());
        return member;
    }

    //cancel membership
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeMember(String memberEmail) throws ExistException {
        member = em.find(MemberEntity.class, memberEmail);
        if (member == null) {
            throw new ExistException("Member doesn't exist!");
        }
        em.remove(member);
    }

    //update member profile & password
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public MemberEntity updateMember(String memberEmail, String name, String memberHP, Date memberDob, String maritalStatus, String gender) throws ExistException {
        member = em.find(MemberEntity.class, memberEmail);
        //  if(member==null) throw new ExistException ("Member doesn't exist!");
        //     if (!(member.getMemberPassword().equals(memberPassword))) throw new ExistException("Wrong ID or password");
        System.out.println("email: " + memberEmail);
        member.setMemberName(name);
//        member.setMemberPassword(memberPassword);
        member.setMemberHP(memberHP);
        member.setGender(gender);
//        member.setNationality(nationality);
        member.setMemberDob(memberDob);
        member.setMaritalStatus(maritalStatus);
     //   member.setIsSubscriber(subscribe);
        //       member.setSecurityQuestion(securityQuestion);
        //       member.setAnswer(answer);
        em.merge(member);
        return member;
    }

    public boolean updatePassword(String memberPassword) {
        member.setMemberPassword(memberPassword);
        em.merge(member);
        return true;
    }

    public boolean updateMember(MemberEntity member) {
        em.merge(member);
        System.out.println("MemberSessionBean: member " + member.getMemberEmail() + " is successfully updated");
        return true;
    }

    //member subscribe/unsubscribe from email list
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateSubscription(String memberEmail, boolean isSubscriber) throws ExistException {
        member = em.find(MemberEntity.class, memberEmail);
        if (member == null) {
            throw new ExistException("Member doesn't exist!");
        }
        member.setIsSubscriber(isSubscriber);
        em.merge(member);
    }

    public List<MemberTransactionEntity> getAllTransactions(String memberEmail) {
//        member = em.find(MemberEntity.class, memberEmail);
        Query q = em.createQuery("SELECT mt FROM MemberTransactionEntity mt where mt.memberEmail = '" + memberEmail + "'");
        List memberTransactionList = new ArrayList<MemberTransactionEntity> ();
        for(Object o: q.getResultList()) {
            MemberTransactionEntity mt = (MemberTransactionEntity) o;
            memberTransactionList.add(mt);
        }
        return memberTransactionList;
    }

    public List<MemberEntity> getAllMembers() {
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List memberList = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity m = (MemberEntity) o;
            memberList.add(m);
        }
        return memberList;
    }
    
    public void updateMemberTicketPurchase(MemberEntity member, TicketPurchaseEntity tp){
        System.out.println("updateMemberTicketPurchase");
        List<TicketPurchaseEntity> tps=member.getTicketPurchases();
        tps.add(tp);
        member.setTicketPurchases(tps);
        em.merge(member);
    }

    @Remove
    public void remove() {
        System.out.println("MemberManagerBean: remove()");
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
}
