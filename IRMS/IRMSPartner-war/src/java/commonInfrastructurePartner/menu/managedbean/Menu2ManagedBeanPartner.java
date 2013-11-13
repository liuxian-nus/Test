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
public class Menu2ManagedBeanPartner implements Serializable {

    private MenuModel model;
    @EJB
    MerchantSessionBean merchant;

    public Menu2ManagedBeanPartner() {
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
            MerchantEntity user = merchant.getMerchantById(loginId);
            String userType = user.getPartnerType();

//            for (int i = 0; i < user.getRoles().size(); i++) {
//                userType.add(user.getRoles().get(i).getRoleName());
//            }

            //First submenu
            Submenu submenu = new Submenu();
            MenuItem item = new MenuItem();

            
            if (userType == "shoppingMall") { //|| (userType.contains("ACMSFrontDesk")) || (userType.contains("ACMSRoomService"))) {
                System.out.println("Manage Contracts");
                submenu = new Submenu();
                submenu.setLabel("Manage Contracts");

                item = new MenuItem();
                item.setValue("Manage Contracts");
                item.setUrl("/shoppingMall/manageContractPartner.xhtml");
                item.setIcon("ui-icon ui-icon-suitcase");
                submenu.getChildren().add(item);

                item = new MenuItem();
                item.setValue("Manage Outlets");
                item.setUrl("/shoppingMall/manageOutletPartner.xhtml");
                item.setIcon("ui-icon ui-icon-script");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Manage Bills");
                item.setUrl("/shoppingMall/manageBillPartner.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);
                
                item = new MenuItem();
                item.setValue("Add Transaction");
                item.setUrl("/shoppingMall/addItemTransactionPartner.xhtml");
                item.setIcon("ui-icon ui-icon-pencil");
                submenu.getChildren().add(item);

                model.addSubmenu(submenu);
            }
        }
        return model;
    }
}
