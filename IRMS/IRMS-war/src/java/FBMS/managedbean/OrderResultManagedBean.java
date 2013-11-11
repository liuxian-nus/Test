/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.managedbean;

import FBMS.entity.CourseEntity;
import FBMS.entity.OrderEntity;
import FBMS.session.BillingSessionBean;
import FBMS.session.FBEmailSessionBean;
import FBMS.session.InventorySessionBean;
import FBMS.session.OrderSessionBean;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author liudazhi
 */
@ManagedBean
@ViewScoped
public class OrderResultManagedBean {

    @EJB
    private BillingSessionBean billingSessionBean;
    @EJB
    private InventorySessionBean inventorySessionBean;
    @EJB
    private FBEmailSessionBean fBEmailSessionBean;
    @EJB
    private OrderSessionBean orderSessionBean;
    private OrderEntity order;
    private OrderEntity thisOrder;
    private Date currentDate = new Date();

    /**
     * Creates a new instance of OrderResultManagedBean
     */
    public OrderResultManagedBean() {
        order = new OrderEntity();
        thisOrder = new OrderEntity();
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public OrderEntity getThisOrder() {
        return thisOrder;
    }

    public void setThisOrder(OrderEntity thisOrder) {
        this.thisOrder = thisOrder;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public void initViewSelect(PhaseEvent event) {

        order = (OrderEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisOrder");
        System.out.println("In init view select event size" + order.getOrderId());
//        request.getSession().setAttribute("roomId", thisRoom.getRoomId());

    }
    
    public void viewMenu(ActionEvent event)
    {
        System.out.println("in viewing menu" + thisOrder.getOrderId());
         HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
//            selected = (ContractEntity) event.getComponent().getAttributes().get("selectedContract");
//            System.out.println("N02: in displaying bean " + selected.getContractId());

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisMenu", thisOrder.getMenu());
            System.out.println("we are after setting parameter");
//            request.getSession().setAttribute("contractId", selected.getContractId());
//            System.out.println("we are after setting contractId session attribute");
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewMenu.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing menu", ""));
        }
    }

    public List<CourseEntity> getCourses() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        System.err.println("hahaha teest" + thisOrder.getOrderId());
        System.out.println("!!!!!!!!!!!!!in getting courses!!!!!!!!!!!!!!!!" + thisOrder.getOrderId());
        return orderSessionBean.convertMenuCourses(thisOrder.getMenu().getCourses());
    }

    public void confirmOrder(ActionEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        System.err.println("hahaha teest");
//        thisOrder = (OrderEntity) event.getComponent().getAttributes().get("viewOrder");
        System.out.println("No1: in confirming order" + thisOrder.getOrderId());
        thisOrder.setStatus("Confirmed");
        orderSessionBean.updateOrder(thisOrder);
        System.out.println("NO2: After confirming" + thisOrder.getStatus());
        fBEmailSessionBean.sendConfirmation(thisOrder.getEmail(), thisOrder);
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisOrder", thisOrder);
    }

    public void deliverGoods(ActionEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        System.err.println("hahaha teest");
//        thisOrder = (OrderEntity) event.getComponent().getAttributes().get("viewOrder");
        System.out.println("No1: in delivering goods" + thisOrder.getOrderId());
//        thisOrder.setStatus("Delivered");
        inventorySessionBean.issueGoods(thisOrder.getOrderId());
        System.out.println("NO2: After confirming" + thisOrder.getStatus());
//        fBEmailSessionBean.sendIssueGoods(thisOrder.getEmail(), thisOrder);
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisOrder", thisOrder);
    }

    public void sendInvoice(ActionEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        System.err.println("hahaha teest");
//        thisOrder = (OrderEntity) event.getComponent().getAttributes().get("viewOrder");
        System.out.println("No1: in sending invoice" + thisOrder.getOrderId());
        thisOrder.setStatus("Invoiced");
        billingSessionBean.createInvoice(thisOrder.getOrderId(), currentDate);
        System.out.println("NO2: After confirming" + thisOrder.getStatus());
        //   fBEmailSessionBean.sendInvoice(thisOrder.getEmail(), thisOrder);
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisOrder", thisOrder);
    }

    public void sendReceipt(ActionEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        System.err.println("hahaha teest");
//        thisOrder = (OrderEntity) event.getComponent().getAttributes().get("viewOrder");
        System.out.println("No1: in sending invoice" + thisOrder.getOrderId());
        billingSessionBean.createReceipt(thisOrder.getInvoice().getInvoiceId(), currentDate);
        thisOrder.setStatus("ReceiptSent");
        orderSessionBean.updateOrder(thisOrder);
        System.out.println("NO2: After confirming" + thisOrder.getStatus());
        //   fBEmailSessionBean.sendReceipt(thisOrder.getEmail(), thisOrder);
    }
}
