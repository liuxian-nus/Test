/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.RoomServiceEntity;
import Exception.ExistException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author liudazhi
 */
@Stateless
public class RoomServiceSessionBean {

    @PersistenceContext
    private EntityManager em;
    RoomServiceEntity rmService = new RoomServiceEntity();
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public RoomServiceSessionBean(){
        
    }
    
    public void addRoomService(RoomServiceEntity newRoomService){
        em.persist(newRoomService);
    }
    
    public boolean removeRoomService(String roomServiceName) throws ExistException{
        rmService = em.find(RoomServiceEntity.class, roomServiceName);
        if(rmService == null) throw new ExistException("This room service doesn't exist");
        em.remove(rmService);
        return true;
    }
    
    public RoomServiceEntity getServiceByName(String roomServiceName) throws ExistException{
        rmService = em.find(RoomServiceEntity.class, roomServiceName);
        if(rmService == null) throw new ExistException("This room service doesn't exist");
        return rmService;
    }

}
