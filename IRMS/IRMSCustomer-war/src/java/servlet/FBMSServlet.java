/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Exception.ExistException;
import FBMS.entity.CourseEntity;
import FBMS.entity.DishEntity;
import FBMS.entity.IndReservationEntity;
import FBMS.entity.MenuEntity;
import FBMS.entity.OrderEntity;
import FBMS.entity.RestaurantEntity;
import FBMS.session.FBEmailSessionBean;
import FBMS.session.IndReservationSessionBeanRemote;
import FBMS.session.OrderSessionBean;
import FBMS.session.RestaurantSessionBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diana Wang
 */
@WebServlet(name = "FBMSServlet", urlPatterns = {"/FBMSServlet","/FBMSServlet/*"})
public class FBMSServlet extends HttpServlet {
    @EJB
    private RestaurantSessionBeanRemote restaurantSessionBean;
    @EJB
    private FBEmailSessionBean emailSessionBean;
    
    @EJB
    private OrderSessionBean orderSessionBean;
    @EJB
    private IndReservationSessionBeanRemote indReservationSessionBean;
    
    
    
    

    
    
    private Set<RestaurantEntity> data = null;
    private boolean data1;
    private RestaurantEntity data2 = null;
    private IndReservationEntity data3 = null;
    private Set<CourseEntity> data4 = null;
    private OrderEntity data5 = null;
    
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    public void init(){
        System.out.println("irmsSERVLET: init()");
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            
            String temp = request.getServletPath();

            String page = request.getPathInfo();
            page = page.substring(1);
            System.out.println(page);
            
            if("restaurantSearch".equalsIgnoreCase(page))
            {
                System.out.println("*****restaurantSearch*****");
                System.out.println("FBMSServlet: Current page is restaurant!");
                data = searchRestaurant(request);
                System.out.println("data search has been performed and result has been returned by bean");
                System.out.println(request.getParameter("keyword"));
                System.out.println(data.isEmpty());
                request.setAttribute("data", data);
                request.getRequestDispatcher("/restaurantSearch.jsp").forward(request, response);
            }
            else if ("MakeReservation".equals(page))
            {
                System.out.println("*****MakeReservation*****");
                System.out.println("FBMSServlet: Current page is makeReservation");
                data3 = makeReservation(request);
                System.out.println("FBMSServlet: Reservation has been made");
                System.out.println(data1);
                request.setAttribute("data", data1);
                //request..........
                
                
            }
            else if ("checkAvailability".equalsIgnoreCase(page))
            {
                System.out.println("*****checkAvailability*****");
                System.out.println("FBMSServlet: Current page is checkAvailability");
                data1 = checkAvailability(request);
                System.out.println("availability has been checked for the restaurant");
                System.out.println(request.getParameter("restId"));//to be modified
                System.out.println(data1);
                request.setAttribute("data", data1);
                request.getRequestDispatcher("/restaurantCheck.jsp").forward(request,response);
            }
            else if ("restaurantBook".equalsIgnoreCase(page))
            {
                System.out.println("*****restaurantBook*****");
                System.out.println("FBMSServlet: Current page is restaurantBook");
                Long restId = Long.parseLong(request.getParameter("restId"));
                System.out.println("FBMSServlet: the restaurant ID is "+restId);
                data2 = indReservationSessionBean.getRestaurantEntity(restId);
                System.out.println("FBMSServlet: the restaurant has been returned");
                request.setAttribute("data", data2);
                request.getRequestDispatcher("/restaurantBook.jsp").forward(request, response);
                
            }
            else if ("restaurantCheck".equalsIgnoreCase(page))
            {
                System.out.println("*****restaurantCheck*****");
                System.out.println("FBMSServlet: Current page is restaurantCheck");
                Long restId = Long.valueOf(request.getParameter("restId"));
                System.out.println("FBMSServlet: the restaurant ID is "+restId);
                
                if(validationCheck(request))
                {
                    if(request.getParameter("booking").equalsIgnoreCase("true")){
                        data3 = makeReservation(request);
                        
                        if(data3!=null){
                        request.setAttribute("data", data3);
                        System.out.println("data is not null, go to check page");
                        request.getRequestDispatcher("/restaurantCheck.jsp").forward(request, response);

                        }else{
                        System.out.println("data is null,book again");
                        RestaurantEntity thisre = restaurantSessionBean.getRestaurantById(restId);
                        request.setAttribute("data", thisre);
                        request.setAttribute("message","Restaurant is full at the time selected! Please book again!");
                        request.getRequestDispatcher("/restaurantBook.jsp").forward(request, response);
                        }
                    }
                    else
                    {
                        System.out.println("FBMSServlet: going to modify the reservation!");
                        data3 = modifyReservation(request);
                        
                        if(data3!=null){
                        
                        request.setAttribute("data", data3);
                        System.out.println("data is not null, go to check page");
                        request.getRequestDispatcher("/restaurantCheck.jsp").forward(request, response);

                        }else{
                            
                        System.out.println("data is null,modify again");
                        Long reservationId = Long.parseLong(request.getParameter("indReservationId"));
                        data3 = indReservationSessionBean.viewReservation(reservationId);
                        request.setAttribute("data", data3);
                        request.setAttribute("message","Restaurant is full at the time selected! Please book again!");
                        request.getRequestDispatcher("/restaurantIndModify.jsp").forward(request, response);
                        }
                        }
                
                
                }
                
                else 
                {
                    System.out.println("FBMSServlet: Current page is restaurantCheck: validation condition fails");
                    if(request.getParameter("booking").equalsIgnoreCase("false"))
                    {
                        Long currentId = Long.parseLong(request.getParameter("indReservationId"));
                        data3 = indReservationSessionBean.viewReservation(currentId);
                        System.out.println("FBMSServlet: the order has not been modified: it has been returned back");
                        request.setAttribute("data",data3);
                        request.setAttribute("message", "Wrong input format: please check and submit again!");
                        request.getRequestDispatcher("/restaurantIndModify.jsp").forward(request, response);
                    }
                    else
                    {
                        data2 = restaurantSessionBean.getRestaurantById(restId);
                        request.setAttribute("data",data2 );
                        request.setAttribute("message","Wrong input format: please check and submit again!" );
                        request.getRequestDispatcher("/restaurantBook.jsp").forward(request, response);
                    }
                }
            }
            else if ("restaurantIndModify".equalsIgnoreCase(page))
            {
                String type = request.getParameter("type");
                if(type.equalsIgnoreCase("Individual"))
                    {
                    System.out.println("*****restaurantIndModify*****");
                    System.out.println("FBMSServlet: Current page is restaurantIndModify");
                    Long reservationId = Long.parseLong(request.getParameter("reservationId"));
                    System.out.println("FBMSServlet: the reservation confirmation nubmer is "+reservationId);
                    data3 = indReservationSessionBean.viewReservation(reservationId);
                    System.out.println("FBMSServlet: the individual reservation has been returned");
                    request.setAttribute("data", data3);
                    request.getRequestDispatcher("/restaurantIndModify.jsp").forward(request, response);
                    }
                else
                {
                    System.out.println("*****restaurantIndModify*****");
                    System.out.println("FBMSServlet: Current page is restaurantIndModify: Event Catering Modification");
                    Long reservationId = Long.parseLong(request.getParameter("reservationId"));
                    System.out.println("FBMSServlet: the reservation confirmation nubmer is "+reservationId);
                    data5 = orderSessionBean.viewOrder(reservationId);
                    System.out.println("FBMSServlet: the event catering reservation has been returned");
                    request.setAttribute("data", data5);
                    request.getRequestDispatcher("/cateringModify.jsp").forward(request, response);
                }
            
            }
            else if("cateringBook".equalsIgnoreCase(page))
            {
                System.out.println("*****cateringBook*****");
                System.out.println("FBMSServlet: current page is cateringBook");
                request.getRequestDispatcher("/cateringBook.jsp").forward(request, response);
            }
            else if("cateringConfirm".equalsIgnoreCase(page))
            {
                System.out.println("*****cateringConfirm*****");
                System.out.println("FBMSServlet: Current page is cateringConfirm");
                data4 = configureMenu(request);
                System.out.println("FBMSServlet: the menu containing a list of dishes has been returned");
                request.setAttribute("data", data4);
                request.getRequestDispatcher("/cateringConfirm.jsp").forward(request, response);
            }
            else if("cateringCheck".equalsIgnoreCase(page))
            {
                if(validationCheck(request)){
                    

                    if(request.getParameter("booking").equalsIgnoreCase("true")){
                    System.out.println("*****cateringCheck*****");
                    System.out.println("FBMSServlet: Current page is cateringCheck");

                    data5 = cateringReservation(request);
                    System.out.println("FBMSServlet: the order has been confirmed!");
                    
                    if(data5!=null){
                        request.setAttribute("data", data5);
                        System.out.println("FBMSServlet: the data returned is not null!");
                        request.getRequestDispatcher("/cateringCheck.jsp").forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("data", data5);
                        System.out.println("FBMSServlet: the data returned is null");
                        request.setAttribute("message","the catering date you select is not available");
                        request.getRequestDispatcher("/cateringConfirm.jsp").forward(request, response);
                    }
                    }
                
                    else
                    {
                        System.out.println("*****cateringCheck*****");
                        System.out.println("FBMSServlet: Current page is catering Check: Modify catering order");

                        data5 = modifyCatering(request);
                        System.out.println("FBMSServlet: the order has been modified!");
                        request.setAttribute("data", data5);
                        request.getRequestDispatcher("/cateringCheck.jsp").forward(request, response);
                    }
                }
                else
                {
                    System.out.println("FBMSServlet: Current page is cateringCheck: validation condition fails");
                    if(request.getParameter("booking").equalsIgnoreCase("false"))
                    {
                        Long currentId = Long.parseLong(request.getParameter("orderId"));
                        data5 = orderSessionBean.viewOrder(currentId);
                        System.out.println("FBMSServlet: the order has not been modified: it has been returned back");
                        request.setAttribute("data",data5);
                        request.setAttribute("message", "Wrong input format: please check and submit again!");
                        request.getRequestDispatcher("/cateringModify.jsp").forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("message","Wrong input format: please check and submit again!" );
                        request.getRequestDispatcher("/cateringConfirm.jsp").forward(request, response);
                    }
                }
            }
            
            
            else 
            {
                System.out.println("other page");
            }
        }
        catch (Exception e){
            System.out.println(e);
            log("Exception in FBMSServlet.processRequest()");
            //System.out.println(e);
        }
        finally {            
            out.close();
        }
    }
    
    private Set<RestaurantEntity> searchRestaurant(HttpServletRequest request) {
        
        Set <RestaurantEntity> al = new HashSet <RestaurantEntity>();
        System.out.println("method invoked");
        String restNeighbourhood = request.getParameter("restNeighbourhood");
        System.out.println(restNeighbourhood);
        System.out.println("restNeighbourhood retrieved");
        String restTypeOfPlace   = request.getParameter("restTypeOfPlace");
        System.out.println(restTypeOfPlace);
        String restCuisine       = request.getParameter("restCuisine");
        System.out.println(restCuisine);
        String keyword           = request.getParameter("keyword");
        System.out.println(keyword);
        RestaurantEntity re = indReservationSessionBean.createRestaurantEntity(restNeighbourhood, restTypeOfPlace, restCuisine, keyword);
        
        System.out.println(re.getRestNeighbourhood()+re.getRestCuisine()+re.getRestTypeOfPlace());

        Set <RestaurantEntity> res =   indReservationSessionBean.searchRestaurant(re);  
        
        al.addAll(res);
        
      //  System.out.println(al.get(0));
        System.out.println("FBMSServlet: restaurant search has been completed!");



        return al;
        //To change body of generated methods, choose Tools | Templates.
    }
    private boolean checkAvailability (HttpServletRequest request) 
        {
            System.out.println("FBMSServlet CheckAvailability: method invoked");
            
            Long restId = Long.parseLong(request.getParameter("restId"));
            System.out.println("The booking restaurant Id is "+ restId);
            
            Integer numberPeople = Integer.parseInt(request.getParameter("numberPeople"));
            System.out.println("The booking numberPeople is "+numberPeople);
            
            Integer year = Integer.parseInt(request.getParameter("year"));
            System.out.println("The booking year is "+ year);
            
            Integer month = Integer.parseInt(request.getParameter("month"));
            System.out.println("The booking month is "+ month);
            
            Integer date = Integer.parseInt(request.getParameter("date"));
            System.out.println("The booking date is "+ date);
            
            Integer hour = Integer.parseInt(request.getParameter("time"));
            System.out.println("The booking hour is "+hour);
            
            int min = 0;
            
            Date newDate;
            newDate = new Date(year-1900,month-1,date,hour,min); //this one needs to be modified later
            
            RestaurantEntity re = indReservationSessionBean.getRestaurantEntity(restId);
            Boolean isAvailable = indReservationSessionBean.checkAvailability(re, numberPeople, newDate);
            
            System.out.println("FBMSServlet CheckAvailability function: the restaurant availability is "+ isAvailable);
            return isAvailable;
        }
    private IndReservationEntity makeReservation (HttpServletRequest request)
    {
        System.out.println("FBMSServlet makeReservation method invoked ");
        
            Integer year = Integer.parseInt(request.getParameter("year"));
            System.out.println("The booking year is "+ year);
            
            Integer month = Integer.parseInt(request.getParameter("month"));
            System.out.println("The booking month is "+ month);
            
            Integer date = Integer.parseInt(request.getParameter("date"));
            System.out.println("The booking date is "+ date);
            
            Integer hour = Integer.valueOf(request.getParameter("time"));
            System.out.println("The booking hour is "+hour);
            
            int min = 0;
            
            Date thisDate;
            thisDate = new Date(year-1900,month-1,date,hour,min);
        
        System.out.println("FBMSServlet makeReservation: date has been retrieved!");
            
            Long restId = Long.parseLong(request.getParameter("restId"));
            System.out.println("The booking restaurant Id is "+ restId);
            
            Integer numberPeople = Integer.parseInt(request.getParameter("numberPeople"));
            System.out.println("The booking numberPeople is "+numberPeople);
            
        System.out.println("FBMSServlet makeReservation: rest and number has been retrieved!");
        
        String title = request.getParameter("title");
        System.out.println("The booking people title is "+title);
        
        String name = request.getParameter("name");
        System.out.println("The booking people name is "+name);
        
        String email = request.getParameter("email");
        System.out.println("The booking people email is "+email);
        
        String mobile = request.getParameter("mobile");
        System.out.println("The booking people mobile is "+mobile);
        
        String notes = request.getParameter("notes");
        System.out.println("The booking people's notes is "+notes);
        
       // RestaurantEntity re = indReservationSessionBean.getRestaurantEntity(restId);
        Boolean isAvailable = checkAvailability(request);
        
        if(isAvailable){
        
        IndReservationEntity correctBooking = indReservationSessionBean.makeReservation(thisDate, restId, numberPeople, title, name, email, mobile, notes);
        
        Boolean thisBooking = indReservationSessionBean.confirmReservation(correctBooking);
        System.out.println("The individual restaurant order has been confirmed? "+thisBooking);
        System.out.println("The restaurant booking made? "+ thisBooking );
        
        emailSessionBean.sendConfirmation(email, correctBooking);
        
        return correctBooking;
        }
        
        else return null;
    }

    private Set <CourseEntity> configureMenu (HttpServletRequest request)
    {
        
        System.out.println("FBMSServlet ConfigureMenu Method Invoked!");
        MenuEntity me = new MenuEntity();
        System.out.println("FBMSServlet: The menu has been initialized "+me.getMenuId());
        Set <CourseEntity> courses = new HashSet <CourseEntity>();
        Integer courseNumber = Integer.parseInt(request.getParameter("courseNumber"));
        System.out.println("FBMSServlet: the course number has been retrieved to be "+courseNumber);
        int i = 1;
        
        while(i<=courseNumber)
        {
            CourseEntity course = new CourseEntity();
            Long dishId = Long.parseLong(request.getParameter("dish"+i));
            dishId += 100000;
            System.out.println("FBMSServlet: the current dishId is "+dishId);
            DishEntity de = orderSessionBean.getDish(dishId);
            System.out.println("FBMSServlet: the dish entity has been found "+de.getDishName());
            
            if(de!=null){
            
               // System.out.println("FBMSServlet: the course entity id is "+ course.getCourseId());
                course.setDish(de);
                System.out.println("FBMSServlet: the course entity associated dish has been found"+ course.getDish().getDishName()+course.getCourseId());
                /* course.setMenu(me);
                System.out.println("FBMSServlet: the dish entity's menu has been set"+ course.getMenu().getMenuId()); 
               courses.add(course); */
               
          //  System.out.println("FBMSServlet: the current element is "+me.getCourses().size());
                System.out.println(courses.contains(course));
 //               System.out.println(courses.add(course));//add course entity as POJO into the list
 //               System.out.println(orderSessionBean.setCourse(course));//persist course entity
                CourseEntity newCourse = orderSessionBean.setCourse(course);
                System.out.println(newCourse);
                System.out.println(courses.add(newCourse));
                
                System.out.println("FBMSServlet: courses have been added new course: "+courses.size());
                i++;
            System.out.println("FBMSServlet: while loop has proceeded to "+i);
            //System.out.println("FBMSServlet: the current element is "+me.getDishes().size());
            course.setMenu(me);//menu haven't been persisted, so this should be null
            }
            else {
                i++;
                continue;
            }
        }
        
        boolean isMenuSet = orderSessionBean.configureMenu(courses);//pass a list of POJO, call next method
//      System.out.println("FBMSServlet: menu has been configured: "+me.getCourses().size());
        System.out.println("FBMSServlet: the menu has been configured!" + isMenuSet);
        return courses;
    }
    
    private IndReservationEntity modifyReservation(HttpServletRequest request) throws ExistException
    {
        System.out.println("FBMSServlet: the modifyReservation method has been invoked");
         Long restId = Long.parseLong(request.getParameter("restId"));
            System.out.println("The booking restaurant Id is "+ restId);
            RestaurantEntity thisRe = restaurantSessionBean.getRestaurantById(restId);
            
            Integer numberPeople = Integer.parseInt(request.getParameter("numberPeople"));
            System.out.println("The booking numberPeople is "+numberPeople);
           
        System.out.println("FBMSServlet makeReservation: rest and number has been retrieved!");
        
        Long indReservationId = Long.parseLong(request.getParameter("indReservationId"));
        System.out.println("The indReservationid is "+indReservationId);
        IndReservationEntity ire = indReservationSessionBean.viewReservation(indReservationId);
        
        Integer year = Integer.parseInt(request.getParameter("year"));
            System.out.println("The booking year is "+ year);
            
            Integer month = Integer.parseInt(request.getParameter("month"));
            System.out.println("The booking month is "+ month);
            
            Integer date = Integer.parseInt(request.getParameter("date"));
            System.out.println("The booking date is "+ date);
            
            Integer hour = Integer.valueOf(request.getParameter("time"));
            System.out.println("The booking hour is "+hour);
            
            int min = 0;
            
            Date thisDate;
            thisDate = new Date(year-1900,month-1,date,hour,min);
        
        System.out.println("FBMSServlet makeReservation: date has been retrieved!");
        
        
        
        
        String title = request.getParameter("title");
        System.out.println("The booking people title is "+title);
        
        String name = request.getParameter("name");
        System.out.println("The booking people name is "+name);
        
        String email = request.getParameter("email");
        System.out.println("The booking people email is "+email);
        
        String mobile = request.getParameter("mobile");
        System.out.println("The booking people mobile is "+mobile);
        
        String notes = request.getParameter("notes");
        System.out.println("The booking people's notes is "+notes);
        
        Integer newAdd = numberPeople - ire.getNumberPeople();
        System.out.println("Modify function: new add people "+newAdd);
        
        Boolean isAvailable = indReservationSessionBean.checkAvailability(thisRe, newAdd, thisDate);
        
        if(isAvailable){
        
        IndReservationEntity correctBooking = indReservationSessionBean.modifyReservation("Confirmed", restId, thisDate, indReservationId, numberPeople, title, name, email, mobile, notes);
        
        Boolean thisBooking = indReservationSessionBean.confirmReservation(correctBooking);
        System.out.println("The individual restaurant order has been confirmed? "+thisBooking);
        System.out.println("The restaurant booking made? "+ thisBooking );
        
        emailSessionBean.sendConfirmation(email, correctBooking);
        
        return correctBooking;
        }
        
        else return null;
         
        
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private OrderEntity cateringReservation(HttpServletRequest request) {
        
        System.out.println("FBMSServlet: cateringReservation method invoked!");
        
       
        System.out.println("FBMSServlet cateringReservation method invoked ");
        
            Integer year = Integer.parseInt(request.getParameter("year"));
            System.out.println("The booking year is "+ year);
            
            Integer month = Integer.parseInt(request.getParameter("month"));
            System.out.println("The booking month is "+ month);
            
            Integer date = Integer.parseInt(request.getParameter("date"));
            System.out.println("The booking date is "+ date);
            
            Integer hour = Integer.valueOf(request.getParameter("time"));
            System.out.println("The booking hour is "+hour);
            
            int min = 0;
            
            Date thisDate;
            thisDate = new Date(year-1900,month-1,date,hour,min);
        
        System.out.println("FBMSServlet cateringReservation: date has been retrieved!");
        
        Integer numberPeople = Integer.parseInt(request.getParameter("numberPeople"));
            System.out.println("The booking numberPeople is "+numberPeople);
       
            MenuEntity me = new MenuEntity();
            /* Long menuId = Long.valueOf(request.getParameter("menuId"));
            System.out.println("FBMSServlet: menuId has been retrieved "+menuId);*/
            
           // me = orderSessionBean.getMenu(menuId);
            me.setNumberOrder(Integer.parseInt(request.getParameter("numberPeople")));
            System.out.println("FBMSServlet: numberPeople has been set"+me.getNumberOrder());
            
            me.setCourses(data4);
            System.out.println("FBMSServlet: courses has been set "+me.getCourses().size());
            
           Iterator <CourseEntity> itr = data4.iterator();
            while(itr.hasNext())
            {
                CourseEntity ce = itr.next();
                ce.setMenu(me);
                System.out.println("FBMSServlet: CourseEntity's datafield menu has been set");
                ce.setQuantity(numberPeople);
                System.out.println("FBMSServlet: CourseEntity's datafield numberPeople has been set");
                orderSessionBean.modifyCourse(ce);
                
            }
            
            orderSessionBean.configureMenu(me);
            System.out.println("FBMSServlet: menu has been manipulated completely!"+ me.getNumberOrder());
            
            
            
            
        String title = request.getParameter("title");
        System.out.println("The booking people title is "+title);
        
        String name = request.getParameter("name");
        System.out.println("The booking people name is "+name);
        
        String email = request.getParameter("email");
        System.out.println("The booking people email is "+email);
        
        String mobile = request.getParameter("mobile");
        System.out.println("The booking people mobile is "+mobile);
        
        String notes = request.getParameter("notes");
        System.out.println("The booking people's notes is "+notes);  
        
        OrderEntity oe = new OrderEntity(thisDate,me,"Confirmed",title,name,email,mobile,notes);
        
        boolean isConfirmed = orderSessionBean.placeOrder(oe);
        
        if(isConfirmed)
        {
            System.out.println("The catering order has been confirmed? "+isConfirmed);
        
             emailSessionBean.sendConfirmation(email,oe );
            return oe;
        }
       
        else return null;
        //别忘了发email！！！
        
    }

    private OrderEntity modifyCatering(HttpServletRequest request) {
        OrderEntity oe = null; 
        System.out.println("FBMSServlet: modifyCatering function has been invoked!");
            
            Integer year = Integer.parseInt(request.getParameter("year"));
            System.out.println("The booking year is "+ year);
            
            Integer month = Integer.parseInt(request.getParameter("month"));
            System.out.println("The booking month is "+ month);
            
            Integer date = Integer.parseInt(request.getParameter("date"));
            System.out.println("The booking date is "+ date);
            
            Integer hour = Integer.valueOf(request.getParameter("time"));
            System.out.println("The booking hour is "+hour);
            
            int min = 0;
            
            Date thisDate;
            thisDate = new Date(year-1900,month-1,date,hour,min);
            
        System.out.println("FBMSServlet makeReservation: date has been retrieved!");
        
            String title = request.getParameter("title");
        System.out.println("The booking people title is "+title);
        
        String name = request.getParameter("name");
        System.out.println("The booking people name is "+name);
        
        String email = request.getParameter("email");
        System.out.println("The booking people email is "+email);
        
        String mobile = request.getParameter("mobile");
        System.out.println("The booking people mobile is "+mobile);
        
        String notes = request.getParameter("notes");
        System.out.println("The booking people's notes is "+notes);
        
        Integer numberOrder = Integer.parseInt(request.getParameter("numberOrder"));
        System.out.println("The booking quantity is "+numberOrder);
        
        Long orderId = Long.parseLong(request.getParameter("orderId"));
        System.out.println("The booking orderId is "+orderId);
        
        oe = orderSessionBean.modifyOrder(orderId, email, mobile, name, notes, thisDate, title, numberOrder);
        System.out.println("FBMSServlet: the booking has been modified!");
        
        //oe = orderSessionBean.viewOrder(orderId);
        
        emailSessionBean.sendConfirmation(email, oe);
        
        return oe;
    }

    private boolean validationCheck(HttpServletRequest request) {
       boolean isValid = true; 
       System.out.println("FBMSServlet validationCheck method invoked ");
        
            Integer year = Integer.parseInt(request.getParameter("year"));
            System.out.println("The booking year is "+ year);
            
            Integer month = Integer.parseInt(request.getParameter("month"));
            System.out.println("The booking month is "+ month);
            
            Integer date = Integer.parseInt(request.getParameter("date"));
            System.out.println("The booking date is "+ date);
            
            Integer hour = Integer.valueOf(request.getParameter("time"));
            System.out.println("The booking hour is "+hour);
            
            int min = 0;
            
            Date thisDate;
            thisDate = new Date(year-1900,month-1,date,hour,min);
            
            Date now = new Date();//This data can be modified!! Do not forget when demo!!
            
            if(thisDate.before(now))
                isValid = false;
        
        System.out.println("FBMSServlet makeReservation: date has been validated!" + isValid);
        
        
        return isValid;
    }
    
    
}
