/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author liuxian
 */
@Entity
public class ReceiverInfoEntity implements Serializable {
       @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiveMsgId;
    private boolean deleted;
    private boolean opened;
    private String receiverId;
    private String senderId;
    @ManyToOne (targetEntity = MessageEntity.class)
    private MessageEntity message;

    public Long getReceiveMsgId() {
        return receiveMsgId;
    }

    public void setReceiveMsgId(Long receiveMsgId) {
        this.receiveMsgId = receiveMsgId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (receiveMsgId != null ? receiveMsgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the receiveMsgId fields are not set
        if (!(object instanceof ReceiverInfoEntity)) {
            return false;
        }
        ReceiverInfoEntity other = (ReceiverInfoEntity) object;
        if ((this.receiveMsgId == null && other.receiveMsgId != null) || (this.receiveMsgId != null && !this.receiveMsgId.equals(other.receiveMsgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "internalMessaging.entity.ReceiverInfo[ receiveMsgId=" + receiveMsgId + " ]";
    }

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return the opened
     */
    public boolean isOpened() {
        return opened;
    }

    /**
     * @param opened the opened to set
     */
    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    /**
     * @return the senderId
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * @param senderId the senderId to set
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * @return the receiverId
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * @param receiverId the receiverId to set
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * @return the message
     */
    public MessageEntity getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(MessageEntity message) {
        this.message = message;
    }

}
