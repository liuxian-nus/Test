/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.managedbean;

import CEMS.entity.VenueEntity;
import CEMS.entity.VenueFunctionEntity;
import CEMS.session.VenueFunctionSessionBean;
import CEMS.session.VenueSessionBean;
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
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ManageVenueManagedBean {

    @EJB
    VenueSessionBean venueSessionBean;
    @EJB
    VenueFunctionSessionBean venueFunctionSessionBean;
    private VenueEntity selectedVenue;
    private boolean editMode;
    private Long id;
    private List<String> selectedFunc = new ArrayList<String>();

    //Constructor
    public ManageVenueManagedBean() {
        selectedVenue = new VenueEntity();
    }

    //Methods
    public List<VenueEntity> getAllVenues() throws ExistException {
        return venueSessionBean.getAllVenues();
    }

    public void saveChanges(ActionEvent event) throws ExistException {
        selectedVenue.setVenueFunction(new ArrayList<VenueFunctionEntity>());
        pushToFunc(selectedVenue);
        venueSessionBean.updateVenue(selectedVenue);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));
    }

    public void pushToFunc(VenueEntity selectedVenue) throws ExistException {
        int i = 0;
        Long id;
        id = Long.valueOf(selectedFunc.get(i));
        selectedVenue.getVenueFunction().add(venueFunctionSessionBean.getVenueFunctionById(id));
//        System.out.println(selectedFunc.get(i));

        while (i < (selectedFunc.size() - 1)) {
            i++;
            id = Long.valueOf(selectedFunc.get(i));
            selectedVenue.getVenueFunction().add(venueFunctionSessionBean.getVenueFunctionById(id));
//            System.out.println(selectedFunc.get(i));
        }
    }

    public void deleteVenue(ActionEvent event) {
        setId((Long) event.getComponent().getAttributes().get("code1"));
        venueSessionBean.deleteVenue(getId());
    }

    public String toSentence(List<VenueFunctionEntity> func) {
        String output = null;
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i < func.size()) {
            sb.append(func.get(i).getFunctionName());
            sb.append(";");
//            System.out.println(func.get(i).getFunctionName());
            i++;
        }
        output = sb.toString();
        return output;
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

    public List<String> getSelectedFunc() {
        return selectedFunc;
    }

    public void setSelectedFunc(List<String> selectedFunc) {
        this.selectedFunc = selectedFunc;
    }

    public VenueFunctionSessionBean getVenueFunctionSessionBean() {
        return venueFunctionSessionBean;
    }

    public void setVenueFunctionSessionBean(VenueFunctionSessionBean venueFunctionSessionBean) {
        this.venueFunctionSessionBean = venueFunctionSessionBean;
    }
}
