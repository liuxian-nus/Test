/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ERMS.entity.RoleEntity;
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
    
    
    public RoleEntity getRole(Long RoleId)
    {
        Query query = em.createQuery("SELECT u FROM RoleEntity u WHERE u.id = :inRoleId");
        query.setParameter("inRoleId", RoleId);
        
        RoleEntity role = null;
        try{
            role = (RoleEntity)query.getSingleResult();
        }
        catch(NoResultException ex){
            ex.printStackTrace();
        }
        return role;
    }
    
    public RoleEntity getRoleByName(String name){
        Query query = em.createQuery("SELECT u FROM RoleEntity u WHERE u.roleName = :inRoleName");
        query.setParameter("inRoleName",name);
        
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
        em.persist(role);
    }
    public void updateRole(RoleEntity role)
    {
        em.merge(role);
    }
    
    public void removeRole(Long RoleId)
    {
        RoleEntity role = em.find(RoleEntity.class, RoleId);
        em.remove(role);
    }
    
    public List<RoleEntity> getAllRoles(){
        Query query = em.createQuery("SELECT s1 FROM RoleEntity s1");
        return query.getResultList();
    }

}
