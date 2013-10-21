/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author lionetdd
 */
@Entity
public class EventServiceEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceId;
    
    private String serviceName;
    private String serviceCategory;
    private Integer serviceQuantity;
    private Double serviceCost;
    
    public EventServiceEntity(){}
    
    public Long getId() {
        return serviceId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public Integer getServiceQuantity() {
        return serviceQuantity;
    }

    public void setServiceQuantity(Integer serviceQuantity) {
        this.serviceQuantity = serviceQuantity;
    }

    public Double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public void setId(Long id) {
        this.serviceId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceId != null ? serviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventServiceEntity)) {
            return false;
        }
        EventServiceEntity other = (EventServiceEntity) object;
        if ((this.serviceId == null && other.serviceId != null) || (this.serviceId != null && !this.serviceId.equals(other.serviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CEMS.entity.ServiceEntity[ id=" + serviceId + " ]";
    }
    
}
