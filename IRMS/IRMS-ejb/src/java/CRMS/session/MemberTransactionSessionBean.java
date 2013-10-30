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

    @PersistenceContext(unitName="IRMS-ejbPU")
    private EntityManager em;
    MemberEntity member = new MemberEntity();
    MemberTransactionEntity mt;

    public MemberTransactionSessionBean() {
    }

    public void addMemberTransaction(MemberEntity member,double amount,Date mtDate, String mtDepartment,String mtPromotion,boolean coinPay) {
        System.out.println("creating member transaction....");
        mt = new MemberTransactionEntity();
        mt.setMemberEmail(member.getMemberEmail());
        mt.setMtAmount(amount);
        mt.setMtDate(mtDate);
        mt.setMtDepartment(mtDepartment);
        mt.setMtPromotion(mtPromotion);
        mt.setMtMode(true); //later add in cash or card
        mt.setPaymentStatus(true);//later add in paid or not paid
        em.persist(mt);
         if (!coinPay) {
            addPoint(member, amount);
            addCoin(member, amount);
            updateVIP(member.getPoint());
            System.out.println("Transaction of " + member.getMemberName() + "has been added successfully");
        } else {
            double tempCoin = member.getCoin();
            member.setCoin(0);
            System.out.println("Transaction of " + member.getMemberName() + "has been added successfully");
        }
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
        System.out.println("mt persist successful");
        System.out.println("mt email"+mt.getMemberEmail());
        
        Set temp = member.getMemberTransactions();
        System.out.println("The transaction record of " + memberEmail + "has been retrieved successfully!");
        if (temp.add(mt)) {
            System.out.println("mt added to transactions");
            member.setMemberTransactions(temp);
        }
        mt.setMemberEmail(member.getMemberEmail());
        if (!coinPay) {
            addPoint(member, mt.getMtAmount());
            addCoin(member, mt.getMtAmount());
            updateVIP(member.getPoint());
            System.out.println("Transaction of " + member.getMemberEmail() + "has been added successfully");
            return mt.getMtAmount();
        } else {
            double tempCoin = member.getCoin();
            member.setCoin(0);
            System.out.println("Transaction of " + member.getMemberEmail() + "has been added successfully");
            return (mt.getMtAmount() - tempCoin);
        }
    }

    public void addPoint(MemberEntity member, double mtAmount){
        if(member!=null){
        double point = member.getPoint();
        point = point + mtAmount * 0.01;
        member.setPoint(point);
        System.out.println("Member : " + member.getMemberName() + " account has been credited by" + point + "points");
        }
        else return;
    }

    public void addCoin(MemberEntity member, double mtAmount) {
        if(member!=null){
        double coin = member.getCoin();
        coin = coin + mtAmount * 0.01;
        member.setCoin(coin);
        }
        else return;
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

