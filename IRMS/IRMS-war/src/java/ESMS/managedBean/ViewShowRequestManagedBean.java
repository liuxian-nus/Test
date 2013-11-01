/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.managedBean;

import CEMS.entity.EventEntity;
import CEMS.session.EventSessionBean;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ViewShowRequestManagedBean {

    @EJB
    private EventSessionBean eventSessionBean;
    private EventEntity selectedEvent;
    private List<EventEntity> requests;
    private final static String[] eventStatus;
    private Long id;

    //Constructor
    public ViewShowRequestManagedBean() {
        selectedEvent = new EventEntity();
    }

    //Methods
    @PostConstruct
    public void init() {
        requests = eventSessionBean.getRequests();
//        System.err.println("init view show size: "+requests.size());
    }

    static {
        eventStatus = new String[3];
        eventStatus[0] = "Pending";
        eventStatus[1] = "Researved";
        eventStatus[2] = "Confirmed";
    }

    public List<EventEntity> getRequests() throws ExistException {
//        requests = eventSessionBean.getRequests();
//        System.err.println("requests size: "+requests.size());
        return requests;
    }

    public void deleteEvent(ActionEvent event) {
        setId((Long) event.getComponent().getAttributes().get("code1"));
//        System.err.println("id: " + getId());
        eventSessionBean.deleteEvent(getId());
    }

    public void onCellEdit(CellEditEvent event) {
        System.err.println("onCellEdit Now");
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        System.err.println("old: " + oldValue);
        System.err.println("new: " + newValue);
        System.err.println("Selected event: " + selectedEvent.getEventName());

        if (newValue != null && !newValue.equals(oldValue)) {
            selectedEvent.setStatus((String) newValue);
            System.err.println("event: " + selectedEvent.getEventName());
            eventSessionBean.updateEvent(selectedEvent);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Changed", "Previously: " + oldValue + ", Now:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    //Getters and Setters
    public EventSessionBean getEventSessionBean() {
        return eventSessionBean;
    }

    public void setEventSessionBean(EventSessionBean eventSessionBean) {
        this.eventSessionBean = eventSessionBean;
    }

    public EventEntity getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(EventEntity selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public String[] getEventStatus() {
        return eventStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRequests(List<EventEntity> requests) {
        this.requests = requests;
    }
}
