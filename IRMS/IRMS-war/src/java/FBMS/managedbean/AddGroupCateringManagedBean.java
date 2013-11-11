/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.session.MemberSessionBean;
import ERMS.session.EmailSessionBean;
import Exception.ExistException;
import FBMS.entity.CourseEntity;
import FBMS.entity.DishEntity;
import FBMS.entity.MenuEntity;
import FBMS.entity.OrderEntity;
import FBMS.session.InventorySessionBean;
import FBMS.session.OrderSessionBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class AddGroupCateringManagedBean {

    @EJB
    private MemberSessionBean memberSessionBean;
    @EJB
    private InventorySessionBean inventorySessionBean;
    @EJB
    private OrderSessionBean orderSessionBean;
    @EJB
    private EmailSessionBean emailSessionBean;
    private static final Logger logger = Logger.getLogger(AddGroupCateringManagedBean.class.getName());
    private MenuEntity menu;
    private OrderEntity order;
    private OrderEntity selectOrder;
    private Date currentDate = new Date();
    private String memberId;
    private MemberEntity member;
    private String type;
    boolean menuChange = false;
    private Set<CourseEntity> courses;
    private CourseEntity course1;
    private CourseEntity course2;
    private CourseEntity course3;
    private CourseEntity course4;
    private CourseEntity course5;
    private CourseEntity course6;
    private CourseEntity course7;
    private CourseEntity course8;
    private CourseEntity course9;
    private CourseEntity course10;
    private CourseEntity course11;
    private CourseEntity course12;
    private Long dishId1;
    private Long dishId2;
    private Long dishId3;
    private Long dishId4;
    private Long dishId5;
    private Long dishId6;
    private Long dishId7;
    private Long dishId8;
    private Long dishId9;
    private Long dishId10;
    private Long dishId11;
    private DishEntity dish1;
    private DishEntity dish2;
    private DishEntity dish3;
    private DishEntity dish4;
    private DishEntity dish5;
    private DishEntity dish6;
    private DishEntity dish7;
    private DishEntity dish8;
    private DishEntity dish9;
    private DishEntity dish10;
    private DishEntity dish11;

    /**
     * Creates a new instance of AddGroupCateringManagedBean
     */
    public AddGroupCateringManagedBean() {
        menu = new MenuEntity();
        order = new OrderEntity();
        member = new MemberEntity();
        selectOrder = new OrderEntity();

        course1 = new CourseEntity();
        course2 = new CourseEntity();
        course3 = new CourseEntity();
        course4 = new CourseEntity();
        course5 = new CourseEntity();
        course6 = new CourseEntity();
        course7 = new CourseEntity();
        course8 = new CourseEntity();
        course9 = new CourseEntity();
        course10 = new CourseEntity();
        course11 = new CourseEntity();
        course12 = new CourseEntity();

        dish1 = new DishEntity();
        dish2 = new DishEntity();
        dish3 = new DishEntity();
        dish4 = new DishEntity();
        dish5 = new DishEntity();
        dish6 = new DishEntity();
        dish7 = new DishEntity();
        dish8 = new DishEntity();
        dish9 = new DishEntity();
        dish10 = new DishEntity();
        dish11 = new DishEntity();
    }

    public String onFlowProcess(FlowEvent event) {
        logger.info("Current wizard step:" + event.getOldStep());
        logger.info("Next step:" + event.getNewStep());
        return event.getNewStep();

    }

    public boolean handleMenuChanges() {

        System.out.println("lalala here see ajax changes lah! menu:" + menu.getType() + "menuchange is what?" + menuChange);
        type = menu.getType();
        if (type == "Premium") {
            menuChange = true;
        }
        System.out.println("haha so whats now for change" + menu);
        return menuChange;
    }

    public void addMenu() {
        System.out.println("NO1: in adding menu" + menu.getMenuId());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        try {
            // create menu first
            orderSessionBean.setMenu(menu);
            System.out.println("NO2: saving menu" + menu.getMenuId());
            System.err.println("here after setting menu");
            //  persist course
            dish1 = inventorySessionBean.getDishById(dishId1); //get existing dish
            course1.setDish(dish1); //set dish to course        
            orderSessionBean.setCourse(course1); // persist course

            dish2 = inventorySessionBean.getDishById(dishId2); //get existing dish
            course2.setDish(dish2); //set dish to course
            orderSessionBean.setCourse(course2);

            dish3 = inventorySessionBean.getDishById(dishId3); //get existing dish
            course3.setDish(dish3); //set dish to course
            orderSessionBean.setCourse(course3);

            dish4 = inventorySessionBean.getDishById(dishId4); //get existing dish
            course4.setDish(dish4); //set dish to course
            orderSessionBean.setCourse(course4);

            dish5 = inventorySessionBean.getDishById(dishId5); //get existing dish
            course5.setDish(dish5); //set dish to course
            orderSessionBean.setCourse(course5);

            dish6 = inventorySessionBean.getDishById(dishId6); //get existing dish
            course6.setDish(dish6); //set dish to course
            orderSessionBean.setCourse(course6);

            dish7 = inventorySessionBean.getDishById(dishId7); //get existing dish
            course7.setDish(dish7); //set dish to course
            orderSessionBean.setCourse(course7);

            dish8 = inventorySessionBean.getDishById(dishId8); //get existing dish
            course8.setDish(dish8); //set dish to course
            orderSessionBean.setCourse(course8);

            dish9 = inventorySessionBean.getDishById(dishId9); //get existing dish
            course9.setDish(dish9); //set dish to course
            orderSessionBean.setCourse(course9);

            dish10 = inventorySessionBean.getDishById(dishId10); //get existing dish
            course10.setDish(dish10); //set dish to course
            orderSessionBean.setCourse(course10);

            dish11 = inventorySessionBean.getDishById(dishId11); //get existing dish
            course11.setDish(dish11); //set dish to course
            orderSessionBean.setCourse(course11);

            System.out.println("NO2: saving COURSES" + course1.getCourseId() + "course dish" + course1.getDish().getDishName());
            System.out.println("NO2: saving COURSES" + course2.getCourseId() + "course dish" + course2.getDish().getDishName());
            System.out.println("NO2: saving COURSES" + course4.getCourseId() + "course dish" + course4.getDish().getDishName());
            System.out.println("NO2: saving COURSES" + course11.getCourseId() + "course dish" + course11.getDish().getDishName());

            // add course to menu
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course1.getCourseId());
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course2.getCourseId());
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course3.getCourseId());
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course4.getCourseId());
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course5.getCourseId());
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course6.getCourseId());
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course7.getCourseId());
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course8.getCourseId());
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course9.getCourseId());
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course10.getCourseId());
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course11.getCourseId());

            System.out.println("after adding courese to menu" + menu.getCourses().size());
            request.getSession().setAttribute("menuId", menu.getMenuId());

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new menu", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Menu has been added.", ""));
        menu = new MenuEntity();
    }

    public void addOrder(ActionEvent event) throws ExistException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Set<CourseEntity> courses = new HashSet<CourseEntity>();
        System.out.println("in adding order");
        //addMenu();

        System.out.println("NO1: in adding menu" + menu.getMenuId());

        // create menu first
        orderSessionBean.setMenu(menu);
        System.out.println("NO2: saving menu" + menu.getMenuId());
        System.err.println("here after setting menu, course/dish starting");

        // addding course to dish starts here
        //  persist course
        System.err.println("NO3:here in setting dish 1" + dishId1);
        dish1 = inventorySessionBean.getDishById(dishId1); //get existing dish
        System.err.println("NO4: here after getting dish by Id" + dish1.getDishId());
        course1.setDish(dish1); //set dish to course
        orderSessionBean.setCourse(course1); // persist course
        courses.add(course1);
        System.err.println("NO5: here after setting course" + course1.getCourseId() + "  and size is  " + courses.size());

        dish2 = inventorySessionBean.getDishById(dishId2); //get existing dish
        course2.setDish(dish2); //set dish to course
        orderSessionBean.setCourse(course2);
        courses.add(course2);

        dish3 = inventorySessionBean.getDishById(dishId3); //get existing dish
        course3.setDish(dish3); //set dish to course
        orderSessionBean.setCourse(course3);
        courses.add(course3);

        dish4 = inventorySessionBean.getDishById(dishId4); //get existing dish
        course4.setDish(dish4); //set dish to course
        orderSessionBean.setCourse(course4);
        courses.add(course4);

        dish5 = inventorySessionBean.getDishById(dishId5); //get existing dish
        course5.setDish(dish5); //set dish to course
        orderSessionBean.setCourse(course5);
        courses.add(course5);

        dish6 = inventorySessionBean.getDishById(dishId6); //get existing dish
        course6.setDish(dish6); //set dish to course
        orderSessionBean.setCourse(course6);
        courses.add(course6);

        System.err.println("NO6:here in setting dish 7" + dishId7);
        dish7 = inventorySessionBean.getDishById(dishId7); //get existing dish
        System.err.println("NO7: here after getting dish by Id" + dish7.getDishId());
        course7.setDish(dish7); //set dish to course
        orderSessionBean.setCourse(course7);
        courses.add(course7);
        System.err.println("NO5: here after setting course" + course7.getCourseId());

        dish8 = inventorySessionBean.getDishById(dishId8); //get existing dish
        course8.setDish(dish8); //set dish to course
        orderSessionBean.setCourse(course8);
        courses.add(course8);

        dish9 = inventorySessionBean.getDishById(dishId9); //get existing dish
        course9.setDish(dish9); //set dish to course
        orderSessionBean.setCourse(course9);
        courses.add(course9);

        System.out.println("NO6: saving COURSES" + course6.getCourseId());
        System.out.println("NO7: saving COURSES" + course2.getCourseId());
        System.out.println("NO8: saving COURSES" + course4.getCourseId());
        System.out.println("NO9: saving COURSES" + course9.getCourseId());

        System.err.println("NO10: after setting 9 courses and now the list zie is !!!!!!!!!!!!!!!!!!!!!" + menu.getType() + courses.size());

        if (menu.getType() == "Premium") {
            dish10 = inventorySessionBean.getDishById(dishId10); //get existing dish
            course10.setDish(dish10); //set dish to course
            orderSessionBean.setCourse(course10);
            courses.add(course10);

            dish11 = inventorySessionBean.getDishById(dishId11); //get existing dish
            course11.setDish(dish11); //set dish to course
            orderSessionBean.setCourse(course11);
            courses.add(course11);
        }


        System.out.println("NO11: STARTING to add courses to menu");
        // add course to menu
        orderSessionBean.addCourseToMenu(menu.getMenuId(), course1.getCourseId());
        orderSessionBean.addCourseToMenu(menu.getMenuId(), course2.getCourseId());
        orderSessionBean.addCourseToMenu(menu.getMenuId(), course3.getCourseId());
        orderSessionBean.addCourseToMenu(menu.getMenuId(), course4.getCourseId());
        orderSessionBean.addCourseToMenu(menu.getMenuId(), course5.getCourseId());
        orderSessionBean.addCourseToMenu(menu.getMenuId(), course6.getCourseId());
        orderSessionBean.addCourseToMenu(menu.getMenuId(), course7.getCourseId());
        orderSessionBean.addCourseToMenu(menu.getMenuId(), course8.getCourseId());
        orderSessionBean.addCourseToMenu(menu.getMenuId(), course9.getCourseId());

        if (menu.getType() == "Premium") {
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course10.getCourseId());
            orderSessionBean.addCourseToMenu(menu.getMenuId(), course11.getCourseId());
        }

        System.out.println("after adding courese to menu" + menu.getCourses().size());
        request.getSession().setAttribute("menuId", menu.getMenuId());
        System.out.println("after creating menu:" + menu.getMenuId() + "order:" + order.getOrderId());

        try {
            System.out.println("we are in save new order in managedbean");
            order.setMenu(menu); // setting menu
            //setting member
            System.out.println("in adding member" + memberId);
            if (memberId!=null) {
                System.out.println(memberSessionBean.getMemberByEmail(memberId).getMemberName());
                order.setMember(memberSessionBean.getMemberByEmail(memberId));
                System.out.println("we are in setting member" + order.getMember().getMemberEmail());
            }
            order.setStatus("Requested");
            orderSessionBean.placeOrder(order); //persisting order
            System.out.println("we are after setting order in managedbean" + order.getOrderId());

            menu.setOrder(order);
            menu.setCourses(courses);
            orderSessionBean.updateMenu(menu);
            System.out.println("we are after setting order in managedbean" + menu.getMenuId());
            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!go to die la menu course sieze" + order.getMenu().getCourses().size());
            order.setSalePrice(inventorySessionBean.assignCost(order.getOrderId()));

            request.getSession().setAttribute("orderId", order.getOrderId());
            FacesContext.getCurrentInstance().getExternalContext().redirect("manageGroupCatering.xhtml");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new order", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New order saved.", ""));
        order = new OrderEntity();
    }

    public List<String> completeMembers() throws ExistException {
        System.out.println("NO4: we are in ALL contracts bean BEFORE");
        List<String> results = new ArrayList<String>();

        List<MemberEntity> merchantList = memberSessionBean.getAllMembers();
        for (Object o : merchantList) {
            MemberEntity rve = (MemberEntity) o;
            results.add((rve.getMemberEmail().toString()));
        }
        System.out.println("NO5: we are in complete bean AFTER");
        return results;
    }

    public void viewOrder(ActionEvent event) {
        System.out.println("No1:in displaying bean lalalala " + selectOrder.getOrderId());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            selectOrder = (OrderEntity) event.getComponent().getAttributes().get("selectOrder");
            System.out.println("N02: in displaying bean " + selectOrder.getOrderId());

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisOrder", selectOrder);
            System.out.println("we are after setting contractId session attribute");
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewOrder.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing order", ""));
            return;
        }
    }

    public List<OrderEntity> getRequestedOrders() {
        return orderSessionBean.getRequestedOrders();
    }

    public List<OrderEntity> getConfirmedOrders() {
        return orderSessionBean.getConfirmedOrders();
    }

    public List<OrderEntity> getDelivedOrders() {
        return orderSessionBean.getDeliveredOrders();
    }

    public List<OrderEntity> getInvoicedOrders() {
        return orderSessionBean.getInvoicedOrders();
    }

    public List<OrderEntity> getReceiptOrders() {
        return orderSessionBean.getReceiptedOrders();
    }

    public OrderEntity getSelectOrder() {
        return selectOrder;
    }

    public void setSelectOrder(OrderEntity selectOrder) {
        this.selectOrder = selectOrder;
    }

