/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.menu.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EmployeeSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.NavigationHandler;
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
public class AllMenuManagedBean {

    private MenuModel model;
    @EJB
    EmployeeSessionBean employee;

    public AllMenuManagedBean() {
    }

    public MenuModel getModel() throws ExistException {

        model = new DefaultMenuModel();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");

        if (isLogin == null) {
            isLogin = false;
        }

        System.out.println("isLogin value = " + isLogin);

        if (isLogin == true) {
            String loginId = (String) request.getSession().getAttribute("userId");
            EmployeeEntity user = employee.getEmployeeById(loginId);
            System.out.println("Employee ID = " + user.getEmployeeId());
            List<String> userType;
            userType = new ArrayList<String>();

            for (int i = 0; i < user.getRoles().size(); i++) {
                userType.add(user.getRoles().get(i).getRoleName());
            }

            //First submenu
            Submenu submenu = new Submenu();
            submenu.setLabel("My Menu");
            MenuItem item = new MenuItem();

            if (userType.contains("SuperUser")) {

                submenu = new Submenu();
                submenu.setLabel("AAU1: Account Management");

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

                
                
                
                
                
                
                
                
                
                submenu = new Submenu();
                submenu.setLabel("AAU2: Accommodation");

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
                item.setValue("Overbooking Management");
                item.setUrl("/acms/overbookingManagement.xhtml");
                item.setIcon("ui-icon ui-icon-calculator");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Log book");
                item.setUrl("/acms/logBook.xhtml");
                item.setIcon("ui-icon ui-icon-comment");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Price Management");
                item.setUrl("/acms/listAllRoomPrices.xhtml");
                item.setIcon("ui-icon ui-icon-tag");
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

                model.addSubmenu(submenu);

                
                
                
                
                
                
                
                
                submenu = new Submenu();
                submenu.setLabel("AAU3: Convention Centre");

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

                item = new MenuItem();
                item.setValue("Book Venue");
                item.setUrl("/cems/venueBooking.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Event Management");
                item.setUrl("/cems/eventService.xhtml");
                item.setIcon("ui-icon ui-icon-suitcase");
                submenu.getChildren().add(item);

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

                item = new MenuItem();
                item.setValue("Event Bill");
                item.setUrl("/cems/eventServiceBill.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);


                
                
                
                
                
                
                
                
                

                submenu = new Submenu();
                submenu.setLabel("AAU4: Shopping Mall");

                item = new MenuItem();
                item.setValue("Manage Contract");
                item.setUrl("/smms/managerManageContract.xhtml");
                item.setIcon("ui-icon ui-icon-plus");
                submenu.getChildren().add(item);

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

                item = new MenuItem();
                item.setValue("Manage Payments");
                item.setUrl("/smms/paymentManagement.xhtml");
                item.setIcon("ui-icon ui-icon-lightbulb");
                submenu.getChildren().add(item);
                model.addSubmenu(submenu);

                item = new MenuItem();
                item.setValue("Add Transaction");
                item.setUrl("/smms/addItemTransaction.xhtml");
                item.setIcon("ui-icon ui-icon-lightbulb");
                submenu.getChildren().add(item);
                model.addSubmenu(submenu);



                
                
                
                
                
                
                
                


                submenu = new Submenu();
                submenu.setLabel("AAU5: Food and Beverage");
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

                item = new MenuItem();
                item.setValue("Add Accounts");
                item.setUrl("/fbms/addAccount.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage F&B Accounts");
                item.setUrl("/fbms/manageAccount.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);









                
                

                submenu = new Submenu();
                submenu.setLabel("AAU6: Attraction");
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



                
                
                
                submenu = new Submenu();
                submenu.setLabel("AAU7: Entertainment Show");

                item = new MenuItem();
                item.setValue("View Show Request");
                item.setUrl("/esms/viewShowRequest.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);

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

                item = new MenuItem();
                item.setValue("View bill");
                item.setUrl("/esms/showBilling.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);

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

                item = new MenuItem();
                item.setValue("Ticketing");
                item.setUrl("/esms/showTicketingBuy.xhtml");
                item.setIcon("ui-icon ui-icon-heart");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
                





                submenu = new Submenu();
                submenu.setLabel("AAU8: Customer Relationship Management");
                submenu.setIcon("ui-icon ui-icon-contact");

                item = new MenuItem();
                item.setValue("Members Management");
                item.setUrl("/crms/listAllMembers.xhtml");
                item.setIcon("ui-icon ui-icon-person");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Member Transactions");
                item.setUrl("/crms/listMemberTransactions.xhtml");
                item.setIcon("ui-icon ui-icon-search");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("VIP Management");
                item.setUrl("/crms/listAllVIPs.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Feedbacks");
                item.setUrl("/crms/feedbackManagement.xhtml");
                item.setIcon("ui-icon ui-icon-mail-open");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("View size of wallet");
                item.setUrl("/crms/sizeOfWallet.xhtml");
                item.setIcon("ui-icon ui-icon-folder-open");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("RFM Value");
                item.setUrl("/crms/rfmValue.xhtml");
                item.setIcon("ui-icon ui-icon-gear");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Tiered Customer");
                item.setUrl("/crms/tiered.xhtml");
                item.setIcon("ui-icon ui-icon-star");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Promotion Evaluation");
                item.setUrl("/crms/evaluatePromotion.xhtml");
                item.setIcon("ui-icon ui-icon-lightbulb");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Promotions");
                item.setUrl("/crms/listPromotions.xhtml");
                item.setIcon("ui-icon ui-icon-tag");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Coupon Types");
                item.setUrl("/crms/listCouponTypes.xhtml");
                item.setIcon("ui-icon ui-icon-document");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);

            }

        }
        return model;
    }

    public void redirect() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/IRMS-war/commonInfrastructure/AccessDeniedPage.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Menu1ManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        NavigationHandler nh = ctx.getApplication().getNavigationHandler();
        nh.handleNavigation(ctx, null, "accessDenied");

    }
}
