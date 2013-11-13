/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author xinyu
 */
@Entity
public class ItemTransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;
    private double total;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date itemtransactiondate;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private SMItemEntity item = new SMItemEntity();

    public Date getDate() {
        return itemtransactiondate;
    }

    public void setDate(Date date) {
        this.itemtransactiondate = date;
    }

    public SMItemEntity getItem() {
        return item;
    }

    public void setItem(SMItemEntity item) {
        this.item = item;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemTransactionEntity)) {
            return false;
        }
        ItemTransactionEntity other = (ItemTransactionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SMMS.entity.ItemTransactionEntity[ id=" + id + " ]";
    }
}
