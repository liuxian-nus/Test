/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.RestaurantEntity;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class IndReservationSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    RestaurantEntity restaurant;
    
    public IndReservationSessionBean(){}
    
    public RestaurantEntity createRestaurantEntity(String restNeighbourhood, String restTypeOfPlace,String restCuisine,String keyword ){
        RestaurantEntity r = new RestaurantEntity();
            r.setRestCuisine(restCuisine);
            r.setRestTypeOfPlace(restTypeOfPlace);
            r.setRestNeighbourhood(restNeighbourhood);
            r.setRestName(keyword);
        return r;
    }
    
    public Set<RestaurantEntity> searchRestaurant(RestaurantEntity r){
        String restNeighbourhood = r.getRestNeighbourhood();
        String restCuisine = r.getRestCuisine();
        String restTypeOfPlace = r.getRestTypeOfPlace();
        String restKeyword = r.getRestName();
        Set stateSet = new HashSet <RestaurantEntity>();
        
       if(restNeighbourhood != null){
           Query q = em.createQuery("SELECT r FROM RestaurantEntity r WHERE r.restNeighbourhood = :restNeighbourhood");
         
           for (Object o :q.getResultList())
           {
               RestaurantEntity re = (RestaurantEntity)o;
               stateSet.add(re);
           }      
           }
        if(restCuisine != null){
            
            
        }
        
     return stateSet;
    }
    public void persist(Object object) {
        em.persist(object);
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
