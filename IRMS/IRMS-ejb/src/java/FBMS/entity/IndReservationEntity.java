/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.entity;

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
 * @author Diana Wang
 */
@Entity
public class IndReservationEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long indReservationId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date indReservationDateTime;
    @ManyToOne(cascade ={CascadeType.ALL})
    private RestaurantEntity restaurant;
    private Integer numberPeople;
    private String title;
    private String name;
    private String email;
    private String mobile;
    private String notes;
    private String status;
    

    public IndReservationEntity(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public Long getIndReservationId() {
        return indReservationId;
    }

    public void setIndReservationId(Long indReservationId) {
        this.indReservationId = indReservationId;
    }

    public Date getIndReservationDateTime() {
        return indReservationDateTime;
    }

    public void setIndReservationDateTime(Date indReservationDateTime) {
        this.indReservationDateTime = indReservationDateTime;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(Integer numberPeople) {
        this.numberPeople = numberPeople;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
    public Long getId() {
        return indReservationId;
    }

    public void setId(Long id) {
        this.indReservationId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indReservationId != null ? indReservationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndReservationEntity)) {
            return false;
        }
        IndReservationEntity other = (IndReservationEntity) object;
        if ((this.indReservationId == null && other.indReservationId != null) || (this.indReservationId != null && !this.indReservationId.equals(other.indReservationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FBMS.entity.ReservationEntity[ id=" + indReservationId + " ]";
    }
    
}
