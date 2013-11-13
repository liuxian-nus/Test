/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import ATMS.entity.ExpressPassPurchaseEntity;
import ATMS.entity.TicketPurchaseEntity;
import CRMS.entity.MemberEntity;
import Exception.ExistException;
import com.lowagie.text.pdf.codec.postscript.ParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface MemberSessionBeanRemote {

    //member registration: used for jsf managed bean
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    MemberEntity addMember(MemberEntity member);
    //member registration: used for jsp servlet

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    MemberEntity addMember(String memberEmail, String memberName, String memberPassword, String memberPassword2, String memberHP, String gender, String nationality, Date memberDob, String maritalStatus, boolean isSubscriber, String question, String answer);

    //used for mobile app log in
    MemberEntity checkLogIn(String email, String password);

    //used for mobile app RegisterViewController
//    MemberEntity createNewMember(String email, String password, String password2, String name, String hp, String dob, String gender, String maritalStatus, String nationality, String securityQuestion, String answer) throws ParseException;

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
    List<MemberEntity> getAllMembers();

    List<String> getAllNationalities();

    List<MemberEntity> getMemberByAge(int year);

    List<MemberEntity> getMemberByAgeRange(int ageYoung, int ageOld);

    List<MemberEntity> getMemberByBirthMonth(int month);

    MemberEntity getMemberByEmail(String email);

    List<MemberEntity> getMemberByGender(String gender);

    List<MemberEntity> getMemberByMaritalStatus(String maritalStatus);

    List<MemberEntity> getMemberByNationality(String nationality);

    List<MemberEntity> getMemberBySubscription();

    void persist(Object object);

    @Remove
    void remove();

    //cancel membership: member is not removed, it is inactivated
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void removeMember(String memberEmail) throws ExistException;

    void updateBirthdayEmail(MemberEntity member);

    //used for mobile application ProfileViewController: memberDob as string
//    MemberEntity updateMember(String memberEmail, String name, String memberHP, String memberDob, String maritalStatus, String gender, String subscription) throws ExistException, ParseException;

    //used for jsp servlet
//    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
//    MemberEntity updateMember(String memberEmail, String name, String memberHP, Date memberDob, String maritalStatus, String gender) throws ExistException, ParseException;

    boolean updateMember(MemberEntity member);

    void updateMemberExpressPassPurchase(MemberEntity member, ExpressPassPurchaseEntity epp);

    void updateMemberTicketPurchase(MemberEntity member, TicketPurchaseEntity tp);

    //for mobile application
    boolean updatePassword(String memberEmail, String memberPassword);
    //used of jsf managed bean

    //member subscribe/unsubscribe from email list
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void updateSubscription(String memberEmail, boolean isSubscriber) throws ExistException;

    void updateVIP(String memberEmail) throws ExistException;
    
}
