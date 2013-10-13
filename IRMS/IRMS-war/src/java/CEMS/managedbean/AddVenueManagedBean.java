/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.managedbean;

import CEMS.entity.VenueEntity;
import CEMS.session.VenueSessionBean;
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
public class AddVenueManagedBean {
    
    @EJB
    VenueSessionBean venueSessionBean;
    
    private VenueEntity venue;
    
    //Constructor
    public AddVenueManagedBean(){
        venue = new VenueEntity();
    }
    
    //Edit methods
    public void saveNewVenue(ActionEvent event) throws IOException {
        System.err.println("Saving new venue: " + venue.getVenueName());
        venueSessionBean.addVenue(venue);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New venue saved.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("addVenue.xhtml");
    }
    
    public void oneMore(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addVenue.xhtml");
    }
    
    //Getter and setter
    public VenueSessionBean getVenueSessionBean() {
        return venueSessionBean;
    }

    public void setVenueSessionBean(VenueSessionBean venueSessionBean) {
        this.venueSessionBean = venueSessionBean;
    }

    public VenueEntity getVenue() {
        return venue;
    }

    public void setVenue(VenueEntity venue) {
        this.venue = venue;
    }
}
