/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import CEMS.entity.EventEntity;
import CEMS.entity.EventServiceEntity;
import CEMS.entity.VenueEntity;
import CEMS.session.EventServiceSessionBean;
import CEMS.session.EventSessionBean;
import CEMS.session.VenueSessionBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "CEMSServlet", urlPatterns = {"/CEMSServlet", "/CEMSServlet/*"})
public class CEMSServlet extends HttpServlet {
    @EJB
    private EventServiceSessionBean eventServiceSessionBean;
    @EJB
    private VenueSessionBean venueSessionBean;

    @EJB
    private EventSessionBean eventSessionBean;
    
 
    private List<VenueEntity> data;
    private VenueEntity data1;
    private EventEntity data2;
    private List<EventServiceEntity> AVList= null;
    private List<EventServiceEntity> FLList= null;
    private List<EventServiceEntity> PEList= null;
    private EventEntity viewedEvent;
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
        System.out.println("CEMSERVLET: init()");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CEMSERVLET: processRequest()");
        HttpSession session = request.getSession(true);

        /* response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();*/
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();

            String temp = request.getServletPath();

            String page = request.getPathInfo();
            page = page.substring(1);
            System.out.println("CEMSServlet page: " + page);


            if ("event".equals(page)) {
                System.out.println("***event***");

                request.getRequestDispatcher("/event.jsp").forward(request, response);
            } else if ("eventRegister".equals(page)) {
                System.out.println("***eventRegister***");

                request.getRequestDispatcher("/eventRegister.jsp").forward(request, response);
            } else if ("eventResource".equals(page)) {
                System.out.println("***eventResource***");

                request.getRequestDispatcher("/eventResource.jsp").forward(request, response);
            }else if ("eventService".equals(page)) {
                
                System.out.println("***eventService***");
                eventServiceSessionBean.getEventServiceByCategory("Food and Beverage Services");
                AVList = eventServiceSessionBean.getEventServiceByCategory("Audio and Video Requirements");
                 request.setAttribute("AVList",AVList);
                FLList = eventServiceSessionBean.getEventServiceByCategory("Floral and Landscaping Services");
                 request.setAttribute("FLList",FLList);
                PEList = eventServiceSessionBean.getEventServiceByCategory("Personnel Services");
                request.setAttribute("PEList",PEList);
                request.getRequestDispatcher("/eventService.jsp").forward(request, response);
            }else if ("eventRegisterResult".equalsIgnoreCase(page)) {
                System.out.println("*****eventRegisterResult*****");
                data2 = registerEvent(request);
                System.out.println(data2.getEventName() + " has been registered!");
                request.setAttribute("data", data2);
                request.getRequestDispatcher("/eventRegisterResult.jsp").forward(request, response);
            } else if ("eventVenueSearch".equalsIgnoreCase(page)) {
                System.out.println("*****eventVenueSearch*****");
                System.out.println("CEMSSevlet: Current page is eventVenueSearch!");
                data = searchVenue(request);
                System.out.println(data.isEmpty());
                request.setAttribute("data", data);
                request.getRequestDispatcher("/eventVenueSearch.jsp").forward(request, response);
            } else if ("eventVenueList".equalsIgnoreCase(page)) {
                System.out.println("*****eventVenueList*****");
                System.out.println("CEMSSevlet: Current page is eventVenue List!");
                data = venueSessionBean.getAllVenues();
                System.out.println(data.isEmpty());
                request.setAttribute("data", data);
                request.getRequestDispatcher("/eventVenueSearch.jsp").forward(request, response);
            } else if ("eventVenueBook".equalsIgnoreCase(page)) {
                System.out.println("*****eventVenueBook*****");
                System.out.println("CEMSSevlet: Current page is eventVenueBook!");
                Long venueId = Long.parseLong(request.getParameter("venueId"));
                data1 = eventSessionBean.getVenue(venueId);
                System.out.println("CEMSServlet:eventVenueBook: the venue has been found " + data1.getVenueName());
                List<Date> unavailDates = new ArrayList<Date>();
                unavailDates = eventSessionBean.checkVenueAvailability(venueId, 1);

                request.setAttribute("data", data1);
                request.setAttribute("unavailDates", unavailDates);
                System.out.println("CEMSServlet:eventVenueBook: the list of unavailable dates!");
                System.out.println(data1.toString() + unavailDates.isEmpty());
                request.getRequestDispatcher("/eventVenueBook.jsp").forward(request, response);

            } else if ("eventList".equals(page)) {
                System.out.println("***eventList***");
				//List<EventEntity> events = eventSessionBean.getConfirmedEvents();
                List<EventEntity> events = eventSessionBean.listEvents();
                request.setAttribute("eventList", events);
                request.getRequestDispatcher("/eventList.jsp").forward(request, response);
            }else if (page.contains("eventInfo")) {
				System.out.println(page.substring(10));
				long eventId = Integer.valueOf(page.substring(10));
				System.out.println(eventId);
				viewedEvent = eventSessionBean.getReservation(eventId);
				request.setAttribute("viewedEvent", viewedEvent);
                request.getRequestDispatcher("/eventInfo.jsp").forward(request, response);
            }else {
                System.out.println("other page:" + page);
            }
        } catch (Exception e) {
            System.out.println(e);
            log("Exception in CEMSServlet.processRequest()");
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
        System.out.println("irmsServlet: destroy()");
    }

    private List<VenueEntity> searchVenue(HttpServletRequest request) {

        List<VenueEntity> al = new ArrayList<VenueEntity>();
        System.out.println("method invoked");
        String venueFunction = request.getParameter("venueFunction");
        System.out.println(venueFunction);
        System.out.println("venueFunction retrieved");
        Integer venueCapacity = Integer.parseInt(request.getParameter("venueCapacity"));
        System.out.println(venueCapacity);

        al = eventSessionBean.searchVenue(venueCapacity, venueFunction);

        //System.out.println(re.getRestNeighbourhood()+re.getRestCuisine()+re.getRestTypeOfPlace());


        //  System.out.println(al.get(0));
        System.out.println("CEMSServlet: event search venue has been completed!");



        return al;
        //To change body of generated methods, choose Tools | Templates.
    }// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private EventEntity registerEvent(HttpServletRequest request) throws ParseException {
        
        System.out.println("CEMSServlet: registerEvent has started!");
        EventEntity ee = new EventEntity();
        
        String title = request.getParameter("title");
        String eventName = request.getParameter("eventName");
        String name = request.getParameter("name");
        String eventType = request.getParameter("eventType");
        String email = request.getParameter("e-mail");
            String dateString = request.getParameter("startDate");
            System.out.println("startDate passed in is "+dateString);
            DateFormat formatter;
            formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date startDate = formatter.parse(dateString);
            System.out.println("startDate is "+startDate);
            
            String endDateString = request.getParameter("endDate");
            System.out.println("startDate passed in is "+endDateString);
            Date endDate = formatter.parse(endDateString);
            
        String phoneNumber = request.getParameter("contact");
        String address = request.getParameter("address");
        Integer eventScale = Integer.parseInt(request.getParameter("eventScale"));
        String countryOfResidence = request.getParameter("countryOfResidence");
        Double estimatedBudget = Double.parseDouble(request.getParameter("estimatedBudget"));
        String company = request.getParameter("company");
        String industry = request.getParameter("industry");
        boolean isPublic = Boolean.parseBoolean(request.getParameter("subscribe"));
        String preferredLanguage = request.getParameter("preferredLanguage");
        
        System.out.println("CEMSServlet:registerEvent: All data has been passed in already!");
        
        ee = eventSessionBean.makeReservation(eventName, eventType, eventName, title,
                name, email, eventScale, startDate, endDate);
        ee = eventSessionBean.completeReservation(ee, address,phoneNumber , countryOfResidence, estimatedBudget, 
                company, industry, preferredLanguage, isPublic);
        System.out.println("CEMSServlet:registerEvent:Event has been created successfully!");
                
                
        
        return ee;
    }
}
