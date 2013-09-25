/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ERMS.entity.FunctionalityEntity;
import ERMS.entity.RoleEntity;
import Exception.ExistException;
import java.util.Iterator;
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
public class RoleSessionBean {

     @PersistenceContext
    private EntityManager em;
    
    
    public RoleEntity getRoleById(int roleId) throws ExistException {
        RoleEntity role = em.find(RoleEntity.class, roleId);
        if(role == null) throw new ExistException("This role does not exist!");
       
        return role;
    }
    
    public RoleEntity getRoleByName(String name){
        Query query = em.createQuery("SELECT u FROM RoleEntity u WHERE u.roleName = '" + name +"'");
        query.setParameter("roleName",name);
        
        RoleEntity role = null;
        try{
            role = (RoleEntity)query.getSingleResult();
        }
        catch(NoResultException ex){
            ex.printStackTrace();
        }
        
        return role;
    }
    
    public void addRole(RoleEntity role)
    {
        
        System.err.println("roleId: "+role.getRoleId()+";   roleName:"+role.getRoleName());
        List <FunctionalityEntity> al = role.getFunctionalities();
        Iterator <FunctionalityEntity>itr = al.iterator();
        while(itr.hasNext())
        {
            System.out.println("current element is "+itr.next());
        }
        em.persist(role);
        em.flush();
        System.err.println("Role persisted");
    }
    public void updateRole(RoleEntity role)
    {
        em.merge(role);
    }
    
    public void removeRole(int RoleId)
    {
        RoleEntity role = em.find(RoleEntity.class, RoleId);
        em.remove(role);
    }
    
    public List<RoleEntity> getAllRoles(){
        Query query = em.createQuery("SELECT s1 FROM RoleEntity s1");
        return query.getResultList();
    }

}
