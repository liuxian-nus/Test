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
    private List<LogBookEntity> logList;
    private Long logId;
    
    
    public LogBookManagedBean() {
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
    
    public void addLog()throws IOException {
        System.err.println("we are in log book managedbean");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        Long getRerservationId = (Long) request.getSession().getAttribute("reservationId");
//        String getGuestName = (String) request.getSession().getAttribute("guestName");
    }
    
     public void deleteLog(ActionEvent event) throws ExistException{
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Long id=(Long)event.getComponent().getAttributes().get("deleteMsg");
        
        System.out.println("Delete Message number "+ id.toString());
        
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

    public List<LogBookEntity> getLogList() throws ExistException {
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
    
    
}
