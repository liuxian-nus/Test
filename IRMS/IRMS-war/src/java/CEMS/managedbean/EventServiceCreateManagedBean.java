/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.managedbean;

import CEMS.entity.EventServiceEntity;
import CEMS.session.EventServiceSessionBean;
import Exception.ExistException;
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
public class EventServiceCreateManagedBean {
    
    @EJB
    EventServiceSessionBean eventServiceSessionBean;
    EventServiceEntity selectedEventService;

    /**
     * Creates a new instance of EventServiceCreateManagedBean
     */
    public EventServiceCreateManagedBean() {
        selectedEventService = new EventServiceEntity();
    }
    
     public void saveNew(ActionEvent event) throws IOException, ExistException {
        eventServiceSessionBean.addEventService(selectedEventService);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New venue saved.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("eventServiceCreate.xhtml");
    }

    public EventServiceSessionBean getEventServiceSessionBean() {
        return eventServiceSessionBean;
    }

    public void setEventServiceSessionBean(EventServiceSessionBean eventServiceSessionBean) {
        this.eventServiceSessionBean = eventServiceSessionBean;
    }

    public EventServiceEntity getSelectedEventService() {
        return selectedEventService;
    }

    public void setSelectedEventService(EventServiceEntity selectedEventService) {
        this.selectedEventService = selectedEventService;
    }
}
