/*
 * record price of different room types
 */
package ACMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author liuxian
 */
@Entity
public class PriceEntity implements Serializable {
    @Id
    private String priceType;
    private double price;
 
    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (priceType != null ? priceType.hashCode() : 0);
        return hash;
    }

    @Override
   /* public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PriceEntity)) {
            return false;
        }
        PriceEntity other = (PriceEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }*/

    public String toString() {
        return "ACMS.entity.PriceEntity[ id=" + priceType + " ]";
    }
    
}
