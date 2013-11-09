/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.session;

import CEMS.entity.VenueFunctionEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ser3na
 */
@Stateless
public class VenueFunctionSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    VenueFunctionEntity venueFunction;

    public VenueFunctionSessionBean() {
    }
    
    public VenueFunctionEntity getVenueFunctionById(Long funcId) throws ExistException{
        VenueFunctionEntity func = em.find(VenueFunctionEntity.class, funcId);
         if(func==null) throw new ExistException ("EventFunctionSessionBean-->ExistException-->Function doesn't exist!");
        return func;
    }
    
    public void addVenueFunction(VenueFunctionEntity func){
        em.persist(func);
        em.flush();
    }
    public void updateVenueFunction(VenueFunctionEntity func){
        em.merge(func);
        em.flush();
    }

    public void removeVenueFunction(Long funcId)
    {
        VenueFunctionEntity func = em.find(VenueFunctionEntity.class, funcId);
        em.remove(func);
    }
    
    public List<VenueFunctionEntity> getAllVenueFunctions(){
        Query query = em.createQuery("SELECT s1 FROM VenueFunctionEntity s1");
        return query.getResultList();
    }
    
    public List<String> getAllVenueFunctionNames(){
        Query query = em.createQuery("SELECT s1 FROM VenueFunctionEntity s1");
        List<String> names = new ArrayList();
        System.err.println("Getting all venue function names...");
        String name;
        List<VenueFunctionEntity> list;
        list = query.getResultList();
        System.err.println("List size: "+list.size());
        Iterator<VenueFunctionEntity> itr = list.iterator();
        while (itr.hasNext()) {
            System.err.println("While looping...");
            venueFunction = itr.next();
            
            name = venueFunction.getFunctionName();
            System.err.println("name: "+name);
            names.add(name);
        }
        return names;
    }
}
