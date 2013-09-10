/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MemberEntity;
import Exception.ExistException;
import java.util.Date;
import java.util.Set;
import javax.ejb.Remove;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Diana Wang
 */
public interface MemberSessionBeanRemote {

    //member registration
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void addMember(String memberEmail, String memberPassword, String memberName, String memberHP, String gender, String nationality, Date memberDob, boolean maritalStatus, boolean isSubscriber);

    Set<MemberEntity> getMember();

    boolean login(String memberEmail, String memberPassword);

    @Remove
    void remove();

    //cancel membership
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void removeMember(String memberEmail) throws ExistException;

    //update member profile & password
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void updateMember(String memberEmail, String memberPassword, String memberName, String memberHP, String gender, String nationality, Date memberDob, boolean maritalStatus) throws ExistException;

    //member subscribe/unsubscribe from email list
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void updateSubscription(String memberEmail, boolean isSubscriber) throws ExistException;
    
}
