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
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author Jieqiong
 */
@Entity
@TableGenerator(name="seqTicket", initialValue = 1)
public class AttrTicketEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqTicket")
    private Long attrTicketId;
    @OneToOne(mappedBy="attrTicket")
    private AttractionEntity attr;
    private String attrTicketName;
    private double attrTicketPrice;
    private String attrTicketType; //one day, two day, annual
    private String attrTicketCluster; //adult, child, senior(over 60)

    
    public Long getAttrTicketId() {
        return attrTicketId;
    }

    public void setAttrTicketId(Long attrTicketId) {
        this.attrTicketId = attrTicketId;
    }

    public AttractionEntity getAttr() {
        return attr;
    }

    public void setAttr(AttractionEntity attr) {
        this.attr = attr;
    }

    public String getAttrTicketName() {
        return attrTicketName;
    }

    public void setAttrTicketName(String attrTicketName) {
        this.attrTicketName = attrTicketName;
    }

    public double getAttrTicketPrice() {
        return attrTicketPrice;
    }

    public void setAttrTicketPrice(double attrTicketPrice) {
        this.attrTicketPrice = attrTicketPrice;
    }

    public String getAttrTicketType() {
        return attrTicketType;
    }

    public void setAttrTicketType(String attrTicketType) {
        this.attrTicketType = attrTicketType;
    }

    public String getAttrTicketCluster() {
        return attrTicketCluster;
    }

    public void setAttrTicketCluster(String attrTicketCluster) {
        this.attrTicketCluster = attrTicketCluster;
    }
    
    

}
