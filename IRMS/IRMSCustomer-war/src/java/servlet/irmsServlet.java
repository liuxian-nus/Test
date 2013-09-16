package servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import FBMS.session.IndReservationSessionBeanRemote;
import FBMS.session.RestaurantSessionBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(urlPatterns = {"/irmsServlet"})
public class irmsServlet extends HttpServlet {
    @EJB
    private IndReservationSessionBeanRemote indReservationSessionBean;
    @EJB
    private RestaurantSessionBeanRemote restaurantSessionBean;
    private ArrayList data=null;
  
    

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
        System.out.println("irmsSERVLET: processRequest()");
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            
            String page = request.getPathInfo();
            page = page.substring(1);
            
            if("restaurant".equals(page)){
            
               //data = irm.searchRestaurant(null)
            } else{
                page="Error";
            }
            dispatcher=servletContext.getNamedDispatcher(page);
            if(dispatcher==null){
                dispatcher=servletContext.getNamedDispatcher("Error");
            }
            dispatcher.forward(request, response);
            
        }catch (Exception e){
            log("Exception in irmsServlet.processRequest()");
        }
    }
    
    private ArrayList 
            
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