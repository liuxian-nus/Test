/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author Diana Wang
 */
@Entity
@TableGenerator(name="seq", initialValue = 101)
public class DishEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    private Long dishId;
    private String dishName;
    private Integer dishQuantity;
    private Double dishCost;
   /* @OneToOne (cascade={CascadeType.ALL},mappedBy="dish")
    private CourseEntity course;*/
    
    public DishEntity(){}

    /*public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }*/

   
    
    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Integer getDishQuantity() {
        return dishQuantity;
    }

    public void setDishQuantity(Integer dishQuantity) {
        this.dishQuantity = dishQuantity;
    }

    public Double getDishCost() {
        return dishCost;
    }

    public void setDishCost(Double dishCost) {
        this.dishCost = dishCost;
    }
    
    
    
    

    public Long getId() {
        return dishId;
    }

    public void setId(Long id) {
        this.dishId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dishId != null ? dishId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DishEntity)) {
            return false;
        }
        DishEntity other = (DishEntity) object;
        if ((this.dishId == null && other.dishId != null) || (this.dishId != null && !this.dishId.equals(other.dishId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FBMS.entity.DishEntity[ id=" + dishId + " ]";
    }
    
}
