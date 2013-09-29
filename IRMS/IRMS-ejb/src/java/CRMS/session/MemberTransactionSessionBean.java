/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import Exception.ExistException;
import java.util.Date;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class MemberTransactionSessionBean {

    @PersistenceContext
    private EntityManager em;
    MemberEntity member = new MemberEntity();
    MemberTransactionEntity mt = new MemberTransactionEntity();

    public MemberTransactionSessionBean() {
    }

    public void addMemberTransaction(MemberEntity member,double amount,Date mtDate, String mtDepartment,String mtPromotion,boolean coinPay) {
        System.out.println("creating member transaction....");
        mt.setMember(member);
        mt.setMtAmount(amount);
        mt.setMtDate(mtDate);
        mt.setMtDepartment(mtDepartment);
        mt.setMtPromotion(mtPromotion);
        mt.setMtMode(true); //later add in cash or card
        mt.setPaymentStatus(true);//later add in paid or not paid
        em.persist(mt);
        System.out.println("member transaction successfully added");
        member.addMemberTransaction(mt);
    }
    
    public double addMemberTransaction(String memberEmail, MemberTransactionEntity mt, boolean coinPay) throws ExistException {
        member = em.find(MemberEntity.class, memberEmail);
        if (member == null) {
            throw new ExistException("Member does not exist!");
        }
        // mt.create(mtDate, mtAmount, mtDepartment, mtMode);
        em.persist(mt);
        Set temp = member.getMemberTransactions();
        System.out.println("The transaction record of " + memberEmail + "has been retrieved successfully!");
        if (temp.add(mt)) {
            member.setMemberTransactions(temp);
        }
        mt.setMember(member);
        if (!coinPay) {
            addPoint(memberEmail, mt.getMtAmount());
            addCoin(memberEmail, mt.getMtAmount());
            updateVIP(member.getPoint());
            System.out.println("Transaction of " + memberEmail + "has been added successfully");
            return mt.getMtAmount();
        } else {
            double tempCoin = member.getCoin();
            member.setCoin(0);
            System.out.println("Transaction of " + memberEmail + "has been added successfully");
            return (mt.getMtAmount() - tempCoin);
        }
    }

    public void addPoint(String memberEmail, double mtAmount) throws ExistException {
        member = em.find(MemberEntity.class, memberEmail);
        if (member == null) {
            throw new ExistException("Member does not exist!");
        }
        double point = member.getPoint();
        point = point + mtAmount * 0.01;
        member.setPoint(point);
        System.out.println("Member : " + memberEmail + " account has been credited by" + point + "points");
    }

    public void addCoin(String memberEmail, double mtAmount) throws ExistException {
        member = em.find(MemberEntity.class, memberEmail);
        if (member == null) {
            throw new ExistException("Member does not exist!");
        }
        double coin = member.getCoin();
        coin = coin + mtAmount * 0.01;
        member.setCoin(coin);
    }

    private void updateVIP(double point) {
        if (point >= 500) {
            member.setIsVIP(true);
            em.merge(member);
            System.out.println("MTSessionBean: Member has been upgraded to VIP!");
        } else {
            System.out.println("MTSessionBean: Member does not meet the requirement for VIP upgrade!");
        }
    }
}
// Add business logic below. (Right-click in editor and choose
// "Insert Code > Add Business Method")

