
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import CEMS.entity.EventEntity;
import CEMS.session.EventSessionBean;
import ESMS.entity.ShowEntity;
import ESMS.entity.ShowScheduleEntity;
import ESMS.entity.ShowTicketEntity;
import ESMS.session.ShowScheduleSessionBean;
import ESMS.session.ShowSessionBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
@WebServlet(name = "ESMSServlet", urlPatterns = {"/ESMSServlet", "/ESMSServlet/*"})
public class ESMSServlet extends HttpServlet {
    @EJB
    private ShowScheduleSessionBean showScheduleSessionBean;
    @EJB
    private EventSessionBean eventSessionBean;

    @EJB
    private ShowSessionBean showSessionBean;
    
    private List<ShowEntity> shows;
    private List<ShowScheduleEntity> showSchedule;
    private Long showId;
    private EventEntity ee;
    private List<ShowTicketEntity> showTickets;

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
                    shows = showSessionBean.getAllShows();
                    request.setAttribute("shows", shows);
                    request.getRequestDispatcher("/entertainment.jsp").forward(request, response);
                } else if ("entertainmentSchedule".equals(page)) {

                    System.out.println("***entertainmentSchedule***");
                    showId = Long.parseLong(request.getParameter("showId"));
                    session.setAttribute("thisShow", showSessionBean.getShowById(showId));
                    showSchedule = showSessionBean.getAllShowSchedules(showId);
                    System.out.println(showSchedule.isEmpty());
                    System.out.println(showId);
                    System.out.println(showSchedule.size());
                    request.setAttribute("showSchedules", showSchedule);
                    //below set show ticket types and return back to jsp
                    ShowScheduleEntity thisShowSchedule = showScheduleSessionBean.getShowScheduleById(Long.parseLong("4"));
                    showTickets = thisShowSchedule.getShowTickets();
                    System.out.println(showTickets.isEmpty());
                    System.out.println(showTickets.size());
                    
                    //below retrieve every ticket type and set into attribute
                    if(!showTickets.isEmpty())
                    {
                    Iterator<ShowTicketEntity> itr =    showTickets.iterator();
                    int i = 1;
                    while(itr.hasNext())
                    {
                        ShowTicketEntity current = itr.next();
                        request.setAttribute("showTicket"+i, current);
                        System.out.println("Current ticket retrieved is "+i);
                        System.out.println(current.getShowTicketPrice());
                    }
                    }
//                    request.setAttribute("showTickets", showTickets);
                    request.getRequestDispatcher("/entertainmentSchedule.jsp").forward(request, response);
                } else if ("entertainmentVenue".equals(page)) {
                    System.out.println("***entertainmentVenue***");

                    request.getRequestDispatcher("/entertainmentVenue.jsp").forward(request, response);
                } else if ("entertainmentPay".equals(page)) {
                    System.out.println("***entertainmentPay***");

                    request.getRequestDispatcher("/entertainmentPay.jsp").forward(request, response);
                } else if ("entertainmentRegisterResult".equals(page)) {
                    System.out.println("***entertainmentRegisterResult***");
                     System.out.println("***entertainmentRegister***");
                    ee = new EventEntity();
          
                    String eventName = request.getParameter("eventName");
                    ee.setEventName(eventName);
                    String name = request.getParameter("name");
                    ee.setName(name);
                    String eventType="show";
                    ee.setEventType(eventType);
                    String email = request.getParameter("e-mail");
                    ee.setEmail(email);
                    String phoneNumber = request.getParameter("contact");
                    ee.setEventContact(phoneNumber);
                    String description=request.getParameter("description");
                    ee.setDescription(description);
                    String Status="pending";
                    ee.setStatus(Status);
                    eventSessionBean.addEvent(ee);
                    request.getRequestDispatcher("/entertainmentRegisterResult.jsp").forward(request, response);
                }else if ("entertainmentRegister".equals(page)) {
          
                    
                  
                    request.getRequestDispatcher("/entertainmentRegister.jsp").forward(request, response);
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
