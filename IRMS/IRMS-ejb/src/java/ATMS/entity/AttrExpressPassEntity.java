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
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author Jieqiong
 */
@Entity
//@TableGenerator(name="seqEP", initialValue = 100)
public class AttrExpressPassEntity implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqEP")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long attrEPId;
    private String attrEPName;
    @ManyToOne
    private AttractionEntity attr;
    private double attrEPPrice;
    private String attrEPType; //One day, two day, annual
    private String attrEPCluster;

    public Long getAttrEPId() {
        return attrEPId;
    }

    public void setAttrEPId(Long attrEPId) {
        this.attrEPId = attrEPId;
    }

    public String getAttrEPName() {
        return attrEPName;
    }

    public void setAttrEPName(String attrEPName) {
        this.attrEPName = attrEPName;
    }

    public AttractionEntity getAttr() {
        return attr;
    }

    public void setAttr(AttractionEntity attr) {
        this.attr = attr;
    }

    public double getAttrEPPrice() {
        return attrEPPrice;
    }

    public void setAttrEPPrice(double attrEPPrice) {
        this.attrEPPrice = attrEPPrice;
    }

    public String getAttrEPType() {
        return attrEPType;
    }

    public void setAttrEPType(String attrEPType) {
        this.attrEPType = attrEPType;
    }

    public String getAttrEPCluster() {
        return attrEPCluster;
    }

    public void setAttrEPCluster(String attrEPCluster) {
        this.attrEPCluster = attrEPCluster;
    }

    
    
}
