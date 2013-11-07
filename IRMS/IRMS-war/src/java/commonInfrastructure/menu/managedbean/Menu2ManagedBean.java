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

            if (userType.contains("SuperAdmin")) {

                submenu = new Submenu();
                submenu.setLabel("Account");

                item = new MenuItem();
                item.setValue("Create New Account");
                item.setUrl("/accountManagement/addEmployee.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage Employees");
                item.setUrl("/accountManagement/manageEmployee.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);

                submenu = new Submenu();
                submenu.setLabel("Role");
                submenu.setIcon("ui-icon ui-icon-star");

                item = new MenuItem();
                item.setValue("Create New Role");
                item.setUrl("/accountManagement/addRole.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage Roles");
                item.setUrl("/accountManagement/manageRole.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);

                submenu = new Submenu();
                submenu.setLabel("Functionality");
                submenu.setIcon("ui-icon ui-icon-wrench");

                item = new MenuItem();
                item.setValue("Create New Functionality");
                item.setUrl("/accountManagement/addFunctionality.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage Functionality");
                item.setUrl("/accountManagement/manageFunctionality.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
            }
            if (userType.contains("ACMSAdmin")) { //|| (userType.contains("ACMSFrontDesk")) || (userType.contains("ACMSRoomService"))) {
                System.out.println("ACMSAdmin menu bar");
                submenu = new Submenu();
                submenu.setLabel("Hotel");

                item = new MenuItem();
                item.setValue("Front Desk");
                item.setUrl("/acms/checkIncheckOut.xhtml");
                item.setIcon("ui-icon ui-icon-suitcase");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Room Service");
                item.setUrl("/acms/RoomService.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Overbooking Mgt");
                item.setUrl("/acms/overbookingManagement.xhtml");
                item.setIcon("ui-icon ui-icon-calculator");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("log book");
                item.setUrl("/acms/logBook.xhtml");
                item.setIcon("ui-icon ui-icon-comment");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Price Mgt");
                item.setUrl("/acms/listAllRoomPrices.xhtml");
                item.setIcon("ui-icon ui-icon-tag");
                submenu.getChildren().add(item);
                
                model.addSubmenu(submenu);
            }

            /*             if (userType.contains("ACMSRoomService")){ //|| (userType.contains("ACMSFrontDesk")) || (userType.contains("ACMSRoomService"))) {
             System.out.println("ACMSRoomService menu bar");
             submenu = new Submenu();
             submenu.setLabel("Room Service");
             submenu.setIcon("ui-icon ui-icon-contact");
                
             item = new MenuItem();
             item.setValue("Room Service");
             item.setUrl("/acms/RoomService.xhtml");
             submenu.getChildren().add(item);
                
             model.addSubmenu(submenu);
             }
             */

            if (userType.contains("CEMSAdmin")) {
                System.out.println("CEMSAdmin menu bar");
                submenu = new Submenu();
                submenu.setLabel("Venue Management");

                item = new MenuItem();
                item.setValue("Create Venue");
                item.setUrl("/cems/addVenue.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage Venue");
                item.setUrl("/cems/manageVenue.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
                
                
                submenu = new Submenu();
                submenu.setLabel("Event Management");

                item = new MenuItem();
                item.setValue("Create Event");
                item.setUrl("/cems/addEvent.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage Event");
                item.setUrl("/cems/manageEvent.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
            }

            if (userType.contains("FBMSAdmin")) {
                System.out.println("FBMSAdmin menu bar");
                submenu = new Submenu();
                submenu.setLabel("Restaurant");
                submenu.setIcon("ui-icon ui-icon-contact");

                item = new MenuItem();
                item.setValue("Create Restaurant");
                item.setUrl("/fbms/addRestaurant.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage Restaurants");
                item.setUrl("/fbms/manageRestaurant.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Create Inventory");
                item.setUrl("/fbms/addDish.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage Inventory");
                item.setUrl("/fbms/manageDish.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
                
                submenu = new Submenu();
                submenu.setLabel("Group Catering");
                submenu.setIcon("ui-icon ui-icon-contact");

                item = new MenuItem();
                item.setValue("Add Group Catering");
                item.setUrl("/fbms/addGroupCatering.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Manage Group Catering");
                item.setUrl("/fbms/manageGroupCatering.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);
                
                model.addSubmenu(submenu);
            }

            if (userType.contains("ATMSAdmin")) {
                System.out.println("ATMSAdmin menu bar");
                submenu = new Submenu();
                submenu.setLabel("Attraction/ticket management");
                submenu.setIcon("ui-icon ui-icon-contact");

                item = new MenuItem();
                item.setValue("Add Attractions");
                item.setUrl("/atms/addAttractions.xhtml");
                item.setIcon("ui-icon ui-icon-search");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage Attractions");
                item.setUrl("/atms/manageAttractions.xhtml");
                item.setIcon("ui-icon ui-icon-search");
                submenu.getChildren().add(item);


                item = new MenuItem();
                item.setValue("Add Tickets");
                item.setUrl("/atms/addAttrTickets.xhtml");
                item.setIcon("ui-icon ui-icon-search");
                submenu.getChildren().add(item);


                item = new MenuItem();
                item.setValue("Manage Tickets");
                item.setUrl("/atms/manageAttrTickets.xhtml");
                item.setIcon("ui-icon ui-icon-search");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Add Ticket Combos");
                item.setUrl("/atms/addAttrCombos.xhtml");
                item.setIcon("ui-icon ui-icon-search");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Manage Ticket Combos");
                item.setUrl("/atms/manageAttrCombos.xhtml");
                item.setIcon("ui-icon ui-icon-search");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Add Express Ticket");
                item.setUrl("/atms/addAttrExpressPass.xhtml");
                item.setIcon("ui-icon ui-icon-search");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Manage Express Combos");
                item.setUrl("/atms/manageAttrExpressPass.xhtml");
                item.setIcon("ui-icon ui-icon-search");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
                
                submenu = new Submenu();
                submenu.setLabel("Ticket Purchase");
                submenu.setIcon("ui-icon ui-icon-contact");
                
                item = new MenuItem();
                item.setValue("Ticket Purchase");
                item.setUrl("/atms/attrTicketPurchase.xhtml");
                item.setIcon("ui-icon ui-icon-search");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Express Pass Purchase");
                item.setUrl("/atms/attrExpressPassPurchase.xhtml");
                item.setIcon("ui-icon ui-icon-search");
                submenu.getChildren().add(item);
                
                model.addSubmenu(submenu);
            }

            if (userType.contains("CRMSAdmin")) {
                System.out.println("CRMSAdmin menu bar");
                submenu = new Submenu();
                submenu.setLabel("VIP management");
                submenu.setIcon("ui-icon ui-icon-contact");

                item = new MenuItem();
                item.setValue("Search VIP");
                item.setUrl("/crms/searchVIP.xhtml");
                item.setIcon("ui-icon ui-icon-search");
                submenu.getChildren().add(item);


                model.addSubmenu(submenu);
            }

            if (userType.contains("SMMSAdmin")) {
                System.out.println("SMMSOps menu bar");
                submenu = new Submenu();
                submenu.setLabel("Manage Contract");

                item = new MenuItem();
                item.setValue("Manage Contract");
                item.setUrl("/smms/managerManageContract.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
            }


            if (userType.contains("SMMSOps")) {
                System.out.println("SMMSAdmin menu bar");
                
                submenu = new Submenu();
                submenu.setLabel("Manage Merchants");

                item = new MenuItem();
                item.setValue("Add Merchant");
                item.setUrl("/smms/addMerchant.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Manage Merchant");
                item.setUrl("/smms/manageMerchants.xhtml");
                item.setIcon("ui-icon ui-icon-lightbulb");
                submenu.getChildren().add(item);
                
                model.addSubmenu(submenu);
                
                submenu = new Submenu();
                submenu.setLabel("Manage Property");

                item = new MenuItem();
                item.setValue("Create Pushing Cart");
                item.setUrl("/smms/addPushingcart.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("View Pushing Cart");
                item.setUrl("/smms/pushingcartManagement.xhtml");
                item.setIcon("ui-icon ui-icon-cart");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Create Outlet");
                item.setUrl("/smms/addOutlet.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage Outlets");
                item.setUrl("/smms/outletManagement.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);
                model.addSubmenu(submenu);

                submenu = new Submenu();
                submenu.setLabel("Manage Contracts");

                item = new MenuItem();
                item.setValue("Add Contract");
                item.setUrl("/smms/addContract.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("View Contracts");
                item.setUrl("/smms/operatorManageContract.xhtml");
                item.setIcon("ui-icon ui-icon-document");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);

                submenu = new Submenu();
                submenu.setLabel("Manage Contract payments");

                item = new MenuItem();
                item.setValue("Manage Contract Payments");
                item.setUrl("/smms/paymentManagement.xhtml");
                item.setIcon("ui-icon ui-icon-lightbulb");
                submenu.getChildren().add(item);
                model.addSubmenu(submenu);
            }


            if (userType.contains("ESMSAdmin")) {
                System.out.println("ESMSAdmin menu bar");
                
                submenu = new Submenu();
                submenu.setLabel("Contract Management");

                item = new MenuItem();
                item.setValue("New Contract");
                item.setUrl("/esms/showContract.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("View Contracts");
                item.setUrl("/esms/viewShowContract.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
                
                submenu = new Submenu();
                submenu.setLabel("Show Management");

                item = new MenuItem();
                item.setValue("Create new show");
                item.setUrl("/esms/addShow.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage show");
                item.setUrl("/esms/manageShow.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
                
                submenu = new Submenu();
                submenu.setLabel("Show Ticketing");

                item = new MenuItem();
                item.setValue("Ticketing");
                item.setUrl("/esms/showTicketing.xhtml");
                item.setIcon("ui-icon ui-icon-contact");
                submenu.getChildren().add(item);
                
                model.addSubmenu(submenu);
                
                submenu = new Submenu();
                submenu.setLabel("Show billing");

                item = new MenuItem();
                item.setValue("View bill");
                item.setUrl("/esms/showBilling.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);
                
                model.addSubmenu(submenu);
                
                submenu = new Submenu();
                submenu.setLabel("Request Management");

                item = new MenuItem();
                item.setValue("View Request");
                item.setUrl("/esms/viewShowRequest.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Manage Request");
                item.setUrl("/esms/manageShowRequest.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);
                
                model.addSubmenu(submenu);
            }

        }
        return model;
    }
}
