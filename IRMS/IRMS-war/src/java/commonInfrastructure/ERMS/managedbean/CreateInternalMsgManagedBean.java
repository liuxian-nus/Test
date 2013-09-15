
package commonInfrastructure.ERMS.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EmployeeSessionBean;
import ERMS.entity.MessageEntity;
import ERMS.session.MessageSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author frankxgz
 */
@ManagedBean
@ViewScoped
public class CreateInternalMsgManagedBean implements Serializable {

    private MessageEntity message;
    private String msg;
    private String receiver;
    private String msgType;
    private String title;
    @EJB
    MessageSessionBean messageManager;
    @EJB
    EmployeeSessionBean employee;

    /** Creates a new instance of CreateInternalMsgManagedBean */
    public CreateInternalMsgManagedBean() {
    }

    public List<String> complete(String query) {
        List<String> results = new ArrayList<String>();

        Set<EmployeeEntity> employeeList = employee.getAllEmployees();

        for (Object o : employeeList) {
            EmployeeEntity emp = (EmployeeEntity) o;
            if (emp.getEmployeeName().startsWith(query)) {
                results.add(emp.getEmployeeName());
            }
        }
        return results;
    }

    public void sendMsg(ActionEvent event) throws IOException, ExistException {
        String type;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Long senderId = (Long) request.getSession().getAttribute("userId");
        EmployeeEntity user = employee.getEmployeeById(senderId);
        List<String> userType = new ArrayList<String>();

        for (int i = 0; i < user.getEmployeeRoles().size(); i++) {
            userType.add(user.getEmployeeRoles().get(i).getRoleName());
        }

        if (receiver.equals("000")) {          //All
            if (!userType.contains("SystemMsg")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "You have no such right", ""));
                return;
            } else {
                Set<EmployeeEntity> employeeList = employee.getAllEmployees();
                List<Long> idList = new ArrayList<Long>();
                for (int i = 0; i < employeeList.size(); i++) {
                    idList.add(employeeList.get(i).getEmployeeId());
                }
                type = "Broadcast";
                messageManager.addSystemMessage(senderId, idList, title, msg, type);
            }
        } 
        
        else if (receiver.equals("001")) {      //Admin
            if (!userType.contains("SystemMsg")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "You have no such right", ""));
                return;
            } else {
                List<EmployeeEntity> employeeList = employee.getAllEmployees();
                List<Long> idList = new ArrayList<Long>();
                for (int i = 0; i < employeeList.size(); i++) {
                    if (employeeList.get(i).getDepartment().equals("Admin")) {
                        idList.add(employeeList.get(i).getId());
                    }
                }
                type = "Broadcast";
                messageManager.addSystemMessage(senderId, idList, title, msg, type);
            }
        } 
        
        else if (receiver.equals("002")) {      //Sales
            if (!userType.contains("SystemMsg")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "You have no such right", ""));
                return;
            } else {
                List<EmployeeEntity> employeeList = employee.getAllEmployees();
                List<Long> idList = new ArrayList<Long>();
                for (int i = 0; i < employeeList.size(); i++) {
                    if (employeeList.get(i).getDepartment().equals("Sales")) {
                        idList.add(employeeList.get(i).getId());
                    }
                }
                type = "Broadcast";
                messageManager.addSystemMessage(senderId, idList, title, msg, type);
            }
        } 
        
        else {
            try {
                Long receiverId = employee.getEmployeeByUserName(receiver).getId();
                type = "Private";
                messageManager.addPrivateMessage(senderId, receiverId, title, msg, type);
            } 
            catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No such person", ""));
                return;
            }

        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("createInternalMsgResult.xhtml");
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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
