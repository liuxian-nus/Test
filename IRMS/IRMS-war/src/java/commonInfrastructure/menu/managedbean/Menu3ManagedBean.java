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

            if (userType.contains("ACMSManager")) {
                System.out.println("manager menu bar");
                submenu = new Submenu();
                submenu.setLabel("Hotel Management");

                item = new MenuItem();
                item.setValue("Overbooking Management");
                item.setUrl("/acms/overbookingManagement.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Price Management");
                item.setUrl("/acms/listAllRoomPrices.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Room Management");
                item.setUrl("/acms/RoomManagement.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Reservation Management");
                item.setUrl("/acms/listReservations.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("log book");
                item.setUrl("/acms/logBook.xhtml");
                item.setIcon("ui-icon ui-icon-comment");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
            }

            if (userType.contains("CEMSEvent")) {
                System.out.println("CEMSEvent menu bar");
                submenu = new Submenu();
                submenu.setLabel("Event Service Management");

                item = new MenuItem();
                item.setValue("Event Management");
                item.setUrl("/cems/eventService.xhtml");
                item.setIcon("ui-icon ui-icon-suitcase");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);

                submenu = new Submenu();
                submenu.setLabel("Service Management");

                item = new MenuItem();
                item.setValue("Create New Service");
                item.setUrl("/cems/eventServiceCreate.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage Services");
                item.setUrl("/cems/eventServiceManage.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);

                submenu = new Submenu();
                submenu.setLabel("Event Billing");

                item = new MenuItem();
                item.setValue("Event Bill");
                item.setUrl("/cems/eventServiceBill.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
            }

            if (userType.contains("ESMSFront")) {
                submenu = new Submenu();
                submenu.setLabel("List Show");
                
                item = new MenuItem();
                item.setValue("Search show");
                item.setUrl("/esms/showTicketing.xhtml");
                item.setIcon("ui-icon ui-icon-contact");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("List show");
                item.setUrl("/esms/showTicketingList.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
                submenu = new Submenu();
                submenu.setLabel("Show Ticketing");

                item = new MenuItem();
                item.setValue("Ticketing");
                item.setUrl("/esms/showTicketingBuy.xhtml");
                item.setIcon("ui-icon ui-icon-heart");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);

            }

        }
        return model;
    }
}
