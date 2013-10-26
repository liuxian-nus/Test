/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

/**
 *
 * @author Jieqiong
 */
@Entity
@TableGenerator(name="seqPerformance", initialValue = 1)
public class AttrPerformanceEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqPerformance")
    private Long attrPFId;
    private String attrPFName;
    private String attrPFDescription;
    @ManyToOne
    private AttractionEntity attr;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date attrPFStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date attrPFEndDate;
    private int attrPFNumberOfPFDaily;
    private String attrPFPeriod;

    public Long getAttrPFId() {
        return attrPFId;
    }

    public void setAttrPFId(Long attrPFId) {
        this.attrPFId = attrPFId;
    }

    public String getAttrPFName() {
        return attrPFName;
    }

    public void setAttrPFName(String attrPFName) {
        this.attrPFName = attrPFName;
    }

    public String getAttrPFDescription() {
        return attrPFDescription;
    }

    public void setAttrPFDescription(String attrPFDescription) {
        this.attrPFDescription = attrPFDescription;
    }

    public AttractionEntity getAttr() {
        return attr;
    }

    public void setAttr(AttractionEntity attr) {
        this.attr = attr;
    }

    public Date getAttrPFStartDate() {
        return attrPFStartDate;
    }

    public void setAttrPFStartDate(Date attrPFStartDate) {
        this.attrPFStartDate = attrPFStartDate;
    }

    public Date getAttrPFEndDate() {
        return attrPFEndDate;
    }

    public void setAttrPFEndDate(Date attrPFEndDate) {
        this.attrPFEndDate = attrPFEndDate;
    }

    public int getAttrPFNumberOfPFDaily() {
        return attrPFNumberOfPFDaily;
    }

    public void setAttrPFNumberOfPFDaily(int attrPFNumberOfPFDaily) {
        this.attrPFNumberOfPFDaily = attrPFNumberOfPFDaily;
    }

    public String getAttrPFPeriod() {
        return attrPFPeriod;
    }

    public void setAttrPFPeriod(String attrPFPeriod) {
        this.attrPFPeriod = attrPFPeriod;
    }

    
    
}
