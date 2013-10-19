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
@IdClass(RoomPriceKey.class)
public class RoomPriceEntity implements Serializable {

    @Id
    private int hotelId;
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

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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
        if ((this.hotelId == 0 && other.hotelId != 0) || (this.hotelId != 0 && this.hotelId!=other.hotelId)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "ACMS.entity.PriceEntity[ id=" + priceType + " ]";
    }
}
