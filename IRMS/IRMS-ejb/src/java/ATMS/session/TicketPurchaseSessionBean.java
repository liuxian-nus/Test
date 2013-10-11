/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.session;

import ATMS.entity.AttractionEntity;
import ATMS.entity.TicketEntity;
import ATMS.entity.TicketPurchaseEntity;
import CRMS.entity.MemberEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jieqiong
 */
@Stateless
@LocalBean
public class TicketPurchaseSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    private TicketPurchaseEntity tp;
    private TicketEntity ticket=new TicketEntity();
    private List<TicketEntity> tkts;
    private MemberEntity member=new MemberEntity();
    
    
    public TicketPurchaseSessionBean(){
        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long addTicketPurchase(TicketPurchaseEntity tp){
        
        em.persist(tp);
        System.out.println("tpId: "+tp.getId());
        return tp.getId();
    }
    
  /*  public Long updateTicketListAndQuantity(List<TicketEntity> tkts, int quantity){
        System.out.println("TicketPurchaseSessoinBean: updateTicketList");
        tp.setTickets(tkts);
        System.out.println("tickets set");
     //   setMemberEmail
        tp.setQuantity(quantity);
        System.out.println("ticket quantity set");
        em.merge(tp);
        em.flush();
        System.out.println("tp updated in database");
        System.out.println("tpId: "+tp.getId());
        return tp.getId();     
    }*/
    
    public void updateTicketListAndQuantity(Long tpId, List<TicketEntity> tkts, int quantity){
        System.out.println("TicketPurchaseSessoinBean: updateTicketList with tpId");
        tp=em.find(TicketPurchaseEntity.class, tpId);
        System.out.println("tp found"+tp.getId());
        tp.setTickets(tkts);
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
        tp.setBookDate(date);
        System.out.println("date updated."+date);
    }
    
    public void updateFee(Long tpId, double fee){
        tp=em.find(TicketPurchaseEntity.class, tpId);
        tp.setFee(fee);
        System.out.println("fee updated."+fee);
    }
    
    public void updatePurchase(Long tpId, List<TicketEntity> tkts, List<Integer> quantities, Date date, double fee){
        tp=em.find(TicketPurchaseEntity.class, tpId);
        tp.setTickets(tkts);
        tp.setQuantity(quantities);
        tp.setBookDate(date);
        tp.getFee();
        System.out.println("quantity2:"+quantities.get(1));
        System.out.println("quantity size: "+quantities.size());
        em.merge(tp);
        em.flush();
        System.out.println("tp updated in database");
    }

    
    
    
    /*public void addTicket(TicketEntity ticket){
        System.out.println("into TicketPurchaseSessionBean: addTicket");
        System.out.println("ticket: "+ticket.getTicketName());
        tkts=tp.getTickets();
        tkts.add(ticket);
        tp.setTickets(tkts);
        System.out.println("ticket added into tkts list");
        em.merge(tp);
        em.flush();
        System.out.println("ticket added into database");
    }*/
    
  /*  public void addTicketPurchase(Long ticketId,Integer quantity){
        ticket=new TicketEntity();
        ticket=em.find(TicketEntity.class, ticketId);
        tp=new TicketPurchaseEntity();
        tp.setTicket(ticket);
        
        
    }*/
  
   
    

    public void persist(Object object) {
        em.persist(object);
    }
    
   
    
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
