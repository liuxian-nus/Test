package CRMS.session;

import ATMS.entity.ExpressPassPurchaseEntity;
import ATMS.entity.TicketPurchaseEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import ERMS.session.EPasswordHashSessionBean;
import Exception.ExistException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ejb.Stateless;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
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
@LocalBean
public class MemberSessionBean implements MemberSessionBeanRemote {
    @EJB
    private CPasswordHashSessionBean cPasswordHashSessionBean;

    @EJB
    private EPasswordHashSessionBean ePasswordHashSessionBean;
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    MemberEntity member = new MemberEntity();

    //create new instance of managerBean
    public MemberSessionBean() {
    }

    //member registration: used for jsf managed bean
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public MemberEntity addMember(MemberEntity member) {
        //member.create(memberEmail,memberPassword,memberName,memberHP,gender,nationality,memberDob,maritalStatus,isSubscriber);
        em.persist(member);
        return member;
    }
    //member registration: used for jsp servlet

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public MemberEntity addMember(String memberEmail, String memberName, String memberPassword, String memberPassword2,
            String memberHP, String gender, String nationality, Date memberDob, String maritalStatus,
            boolean isSubscriber, String question, String answer) {

        System.out.println("into MemberSessionBean: addMember");

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
        em.persist(member);
        System.out.println("new member add successfully!");
        return member;
    }

    @Override
    public MemberEntity getMemberByEmail(String email) {
        if (em == null) {
            System.err.println("EM IS NULL");
        }
        member = em.find(MemberEntity.class, email);
        if (member == null) {
            System.out.println("in MemberSessionBean: member doesn't exist");
            return null;
        }
        //       System.out.println("member name: "+member.getMemberName());
        return member;
    }

    @Override
    public List<MemberEntity> getMemberByBirthMonth(int month) {
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List memberList = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity thisMember = (MemberEntity) o;
            if (thisMember.getMemberDob().getMonth() == month) {
                memberList.add(thisMember);
            }
        }
        return memberList;
    }

    @Override
    public List<MemberEntity> getMemberByNationality(String nationality) {
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List memberList = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity thisMember = (MemberEntity) o;
            if (thisMember.getNationality().toLowerCase().equals(nationality.toLowerCase())) {
                memberList.add(thisMember);
            }
        }
        return memberList;
    }

    @Override
    public List<MemberEntity> getMemberByGender(String gender) {
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List memberList = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity thisMember = (MemberEntity) o;
            if (thisMember.getGender().toLowerCase().equals(gender.toLowerCase())) {
                memberList.add(thisMember);
            }
        }
        return memberList;
    }

    @Override
    public List<MemberEntity> getMemberByMaritalStatus(String maritalStatus) {
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List memberList = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity thisMember = (MemberEntity) o;
            if (thisMember.getMaritalStatus().toLowerCase().equals(maritalStatus.toLowerCase())) {
                System.out.println("get by marital status --> one member found: " + thisMember.getMemberName());
                memberList.add(thisMember);
            }
        }
        return memberList;
    }

    @Override
    public List<MemberEntity> getMemberBySubscription() {
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List memberList = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity thisMember = (MemberEntity) o;
            if (thisMember.isSubscriber() == true) {
                memberList.add(thisMember);
            }
        }
        return memberList;
    }

    @Override
    public List<MemberEntity> getMemberByAgeRange(int ageYoung, int ageOld) {
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List memberList = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity thisMember = (MemberEntity) o;
            int memberAge = 2013 - thisMember.getMemberDob().getYear();
            if ((memberAge > ageYoung) && (memberAge < ageOld)) {
                memberList.add(thisMember);
            }
        }
        return memberList;
    }

    @Override
    public List<MemberEntity> getMemberByAge(int year) {
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List memberList = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity thisMember = (MemberEntity) o;
//           System.out.println("matching the year of birth for member: " + thisMember.getMemberDob().getYear() + " with the year " + year);
            if (thisMember.getMemberDob().getYear() == year) {
 //               System.out.println("get by age " + year +" --> one member found: " + thisMember.getMemberName());
                memberList.add(thisMember);
            }
        }
