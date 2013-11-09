/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.managedbean;

import CEMS.entity.VenueEntity;
import CEMS.session.VenueSessionBean;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class VenueBookingManagedBean {

    @EJB
    VenueSessionBean venueSessionBean;
    private List<VenueEntity> venues;
    private List<VenueEntity> filteredVenues;

    /**
     * Creates a new instance of VenueBookingManagedBean
     */
    public VenueBookingManagedBean() {
        venues = new ArrayList<VenueEntity>();
    }

    @PostConstruct
    public void init() throws ExistException {
        venues = venueSessionBean.getAllVenues();
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
}
