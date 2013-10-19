/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.entity;

import ERMS.entity.EmployeeEntity;
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
 * @author liuxian
 */
@Entity
public class LogBookEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logBookId;
    private String logTitle;
    private String logText;
    private String remark;
    private int logShift;
    private boolean resolved;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date publishDate;
    @ManyToOne(cascade={CascadeType.MERGE})
    private EmployeeEntity logEmployee;

    public LogBookEntity(){}
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logBookId != null ? logBookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogBookEntity)) {
            return false;
        }
        LogBookEntity other = (LogBookEntity) object;
        if ((this.logBookId == null && other.logBookId != null) || (this.logBookId != null && !this.logBookId.equals(other.logBookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ACMS.entity.LogBookEntity[ id=" + logBookId + " ]";
    }

    public Long getLogBookId() {
        return logBookId;
    }

    public void setLogBookId(Long logBookId) {
        this.logBookId = logBookId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public EmployeeEntity getEmployee() {
        return logEmployee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.logEmployee = employee;
    }

    public String getLogTitle() {
        return logTitle;
    }

    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }

    public EmployeeEntity getLogEmployee() {
        return logEmployee;
    }

    public void setLogEmployee(EmployeeEntity logEmployee) {
        this.logEmployee = logEmployee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    public int getLogShift() {
        return logShift;
    }

    public void setLogShift(int logShift) {
        this.logShift = logShift;
    }

    public boolean getResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
    
}
