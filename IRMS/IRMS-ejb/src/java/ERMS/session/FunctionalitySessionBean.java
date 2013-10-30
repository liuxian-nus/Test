/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ERMS.entity.FunctionalityEntity;
import Exception.ExistException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author liuxian
 */
@Stateless
@LocalBean
public class FunctionalitySessionBean {

    @PersistenceContext(unitName="IRMS-ejbPU")
    private EntityManager em;
    
    public FunctionalityEntity getFunctionality(Long funcId) throws ExistException
    {
        FunctionalityEntity functionality = em.find(FunctionalityEntity.class, funcId);
        System.err.println("getFunctionality: "+functionality.getFuncId());
         if(functionality==null) throw new ExistException ("FunctionalitySessionBean-->ExistException-->Function doesn't exist!");
        return functionality;
    }
    
    public void addFunctionality(FunctionalityEntity functionality)
    {
        em.persist(functionality);
        em.flush();
    }
    public void updateFunctionality(FunctionalityEntity functionality)
    {
        em.merge(functionality);
        em.flush();
    }

    public void removeFunctionality(Long funcId)
    {
        FunctionalityEntity functionality = em.find(FunctionalityEntity.class, funcId);
        em.remove(functionality);
    }
    
    public List<FunctionalityEntity> getAllFunctionalities(){
        Query query = em.createQuery("SELECT s1 FROM FunctionalityEntity s1");
        return query.getResultList();
    }
    
    /*public FunctionalityEntity searchFunctionalityByName(String funcName){
        FunctionalityEntity thisFunc = em.find(FunctionalityEntity.class, funcName);
        return thisFunc;
    }*/
    
}
