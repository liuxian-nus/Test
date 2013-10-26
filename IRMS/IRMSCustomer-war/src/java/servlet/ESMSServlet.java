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
 * @author lionetdd
 */
@WebServlet(name = "ESMSServlet", urlPatterns = {"/ESMSServlet","/ESMSServlet/*"})
public class ESMSServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            System.out.println("ESMSERVLET: processRequest()");
            HttpSession session = request.getSession(true);

            /* response.setContentType("text/html;charset=UTF-8");
             PrintWriter out = response.getWriter();*/
            try {
                RequestDispatcher dispatcher;
                ServletContext servletContext = getServletContext();

                String temp = request.getServletPath();

                String page = request.getPathInfo();
                page = page.substring(1);
                System.out.println("ESMSServlet page: " + page);


                if ("entertainment".equals(page)) {
                    System.out.println("***entertainment***");

                    request.getRequestDispatcher("/entertainment.jsp").forward(request, response);
                } else if ("entertainmentVenue".equals(page)) {
                    System.out.println("***entertainmentVenue***");

                    request.getRequestDispatcher("/entertainmentVenue.jsp").forward(request, response);
                } else {
                    System.out.println("other page");
                }
            } catch (Exception e) {
                System.out.println(e);
                log("Exception in ACMSServlet.processRequest()");
                //System.out.println(e);
            }

        } finally {
            out.close();
        }
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