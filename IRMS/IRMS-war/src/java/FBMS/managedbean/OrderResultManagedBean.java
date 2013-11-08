/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.managedbean;

import FBMS.entity.OrderEntity;
import FBMS.session.FBEmailSessionBean;
import FBMS.session.OrderSessionBean;
import javax.ejb.EJB;
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
    private FBEmailSessionBean fBEmailSessionBean;

    @EJB
    private OrderSessionBean orderSessionBean;
    private OrderEntity order;
    private OrderEntity thisOrder;

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

    public void initViewSelect(PhaseEvent event) {

        order = (OrderEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisOrder");
        System.out.println("In init view select event size" + order.getOrderId());
//        request.getSession().setAttribute("roomId", thisRoom.getRoomId());

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
}
