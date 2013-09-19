/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Diana Wang
 */
@Entity
public class RestaurantEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long restId;
    private String restNeighbourhood; 
    private String restTypeOfPlace;
    private String restCuisine;
    private String restName;
    private Integer restQuota;
    
    public RestaurantEntity(){
    }

    public Integer getRestQuota() {
        return restQuota;
    }

    public void setRestQuota(Integer restQuota) {
        this.restQuota = restQuota;
    }

    
    public Long getRestId() {
        return restId;
    }

    public void setRestId(Long restId) {
        this.restId = restId;
    }

    public String getRestNeighbourhood() {
        return restNeighbourhood;
    }

    public void setRestNeighbourhood(String restNeighbourhood) {
        this.restNeighbourhood = restNeighbourhood;
    }

    public String getRestTypeOfPlace() {
        return restTypeOfPlace;
    }

    public void setRestTypeOfPlace(String restTypeOfPlace) {
        this.restTypeOfPlace = restTypeOfPlace;
    }

    public String getRestCuisine() {
        return restCuisine;
    }

    public void setRestCuisine(String restCuisine) {
        this.restCuisine = restCuisine;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }
    

    

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restId != null ? restId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestaurantEntity)) {
            return false;
        }
        RestaurantEntity other = (RestaurantEntity) object;
        if ((this.restId == null && other.restId != null) || (this.restId != null && !this.restId.equals(other.restId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FBMS.entity.RestaurantEntity[ id=" + restId + " ]";
    }
    
}
