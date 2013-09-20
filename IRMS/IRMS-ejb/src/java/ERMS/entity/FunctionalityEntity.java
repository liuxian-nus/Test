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
@Table(
        uniqueConstraints=
            @UniqueConstraint(columnNames={"funcId"})
    )
public class FunctionalityEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long functionalityId;
    private String funcName;
    /*@ManyToOne(targetEntity=Role.class,cascade={CascadeType.ALL},fetch=FetchType.EAGER)
    private Role roleEntity;
    */
    public Long getFunctionalityId() {
        return functionalityId;
    }

    public void setFunctionalityId(Long functionalityId) {
        this.functionalityId = functionalityId;
    }

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
