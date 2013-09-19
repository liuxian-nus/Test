/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.internalMessaging.managedbean;

import ERMS.entity.MessageEntity;
import ERMS.session.MessageSessionBean;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ViewSystemManagedBean implements Serializable {
    
    private String sender;
    private String sendTime;
    private String title;
    private String content;
    private MessageEntity message;
    
    @EJB
    MessageSessionBean messageManager;

    /** Creates a new instance of ViewMsgManagedBean */
    public ViewSystemManagedBean() {
    }
    
    public void initView(PhaseEvent event) {

        if (message == null) {

            message = (MessageEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("msg");
        }
        sender = message.getSenderName();
        sendTime = message.getSendTime();
        title = message.getTitle();
        content = message.getContent();
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String userId = (String) request.getSession().getAttribute("userId");
        for (int i=0;i<message.getRecInfo().size();i++) {
            if (message.getRecInfo().get(i).getReceiverId().equals(userId)) {
                message.getRecInfo().get(i).setOpened(true);
            }
        }
        
        messageManager.editMessage(message);
        
    }
    
    public void goBack() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("./systemInbox.xhtml");
    }

    /**
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * @return the sendTime
     */
    public String getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime the sendTime to set
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
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

