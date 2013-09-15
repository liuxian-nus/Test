/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.RestaurantEntity;
import FBMS.entity.IndReservationEntity;
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
            for(Object o : stateSet){
                RestaurantEntity re = (RestaurantEntity)o;
                if(!re.getRestCuisine().equalsIgnoreCase(restCuisine))
                    stateSet.remove(re);
            }
               
        }
        if(restTypeOfPlace != null){
            for (Object o :stateSet){
                RestaurantEntity re = (RestaurantEntity)o;
                if(!re.getRestTypeOfPlace().equalsIgnoreCase(restTypeOfPlace))
                    stateSet.remove(re);
            }
        }
        
        if(restKeyword!= null){
            for(Object o: stateSet){
                RestaurantEntity re = (RestaurantEntity)o;
                if(!re.getRestName().contains(restKeyword))
                    stateSet.remove(re);
            }
    }
        
        
     return stateSet;
    }
    public void persist(Object object) {
        em.persist(object);
    }
    
    /*E.1.1.2 View restaurant details*/
    public Set<RestaurantEntity> viewRestaurantDetails(Long restId){
        Query q = em.createQuery("SELECT r FROM RestaurantEntity r");
        Set stateSet = new HashSet<RestaurantEntity>();
        for (Object o: q.getResultList()) {
            RestaurantEntity r = (RestaurantEntity) o;
            if(r.getRestId()==restId){
                stateSet.add(r);
                break;
            }
        }
        return stateSet;
    }
    
    /*E.1.1.3 Check Availability*/
    public boolean checkAvailability (IndReservationEntity indRes){
        Query q=em.createQuery("SELECT indRes FROM IndReservationEntity IndRes");
        int 
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
