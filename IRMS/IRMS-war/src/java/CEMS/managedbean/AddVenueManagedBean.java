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
public class AddVenueManagedBean {

    @EJB
    VenueSessionBean venueSessionBean;
    @EJB
    VenueFunctionSessionBean venueFunctionSessionBean;
    private VenueEntity venue;
    private List<String> selectedFunc;

    //Constructor
    public AddVenueManagedBean() {
        venue = new VenueEntity();
    }

    //Methods
    public void saveNewVenue(ActionEvent event) throws IOException, ExistException {
        System.err.println("Saving new venue: " + venue.getVenueName());
        pushToFunc();
        venueSessionBean.addVenue(venue);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New venue saved.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("addVenue.xhtml");
    }

    public void oneMore(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addVenue.xhtml");
    }

    private void pushToFunc() throws ExistException {
        int i = 0;
        Long id;
        System.out.println("pushToFunc...");
        id = Long.valueOf(selectedFunc.get(i));
        venue.getVenueFunction().add(getVenueFunctionSessionBean().getVenueFunctionById(id));
        System.out.println(selectedFunc.get(i));

        while (i < (selectedFunc.size() - 1)) {
            i++;
            id = Long.valueOf(selectedFunc.get(i));
            venue.getVenueFunction().add(getVenueFunctionSessionBean().getVenueFunctionById(id));
            System.out.println(selectedFunc.get(i));
        }
    }

    //Getter and setter
    public List<String> getSelectedFunc() {
        return selectedFunc;
    }

    public void setSelectedFunc(List<String> selectedFunc) {
        this.selectedFunc = selectedFunc;
    }

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

    public VenueFunctionSessionBean getVenueFunctionSessionBean() {
        return venueFunctionSessionBean;
    }

    public void setVenueFunctionSessionBean(VenueFunctionSessionBean venueFunctionSessionBean) {
        this.venueFunctionSessionBean = venueFunctionSessionBean;
    }
}
