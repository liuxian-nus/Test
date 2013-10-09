/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Jieqiong
 */
//@WebServlet(name = "ATMSServlet", urlPatterns = {"/ATMSServlet"})
@WebServlet(urlPatterns = {"/ATMSServlet", "/ATMSServlet/*"})
public class ATMSServlet extends HttpServlet {

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
        System.out.println("ATMSSERVLET: init()");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("ATMSSERVLET: processRequest()");
   //     HttpSession session = request.getSession(true);
        
        
        /*response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();*/
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();

            String temp = request.getServletPath();

            String page = request.getPathInfo();
            page = page.substring(1);
            System.out.println("ATMSServlet page: " + page);
            
            if ("attraction".equals(page)) {
                System.out.println("***attraction page***");
                

                request.getRequestDispatcher("/attraction.jsp").forward(request, response);
            }else if("adventureWorld".equals(page)){
                 System.out.println("***adventureWorld page***");
                

                request.getRequestDispatcher("/adventureWorld.jsp").forward(request, response);
            }else if("ticketBooking".equals(page)){
                System.out.println("***ticketBooking page***");
                

                request.getRequestDispatcher("/ticketBooking.jsp").forward(request, response);
            }else {
                System.out.println("other page");
            }
        } catch (Exception e) {
            System.out.println(e);
            log("Exception in ATMSServlet.processRequest()");
            //System.out.println(e);
        }       
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
        System.out.println("ATMSServlet: destroy()");
    }

   /* @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>*/
}
