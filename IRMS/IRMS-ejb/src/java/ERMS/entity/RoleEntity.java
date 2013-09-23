/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author liuxian
 */
@Entity
public class RoleEntity implements Serializable {
     private static final long serialVersionUID = 1L;
    @Id
    private int roleId;
    private String roleName;
    
    @OneToMany(targetEntity = FunctionalityEntity.class, cascade = {CascadeType.ALL})
    private List<FunctionalityEntity> functionalities = new ArrayList<FunctionalityEntity>();

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
     // @return the RoleName
    public String getRoleName() {
        return roleName; 
    }


     //@param RoleName the RoleName to set
    public void setRoleName(String RoleName) {
        this.roleName = RoleName;
    }

     //@return the functionalities
    public List<FunctionalityEntity> getFunctionalities() {
        return functionalities;
    }


     // @param functionalities the functionalities to set

    public void setFunctionalities(List<FunctionalityEntity> functionalities) {
        this.functionalities = functionalities;
    }
    
    public void addFunctionality(FunctionalityEntity functionality) {
        this.functionalities.add(functionality);
    }

    @Override
    public String toString() {
        return "ERMS.entity.RoleEntity[ id=" + roleId + " ]";
    }
    
}
