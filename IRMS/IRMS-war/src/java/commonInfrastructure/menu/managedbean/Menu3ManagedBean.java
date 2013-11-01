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
@ManagedBean
@ViewScoped
public class Menu3ManagedBean implements Serializable {

    @EJB
    EmployeeSessionBean employee;
    private MenuModel model;

    public Menu3ManagedBean() {
    }

    public MenuModel getModel() throws ExistException {
        model = new DefaultMenuModel();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");

        if (isLogin == null) {
            isLogin = false;
        }

        if (isLogin == true) {
            String loginId = (String) request.getSession().getAttribute("userId");
            EmployeeEntity user = employee.getEmployeeById(loginId);
            List<String> userType = new ArrayList<String>();

            for (int i = 0; i < user.getRoles().size(); i++) {
                userType.add(user.getRoles().get(i).getRoleName());
            }

            //First submenu
            Submenu submenu = new Submenu();
            MenuItem item = new MenuItem();

            if (userType.contains("ACMSFrontDesk")) {
                System.out.println("Front desk menu bar");
                submenu = new Submenu();
                submenu.setLabel("Front Desk");

                item = new MenuItem();
                item.setValue("Front Desk");
                item.setUrl("/acms/checkIncheckOut.xhtml");
                item.setIcon("ui-icon ui-icon-suitcase");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("log book");
                item.setUrl("/acms/logBook.xhtml");
                item.setIcon("ui-icon ui-icon-comment");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
            }
            
            if (userType.contains("ACMSRoomService")) {
                System.out.println("Room service menu bar");
                submenu = new Submenu();
                submenu.setLabel("RoomService");

                item = new MenuItem();
                item.setValue("Room Service");
                item.setUrl("/acms/RoomService.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("log book");
                item.setUrl("/acms/logBook.xhtml");
                item.setIcon("ui-icon ui-icon-comment");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
            }

        }
        return model;
    }
}