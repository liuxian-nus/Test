/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.managedBean;

import ATMS.entity.AttrComboEntity;
import ATMS.entity.AttrTicketEntity;
import ATMS.session.AttrComboSessionBean;
import ATMS.session.TicketSessionBean;
import Exception.ExistException;
import java.util.ArrayList;
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
public class manageComboManagedBean {
    @EJB
    private AttrComboSessionBean attrComboSessionBean;
    @EJB
    private TicketSessionBean ticketSessionBean;
    private AttrComboEntity combo;
    private Long comboId;
    private AttrTicketEntity ticket;
    private List<AttrTicketEntity> tickets=new ArrayList<AttrTicketEntity>();
    private boolean editMode;
    

    /**
     * Creates a new instance of manageComboManagedBean
     */
    public manageComboManagedBean() {
        combo=new AttrComboEntity();
    }
    
    public void saveChanges(ActionEvent event) throws ExistException
    {
        System.out.println("into ManageComboManagedBean");
        attrComboSessionBean.updateAttrCombo(getCombo());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));        
    }
    
    public void deleteCombo(ActionEvent event) throws ExistException {
        setComboId((Long)event.getComponent().getAttributes().get("code1"));
   //     System.out.println(("ticketId ")+getTicketId());
        attrComboSessionBean.removeAttrCombo(getComboId());
    }

    public AttrComboSessionBean getAttrComboSessionBean() {
        return attrComboSessionBean;
    }

    public void setAttrComboSessionBean(AttrComboSessionBean attrComboSessionBean) {
        this.attrComboSessionBean = attrComboSessionBean;
    }

    public TicketSessionBean getTicketSessionBean() {
        return ticketSessionBean;
    }

    public void setTicketSessionBean(TicketSessionBean ticketSessionBean) {
        this.ticketSessionBean = ticketSessionBean;
    }

    public AttrComboEntity getCombo() {
        return combo;
    }

    public void setCombo(AttrComboEntity combo) {
        this.combo = combo;
    }

    public AttrTicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(AttrTicketEntity ticket) {
        this.ticket = ticket;
    }

    public List<AttrTicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<AttrTicketEntity> tickets) {
        this.tickets = tickets;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Long getComboId() {
        return comboId;
    }

    public void setComboId(Long comboId) {
        this.comboId = comboId;
    }
    
    
    
    
}
