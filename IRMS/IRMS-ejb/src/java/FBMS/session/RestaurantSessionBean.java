
package FBMS.session;

import FBMS.entity.RestaurantEntity;
import Exception.ExistException;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jieqiong
 */

@Stateless
@LocalBean
public class RestaurantSessionBean implements RestaurantSessionBeanRemote{
    
    
    private RestaurantEntity restaurant=new RestaurantEntity();
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    public RestaurantSessionBean (){
    }
    
    /*E.4.2.1 Create restaurant*/
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public RestaurantEntity addRestaurant(RestaurantEntity restaurant) {
        em.persist(restaurant);
        return restaurant;
    }
    
    /*E.4.2.2 Update restaurant information*/
    @TransactionAttribute(TransactionAttributeType.REQUIRED)   
    public RestaurantEntity updateRestaurant(Long restId,String restNeighbourhood,
            String restTypeOfPlace, String restCuisine,String restName, Integer restQuota) throws ExistException{
        
        restaurant=em.find(RestaurantEntity.class, restId);
        if(restaurant==null) throw new ExistException ("Restaurant doesn't exist!");
        
        //Since restId is autogenerated, it cannot be changed.
        restaurant.setRestNeighbourhood(restNeighbourhood);
        restaurant.setRestTypeOfPlace(restTypeOfPlace);
        restaurant.setRestCuisine(restCuisine);
        restaurant.setRestName(restName);
        restaurant.setRestQuota(restQuota);
        
        em.merge(restaurant);
        return restaurant;
    }
    
    @Override
    public boolean updateRestaurant(RestaurantEntity restaurant)
    {
        System.out.println("restaurant quota: "+restaurant.getRestQuota());
        em.merge(restaurant);
        em.flush();
        System.out.println("RestaurantSessionBean: rest " + restaurant.getRestId() + " is successfully updated");
        return true;
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public List<RestaurantEntity> getAllRestaurants() throws NoResultException{
        Query query = em.createQuery("SELECT r FROM RestaurantEntity r");
        return query.getResultList();
        
        /*List<RestaurantEntity> restaurants=new ArrayList<RestaurantEntity>();
        restaurant=new RestaurantEntity();
        restaurant.setRestCuisine("Cuisine");
        restaurant.setRestNeighbourhood("Neighbourhood");
        restaurant.setRestTypeOfPlace("Type");
        restaurant.setRestName("Name");
        restaurant.setRestQuota(100);
        restaurants.add(restaurant);
        System.out.println(restaurant.getRestName());
        
        RestaurantEntity restaurant2=new RestaurantEntity();
        restaurant2.setRestCuisine("Cuisine2");
        restaurant2.setRestNeighbourhood("Neighbourhood2");
        restaurant2.setRestTypeOfPlace("Type2");
        restaurant2.setRestName("Name2");
        restaurant2.setRestQuota(200);
        restaurants.add(restaurant2);
        
        return restaurants;*/
        
    }
    
    @Override
    public RestaurantEntity getRestaurantById(Long restId) throws ExistException{
        restaurant = em.find(RestaurantEntity.class, restId);
        if(restaurant == null)  throw new ExistException("Restaurant does not exist!");
        return restaurant;
    }

    @Override
    public RestaurantEntity updateRestaurant(Long restId, String restNeighbourhood, String restTypeOfPlace, String restCuisine, String restName) throws ExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean removeRestaurant(Long restId)throws ExistException {
        System.out.println("into removeRestaurant");
        restaurant = em.find(RestaurantEntity.class, restId);
        if(restaurant == null) {
            throw new ExistException("Restaurant does not exist!");
        }
        em.remove(restaurant);
        return true;
    } 
    
    
    
}
