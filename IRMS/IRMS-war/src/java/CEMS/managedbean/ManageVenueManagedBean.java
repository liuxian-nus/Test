/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.managedbean;

import CEMS.entity.VenueEntity;
import CEMS.session.VenueSessionBean;
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
public class ManageVenueManagedBean {
    @EJB
    VenueSessionBean venueSessionBean;
    private VenueEntity selectedVenue;
    private boolean editMode;
    private Long id;
    
    //Constructor
    public ManageVenueManagedBean(){
        selectedVenue = new VenueEntity();
    }

    //Methods
    public List<VenueEntity> getAllVenues()throws ExistException{
        return venueSessionBean.getAllVenues();
    }
    
    public void saveChanges(ActionEvent event) {
        venueSessionBean.updateVenue(selectedVenue);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));
    }
    
    public void deleteVenue(ActionEvent event) {
        setId((Long) event.getComponent().getAttributes().get("code1"));
        venueSessionBean.deleteVenue(getId());
    }
    
    //Getter and Setter
    public VenueSessionBean getVenueSessionBean() {
        return venueSessionBean;
    }

    public void setVenueSessionBean(VenueSessionBean venueSessionBean) {
        this.venueSessionBean = venueSessionBean;
    }

    public VenueEntity getSelectedVenue() {
        return selectedVenue;
    }

    public void setSelectedVenue(VenueEntity selectedVenue) {
        this.selectedVenue = selectedVenue;
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
