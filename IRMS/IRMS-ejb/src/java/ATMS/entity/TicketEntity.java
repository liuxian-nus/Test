/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Jieqiong
 */
@Entity
public class TicketEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ticketName;
    private double ticketPrice;
    @OneToOne (cascade ={CascadeType.ALL})  
    private QuotaEntity quota=new QuotaEntity();
    
    
    public Long getTicketId() {
        return id;
    }

    public void setTicketId(Long id) {
        this.id = id;
    }
    
    public String getTicketName(){
        return ticketName;
    }
    
    public void setTicketName(String name){
        ticketName=name;
    }
    
    public double getTicketPrice(){
        return ticketPrice;
    }
    
    public void setTicketPrice(double price){
        ticketPrice=price;
    }

    public QuotaEntity getQuota() {
        return quota;
    }

    public void setQuota(QuotaEntity quota) {
        this.quota = quota;
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
        if (!(object instanceof TicketEntity)) {
            return false;
        }
        TicketEntity other = (TicketEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ATMS.entity.TicketEntity[ id=" + id + " ]";
    }
    
}
