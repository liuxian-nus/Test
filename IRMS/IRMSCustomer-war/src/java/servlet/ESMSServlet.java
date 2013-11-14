
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ACMS.entity.ReservationEntity;
import CEMS.entity.EventEntity;
import CEMS.session.EventSessionBean;
import CRMS.entity.CouponTypeEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.PromotionEntity;
import CRMS.session.PromotionSessionBean;
import ESMS.entity.ShowEntity;
import ESMS.entity.ShowScheduleEntity;
import ESMS.entity.ShowTicketEntity;
import ESMS.entity.ShowTicketSaleEntity;
import ESMS.session.ShowScheduleSessionBean;
import ESMS.session.ShowSessionBean;
import ESMS.session.ShowTicketSaleSessionBean;
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
    private PromotionSessionBean promotionSessionBean;
    @EJB
    private ShowTicketSaleSessionBean showTicketSaleSessionBean;
    @EJB
    private ShowScheduleSessionBean showScheduleSessionBean;
    @EJB
    private EventSessionBean eventSessionBean;
    @EJB
    private ShowSessionBean showSessionBean;
    private List<ShowEntity> shows;
    private List<ShowScheduleEntity> showSchedule;
    private Long showId;
    private Long scheduleId;
    private EventEntity ee;
    private List<ShowTicketEntity> showTickets;
    private ShowTicketEntity thisShowTicket;
    private int ticket1;
    private int ticket2;
    private int ticket3;
    private int ticket4;
    private int ticket5;
    private int ticket6;
    private ShowScheduleEntity thisShowSchedule;
    private ShowTicketSaleEntity thisShowTicketSale;
    private ShowEntity thisShow;
    private PromotionEntity promotion;
    List<ShowTicketSaleEntity> totalTickets;
    List <ShowTicketSaleEntity> purchasedTickets;
    private  Double ticTotal;

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
                    shows = showSessionBean.getAvailableShows();
                    request.setAttribute("shows", shows);
                    request.getRequestDispatcher("/entertainment.jsp").forward(request, response);
                } else if ("entertainmentSchedule".equals(page)) {

                    System.out.println("***entertainmentSchedule***");

                    showId = Long.parseLong(request.getParameter("showId"));
                    thisShow = showSessionBean.getShowById(showId);
                    session.setAttribute("thisShow", thisShow);
                    showSchedule = showSessionBean.getAllShowSchedules(showId);
                    System.out.println(showSchedule.isEmpty());
                    System.out.println(showId);
                    System.out.println(showSchedule.size());
                    request.setAttribute("showSchedules", showSchedule);

                    request.getRequestDispatcher("/entertainmentSchedule.jsp").forward(request, response);
                } else if ("entertainmentVenue".equals(page)) {
                    System.out.println("***entertainmentVenue***");
                    //below set show ticket types and return back to jsp
                    System.out.println(request.getParameter("scheduleId"));
                    scheduleId = Long.valueOf(request.getParameter("scheduleId"));
                    System.out.println(scheduleId);
                    thisShowSchedule = showScheduleSessionBean.getShowScheduleById(scheduleId);
                    session.setAttribute("thisSchedule", thisShowSchedule);
                    showTickets = thisShowSchedule.getShowTickets();
                    System.out.println(showTickets.isEmpty());
                    System.out.println(showTickets.size());

                    //below retrieve every ticket type and set into attribute
                    if (!showTickets.isEmpty()) {
                        Iterator<ShowTicketEntity> itr = showTickets.iterator();
                        int i = 1;
                        while (itr.hasNext()) {

                            ShowTicketEntity current = itr.next();
                            request.setAttribute("showTicket" + i, current);
                            System.out.println("Current ticket retrieved is " + i);
                            System.out.println(current.getShowTicketPrice());
                            i++;
                        }
                    }
//                    request.setAttribute("showTickets", showTickets);
                    request.getRequestDispatcher("/entertainmentVenue.jsp").forward(request, response);
                } else if ("entertainmentPay".equals(page)) {
                    System.out.println("***entertainmentPay***");
                    ticket1 = Integer.parseInt(request.getParameter("ticket1"));
                    ticket2 = Integer.parseInt(request.getParameter("ticket2"));
                    ticket3 = Integer.parseInt(request.getParameter("ticket3"));
                    ticket4 = Integer.parseInt(request.getParameter("ticket4"));
                    ticket5 = Integer.parseInt(request.getParameter("ticket5"));
                    ticket6 = Integer.parseInt(request.getParameter("ticket6"));

                    ticTotal = 0.00;
                    List<ShowTicketEntity> list = thisShowSchedule.getShowTickets();
                    Iterator<ShowTicketEntity> itr = list.iterator();

                    List<Integer> totalQuant = new ArrayList();
                    totalTickets = new ArrayList();
                    int i = 1;
                    MemberEntity thisMember = (MemberEntity) session.getAttribute("member");
                    while (itr.hasNext()) {
                        ShowTicketEntity current = itr.next();
                        System.out.println("A ticket has been retrieved!" + i);
                        ticTotal += current.getShowTicketPrice() * Integer.parseInt(request.getParameter("ticket" + i));
                        totalQuant.add(Integer.parseInt(request.getParameter("ticket" + i)));

                        ShowTicketSaleEntity currentSale = new ShowTicketSaleEntity();
                        currentSale.setShow(thisShow);
                        currentSale.setShowStartDateTime(thisShowSchedule.getStartDateTime());
                        // thisShowTicketSale.setShowTicketPrice(thisShowSchedule.getShowTickets());
                        currentSale.setShowTicketType(current.getShowTicketType());
                        currentSale.setShowTicketQuantity(Integer.parseInt(request.getParameter("ticket" + i)));
                        currentSale.setShowTicketPrice(current.getShowTicketPrice() * Integer.parseInt(request.getParameter("ticket" + i)));
                         
                        if (thisMember != null) {
                            
                            thisShowTicketSale.setMemberEmail(thisMember.getMemberEmail());
                        }
                        totalTickets.add(currentSale);

                        i++;
                    }

                    System.out.println("The total price calculated is : " + ticTotal);

                    session.setAttribute("totalTickets", totalTickets);
                    request.setAttribute("ticTotal", ticTotal);

                    //below no use any more
                    request.setAttribute("totalQuant", totalQuant);
                    request.setAttribute("showTickets", list);
                    request.setAttribute(temp, i);
                    request.getRequestDispatcher("/entertainmentPay.jsp").forward(request, response);
                } else if ("entertainmentRegisterResult".equals(page)) {
                    System.out.println("***entertainmentRegisterResult***");
                    System.out.println("***entertainmentRegister***");
                    ee = new EventEntity();

                    String eventName = request.getParameter("eventName");
                    ee.setEventName(eventName);
                    String name = request.getParameter("name");
                    ee.setName(name);
                    String eventType = "show";
                    ee.setEventType(eventType);
                    String email = request.getParameter("e-mail");
                    ee.setEmail(email);
                    String phoneNumber = request.getParameter("contact");
                    ee.setEventContact(phoneNumber);
                    String description = request.getParameter("description");
                    ee.setDescription(description);
                    String Status = "pending";
                    ee.setStatus(Status);
                    eventSessionBean.addEvent(ee);
                    request.getRequestDispatcher("/entertainmentRegisterResult.jsp").forward(request, response);
                } else if ("entertainmentPayConfirm".equals(page)) {

                    System.out.println("***hotel payment confirmation***");
                    System.out.println("adding reservation to database....");

                    String promotionCode = request.getParameter("promotionCode");
                  
                    try {


                        showTicketSaleSessionBean.addShowTicketSale(thisShowTicketSale);
                        
                         if (promotionCode == "") {
                         System.out.println("no promotion code entered");
                         promotion = (PromotionEntity) session.getAttribute("promotion");
                         ticTotal=ticTotal*(1-promotion.getDiscount());
                         } else {
                       
                         }
                        
                    } catch (Exception e) {
                        System.err.println("error occured when adding reservation in servlet");
                        e.printStackTrace();
                    }
                    /*
                     if (true) {//add in conditions later
                     System.out.println("start generate coupon");
                     CouponTypeEntity ct = couponTypeSessionBean.getAllCouponTypes().get(0);
                     Date today = new Date(113, 10, 12);//dummy, should be changed to the date of making reservation
                     String email = data.getRcEmail();
                     MemberEntity member = memberSessionBean.getMemberByEmail(email);
                     System.out.println("member info: " + member.getMemberEmail());
                     coupon = couponSessionBean.generateCoupon(today, member, ct);
                     }
                     session.setAttribute("coupon", coupon);
                     */
                    Iterator <ShowTicketSaleEntity> itr = totalTickets.iterator();
                    while(itr.hasNext())
                    {
                        ShowTicketSaleEntity current = itr.next();
                        if(current.getShowTicketQuantity()!=0)
                            this.purchasedTickets.add(current);
                            
                    }
                    request.setAttribute("totalTickets", totalTickets);
                    request.setAttribute("ticTotal",ticTotal);
                   
                    request.getRequestDispatcher("/entertainmentPayConfirm.jsp").forward(request, response);
                } else if ("entertainmentRegister".equals(page)) {



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
