/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.managedBean;

import ESMS.entity.ShowEntity;
import ESMS.session.ShowSessionBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ListShowsManagedBean {

    @EJB
    ShowSessionBean showSessionBean;
    private List<ShowEntity> showList = new ArrayList<ShowEntity>();
    private List<ShowEntity> shows = new ArrayList<ShowEntity>();

    public ListShowsManagedBean() {
    }

    @PostConstruct
    public void initViewList() {
      showList = (List<ShowEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("showList");

//        Collections.copy(shows, showList);
    }

    public void onRowToggle(ToggleEvent event) {
        System.err.println(event.getComponent());
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Show schedule");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ShowSessionBean getShowSessionBean() {
        return showSessionBean;
    }

    public void setShowSessionBean(ShowSessionBean showSessionBean) {
        this.showSessionBean = showSessionBean;
    }

    public List<ShowEntity> getShowList() {
        
        
        return showList;
    }

    public void setShowList(List<ShowEntity> showList) {
        this.showList = showList;
    }

    public List<ShowEntity> getShows() {
        return shows;
    }

    public void setShows(List<ShowEntity> shows) {
        this.shows = shows;
    }
}