//        System.out.println("the memberlist size is: " + memberList.size());尼玛终于好使了
        return memberList;
    }


    //cancel membership: member is not removed, it is inactivated
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void removeMember(String memberEmail) throws ExistException {
        member = em.find(MemberEntity.class, memberEmail);
        if (member == null) {
            throw new ExistException("Member doesn't exist!");
        }
        member.setMemberAccountStatus(false);
        em.merge(member);
    }

    //used for mobile application ProfileViewController: memberDob as string

    public MemberEntity updateMember(String memberEmail, String name, String memberHP, String memberDob, String maritalStatus, String gender, String subscription) throws ExistException, ParseException {
        member = em.find(MemberEntity.class, memberEmail);
        System.out.println("member session bean: email is " + memberEmail);
        if (member == null) {
            throw new ExistException("Member doesn't exist!");
        }
        System.out.println("email: " + memberEmail);
        if (name != null) {
            member.setMemberName(name);
        }
        if (memberHP != null) {
            member.setMemberHP(memberHP);
        }
        if (gender != null) {
            member.setGender(gender);
        }
//        member.setNationality(nationality);
        if (memberDob != null) {
            System.out.println(memberDob);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(memberDob);

            member.setMemberDob(date);
        }
        if (maritalStatus != null) {
            member.setMaritalStatus(maritalStatus);
        }
        if (subscription != null) {
            if (subscription.equals("true")) {
                member.setIsSubscriber(true);
            } else if (subscription.equals("false")) {
                member.setIsSubscriber(false);
            }
        }
        em.merge(member);
        return member;
    }

    //used for jsp servlet
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public MemberEntity updateMember(String memberEmail, String name, String memberHP, Date memberDob, String maritalStatus, String gender) throws ExistException, ParseException {
        member = em.find(MemberEntity.class, memberEmail);
        if (member == null) {
            throw new ExistException("Member doesn't exist!");
        }
        //    if (!(member.getMemberPassword().equals(memberPassword))) throw new ExistException("Wrong ID or password");
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

    //for mobile application 
    @Override
    public boolean updatePassword(String memberEmail, String memberPassword) {
        System.out.println("updatePassword: member Email is: " + memberEmail);
        member = em.find(MemberEntity.class, memberEmail);
        if (member == null) {
            System.err.println("member not found!");
        } else {
            System.err.println("member found!");
        }
        System.out.println("UpdatePassword: member Name is: " + member.getMemberName());
        member.setMemberPassword(ePasswordHashSessionBean.hashPassword(memberPassword));
        em.merge(member);
        return true;
    }
    //used of jsf managed bean

    @Override
    public boolean updateMember(MemberEntity member) {
        em.merge(member);
        System.out.println("MemberSessionBean: member " + member.getMemberEmail() + " is successfully updated");
        return true;
    }
    
    @Override
    public void updateBirthdayEmail(MemberEntity member) {
        member.setBirthdayEmail(true);
        em.merge(member);
    }

    //member subscribe/unsubscribe from email list
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void updateSubscription(String memberEmail, boolean isSubscriber) throws ExistException {
        member = em.find(MemberEntity.class, memberEmail);
        if (member == null) {
            throw new ExistException("Member doesn't exist!");
        }
        member.setIsSubscriber(isSubscriber);
        em.merge(member);
    }

    @Override
    public void updateVIP(String memberEmail) throws ExistException {
        member = em.find(MemberEntity.class, memberEmail);
        if (member == null) {
            throw new ExistException("Member doesn't exist!");
        }
        if (member.isVIP() == true) {
            throw new ExistException("This member is already a VIP");
        }
        member.setIsVIP(true);
        em.merge(member);
    }
    
//    public void coinPayment(MemberEntity member, double amount) throws ExistException {
//        if(member.getCoin()<amount) {
//            throw new ExistException ("Member doesn't have enough coin for payment");
//        }
//        member.setCoin(member.getCoin()-amount);
//        System.out.println("Member now has deducted coin  and the updated coin value is: " + member.getCoin());
//        em.merge(member);
//    }
//    
//    public boolean checkAffordable(MemberEntity member, double amount) {
//        if(member.getCoin()>amount) return true;
//        else return false;
//    }

    @Override
    public List<MemberEntity> getAllMembers() {
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List memberList = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity m = (MemberEntity) o;
            memberList.add(m);
        }
        return memberList;
    }

    @Override
    public List<String> getAllNationalities() {
        List memberList = this.getAllMembers();
        List<String> nationalityList = new ArrayList();
        System.err.println("Getting all nationality names...");
        String nationality;
        Iterator<MemberEntity> itr = memberList.iterator();
        while (itr.hasNext()) {
            System.err.println("While looping...");
            member = itr.next();
            nationality = member.getNationality();
            System.err.println("nationality: " + nationality);
            if (!(nationalityList.contains(nationality))) {
                nationalityList.add(nationality);
            }
        }
        return nationalityList;
    }

    @Override
    public void updateMemberTicketPurchase(MemberEntity member, TicketPurchaseEntity tp) {
        System.out.println("updateMemberTicketPurchase");
        List<TicketPurchaseEntity> tps = member.getTicketPurchases();
        tps.add(tp);
        member.setTicketPurchases(tps);
        em.merge(member);
        em.flush();
        return;
    }

    @Override
    public void updateMemberExpressPassPurchase(MemberEntity member, ExpressPassPurchaseEntity epp) {
        System.out.println("updateMemberExpressPassPurchase");
        List<ExpressPassPurchaseEntity> epps = member.getExpressPassPurchases();
        epps.add(epp);
        member.setExpressPassPurchases(epps);
        em.merge(member);
        em.flush();
        return;
    }

    @Remove
    @Override
    public void remove() {
        System.out.println("MemberManagerBean: remove()");
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }

    //used for mobile app log in
    @Override
    public MemberEntity checkLogIn(String email, String password) {
        MemberEntity thisMember = em.find(MemberEntity.class, email);
        if (thisMember != null) {
//            if (ePasswordHashSessionBean.hashPassword(password).equals(thisMember.getMemberPassword())) {
            
            if (cPasswordHashSessionBean.hashPassword(password).equals(thisMember.getMemberPassword())) {
                System.out.println("member log in from mobile successful!");
                return thisMember;
            } else {
                System.out.println("invalid password! please try again");
                return null;
            }
        } else {
            System.out.println("this email is not regisgered");
            return null;
        }
    }

    //used for mobile app RegisterViewController

    public MemberEntity createNewMember(String email, String password, String password2, String name, String hp, String dob, String gender, String maritalStatus, String nationality, String securityQuestion, String answer) throws ParseException {
        MemberEntity newMember = new MemberEntity();
        member = em.find(MemberEntity.class, email);
        if (member != null) {
            System.out.println(member.getMemberEmail());
            System.out.println("You have registered already. Please log in.");
            return member;
        }
        if (!password.equals(password2)) {
            System.out.println("Two passwords are not the same. Please register again.");
            return null;
        }
        newMember.setMemberEmail(email);
        newMember.setMemberPassword(password);
        newMember.setMemberName(name);
        newMember.setMemberHP(hp);
        System.out.println(dob);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
        newMember.setMemberDob(date);
        newMember.setGender(gender);
        newMember.setMaritalStatus(maritalStatus);
        newMember.setNationality(nationality);
        newMember.setSecurityQuestion(securityQuestion);
        newMember.setAnswer(answer);
        newMember.setCoin(0);
        newMember.setIsSubscriber(true);
        newMember.setIsVIP(false);
        newMember.setPoint(0);
        newMember.setPreferences("to be set");
        em.persist(newMember);
        System.out.println("New member created via mobile application");
        return newMember;
    }
}
