/*
 * Model!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Primeface Showcase: Data table -- Expandable Rows
 */
package ACMS.managedbean;

import ACMS.entity.RoomEntity;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author liuxian
 */
@ManagedBean
@RequestScoped
public class RoomManagedBean {

    private List<RoomEntity> roomList;
    
    public RoomManagedBean() {
        roomList = new ArrayList<RoomEntity> (); 
    }
    
     public void initView(PhaseEvent event) {
        roomList = (List<RoomEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("AllRooms");
    }
    
    public List<RoomEntity> getRooms() {
        return roomList;
    }
    
    public void onRowToggle(ToggleEvent event) {  
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,  
                                            "Row State " + event.getVisibility(),  
                                            "Model:" + ((RoomEntity) event.getData()).getRoomId());  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
}
