/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import SMMS.session.OutletSessionBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author lionetdd
 */
import SMMS.entity.OutletEntity;
import java.util.Set;
@WebServlet(name = "SMMSServlet", urlPatterns = {"/SMMSServlet/*","/SMMSServlet"})
public class SMMSServlet extends HttpServlet {
    @EJB
    private OutletSessionBean outletSessionBean;

     private List<OutletEntity> LingrieList = null;

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
        System.out.println("SMMSERVLET: init()");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("SMMSERVLET: processRequest()");
        HttpSession session = request.getSession(true);

        /* response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();*/
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String temp = request.getServletPath();
            String page = request.getPathInfo();
            page = page.substring(1);
            System.out.println("SMMSServlet page: " + page);


            if ("shopping".equals(page)) {
                System.out.println("***shopping***");
 
                LingrieList =outletSessionBean.getOutletsByType("Lingerie");
                System.out.println(outletSessionBean.getOutletsByType("Lingerie"));
                request.setAttribute("LingrieList",LingrieList);
                request.getRequestDispatcher("/shopping.jsp").forward(request, response);
            } 
        } catch (Exception e) {
            System.out.println(e);
            log("Exception in SMMSServlet.processRequest()");
            //System.out.println(e);
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
         *
         */
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
        System.out.println("SMMServlet: destroy()");
    }

   
}
