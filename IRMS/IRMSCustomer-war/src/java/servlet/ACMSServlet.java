/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ACMS.entity.ReservationEntity;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                data = createTempReservation(request);
                isAvailable = checkAvailability(data);
                System.out.println("data search has been performed and result has been returned by bean");
                request.setAttribute("data", data);
                request.getRequestDispatcher("/hotelSearch.jsp").forward(request, response);
            } else if ("hotelBook".equals(page)) {
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
        int hotel = Integer.valueOf(shotel);
        int roomCount = Integer.valueOf(sroomCount);
        int guestCount = Integer.valueOf(speopleCount);
        DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        Date inDate = df.parse(sinDate);
        Date outDate = df.parse(soutDate);
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
       //total number of rooms availableCount = 60; (eg.)
        //for loop: for each reservation
       //if RcCheckOutDate.after(inDate) && RcCheckInDate.before(outDate) availableCount--;
        //if roomCount <= availableCount return true; else return false;
       return true;
    }
}