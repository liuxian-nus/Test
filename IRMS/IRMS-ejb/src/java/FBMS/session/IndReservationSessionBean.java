/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.RestaurantEntity;
import FBMS.entity.IndReservationEntity;
import java.util.Date;
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
    public boolean checkAvailability (RestaurantEntity restaurant, int numberPeople, Date date){
        
        Integer totalNum=0;
        Integer restQuota=restaurant.getRestQuota();
        
        Query q=em.createQuery("SELECT indRes FROM IndReservationEntity IndRes");
  
        System.out.println("FBMS,IndReservationSessionBean, checkAvailability, starts!");
        for(Object o:q.getResultList()){
            IndReservationEntity indReservation=(IndReservationEntity) o;
            if(indReservation.getRestaurant().equals(restaurant)){
                if(indReservation.getStatus().equals("Confirmed")&&indReservation.getIndReservationDateTime().equals(date)){
                    totalNum+=indReservation.getNumberPeople();
                }
                totalNum+=indReservation.getNumberPeople();
            }
        }
        
        totalNum+=numberPeople;
        System.out.println("FBMS,IndReservationSessionBean, checkAvailability, ends!");
        
        if(totalNum<=restQuota) return true;
        else return false;
    }
    
    
    public boolean makeReservation(Date indReservationDateTime, Long restId,Integer numberPeople, String title, String name, String email,String mobile, String notes ){
        System.out.println("IndReservationSessionBean: make reservation starts!");
            IndReservationEntity ire = new IndReservationEntity();
                ire.setIndReservationDateTime(indReservationDateTime);
                ire.setEmail(email);
                ire.setMobile(mobile);
                ire.setName(name);
                ire.setNotes(notes);
                ire.setTitle(title);
                ire.setNumberPeople(numberPeople);
                ire.setRestaurant(em.find(RestaurantEntity.class, restId));
              //  ire.setStatus("Confirmed");
            
                System.out.println("IndReservationSessionBean: reservation updated successfully!");
                
                em.persist(ire);
                System.out.println("IndReservationSessionBean: reservation is made successfully!");
                  
        return true;
    } 
    public boolean makeReservation (IndReservationEntity ire){
        em.persist(ire);
        System.out.println("IndReservationSessionBean: reservation has been made!");
        return true;
    }
    public boolean confirmReservation(IndReservationEntity ire){
        
        ire.setStatus("Confirmed");
        System.out.println("IndReservationSessionBean: Reservation has been confirmed successfully!");
        return true;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public IndReservationEntity viewReservation (Long indReservationId){

        IndReservationEntity ire = em.find(IndReservationEntity.class,indReservationId);
        System.out.println("IndReservationSessionBean: the reservation has been found!");
        return ire;
}
    
    public boolean modifyReservation (String status,Long restId, Date indReservationDateTime, Long indReservationId,Integer numberPeople, String title, String name, String email,String mobile, String notes){
        System.out.println("IndReservationSessionBean: reservation modification starts!");
        
        IndReservationEntity ire = em.find(IndReservationEntity.class,indReservationId);
        RestaurantEntity re = em.find(RestaurantEntity.class, restId);
        ire.setEmail(email);
        ire.setIndReservationDateTime(indReservationDateTime);
        ire.setMobile(mobile);
        ire.setName(name);
        ire.setNotes(notes);
        ire.setNumberPeople(numberPeople);
        ire.setRestaurant(re);
        ire.setTitle(title);
        ire.setStatus(status);
        
        System.out.println("IndReservationSessionBean: reservation modification successful!");
        return true;
    }
}
