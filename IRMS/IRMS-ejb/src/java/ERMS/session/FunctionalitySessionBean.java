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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author liuxian
 */
@Stateless
@LocalBean
public class FunctionalitySessionBean {

    @PersistenceContext
    private EntityManager em;
    
    public FunctionalityEntity getFunctionality(Long functionalityId) throws ExistException
    {
        FunctionalityEntity functionality = em.find(FunctionalityEntity.class, functionalityId);
         if(functionality==null) throw new ExistException ("FunctionalitySessionBean-->ExistException-->Function doesn't exist!");
        return functionality;
    }
       public FunctionalityEntity getFunctionalityByName(String funcName)
    {   

        Query query = em.createQuery("SELECT u FROM FunctionalityEntity u WHERE u.funcName = :inUserFunctionalityName");
        query.setParameter("inUserFunctionalityName", funcName);

        FunctionalityEntity functionality = null;
        try{
            functionality = (FunctionalityEntity)query.getSingleResult();
        }
        catch(NoResultException ex){
            ex.printStackTrace();
        }
        return functionality;
    }
    
    public void addFunctionality(FunctionalityEntity functionality)
    {
        em.persist(functionality);
    }
    public void updateFunctionality(FunctionalityEntity functionality)
    {
        em.merge(functionality);
    }
    

    public void removeFunctionality(Long FunctionalityId)
    {
        FunctionalityEntity functionality = em.find(FunctionalityEntity.class, FunctionalityId);
        em.remove(functionality);
    }
    
    public List<FunctionalityEntity> getAllFunctionalities(){
        Query query = em.createQuery("SELECT s1 FROM FunctionalityEntity s1");
        return query.getResultList();
    }
    
}
