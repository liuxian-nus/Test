/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author liuxian
 */
@Entity
public class VIPEntity extends MemberEntity implements Serializable {
    private static final long serialVersionUID = 1L;

     private long VIPId;
     private String VIPPreference;

     public VIPEntity(){}
     
     public void setVIPId(long VIPId) {
        this.VIPId = VIPId;
    }
     
    
    
    public void create (String preference)
    {
        this.setVIPPreference(preference);
        this.setVIPId(System.nanoTime());
        System.out.println("VIPEntity: a new VIPEntity has been generated!");
    }

    public String getVIPPreference() {
        return VIPPreference;
    }

    public void setVIPPreference(String VIPPreference) {
        this.VIPPreference = VIPPreference;
    }

  
     

    /**
     * Get the value of VIPId
     *
     * @return the value of VIPId
     */
    public long getVIPId() {
        return VIPId;
    }
 
    
}
