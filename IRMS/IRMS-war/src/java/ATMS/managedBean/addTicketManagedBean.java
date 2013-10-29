/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.managedBean;

import ATMS.entity.AttrTicketEntity;
import ATMS.entity.AttractionEntity;
import ATMS.session.AttractionSessionBean;
import ATMS.session.TicketSessionBean;
import Exception.ExistException;
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
    private Long ticketId;
    private AttractionEntity attr;
    private String attrId;
    private boolean editMode;
    
    @PostConstruct
    public void init()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
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
      //      ticket=mapAttractionId(ti=cket);
            attr=attractionSessionBean.getAttrById(attrId);
            System.out.println("attr: "+attr.getAttrName());
            ticket.setAttr(attr);
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
    
    public void saveChanges(ActionEvent event) throws ExistException
    {
        System.out.println("here");
        System.out.println("addTicketManagedBean : saveChanges");
        ticketSessionBean.updateTicket(ticket);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));        
    }
    
    public void deleteAttrTicket(ActionEvent event) throws ExistException {
        System.out.println("addTicketManagedBean : deleteAttrTicket");
        setTicketId((Long)event.getComponent().getAttributes().get("code1"));
        System.out.println(("attrTicketId ")+getTicketId());
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
    
     public AttrTicketEntity getTicket(){
        System.out.println("ticket name: "+ticket.getAttrTicketName());
        return ticket;
    }
    
    public void setTicket(AttrTicketEntity ticket){
        this.ticket=ticket;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
    

    public AttractionEntity getAttr() {
        return attr;
    }

    public void setAttr(AttractionEntity attr) {
        this.attr = attr;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    } 
  
}
