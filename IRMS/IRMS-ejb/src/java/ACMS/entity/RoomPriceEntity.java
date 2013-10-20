/*
 * record price of different room types
 */
package ACMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 *
 * @author liuxian
 */
@Entity
public class RoomPriceEntity implements Serializable {

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
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the reservationId fields are not set
        if (!(object instanceof RoomPriceEntity)) {
            return false;
        }
        RoomPriceEntity other = (RoomPriceEntity) object;
        if ((this.priceType == null && other.priceType != null) || (this.priceType != null && !(this.priceType).equals(other.priceType))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "ACMS.entity.PriceEntity[ id=" + priceType + " ]";
    }
}
