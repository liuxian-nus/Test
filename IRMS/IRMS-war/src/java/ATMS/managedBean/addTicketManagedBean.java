/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.managedBean;

import ATMS.entity.QuotaEntity;
import ATMS.entity.TicketEntity;
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
    private TicketSessionBean ticketSessionBean;   
    private TicketEntity ticket;
    private QuotaEntity quota;
    
    @PostConstruct
    public void init()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    public TicketEntity getTicket(){
        return ticket;
    }
    
    public void setTicket(TicketEntity ticket){
        this.ticket=ticket;
    }

    /**
     * Creates a new instance of addTicketManagedBean
     */
    public addTicketManagedBean() {
        ticket=new TicketEntity();
    }
    
    public void saveNewTicket(ActionEvent event) throws IOException{
        try{
            System.out.println("into addTicketManagedBean: saveNewTicket");
            quota=ticket.getQuota();
            quota.setRestQuota(quota.getMaxQuota());
            ticket.setQuota(quota);
            ticketSessionBean.addTicket(ticket);
            System.out.println("new ticket added");
        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding a new ticket", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Ticket Saved.", ""));
        ticket=new TicketEntity();
    }
    
    public void oneMore(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addTicket.xhtml");
    }
    
   
}
