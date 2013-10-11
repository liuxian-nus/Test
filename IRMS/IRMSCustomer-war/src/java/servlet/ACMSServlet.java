/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ACMS.entity.ReservationEntity;
import ACMS.session.ReservationSessionBean;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @author liuxian
 */
@WebServlet(name = "ACMSServlet", urlPatterns = {"/ACMSServlet", "/ACMSServlet/*"})
public class ACMSServlet extends HttpServlet {
    @EJB
    private ReservationSessionBean reservationSessionBean;

    ReservationEntity data = new ReservationEntity();
    boolean isAvailable = false;

    @Override
    public void init() {
        System.out.println("ACMSERVLET: init()");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ACMSERVLET: processRequest()");
        HttpSession session = request.getSession(true);

        /* response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();*/
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();

            String temp = request.getServletPath();

            String page = request.getPathInfo();
            page = page.substring(1);
            System.out.println("ACMSServlet page: " + page);


            if ("hotelSearch".equals(page)) {
                System.out.println("***hotel Search***");
          
                request.getRequestDispatcher("/hotelSearch.jsp").forward(request, response);
            }else if("searchAvailable".equals(page)){ 
                System.out.println("***search hotel availability***");
                data = createTempReservation(request);
                isAvailable = checkAvailability(data);
                System.out.println("data search has been performed and result has been returned by bean");
                request.setAttribute("data", data);
                if(isAvailable) request.getRequestDispatcher("/hotelBook.jsp").forward(request, response);
                else request.getRequestDispatcher("/hotelSearch.jsp").forward(request, response);
            }else if ("hotelBook".equals(page)) {
                System.out.println("***hotel Book***");

                request.getRequestDispatcher("/hotelBook.jsp").forward(request, response);
            } else if ("hotelPay".equals(page)) {
                System.out.println("***hotel payment***");

                request.getRequestDispatcher("/hotelPay.jsp").forward(request, response);
            } else if ("hotelPayConfirm".equals(page)) {
                System.out.println("***hotel payment confirmation***");

                request.getRequestDispatcher("/hotelPayConfirm.jsp").forward(request, response);
            } else {
                System.out.println("other page");
            }
        } catch (Exception e) {
            System.out.println(e);
            log("Exception in ACMSServlet.processRequest()");
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
        System.out.println("irmsServlet: destroy()");
    }

    private ReservationEntity createTempReservation(HttpServletRequest request) throws ParseException {
        ReservationEntity tempReservation = new ReservationEntity();
        System.out.println("create temp reservation");
        //retrieve info
        String shotel = request.getParameter("hotel");
        String sroomType = request.getParameter("roomType");
        String sinDate = request.getParameter("in_date");
        String soutDate = request.getParameter("out_date");
        String sroomCount = request.getParameter("roomCount");
        String speopleCount = request.getParameter("people");
        System.out.println("reservation data retrieved: " + shotel + sroomType + sinDate + soutDate + sroomCount + speopleCount);
        //change datatype
        int hotel = 0;
        if(shotel.equals("Orchard Hotel")) hotel = 1;
        if(shotel.equals("Marina Hotel")) hotel = 2;
        if(shotel.equals("BeachView Hotel")) hotel = 3;
        int roomCount = Integer.valueOf(sroomCount);
        int guestCount = Integer.valueOf(speopleCount);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date inDate = df.parse(sinDate);
        Date outDate = df.parse(soutDate);
        System.out.println("indate is: " + inDate);
        System.out.println("outdate is: " + outDate);
        //create temp POJO
        tempReservation.setReservationHotelNo(hotel);
        tempReservation.setReservationRoomType(sroomType);
        tempReservation.setReservationGuestCount(guestCount);
        tempReservation.setReservationRoomCount(roomCount); 
        tempReservation.setRcCheckInDate(inDate);
        tempReservation.setRcCheckOutDate(outDate);
        
        return tempReservation; //now we have POJO data
    }

    private boolean checkAvailability(ReservationEntity data) {
        List<ReservationEntity> reservationList = reservationSessionBean.getAllReservations();
        int count = 0;
        //set for total room number
        if ((data.getReservationHotelNo() == 1)&&(data.getReservationRoomType().equals("Deluxe"))) count = 80;
        else if ((data.getReservationHotelNo() == 1)&&(data.getReservationRoomType().equals("Deluxe Suite"))) count = 50;
        else if ((data.getReservationHotelNo() == 1)&&(data.getReservationRoomType().equals("Orchard Suite"))) count = 30;
        else if ((data.getReservationHotelNo() == 2)&&(data.getReservationRoomType().equals("Deluxe"))) count = 100;
        else if ((data.getReservationHotelNo() == 2)&&(data.getReservationRoomType().equals("Deluxe Suite"))) count = 60;
        else if ((data.getReservationHotelNo() == 2)&&(data.getReservationRoomType().equals("Chairman Suite"))) count = 20;
        else if ((data.getReservationHotelNo() == 3)&&(data.getReservationRoomType().equals("Superior"))) count = 60;
        else if ((data.getReservationHotelNo() == 3)&&(data.getReservationRoomType().equals("Deluxe"))) count = 60;
        else if ((data.getReservationHotelNo() == 3)&&(data.getReservationRoomType().equals("Deluxe Suite"))) count = 50;
        System.out.println("room information: " + data.getReservationHotelNo() + data.getReservationRoomType());
        System.err.println("Total number of rooms is: " + count);
        //while loop: deduct unavailable rooms
        Iterator <ReservationEntity> itr = reservationList.iterator();
        while(itr.hasNext()) {
              ReservationEntity re = itr.next();
              
        }

        //for loop: for each reservation
       //if RcCheckOutDate.after(inDate) && RcCheckInDate.before(outDate) availableCount--;
        //if roomCount <= availableCount return true; else return false;
       return true;
    }
}