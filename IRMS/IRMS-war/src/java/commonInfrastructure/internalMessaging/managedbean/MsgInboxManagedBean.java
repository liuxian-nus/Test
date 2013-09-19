/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.internalMessaging.managedbean;

import ERMS.entity.MessageEntity;
import ERMS.entity.ReceiverInfoEntity;
import ERMS.session.EmployeeSessionBean;
import ERMS.session.MessageSessionBean;
import ERMS.session.ReceiverInfoSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class MsgInboxManagedBean implements Serializable {

    @EJB
    ReceiverInfoSessionBean infoManager;
    @EJB
    MessageSessionBean messageManager;
    @EJB
    EmployeeSessionBean employeeManager;
    
    private List<MessageEntity> messageList;
    private MessageEntity selectedMessage;

    /** Creates a new instance of MsgInboxManagedBean */
    public MsgInboxManagedBean() {
    }

    public List<MessageEntity> getPrivateMessages() throws ExistException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String loginId = (String) request.getSession().getAttribute("userId");
        setMessageList(infoManager.getMessageByReceiver(loginId));
        
        for (int i=0;i<getMessageList().size();i++){
            if (getMessageList().get(i).getType().equals("S")){
                getMessageList().remove(i);
                i = i-1;
            }
        }
        
        for (int i=0;i<messageList.size();i++){
            String senderId = messageList.get(i).getRecInfo().get(0).getSenderId();
            String senderName = employeeManager.getEmployeeById(senderId).getEmployeeName();
            messageList.get(i).setSenderName(senderName);
            
            String status;
            if (messageList.get(i).getRecInfo().get(0).isOpened()){
                status = "Read";
            }
            else{
                status = "Unread";
            }
            messageList.get(i).setStatus(status);
        }
        
        return getMessageList();
    }
    
    public List<MessageEntity> getSystemMessages() throws ExistException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String loginId = (String) request.getSession().getAttribute("userId");
        setMessageList(infoManager.getMessageByReceiver(loginId));
        
        String senderName;
        for (int i=0;i<messageList.size();i++){
            String senderId = messageList.get(i).getRecInfo().get(0).getSenderId();
            senderName = employeeManager.getEmployeeById(senderId).getEmployeeName();
            messageList.get(i).setSenderName(senderName);
            
            String status = "Unread";
            List<ReceiverInfoEntity> infoList = messageList.get(i).getRecInfo();

            for (int j=0;j<infoList.size();j++){
                
                if (infoList.get(j).getReceiverId().equals(loginId)){
                    if (infoList.get(j).isOpened()) {
                        status = "Read";
                        break;
                    }  
                }
            }
            
            messageList.get(i).setStatus(status);
        }
        return messageList;
    }
    
    public void viewMsg(MessageEntity msg) throws IOException {
        selectedMessage = msg;
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("msg", selectedMessage);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewMessage.xhtml");
    }
    
    public void viewSystemMsg(MessageEntity msg) throws IOException {
        selectedMessage = msg;
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("msg", selectedMessage);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewSystemMessage.xhtml");
    }
    
    public void deleteMsg(ActionEvent event) throws ExistException{
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String receiverId = (String) request.getSession().getAttribute("userId");
        
        Long id=(Long)event.getComponent().getAttributes().get("deleteMsg");
        messageManager.removeMessage(id,receiverId);
        selectedMessage = new MessageEntity();
    }

    /**
     * @return the messageList
     */
    public List<MessageEntity> getMessageList() {
        return messageList;
    }

    /**
     * @param messageList the messageList to set
     */
    public void setMessageList(List<MessageEntity> messageList) {
        this.messageList = messageList;
    }

    /**
     * @return the selectedMessage
     */
    public MessageEntity getSelectedMessage() {
        return selectedMessage;
    }

    /**
     * @param selectedMessage the selectedMessage to set
     */
    public void setSelectedMessage(MessageEntity selectedMessage) {
        this.selectedMessage = selectedMessage;
    }
}
