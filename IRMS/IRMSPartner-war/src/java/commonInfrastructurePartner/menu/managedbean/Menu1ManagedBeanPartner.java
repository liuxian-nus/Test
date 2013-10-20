/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructurePartner.menu.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EmployeeSessionBean;
import Exception.ExistException;
import SMMS.entity.MerchantEntity;
import SMMS.session.MerchantSessionBean;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class Menu1ManagedBeanPartner implements Serializable {

    private MenuModel model;
    @EJB
    MerchantSessionBean merchant;

    public Menu1ManagedBeanPartner() {
    }

    public MenuModel getModel() throws ExistException {

        model = new DefaultMenuModel();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
        
        if (isLogin==null){
            isLogin = false;
        }
        
        System.out.println("isLogin value = "+isLogin);
        
        if (isLogin == true) {
            String loginId = (String) request.getSession().getAttribute("userId");
            MerchantEntity user = merchant.getMerchantById(loginId);
            System.out.println("Merchant ID = "+user.getMerchantEmail());
            List<String> userType;
            userType = new ArrayList<String>();

            for (int i = 0; i < user.getRoles().size(); i++) {
                userType.add(user.getRoles().get(i).getRoleName());
            }

            //First submenu
            Submenu submenu = new Submenu();
            submenu.setLabel("My Menu");


            MenuItem item = new MenuItem();
            item.setValue("Home");
            item.setUrl("/");
            item.setIcon("ui-icon ui-icon-home");
            submenu.getChildren().add(item);

            item = new MenuItem();
            item.setValue("My Info");
            item.setUrl("/commonInfrastructure/ViewInfo.xhtml");
            item.setIcon("ui-icon ui-icon-person");
            submenu.getChildren().add(item);

            item = new MenuItem(); // not implemented
            item.setValue("Write Message");
            item.setUrl("/messages/createInternalMessage.xhtml");
            item.setIcon("ui-icon ui-icon-pencil");
            submenu.getChildren().add(item);

            item = new MenuItem(); // not implemented
            item.setValue("Message Inbox");
            item.setUrl("/messages/systemInbox.xhtml");
            item.setIcon("ui-icon ui-icon-mail-closed");
            submenu.getChildren().add(item);

            model.addSubmenu(submenu);
            
        }
        return model;
    }
        
     public void redirect(){   
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/IRMSPartner-war/commonInfrastructure/AccessDeniedPage.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Menu1ManagedBeanPartner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        NavigationHandler nh = ctx.getApplication().getNavigationHandler();
        nh.handleNavigation(ctx, null, "accessDenied");
 
    }
}
