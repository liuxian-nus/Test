/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.ERMS.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author Ser3na
 */

@ManagedBean
@ViewScoped
public class IndexManagementManagedBean {
    private String message;
    private Boolean loginRender;
    private Boolean logoutRender;
    
    public IndexManagementManagedBean() {
        message = checkLoginStatus();
    }
    
   
    public String checkLoginStatus(){
    HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    Boolean isLogin = (Boolean)request.getSession().getAttribute("isLogin");
    //Boolean isLogin = true;
    if(isLogin != null && isLogin == true){
        String userId = request.getSession().getAttribute("userId").toString();
        logoutRender = true;
        return "Welcome, you user ID is " + userId;
    }
    else{
            loginRender = true;
        return "You are not login, Pls do login First";
    }
}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Boolean getLoginRender() {
        return loginRender;
    }

    public void setLoginRender(Boolean loginRender) {
        this.loginRender = loginRender;
    }

    public Boolean getLogoutRender() {
        return logoutRender;
    }

    public void setLogoutRender(Boolean logoutRender) {
        this.logoutRender = logoutRender;
    }

}
