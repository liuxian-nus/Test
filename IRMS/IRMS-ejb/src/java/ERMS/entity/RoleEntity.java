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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String roleName;
    
    @OneToMany(targetEntity = FunctionalityEntity.class, cascade = {CascadeType.MERGE})
    private List<FunctionalityEntity> functionalities = new ArrayList<FunctionalityEntity>();

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleEntity)) {
            return false;
        }
        RoleEntity other = (RoleEntity) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ERMS.entity.RoleEntity[ id=" + roleId + " ]";
    }
    
}
