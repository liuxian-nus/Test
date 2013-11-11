/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.entity;

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
public class RFMModelEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double Recency;
    private double Frequency;
    private double Monetary;

    public double getRecency() {
        return Recency;
    }

    public void setRecency(double Recency) {
        this.Recency = Recency;
    }

    public double getFrequency() {
        return Frequency;
    }

    public void setFrequency(double Frequency) {
        this.Frequency = Frequency;
    }

    public double getMonetary() {
        return Monetary;
    }

    public void setMonetary(double Monetary) {
        this.Monetary = Monetary;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
}
