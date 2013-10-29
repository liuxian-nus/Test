/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.managedbean;

import CEMS.entity.EventEntity;
import CEMS.session.EventSessionBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class AddEventManagedBean {
    
    @EJB
    EventSessionBean eventSessionBean;
    
    private EventEntity eventEntity;

    public AddEventManagedBean() {
        eventEntity = new EventEntity();
    }
    
    public void saveNew(ActionEvent event) throws IOException {
        System.err.println("Saving New Event...");
        eventSessionBean.addEvent(eventEntity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New event saved.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("addEvent.xhtml");
    }
    
    public void redirect(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addEvent.xhtml");
    }

    public EventSessionBean getEventSessionBean() {
        return eventSessionBean;
    }

    public void setEventSessionBean(EventSessionBean eventSessionBean) {
        this.eventSessionBean = eventSessionBean;
    }

    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }
}
