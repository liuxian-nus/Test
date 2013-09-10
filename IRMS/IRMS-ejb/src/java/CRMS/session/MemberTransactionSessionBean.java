/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.entity.VIPEntity;
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
public class MemberTransactionSessionBean implements MemberTransactionSessionRemote {
 @PersistenceContext
    private EntityManager em ;
    
 MemberEntity member = new MemberEntity();
 MemberTransactionEntity mt = new MemberTransactionEntity();
    
    public MemberTransactionSessionBean() {
    }
    
        public double addMemberTransaction(String memberEmail,MemberTransactionEntity mt,Boolean coinPay) throws ExistException{
      member = em.find(MemberEntity.class, memberEmail);
      if(member==null) throw new ExistException("Member does not exist!");
     // mt.create(mtDate, mtAmount, mtDepartment, mtMode);
      em.persist(mt);
      Set temp = member.getMemberTransactions();
      System.out.println("The transaction record of "+memberEmail+"has been retrieved successfully!");
      if(temp.add(mt))
          member.setMemberTransactions(temp);
      mt.setMember(member);
      if(!coinPay){
      addPoint(memberEmail,mt.getMtAmount());
      addCoin(memberEmail,mt.getMtAmount());
      updateVIP(member.getPoint());
      System.out.println("Transaction of "+memberEmail+"has been added successfully");
      return mt.getMtAmount();
      }
      else{
          double tempCoin = member.getCoin();
          member.setCoin(0);
          System.out.println("Transaction of "+memberEmail+"has been added successfully");
          return(mt.getMtAmount() - tempCoin);
        }
     
    }
    
    @Override
    public void addPoint(String memberEmail,double mtAmount) throws ExistException{
        member = em.find(MemberEntity.class, memberEmail);
        if(member == null) throw new ExistException("Member does not exist!");
        double point = member.getPoint();
        point = point + mtAmount*0.01;
        member.setPoint(point);
        System.out.println("Member : "+memberEmail+" account has been credited by"+point+"points");
    }
    
    @Override
    public void addCoin (String memberEmail, double mtAmount)throws ExistException{
        member = em.find(MemberEntity.class,memberEmail);
        if(member ==null) throw new ExistException("Member does not exist!");
        double coin = member.getCoin();
        coin = coin + mtAmount*0.01;
        member.setCoin(coin);
    }

    private void updateVIP(double point) {
       if(point>=500) {
       member.setIsVIP(true);
       VIPEntity newVIP = new VIPEntity();
       newVIP.create("new VIP: currently no preference");
       em.persist(newVIP);
       System.out.println("MTSessionBean: Member has been upgraded to VIP!"); 
       }
       else 
           System.out.println("MTSessionBean: Member does not meet the requirement for VIP upgrade!");
    }
    
    
    }




    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")



     