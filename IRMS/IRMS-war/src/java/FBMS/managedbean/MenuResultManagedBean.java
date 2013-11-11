/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.managedbean;

import FBMS.entity.CourseEntity;
import FBMS.entity.MenuEntity;
import FBMS.session.OrderSessionBean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xinyu
 */
@ManagedBean
@ViewScoped
public class MenuResultManagedBean {

    @EJB
    private OrderSessionBean orderSessionBean;
    private List<CourseEntity> courses;
    private MenuEntity menu;

    /**
     * Creates a new instance of MenuResultManagedBean
     */
    public MenuResultManagedBean() {
        menu = new MenuEntity();
        courses = new ArrayList<CourseEntity>();
    }

    public void initViewSelect(PhaseEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        menu = (MenuEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisMenu");
        System.out.println("In init view select event size" + menu.getMenuId());
        courses = orderSessionBean.convertMenuCourses(menu.getCourses());
//        request.getSession().setAttribute("roomId", thisRoom.getRoomId());
    }

    public List<CourseEntity> convertCourses() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        System.err.println("hahaha teest" + thisOrder.getOrderId());
        System.out.println("!!!!!!!!!!!!!in getting courses!!!!!!!!!!!!!!!!" + menu.getMenuId());
        return orderSessionBean.convertMenuCourses(menu.getCourses());
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }
}
