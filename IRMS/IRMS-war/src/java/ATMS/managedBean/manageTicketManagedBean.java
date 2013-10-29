/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.managedBean;

import ATMS.entity.AttrTicketEntity;
import ATMS.session.TicketSessionBean;
import Exception.ExistException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Jieqiong
 */
@ManagedBean
@ViewScoped
public class manageTicketManagedBean {
    @EJB
    private TicketSessionBean ticketSessionBean;
    private AttrTicketEntity ticket;
    private Long ticketId;
    private boolean editMode;
    

    /**
     * Creates a new instance of manageTicketManagedBean
     */
    public manageTicketManagedBean() {
        ticket=new AttrTicketEntity();
    }
    
    public List<AttrTicketEntity> getAllTickets(){
        return ticketSessionBean.getAllTickets();
    }
    
    public boolean isEditMode(){
        return editMode;
    }
    
    public void saveChanges(ActionEvent event) throws ExistException
    {
        System.out.println("into ManageTicketManagedBean");
   //     ticket=mapAttractionId(ticket);
        ticketSessionBean.updateTicket(getTicket());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));        
    }
    
    public void deleteTicket(ActionEvent event) throws ExistException {
        setTicketId((Long)event.getComponent().getAttributes().get("code1"));
        System.out.println(("ticketId ")+getTicketId());
        ticketSessionBean.removeTicket(getTicketId());
    }
    
  /*  public AttrTicketEntity mapAttractionId(AttrTicketEntity ticket){
        String name=ticket.getAttrName();
        
        if(name.equals("Aquarium"))
            ticket.setAttrId("AQ");
        else if(name.equals("Horror House"))
            ticket.setAttrId("IT");
        else if(name.equals("Culture Musuem"))
            ticket.setAttrId("MU");
        else if(name.equals("Adventure World"))
            ticket.setAttrId("OT");
        else
            System.out.println("wrong attraction name");
        
        return ticket; 
 
    }*/
    

    public TicketSessionBean getTicketSessionBean() {
        return ticketSessionBean;
    }

    public void setTicketSessionBean(TicketSessionBean ticketSessionBean) {
        this.ticketSessionBean = ticketSessionBean;
    }

    public AttrTicketEntity getTicket() {
        System.out.println("ticket name: "+ticket.getAttrTicketName());
        return ticket;
    }

    public void setTicket(AttrTicketEntity ticket) {
        this.ticket = ticket;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
    
    
    
    
    
    
    
}
