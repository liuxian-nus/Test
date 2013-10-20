/*
 */
package commomInfrastructurePartner.utility.managedbean;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@RequestScoped
public class LogoutManagementManagedBeanPartner {

    private String message;
    public LogoutManagementManagedBeanPartner() {
        message = "";
    }
    public void doLogout(ActionEvent event) throws IOException{
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
       
        Boolean isLogin = (Boolean)request.getSession().getAttribute("isLogin");
        
        if(isLogin == true){
        String systemUserId = request.getSession().getAttribute("userId").toString();    
        request.getSession().setAttribute("isLogin",false);
        request.getSession().setAttribute("userId",null);
       //newly added, delete it if this is not working
        request.getSession().invalidate();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You have been successfully signed out", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
       
    }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Session is Expired Already", ""));    
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

