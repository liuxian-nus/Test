/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 *
 * @author Jieqiong
 */
@Entity
@TableGenerator(name="seqTicket", initialValue = 1)
public class TicketEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqTicket")
    private Long id;
    private String attrId;
    private String attrName;
    private String ticketName;
    private double ticketPrice;
    private String type; //one day, two day, annual
    private String cluster; //adult, child, senior(over 60)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }
    
    
    
    

    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString() {
        return "ATMS.entity.TicketEntity[ id=" + id + " ]";
    }
    
}
