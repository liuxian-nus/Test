/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.managedbean;

import CEMS.entity.VenueEntity;
import CEMS.session.VenueFunctionSessionBean;
import CEMS.session.VenueSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class VenueBookingManagedBean implements Serializable {

    @EJB
    VenueSessionBean venueSessionBean;
    @EJB
    VenueFunctionSessionBean venueFunctionSessionBean;
    private VenueEntity selectedVenue;
    private List<VenueEntity> venues;
    private List<VenueEntity> filteredVenues;
    private SelectItem[] functionOptions;
    private List<String> functions;

    /**
     * Creates a new instance of VenueBookingManagedBean
     */
    public VenueBookingManagedBean() {
        venues = new ArrayList<VenueEntity>();
        functions = new ArrayList();
        selectedVenue = new VenueEntity();
    }

    @PostConstruct
    public void init() throws ExistException {
        venues = venueSessionBean.getAllVenues();
        functions = venueFunctionSessionBean.getAllVenueFunctionNames();
        functionOptions = createFilterOptions(functions);
    }

    private SelectItem[] createFilterOptions(List<String> functions) {
        SelectItem[] options = new SelectItem[functions.size() + 1];

        System.err.println("Creating filter options");
        options[0] = new SelectItem("", "Select");
        for (int i = 0; i < functions.size(); i++) {
            options[i + 1] = new SelectItem(functions.get(i), functions.get(i));
        }
        return options;
    }
    
    public void checkSchedule(ActionEvent event) throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("venue", selectedVenue);
        System.err.println("check schedule: "+selectedVenue.getVenueName());
        FacesContext.getCurrentInstance().getExternalContext().redirect("venueSchedule.xhtml");
    }

    public VenueSessionBean getVenueSessionBean() {
        return venueSessionBean;
    }

    public void setVenueSessionBean(VenueSessionBean venueSessionBean) {
        this.venueSessionBean = venueSessionBean;
    }

    public List<VenueEntity> getVenues() {
        return venues;
    }

    public void setVenues(List<VenueEntity> venues) {
        this.venues = venues;
    }

    public List<VenueEntity> getFilteredVenues() {
        return filteredVenues;
    }

    public void setFilteredVenues(List<VenueEntity> filteredVenues) {
        this.filteredVenues = filteredVenues;
    }

    public SelectItem[] getFunctionOptions() {
        return functionOptions;
    }

    public void setFunctionOptions(SelectItem[] functionOptions) {
        this.functionOptions = functionOptions;
    }

    public VenueFunctionSessionBean getVenueFunctionSessionBean() {
        return venueFunctionSessionBean;
    }

    public void setVenueFunctionSessionBean(VenueFunctionSessionBean venueFunctionSessionBean) {
        this.venueFunctionSessionBean = venueFunctionSessionBean;
    }

    public List<String> getFunctions() {
        return functions;
    }

    public void setFunctions(List<String> functions) {
        this.functions = functions;
    }

    public VenueEntity getSelectedVenue() {
        return selectedVenue;
    }

    public void setSelectedVenue(VenueEntity selectedVenue) {
        this.selectedVenue = selectedVenue;
    }
}
