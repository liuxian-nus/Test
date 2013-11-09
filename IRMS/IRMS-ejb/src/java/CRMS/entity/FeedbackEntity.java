/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author liuxian
 */
@Entity
@XmlRootElement
@XmlType(name="feedbackEntity")

public class FeedbackEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feedbackId;
    private String feedbackContent;
    @ManyToOne(cascade = {CascadeType.ALL})
    private MemberEntity feedbackOwner;
    private String feedbackOwnerEmail;
    private String feedbackStatus; //New, In Progress, Handled
    private String feedbackTitle;
    private String feedbackDepartment;
    private Integer rating;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date feedbackSentDate;

    public Date getFeedbackSentDate() {
        return feedbackSentDate;
    }

    public void setFeedbackSentDate(Date feedbackSentDate) {
        this.feedbackSentDate = feedbackSentDate;
    }
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    
    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public MemberEntity getFeedbackOwner() {
        return feedbackOwner;
    }

    public void setFeedbackOwner(MemberEntity feedbackOwner) {
        this.feedbackOwner = feedbackOwner;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackDepartment() {
        return feedbackDepartment;
    }

    public void setFeedbackDepartment(String feedbackDepartment) {
        this.feedbackDepartment = feedbackDepartment;
    }

    public String getFeedbackOwnerEmail() {
        return feedbackOwnerEmail;
    }

    public void setFeedbackOwnerEmail(String feedbackOwnerEmail) {
        this.feedbackOwnerEmail = feedbackOwnerEmail;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackId != null ? feedbackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the feedbackId fields are not set
        if (!(object instanceof FeedbackEntity)) {
            return false;
        }
        FeedbackEntity other = (FeedbackEntity) object;
        if ((this.feedbackId == null && other.feedbackId != null) || (this.feedbackId != null && !this.feedbackId.equals(other.feedbackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRMS.entity.FeedbackEntity[ feedbackId=" + feedbackId + " ]";
    }
    
}
