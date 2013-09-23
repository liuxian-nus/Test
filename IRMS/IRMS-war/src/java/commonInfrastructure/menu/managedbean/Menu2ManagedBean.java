/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.menu.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EmployeeSessionBean;
import Exception.ExistException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;


/**
 *
 * @author Ser3na
 */
import javax.faces.application.NavigationHandler;

@ManagedBean
@ViewScoped
public class Menu2ManagedBean implements Serializable {

    private MenuModel model;
    @EJB
    EmployeeSessionBean employee;

    public Menu2ManagedBean() {
    }

    public MenuModel getModel() throws ExistException {
        model = new DefaultMenuModel();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
        
        if (isLogin == null){
            isLogin = false;
        }

        if (isLogin == true) {
            String loginId = (String) request.getSession().getAttribute("userId");
            EmployeeEntity user = employee.getEmployeeById(loginId);
            List<String> userType = new ArrayList<String>();

            for (int i=0;i<user.getRoles().size();i++){
                userType.add(user.getRoles().get(i).getRoleName());
            }

            //First submenu
            Submenu submenu = new Submenu();
            MenuItem item = new MenuItem();
           
            if (userType.contains("SuperAdmin")) {
                    
                submenu = new Submenu();
                submenu.setLabel("Account");
                submenu.setIcon("ui-icon ui-icon-contact");
                
                item = new MenuItem();
                item.setValue("Create New");
                item.setUrl("/accountManagement/addEmployee.xhtml");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Manage Employees");
                item.setUrl("/accountManagement/manageEmployee.xhtml");
                submenu.getChildren().add(item);
                
                model.addSubmenu(submenu);
                
                submenu = new Submenu();
                submenu.setLabel("Role");
                submenu.setIcon("ui-icon ui-icon-star");
                
                item = new MenuItem();
                item.setValue("Create New");
                item.setUrl("/accountManagement/addRole.xhtml");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Manage Roles");
                item.setUrl("/accountManagement/manageRole.xhtml");
                submenu.getChildren().add(item);
                
                model.addSubmenu(submenu);
                
                submenu = new Submenu();
                submenu.setLabel("Functionality");
                submenu.setIcon("ui-icon ui-icon-wrench");
                
                item = new MenuItem();
                item.setValue("Create New");
                item.setUrl("/accountManagement/addFunctionality.xhtml");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Manage Functionality");
                item.setUrl("/accountManagement/manageFunctionality.xhtml");
                submenu.getChildren().add(item);
                
                model.addSubmenu(submenu);
            }
             if (userType.contains("ACMSAdmin")){ //|| (userType.contains("ACMSFrontDesk")) || (userType.contains("ACMSRoomService"))) {
                System.out.println("ACMSAdmin menu bar");
                submenu = new Submenu();
                submenu.setLabel("Hotel");
                submenu.setIcon("ui-icon ui-icon-contact");
                
                item = new MenuItem();
                item.setValue("Hotel");
                item.setUrl("/acms/hotelManagement.xhtml");
                submenu.getChildren().add(item);
                
                model.addSubmenu(submenu);
             }
        }
        return model;
    }
}
