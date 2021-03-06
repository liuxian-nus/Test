/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.session;

import ATMS.entity.AttractionEntity;
import ATMS.entity.AttrTicketEntity;
import ATMS.entity.QuotaEntity;
import ATMS.entity.TicketPurchaseEntity;
import CRMS.entity.MemberEntity;
import CRMS.session.MemberSessionBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jieqiong
 */
@Stateless
@LocalBean
public class TicketPurchaseSessionBean {
    @EJB
    private MemberSessionBean memberSessionBean;
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    private TicketPurchaseEntity tp;
    private AttrTicketEntity ticket=new AttrTicketEntity();
    private List<AttrTicketEntity> tkts=new ArrayList<AttrTicketEntity>();
    private MemberEntity member=new MemberEntity();
    private List<TicketPurchaseEntity> ArrayList;
    
    
    
    
    public TicketPurchaseSessionBean(){
    }
    
    public List<TicketPurchaseEntity> getPurchasedTicketsByEmail(String email){
        member=memberSessionBean.getMemberByEmail(email);
        List<TicketPurchaseEntity> selectedTps=new ArrayList<TicketPurchaseEntity>();
        Date today=new Date();
        if(member!=null){
            System.out.println("member exists");
            List<TicketPurchaseEntity> tps=member.getTicketPurchases();      
            for(int i=0;i<tps.size();i++){
                System.out.println("i:"+i);
                TicketPurchaseEntity tp=tps.get(i);
                if(tp.getAttrTPStatus().equals("Purchased")&&tp.getAttrTicketBookDate().after(today)){
                    System.out.println("tp selected");
                    selectedTps.add(tp);
                }
            }
            return selectedTps;
        }
        else return null;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long addTicketPurchase(TicketPurchaseEntity tp){
        
        em.persist(tp);
        System.out.println("tpId: "+tp.getTpId());
        return tp.getTpId();
    }
    
    public void updateTicketPurchase(TicketPurchaseEntity tp){
        em.merge(tp);
        em.flush();
        System.out.println("updated");
        return;
    }
    
    public List<TicketPurchaseEntity> getAllTicketPurchases(){
        //System.out.println("into AttrComboSessionBean: getAllAttrCombo");
        Query query = em.createQuery("SELECT tp FROM TicketPurchaseEntity tp");
        return query.getResultList();
    }
    
    public void updateTicketListAndQuantity(Long tpId, List<AttrTicketEntity> tkts, int quantity){
        System.out.println("TicketPurchaseSessoinBean: updateTicketList with tpId");
        tp=em.find(TicketPurchaseEntity.class, tpId);
        System.out.println("tp found"+tp.getTpId());
        tp.setAttrTickets(tkts);
        System.out.println("tickets set");
   //     tp.setQuantity(quantity);
        System.out.println("ticket quantity set");
        em.merge(tp);
        em.flush();
        System.out.println("tp updated in database");
       // return tp.getId();     
        return;
    }
    
    
    public void updateDate(Long tpId, Date date){
        tp=em.find(TicketPurchaseEntity.class, tpId);
        tp.setAttrTicketBookDate(date);
        em.merge(tp);
        em.flush();
        System.out.println("date updated."+date);
    }
    
    public void updateFee(Long tpId, double fee){
        tp=em.find(TicketPurchaseEntity.class, tpId);
        tp.setAttrTicketFee(fee);
        em.merge(tp);
        em.flush();
        System.out.println("fee updated."+fee);
    }
    
    public void updateStatus(Long tpId){
        tp=em.find(TicketPurchaseEntity.class, tpId);
        tp.setAttrTPStatus("In progress");
        em.merge(tp);
        em.flush();
        System.out.println("status updated!");
    }
    
    public void updatePurchase(Long tpId, List<AttrTicketEntity> tkts, List<Integer> quantities, Date date, double fee, String status, String remarks){
        tp=em.find(TicketPurchaseEntity.class, tpId);
        tp.setAttrTickets(tkts);
        tp.setAttrTicketQuantities(quantities);
        tp.setAttrTicketBookDate(date);
        tp.setAttrTicketFee(fee);
        tp.setAttrTPStatus(status);
        tp.setAttrTicketPurchaseRemarks(remarks);
        System.out.println("quantity size: "+quantities.size());
        em.merge(tp);
        em.flush();
        System.out.println("tp updated in database");
    }
    
    public double calculateFee(AttrTicketEntity ticket, int quantity){
        double ticketPrice=ticket.getAttrTicketPrice();
        return ticketPrice*quantity;
    }
    
    
    public TicketPurchaseEntity getTicketPurchaseById(Long id){
        return em.find(TicketPurchaseEntity.class, id);
    }
    
    public boolean checkTicketValidity(String tpIdString){
        System.out.println("ticketPurchaseSessionBean : checkTicketValidity");
        System.out.println("idString: "+tpIdString);
        Long id=dropDummyZeroAndLastDigit(tpIdString);
        tp=em.find(TicketPurchaseEntity.class, id);
        if(tp==null){
            System.out.println("ticket doesn't exist");
            return false;
        }
        else if(!(tp.getAttrTPStatus().equals("Purchased")))
            return false;
        else{
            System.out.println("valid ticket purchase");
            return true;
        }
    }
    
    public Long dropDummyZeroAndLastDigit(String tpIdString){
        System.out.println("ticketPurchaseSessionBean : dropDummyZero");
        tpIdString=tpIdString.substring(1,7);
        System.out.println("tpIdString: "+tpIdString);
        Long tpId=Long.valueOf(tpIdString);
        System.out.println("tpId: "+tpId);
        
        return tpId;    
    }
    
    /*public int getTicketsSold(AttractionEntity attr, Date date){
        List<TicketPurchaseEntity> allTPs=getAllTicketPurchases();
        List<TicketPurchaseEntity> selected=new ArrayList<TicketPurchaseEntity>();
        for(int i=0;i<allTPs.size();i++){
            tp=allTPs.get(i);
            if(tp.getAttrTickets().get(0).getAttr().equals(attr)){
                System.out.println("matched ticket");
                selected.add(tp);
            }
        }
        for(int i=0;i<selected.size();i++){
            tp=selected.get(i);
            if(tp.getAttrTPStatus().equals("Successful")&&tp.getAttrTicketBookDate().equals(date)){
                for(int j=0;i<)
            }
        }
        
    }*/

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    
   
    
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}