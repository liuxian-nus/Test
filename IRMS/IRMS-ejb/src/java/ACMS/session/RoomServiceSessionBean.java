/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.RoomServiceEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    public boolean updateRoomService(String roomServiceName,String category, double roomServicePrice) throws ExistException {
        rmService = em.find(RoomServiceEntity.class, roomServiceName);
        if(rmService == null) throw new ExistException("This room service doesn't exist");
        rmService.setCategory(category);
        rmService.setRoomServicePrice(roomServicePrice);
        em.merge(rmService);
        return true;
    }
    
    public RoomServiceEntity getServiceByName(String roomServiceName) throws ExistException{
        rmService = em.find(RoomServiceEntity.class, roomServiceName);
        if(rmService == null) throw new ExistException("This room service doesn't exist");
        return rmService;
    }

    public List<RoomServiceEntity> getAllRoomServices() throws ExistException {
       System.err.println("in gell all room services session bean");
       Query q = em.createQuery("SELECT rs FROM RoomServiceEntity rs");
       List serviceList = new ArrayList<RoomServiceEntity> ();
       for (Object o : q.getResultList()) {
            RoomServiceEntity rs = (RoomServiceEntity) o;
            serviceList.add(rs);
        }
       if (serviceList == null) {
           throw new ExistException("RoomServiceEntity database is empty!");
       }
       return serviceList;
    }

}
