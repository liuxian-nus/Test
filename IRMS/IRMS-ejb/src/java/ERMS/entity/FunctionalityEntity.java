/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author liuxian
 */
@Entity
@Table(
        uniqueConstraints=
            @UniqueConstraint(columnNames={"funcName"})
    )
public class FunctionalityEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long funcId;
    private String funcName;
    private String funcDescription;
    /*@ManyToOne(targetEntity=Role.class,cascade={CascadeType.ALL},fetch=FetchType.EAGER)
    private Role roleEntity;
    */
    
    
    
    public Long getFuncId(){
        return funcId;
    }
    
    public void setFuncId(Long funcId){
        this.funcId = funcId;
    }

    /**
     * @return the funcName
     */
    public String getFuncName() {
        return funcName;
    }

    /**
     * @param funcName the funcName to set
     */
    public void setFuncName(String funcName) {
        this.funcName = funcName;
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
