/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.LogBookEntity;
import ACMS.session.LogBookSessionBean;
import ERMS.entity.MessageEntity;
import Exception.ExistException;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author liuxian
 */
@ManagedBean
@ViewScoped
public class LogBookManagedBean {
    @EJB
    private LogBookSessionBean logBookSessionBean;
    private LogBookEntity thisLog;
    private LogBookEntity newLog;
    private List<LogBookEntity> logList;
    private Long logId;
    
    
    public LogBookManagedBean() {
         newLog = new LogBookEntity();
    }
    
     @PostConstruct
    public void init() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
     
    public void initViewSelect(PhaseEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        thisLog = (LogBookEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisLog");
        request.getSession().setAttribute("logId", thisLog.getLogBookId());
    }
    
    public void intiViewAgain(PhaseEvent event) {
        logList = (List<LogBookEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("logList");
    }
    
    public void addLog(ActionEvent event)throws IOException {

        try {
        System.err.println("we are in log book managedbean " + newLog.getLogTitle() );
        System.out.println("get log entity" + newLog.getLogTitle());
        System.out.println("get log entity" + newLog.getLogText());
        logBookSessionBean.addLog(newLog);
        System.err.println("we are after log book managedbean " + newLog.getLogTitle() );
         } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new log", ""));
            e.printStackTrace();
         }
    }
    
     public void deleteLog(ActionEvent event) throws ExistException{
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Long id=(Long)event.getComponent().getAttributes().get("deleteLog");
        
        System.out.println("Delete Log number "+ id.toString());
        
        logBookSessionBean.removeLog(id);
        thisLog = new LogBookEntity();
    }
    
    public void onRowToggle(ToggleEvent event) {  
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,  
                                            "Row State " + event.getVisibility(),  
                                            "Title:" + ((LogBookEntity) event.getData()).getLogTitle());  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  

    public LogBookSessionBean getLogBookSessionBean() {
        return logBookSessionBean;
    }

    public void setLogBookSessionBean(LogBookSessionBean logBookSessionBean) {
        this.logBookSessionBean = logBookSessionBean;
    }

    public LogBookEntity getThisLog() {
        return thisLog;
    }

    public void setThisLog(LogBookEntity log) {
        this.thisLog = log;
    }

    public List<LogBookEntity> getLogList() {
        System.err.println("in get all logs");
        return logBookSessionBean.getAllLogs();
    }

    public void setLogList(List<LogBookEntity> logList) {
        this.logList = logList;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public LogBookEntity getNewLog() {
        return newLog;
    }

    public void setNewLog(LogBookEntity newLog) {
        this.newLog = newLog;
    }
    
    
}
