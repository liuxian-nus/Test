/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.RoomEntity;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author liuxian
 */
@ManagedBean
@RequestScoped
public class RoomSearchResultManagementBean implements Serializable{

    private RoomEntity selectRoom;
    private List<RoomEntity> rooms;
    public RoomSearchResultManagementBean() {
    }
}
