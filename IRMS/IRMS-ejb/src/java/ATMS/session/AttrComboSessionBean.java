/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.session;

import ATMS.entity.AttrComboEntity;
import ATMS.entity.AttrTicketEntity;
import Exception.ExistException;
import java.util.List;
import javax.ejb.Stateless;
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
public class AttrComboSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    private AttrComboEntity attrCombo;
    private List<AttrTicketEntity> tickets;
    
    public AttrComboSessionBean(){
        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addAttrCombo(AttrComboEntity combo){
        System.out.println("into AttrComboSessionBean: addAttrCombo");
        em.persist(combo);
        System.out.println("attrCombo added.");
        return;
    }
    
    public List<AttrComboEntity> getAllAttrCombos(){
        System.out.println("into AttrComboSessionBean: getAllAttrCombo");
        Query query = em.createQuery("SELECT ac FROM AttrComboEntity ac");
        return query.getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateAttrCombo(AttrComboEntity combo){
        System.out.println("into AttrComboSessionBean: updateAttrCombo");
        em.merge(combo);
        em.flush();
        System.out.println("updated");
    }
    
    public List<AttrTicketEntity> getComboTicketsById(Long id){
        System.out.println("into AttrComboSessionBean: updateAttrCombo");
        attrCombo=em.find(AttrComboEntity.class,id);
        return attrCombo.getAttrTickets();
        
    }
    
    public AttrComboEntity getComboById(Long id){
        attrCombo=em.find(AttrComboEntity.class, id);
        return attrCombo;
    }
    
    public void removeAttrCombo(Long id)throws ExistException{
        System.out.println("into AttrComboSessionBean: removeAttrCombo");
        attrCombo = em.find(AttrComboEntity.class, id);
        if(attrCombo == null) {
            throw new ExistException("Combo does not exist!");
        }
        em.remove(attrCombo);
        System.out.println("the combo has been removed.");
        return;   
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public AttrComboEntity getAttrCombo() {
        return attrCombo;
    }

    public void setAttrCombo(AttrComboEntity attrCombo) {
        this.attrCombo = attrCombo;
    }

    public List<AttrTicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<AttrTicketEntity> tickets) {
        this.tickets = tickets;
    }
    

}
