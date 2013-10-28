/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 *
 * @author Jieqiong
 */
@Entity
@TableGenerator(name="comboTicket", initialValue = 1)
public class AttrComboEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "comboTicket")
    private Long attrComboId;
    private String attrComboName;
    @ManyToMany
    private List<AttractionEntity> attrs;
    @OneToMany
    private List<AttrTicketEntity> attrTickets;
    private double attrComboPrice;
    private String attrComboType; //one day, two day
    private String attrComboCluster; 

    public Long getAttrComboId() {
        return attrComboId;
    }

    public void setAttrComboId(Long attrComboId) {
        this.attrComboId = attrComboId;
    }

    public String getAttrComboName() {
        return attrComboName;
    }

    public void setAttrComboName(String attrComboName) {
        this.attrComboName = attrComboName;
    }

    public List<AttractionEntity> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttractionEntity> attrs) {
        this.attrs = attrs;
    }

    public List<AttrTicketEntity> getAttrTickets() {
        return attrTickets;
    }
    
    public void setAttrTickets(List<AttrTicketEntity> attrTickets) {
        this.attrTickets = attrTickets;
    }

    public double getAttrComboPrice() {
        return attrComboPrice;
    }

    public void setAttrComboPrice(double attrComboPrice) {
        this.attrComboPrice = attrComboPrice;
    }

    public String getAttrComboType() {
        return attrComboType;
    }

    public void setAttrComboType(String attrComboType) {
        this.attrComboType = attrComboType;
    }

    public String getAttrComboCluster() {
        return attrComboCluster;
    }

    public void setAttrComboCluster(String attrComboCluster) {
        this.attrComboCluster = attrComboCluster;
    }

    

    
    
}