//    public List<OrderEntity> getRequested() {
//        return orderSessionBean.getRequestedOrders();
//    }
//
//    public List<OrderEntity> getConfirmed() {
//        return orderSessionBean.getConfirmedOrders();
//    }
    public boolean isMenuChange() {
        return menuChange;
    }

    public void setMenuChange(boolean menuChange) {
        this.menuChange = menuChange;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }

    public CourseEntity getCourse1() {
        return course1;
    }

    public void setCourse1(CourseEntity course1) {
        this.course1 = course1;
    }

    public CourseEntity getCourse2() {
        return course2;
    }

    public void setCourse2(CourseEntity course2) {
        this.course2 = course2;
    }

    public CourseEntity getCourse3() {
        return course3;
    }

    public void setCourse3(CourseEntity course3) {
        this.course3 = course3;
    }

    public CourseEntity getCourse4() {
        return course4;
    }

    public void setCourse4(CourseEntity course4) {
        this.course4 = course4;
    }

    public CourseEntity getCourse5() {
        return course5;
    }

    public void setCourse5(CourseEntity course5) {
        this.course5 = course5;
    }

    public CourseEntity getCourse6() {
        return course6;
    }

    public void setCourse6(CourseEntity course6) {
        this.course6 = course6;
    }

    public CourseEntity getCourse7() {
        return course7;
    }

    public void setCourse7(CourseEntity course7) {
        this.course7 = course7;
    }

    public CourseEntity getCourse8() {
        return course8;
    }

    public void setCourse8(CourseEntity course8) {
        this.course8 = course8;
    }

    public CourseEntity getCourse9() {
        return course9;
    }

    public void setCourse9(CourseEntity course9) {
        this.course9 = course9;
    }

    public CourseEntity getCourse10() {
        return course10;
    }

    public void setCourse10(CourseEntity course10) {
        this.course10 = course10;
    }

    public CourseEntity getCourse11() {
        return course11;
    }

    public void setCourse11(CourseEntity course11) {
        this.course11 = course11;
    }

    public CourseEntity getCourse12() {
        return course12;
    }

    public void setCourse12(CourseEntity course12) {
        this.course12 = course12;
    }

    public Long getDishId1() {
        return dishId1;
    }

    public void setDishId1(Long dishId1) {
        this.dishId1 = dishId1;
    }

    public Long getDishId2() {
        return dishId2;
    }

    public void setDishId2(Long dishId2) {
        this.dishId2 = dishId2;
    }

    public Long getDishId3() {
        return dishId3;
    }

    public void setDishId3(Long dishId3) {
        this.dishId3 = dishId3;
    }

    public Long getDishId4() {
        return dishId4;
    }

    public void setDishId4(Long dishId4) {
        this.dishId4 = dishId4;
    }

    public Long getDishId5() {
        return dishId5;
    }

    public void setDishId5(Long dishId5) {
        this.dishId5 = dishId5;
    }

    public Long getDishId6() {
        return dishId6;
    }

    public void setDishId6(Long dishId6) {
        this.dishId6 = dishId6;
    }

    public Long getDishId7() {
        return dishId7;
    }

    public void setDishId7(Long dishId7) {
        this.dishId7 = dishId7;
    }

    public Long getDishId8() {
        return dishId8;
    }

    public void setDishId8(Long dishId8) {
        this.dishId8 = dishId8;
    }

    public Long getDishId9() {
        return dishId9;
    }

    public void setDishId9(Long dishId9) {
        this.dishId9 = dishId9;
    }

    public Long getDishId10() {
        return dishId10;
    }

    public void setDishId10(Long dishId10) {
        this.dishId10 = dishId10;
    }

    public Long getDishId11() {
        return dishId11;
    }

    public void setDishId11(Long dishId11) {
        this.dishId11 = dishId11;
    }

    public DishEntity getDish1() {
        return dish1;
    }

    public void setDish1(DishEntity dish1) {
        this.dish1 = dish1;
    }

    public DishEntity getDish2() {
        return dish2;
    }

    public void setDish2(DishEntity dish2) {
        this.dish2 = dish2;
    }

    public DishEntity getDish3() {
        return dish3;
    }

    public void setDish3(DishEntity dish3) {
        this.dish3 = dish3;
    }

    public DishEntity getDish4() {
        return dish4;
    }

    public void setDish4(DishEntity dish4) {
        this.dish4 = dish4;
    }

    public DishEntity getDish5() {
        return dish5;
    }

    public void setDish5(DishEntity dish5) {
        this.dish5 = dish5;
    }

    public DishEntity getDish6() {
        return dish6;
    }

    public void setDish6(DishEntity dish6) {
        this.dish6 = dish6;
    }

    public DishEntity getDish7() {
        return dish7;
    }

    public void setDish7(DishEntity dish7) {
        this.dish7 = dish7;
    }

    public DishEntity getDish8() {
        return dish8;
    }

    public void setDish8(DishEntity dish8) {
        this.dish8 = dish8;
    }

    public DishEntity getDish9() {
        return dish9;
    }

    public void setDish9(DishEntity dish9) {
        this.dish9 = dish9;
    }

    public DishEntity getDish10() {
        return dish10;
    }

    public void setDish10(DishEntity dish10) {
        this.dish10 = dish10;
    }

    public DishEntity getDish11() {
        return dish11;
    }

    public void setDish11(DishEntity dish11) {
        this.dish11 = dish11;
    }
}
