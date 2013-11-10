/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.managedbean;

import CEMS.entity.EventServiceEntity;
import CEMS.session.EventServiceSessionBean;
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
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class EventServiceManageManagedBean {
    
    @EJB
    EventServiceSessionBean eventServiceSessionBean;
    private EventServiceEntity selectedEventService;
    private boolean editMode;
    private Long id;

    /**
     * Creates a new instance of EventServiceManageManagedBean
     */
    public EventServiceManageManagedBean() {
        selectedEventService = new EventServiceEntity();
    }
    
    public void saveChanges(ActionEvent event) throws ExistException {
        eventServiceSessionBean.updateService(selectedEventService);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));
    }
    
    public void delete(ActionEvent event) {
        setId((Long) event.getComponent().getAttributes().get("code1"));
        eventServiceSessionBean.deleteService(id);
    }
    
    public List<EventServiceEntity> getAllEventServices() throws ExistException {
        return eventServiceSessionBean.getAllEventServices();
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

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
