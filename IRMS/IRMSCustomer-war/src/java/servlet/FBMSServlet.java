/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import FBMS.entity.RestaurantEntity;
import FBMS.session.IndReservationSessionBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Diana Wang
 */
@WebServlet(name = "FBMSServlet", urlPatterns = {"/FBMSServlet","/FBMSServlet/*"})
public class FBMSServlet extends HttpServlet {
    @EJB
    private IndReservationSessionBeanRemote indReservationSessionBean;

    private Set<RestaurantEntity> data = null;
    private boolean data1;
    
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
                
            }
            else if ("checkAvailability".equalsIgnoreCase(page))
            {
                System.out.println("*****CheckAvailability*****");
                System.out.println("FBMSServlet: Current page is checkAvailability");
                data1 = checkAvailability(request);
                System.out.println("availability has been checked for the restaurant");
                System.out.println(request.getParameter("restId"));//to be modified
                System.out.println(data.isEmpty());
                request.setAttribute("data", data);
                //getRequestDispatcher........
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
        System.out.println("irmsServlet: restaurant search has been completed!");



        return al;
        //To change body of generated methods, choose Tools | Templates.
    }
    private boolean checkAvailability (HttpServletRequest request) 
        {
            
            return true;
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
}
