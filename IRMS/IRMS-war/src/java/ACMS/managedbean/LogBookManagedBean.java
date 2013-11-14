/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.LogBookEntity;
import ACMS.session.LogBookSessionBean;
import ERMS.entity.EmployeeEntity;
import ERMS.entity.MessageEntity;
import ERMS.session.EmployeeSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.Date;
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
    private EmployeeSessionBean employeeSessionBean;
    
    @EJB
    private LogBookSessionBean logBookSessionBean;
    private LogBookEntity thisLog;
    private LogBookEntity newLog;
    private List<LogBookEntity> logList;
    private Long logId;
    private String schedule; 
    private String employeeId;
    private Date currentDate = new Date();

    
    
    public LogBookManagedBean() {
         newLog = new LogBookEntity();
         thisLog = new LogBookEntity();
         schedule = "0";
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
        EmployeeEntity employee = employeeSessionBean.getEmployeeById(employeeId);
        newLog.setEmployee(employee);
        newLog.setLogShift(Integer.valueOf(schedule));
        newLog.setResolved(false);
        newLog.setPublishDate(currentDate);
        logBookSessionBean.addLog(newLog);
        System.err.println("we are after log book managedbean " + newLog.getLogTitle() );
         } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new log", ""));
            e.printStackTrace();
         }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Log added in", ""));
        newLog = new LogBookEntity();
    }
    
    public void markResolved(ActionEvent event)throws IOException {
        try {
            System.out.println("in marking resolved"+thisLog.getLogBookId());
            logBookSessionBean.markResolved(thisLog);
        }catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when updating status", ""));
         }
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Now this log is marked as resolved", ""));

    }
    
     public void deleteLog(ActionEvent event) throws ExistException{
         System.out.println("IN DELETE");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        System.out.println("IN DELETE 2");
        Long id=(Long)event.getComponent().getAttributes().get("deleteLog");
        
        System.out.println("Delete Log number "+ thisLog.getLogBookId());
        logBookSessionBean.removeLog(thisLog.getLogBookId());
        thisLog = new LogBookEntity();
    }
    
     public List<EmployeeEntity> getHotelEmployees() throws ExistException
     {
         return employeeSessionBean.getHotelEmployees();
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
    
    public int getSchedule() {
        return Integer.valueOf(schedule);
    }

    public void setSchedule(int schedule) {
        this.schedule = String.valueOf(schedule);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    
}
