/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author liuxian
 */
@Entity
public class FunctionalityEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    private String funcName;
    private String funcDescription;
    /*@ManyToOne(targetEntity=Role.class,cascade={CascadeType.ALL},fetch=FetchType.EAGER)
    private Role roleEntity;
    */

    /**
     * @return the FuncName
     */
    public String getFuncName() {
        return funcName;
    }

    /**
     * @param FuncName the FuncName to set
     */
    public void setFuncName(String FuncName) {
        this.funcName = FuncName;
    }

    public String getFuncDescription() {
        return funcDescription;
    }

    public void setFuncDescription(String funcDescription) {
        this.funcDescription = funcDescription;
    }

    /*
     
    public Role getRoleEntity() {
        return roleEntity;
    }

     @param roleEntity the roleEntity to set
     
    public void setRoleEntity(Role roleEntity) {
        this.roleEntity = roleEntity;
    }*/

    /**
     * @return the funcId
     */
   

}
