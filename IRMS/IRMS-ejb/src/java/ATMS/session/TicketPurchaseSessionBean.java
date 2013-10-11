/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.session;

import ATMS.entity.AttractionEntity;
import ATMS.entity.TicketEntity;
import ATMS.entity.TicketPurchaseEntity;
import CRMS.entity.MemberEntity;
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
    private TicketEntity ticket;
    private List<TicketEntity> tkts;
    private MemberEntity member=new MemberEntity();
    
    
    public TicketPurchaseSessionBean(){
        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public long addTicketPurchase(TicketPurchaseEntity tp){
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
     //   setMemberEmail
        tp.setQuantity(quantity);
        System.out.println("ticket quantity set");
        em.merge(tp);
        em.flush();
        System.out.println("tp updated in database");
       // return tp.getId();     
        return;
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
  
    
 /*   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade ={CascadeType.ALL})
    TicketEntity ticket;
    @ManyToOne
    private MemberEntity member=new MemberEntity();
    private Integer quantity;
    private double fee;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date bookDate =new Date();
    @Temporal(javax.persistence.TemporalType.DATE)
    Date lastDate=new Date();*/
    
    

    public void persist(Object object) {
        em.persist(object);
    }
    
   
    
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
