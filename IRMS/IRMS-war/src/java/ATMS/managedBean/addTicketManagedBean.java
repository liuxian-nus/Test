/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.managedBean;

import ATMS.entity.AttrTicketEntity;
import ATMS.entity.AttractionEntity;
import ATMS.session.AttractionSessionBean;
import ATMS.session.TicketSessionBean;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Jieqiong
 */
@ManagedBean
@RequestScoped
public class addTicketManagedBean {
    @EJB
    private AttractionSessionBean attractionSessionBean;
    @EJB
    private TicketSessionBean ticketSessionBean;    
    private AttrTicketEntity ticket;
    private AttractionEntity attr;
    
    @PostConstruct
    public void init()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    public AttrTicketEntity getTicket(){
        return ticket;
    }
    
    public void setTicket(AttrTicketEntity ticket){
        this.ticket=ticket;
    }

    /**
     * Creates a new instance of addTicketManagedBean
     */
    public addTicketManagedBean() {
        ticket=new AttrTicketEntity();
    }
    
    public void saveNewTicket(ActionEvent event) throws IOException{
        try{
            System.out.println("into addTicketManagedBean: saveNewTicket"); 
      //      ticket=mapAttractionId(ticket);
            ticketSessionBean.addTicket(ticket);
            System.out.println("new ticket added");
        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding a new ticket", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Ticket Saved.", ""));
        ticket=new AttrTicketEntity();
    }
    
    public void oneMore(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addTicket.xhtml");
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
    public AttractionSessionBean getAttractionSessionBean() {
        return attractionSessionBean;
    }

    public void setAttractionSessionBean(AttractionSessionBean attractionSessionBean) {
        this.attractionSessionBean = attractionSessionBean;
    }

    public TicketSessionBean getTicketSessionBean() {
        return ticketSessionBean;
    }

    public void setTicketSessionBean(TicketSessionBean ticketSessionBean) {
        this.ticketSessionBean = ticketSessionBean;
    }

    public AttractionEntity getAttr() {
        return attr;
    }

    public void setAttr(AttractionEntity attr) {
        this.attr = attr;
    }
    
    
   
}
