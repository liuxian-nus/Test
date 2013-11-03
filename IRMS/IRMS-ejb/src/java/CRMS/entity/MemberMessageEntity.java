/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author liuxian
 */
@Entity
public class MemberMessageEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;
    private String messageTitle;
    private String messageCategory;
    private String messageContent;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date messageSentDate;
    @ManyToOne(cascade = {CascadeType.ALL})
    private MemberEntity memberReceiver;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageCategory() {
        return messageCategory;
    }

    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getMessageSentDate() {
        return messageSentDate;
    }

    public void setMessageSentDate(Date messageSentDate) {
        this.messageSentDate = messageSentDate;
    }

    public MemberEntity getMemberReceiver() {
        return memberReceiver;
    }

    public void setMemberReceiver(MemberEntity memberReceiver) {
        this.memberReceiver = memberReceiver;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the messageId fields are not set
        if (!(object instanceof MemberMessageEntity)) {
            return false;
        }
        MemberMessageEntity other = (MemberMessageEntity) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRMS.entity.MemberMessageEntity[ messageId=" + messageId + " ]";
    }
}
