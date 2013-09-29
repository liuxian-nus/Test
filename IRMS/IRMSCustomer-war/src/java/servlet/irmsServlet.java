package servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import FBMS.entity.RestaurantEntity;
import FBMS.session.IndReservationSessionBeanRemote;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
 * @author lionetdd
 */
@WebServlet(urlPatterns = {"/irmsServlet", "/irmsServlet/*"})
public class irmsServlet extends HttpServlet {

    @EJB
    private IndReservationSessionBeanRemote indReservationSessionBean;
    private Set<RestaurantEntity> data = null;

    //private String keyword=null;
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
    public void init() {
        System.out.println("irmsSERVLET: init()");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("irmsSERVLET: processRequest()");

        /* response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();*/
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();

            String temp = request.getServletPath();

            String page = request.getPathInfo();
            page = page.substring(1);
            System.out.println("page:" + page);
            


            if ("restaurantSearch".equals(page)) {

                System.out.println("irmsServlet: restaurantSearch method invoked");
                request.getRequestDispatcher("/FBMSServlet/restaurantSearch").forward(request, response);

            } else if ("MakeReservation".equals(page)) {
                //data=makeReservation(request);
                //request.setAttribute("data", data);
            } else if ("restaurant".equals(page)) {
                System.out.println("***restaurant page***");

                //System.out.println(request.getParameterValues("keyword"));

                request.getRequestDispatcher("/restaurant.jsp").forward(request, response);

            } else if ("restaurantCheck".equalsIgnoreCase(page)) {
                System.out.println("irmsServlet: ******restaurantCheck******");
                System.out.println(request.getParameter("restId"));

                request.getRequestDispatcher("/FBMSServlet/restaurantCheck").forward(request, response);
            } else if ("restaurantBook".equalsIgnoreCase(page)) {
                System.out.println("irmsServlet: ******restaurantBook******");
                System.out.println(request.getParameter("restId"));
                request.getRequestDispatcher("/FBMSServlet/restaurantBook").forward(request, response);
            } else if ("restaurantIndModify".equalsIgnoreCase(page)) {
                System.out.println("irmsServlet: *****restaurantIndModify*****");
                System.out.println(request.getParameter("type"));
                System.out.println(request.getParameter("reservationId"));
                request.getRequestDispatcher("/FBMSServlet/restaurantIndModify").forward(request, response);
            } else if ("checkAvailability".equals(page)) {
                System.out.println("***checkAvailability page***");
                System.out.println(request.getParameter("restId"));

                //System.out.println(request.getParameterValues("keyword"));

                request.getRequestDispatcher("/FBMSServlet/checkAvailability").forward(request, response);

            } else if ("cateringBook".equals(page)) {
                System.out.println("***cateringBook page***");

                //System.out.println(request.getParameterValues("keyword"));

                request.getRequestDispatcher("/FBMSServlet/cateringBook").forward(request, response);

            } else if ("cateringConfirm".equals(page)) {
                System.out.println("***cateringConfirm page***");

                //System.out.println(request.getParameterValues("keyword"));

                request.getRequestDispatcher("/FBMSServlet/cateringConfirm").forward(request, response);

            } else if ("cateringCheck".equals(page)) {
                System.out.println("***cateringCheck page***");

                //System.out.println(request.getParameterValues("keyword"));

                request.getRequestDispatcher("/FBMSServlet/cateringCheck").forward(request, response);

            } else if ("home".equals(page)) {
                System.out.println("***home page***");
                request.getRequestDispatcher("/home.jsp").forward(request, response);

            } else if ("hotel".equals(page)) {
                System.out.println("***hotel page***");
                request.getRequestDispatcher("/hotel.jsp").forward(request, response);

            } else if ("entertainment".equals(page)) {
                System.out.println("***entertainment page***");
                request.getRequestDispatcher("/entertainment.jsp").forward(request, response);

            } else if ("entertainment".equals(page)) {
                System.out.println("***entertainment page***");
                request.getRequestDispatcher("/entertainment.jsp").forward(request, response);

            } else if ("member".equals(page)) {
                //        System.out.println(request.getParameter("email"));
                request.getRequestDispatcher("/CRMServlet/member").forward(request, response);

            } else if ("memberInfo".equals(page)) {
                System.out.println("***irmsServlet memberInfo page***");
                request.getRequestDispatcher("/CRMServlet/memberInfo").forward(request, response);
            } else if ("resetMemberPassword".equals(page)) {
                System.out.println("***irmsServlet resetMemberPassword page***");
                request.getRequestDispatcher("/CRMServlet/resetMemberPassword").forward(request, response);
            }else if ("resetMemberPasswordConfirmation".equals(page)) {
                System.out.println("***irmsServlet resetMemberPasswordConfirmation page***");
                request.getRequestDispatcher("/CRMServlet/resetMemberPasswordConfirmation").forward(request, response);
            }            
            else if ("memberRegister".equals(page)) {
                System.out.println("***irmsServlet memberRegister page***");
                request.getRequestDispatcher("/CRMServlet/memberRegister").forward(request, response);

            } else if ("memberRegisterResult".equals(page)) {
                request.getRequestDispatcher("/CRMServlet/memberRegisterResult").forward(request, response);

            } else if ("memberForgetPassword".equals(page)) {
                request.getRequestDispatcher("/CRMServlet/memberForgetPassword").forward(request, response);

            } else if ("memberForgetPasswordResult".equals(page)) {
                request.getRequestDispatcher("/CRMServlet/memberForgetPasswordResult").forward(request, response);
            } else if ("memberInfoEditionConfirmation".equals(page)) {
                request.getRequestDispatcher("/CRMServlet/memberInfoEditionConfirmation").forward(request, response);
            } else if ("accessDenied".equals(page)) {
                request.getRequestDispatcher("/CRMServlet/accessDenied").forward(request, response);
            } else {
                System.out.println("other page");

                //Below is testing
                /*
                 Integer Date = Integer.parseInt(request.getParameter("date"));
                 System.out.println("The value passed in for date is : "+Date);
                
                 Integer Month = Integer.parseInt(request.getParameter("month"));
                 System.out.println("The value passed in for month is : "+Month);
                
                 Integer Year = Integer.parseInt(request.getParameter("year"));
                 System.out.println("The value passed in for year is : "+Year);
                
                 Integer Time = Integer.parseInt(request.getParameter("time"));
                 System.out.println("The value passed in for year is : "+Time);
                 */
            }
//          
//             
//            dispatcher=servletContext.getNamedDispatcher(page);
//            System.out.println("dispatcher set up");
//            System.out.println(dispatcher);
//            if(dispatcher==null){
//                dispatcher=servletContext.getNamedDispatcher("Error");
//            }
//            System.out.println("Before push content");
//            dispatcher.forward(request, response);
//            System.out.println("After push content");



        } catch (Exception e) {
            System.out.println(e);
            log("Exception in irmsServlet.processRequest()");
            //System.out.println(e);
        }
    }

    private ArrayList makeReservation(HttpServletRequest request) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        ArrayList al = new ArrayList();
        Date indReservationDateTime = formatter.parse(request.getParameter("indReservationDateTime"));

        return al;

    }

    /* TODO output your page here. You may use following sample code. */
    /* TODO output your page here. You may use following sample code. */

    /*
     out.println("<!DOCTYPE html>");
     out.println("<html>");
     out.println("<head>");
     out.println("<title>Servlet irmsServlet</title>");            
     out.println("</head>");
     out.println("<body>");
     out.println("<h1>Servlet irmsServlet at " + request.getContextPath() + "</h1>");
     out.println("</body>");
     out.println("</html>");
     * */
    /*} finally {            
     out.close();
     }*/
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
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Set<RestaurantEntity> searchRestaurant(HttpServletRequest request) {

        Set<RestaurantEntity> al = new HashSet<RestaurantEntity>();
        System.out.println("method invoked");
        String restNeighbourhood = request.getParameter("restNeighbourhood");
        System.out.println(restNeighbourhood);
        System.out.println("restNeighbourhood retrieved");
        String restTypeOfPlace = request.getParameter("restTypeOfPlace");
        System.out.println(restTypeOfPlace);
        String restCuisine = request.getParameter("restCuisine");
        System.out.println(restCuisine);
        String keyword = request.getParameter("keyword");
        System.out.println(keyword);
        RestaurantEntity re = indReservationSessionBean.createRestaurantEntity(restNeighbourhood, restTypeOfPlace, restCuisine, keyword);

        System.out.println(re.getRestNeighbourhood() + re.getRestCuisine() + re.getRestTypeOfPlace());

        Set<RestaurantEntity> res = indReservationSessionBean.searchRestaurant(re);

        al.addAll(res);

        //  System.out.println(al.get(0));
        System.out.println("irmsServlet: restaurant search has been completed!");



        return al;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("irmsServlet: destroy()");
    }
}