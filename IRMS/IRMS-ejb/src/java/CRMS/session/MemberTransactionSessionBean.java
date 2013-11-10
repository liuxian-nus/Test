/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import ATMS.entity.TicketPurchaseEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class MemberTransactionSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    MemberEntity member = new MemberEntity();
    MemberTransactionEntity mt;

    public MemberTransactionSessionBean() {
    }

    public List<MemberTransactionEntity> getAllTransactions() {
        Query q = em.createQuery("SELECT mt FROM MemberTransactionEntity mt");
        return q.getResultList();
    }

    public List<MemberTransactionEntity> getTransactionsByMemberEmail(String memberEmail) {
//        member = em.find(MemberEntity.class, memberEmail);
        Query q = em.createQuery("SELECT mt FROM MemberTransactionEntity mt where mt.memberEmail = '" + memberEmail + "'");
        List memberTransactionList = new ArrayList<MemberTransactionEntity>();
        for (Object o : q.getResultList()) {
            MemberTransactionEntity mt = (MemberTransactionEntity) o;
            memberTransactionList.add(mt);
        }
        return memberTransactionList;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public MemberTransactionEntity addMemberTransaction(MemberTransactionEntity mt) {
        em.persist(mt);
        return mt;
    }

    public void addMemberTransaction(MemberEntity member, double amount, Date mtDate, String mtDepartment, String mtPromotion, boolean coinPay, String mtDescription) {
        System.out.println("creating member transaction....");
        mt = new MemberTransactionEntity();
        mt.setMemberEmail(member.getMemberEmail());
        mt.setMtAmount(amount);
        mt.setMtDate(mtDate);
        mt.setMtDepartment(mtDepartment);
        mt.setMtPromotion(mtPromotion);
        mt.setMtMode(true); //later add in cash or card
        mt.setPaymentStatus(true);//later add in paid or not paid
        mt.setMtDescription(mtDescription);
        em.persist(mt);
        if (!coinPay) {
            addPoint(member, amount);
            addCoin(member, amount);
            updateVIP(member.getPoint());
            System.out.println("Transaction of " + member.getMemberName() + "has been added successfully");
        } else {
//            double tempCoin = member.getCoin();
//            member.setCoin(0); //why set coin to 0??
            payByCoin(member, amount);
            System.out.println("Transaction of " + member.getMemberName() + "has been added successfully");
        }
        System.out.println("member transaction successfully added");
        System.out.println("started to add member transaction");
        member.addMemberTransaction(mt);
        System.out.println("member transaction added");
    }

    public void addMemberTransaction(MemberEntity member, double amount, Date mtDate, String mtDepartment, String mtPromotion, String mtDescription, boolean coinPay) {
        System.out.println("creating member transaction....");
        mt = new MemberTransactionEntity();
        mt.setMemberEmail(member.getMemberEmail());
        mt.setMtAmount(amount);
        mt.setMtDate(mtDate);
        mt.setMtDepartment(mtDepartment);
        mt.setMtPromotion(mtPromotion);
        mt.setMtDescription(mtDescription);
        mt.setMtMode(true); //later add in cash or card
        mt.setPaymentStatus(true);//later add in paid or not paid
        em.persist(mt);
        if (!coinPay) {
            addPoint(member, amount);
            addCoin(member, amount);
            updateVIP(member.getPoint());
            System.out.println("Transaction of " + member.getMemberName() + "has been added successfully");
        } else {
//            double tempCoin = member.getCoin();
//            member.setCoin(0); //why set coin to 0??
            payByCoin(member, amount);
            System.out.println("Transaction of " + member.getMemberName() + "has been added successfully");
        }
        System.out.println("member transaction successfully added");
        System.out.println("started to add member transaction");
        member.addMemberTransaction(mt);
        System.out.println("member transaction added");
    }

    public double addMemberTransaction(String memberEmail, MemberTransactionEntity mt, boolean coinPay) throws ExistException {
        member = em.find(MemberEntity.class, memberEmail);
        if (member == null) {
            throw new ExistException("Member does not exist!");
        }
        // mt.create(mtDate, mtAmount, mtDepartment, mtMode);
        em.persist(mt);
        System.out.println("mt persist successful");
        System.out.println("mt email" + mt.getMemberEmail());

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

    public void addPoint(MemberEntity member, double mtAmount) {
        if (member != null) {
            double point = member.getPoint();
            point = point + mtAmount * 0.01;
            member.setPoint(point);
            em.merge(member);
            em.flush();
            System.out.println("Member : " + member.getMemberName() + " account has been credited by" + point + "points");
            System.out.println("point has been updated to " + member.getPoint());
        } else {
            return;
        }
    }

    public void addCoin(MemberEntity member, double mtAmount) {
        if (member != null) {
            double coin = member.getCoin();
            coin = coin + mtAmount * 0.01;
            member.setCoin(coin);
            em.merge(member);
            em.flush();
            System.out.println("coin has been updated to " + member.getCoin());
        } else {
            return;
        }
    }

    public void payByCoin(MemberEntity member, double mtAmount) {
        double coin = member.getCoin();
        coin -= mtAmount;
        member.setCoin(coin);
        em.merge(member);
        em.flush();
        System.out.println("member coin has been deducted to " + member.getCoin());
    }

    public boolean checkCoinAmount(MemberEntity member, double mtAmount) {
        double coin = member.getCoin();
        System.out.println("coins: " + coin);
        if (coin >= mtAmount) {
            System.out.println("enough coin");
            return true;
        } else {
            return false;
        }
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

    public void updateVIP(MemberEntity member) {
        if (member.isVIP()) {
            System.out.println("member is VIP already");
            return;
        } else {
            if (member.getPoint() >= 500) {
                member.setIsVIP(true);
                em.merge(member);
                em.flush();
                System.out.println("Member has been upgraded to VIP!");
            } else {
                System.out.println("Member does not meet the requirement for VIP upgrade!");
            }
        }
    }
}
