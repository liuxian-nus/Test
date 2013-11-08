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
    int SessionTime;

    @Override
    public void init() {
        System.out.println("ACMSERVLET: init()");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ACMSERVLET: processRequest()");
        HttpSession session = request.getSession(true);
         SessionTime=(int)session.getMaxInactiveInterval();
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
            } else if ("searchAvailable".equals(page)) {
                System.out.println("***search hotel availability***");
                data = createTempReservation(request);
                isAvailable = checkAvailability(data);
                System.out.println("data search has been performed and result has been returned by bean");
                session.setAttribute("data", data);
                 SessionTime=(int)session.getMaxInactiveInterval();
                 System.out.println("Timeleft"+SessionTime);
                 request.setAttribute("SessionTime",SessionTime);
                if (isAvailable) {
                    request.getRequestDispatcher("/hotelBook.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/hotelSearch.jsp").forward(request, response);
                }
            } else if ("hotelBook".equals(page)) {
                System.out.println("***hotel Book***");
                 SessionTime=(int)session.getMaxInactiveInterval();
                 System.out.println("Timeleft"+SessionTime);
                 request.setAttribute("SessionTime",SessionTime);
                
                request.getRequestDispatcher("/hotelBook.jsp").forward(request, response);
            } else if ("hotelPay".equals(page)) {
                System.out.println("***hotel payment***");
                data = continueRead1(request);
 //               data = continueRead2(request);
                
                SessionTime=(int)session.getMaxInactiveInterval();
                 System.out.println("Time left"+SessionTime);
                 request.setAttribute("SessionTime",SessionTime);
                 
                request.getRequestDispatcher("/hotelPay.jsp").forward(request, response);
            } else if ("hotelPayConfirm".equals(page)) {
                data.setRcCreditCardNo(request.getParameter("cardNo"));
               // reservationSessionBean.addReservation(data);
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

    private ReservationEntity continueRead1(HttpServletRequest request) throws ParseException {
     //   ReservationEntity tempReservation = new ReservationEntity();
        System.out.println("continue finish data entity");
        //retrieve info
        String firstName = request.getParameter("firstName");
        System.out.println(firstName);
        String lastName = request.getParameter("lastName");
        System.out.println(lastName);
        String email = request.getParameter("email");
        System.out.println(email);
        //missing with subscribe boolean
        String hp = request.getParameter("phoneNo");
        System.out.println(hp);
        String nationality = request.getParameter("nationality");
        System.out.println(nationality);
        //set to POJO
        data.setRcName(firstName + lastName);
        data.setRcEmail(email);
        data.setRcHP(hp);
        data.setRcNationality(nationality);
        //data should be in session
        return data;
    }
    
        private ReservationEntity continueRead2(HttpServletRequest request) throws ParseException {
     //   ReservationEntity tempReservation = new ReservationEntity();
        System.out.println("continue finish data entity");
        //retrieve info
        String creditCardNo = request.getParameter("cardNo");

        //set to POJO
        data.setRcCreditCardNo(creditCardNo);

        //data should be in session
        return data;
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
        if (shotel.equals("Orchard Hotel")) {
            hotel = 1;
        }
        if (shotel.equals("Marina Hotel")) {
            hotel = 2;
        }
        if (shotel.equals("BeachView Hotel")) {
            hotel = 3;
        }
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
        if ((data.getReservationHotelNo() == 1) && (data.getReservationRoomType().equals("Deluxe"))) {
            count = 80;
        } else if ((data.getReservationHotelNo() == 1) && (data.getReservationRoomType().equals("Deluxe Suite"))) {
            count = 50;
        } else if ((data.getReservationHotelNo() == 1) && (data.getReservationRoomType().equals("Orchard Suite"))) {
            count = 30;
        } else if ((data.getReservationHotelNo() == 2) && (data.getReservationRoomType().equals("Deluxe"))) {
            count = 100;
        } else if ((data.getReservationHotelNo() == 2) && (data.getReservationRoomType().equals("Deluxe Suite"))) {
            count = 60;
        } else if ((data.getReservationHotelNo() == 2) && (data.getReservationRoomType().equals("Chairman Suite"))) {
            count = 20;
        } else if ((data.getReservationHotelNo() == 3) && (data.getReservationRoomType().equals("Superior"))) {
            count = 60;
        } else if ((data.getReservationHotelNo() == 3) && (data.getReservationRoomType().equals("Deluxe"))) {
            count = 60;
        } else if ((data.getReservationHotelNo() == 3) && (data.getReservationRoomType().equals("Deluxe Suite"))) {
            count = 50;
        }
        System.out.println("room information: " + data.getReservationHotelNo() + data.getReservationRoomType());
        System.err.println("Total number of rooms is: " + count);
        //while loop: deduct unavailable rooms
        Iterator<ReservationEntity> itr = reservationList.iterator();
        while (itr.hasNext()) {
            ReservationEntity re = itr.next();
            if ((re.getRcCheckOutDate().after(data.getRcCheckInDate())) && (re.getRcCheckInDate().before(data.getRcCheckOutDate()))) {
                count--;
            }
        }
        if (data.getReservationRoomCount() > count) {
            return false;
        } else {
            return true;
        }
        /*
         * check availability algorithm:
         * notation: (re.getRcCheckInDate=)rIn, rOut, (data.getCheckInDate=)in, out
         * unavailable condition 1: rIn<in and rOut>In
         * unavailabel condition 2: in<rIn<out
         * (hidden condition: rOut>rIn)
         * therefore: [(rIn<in)&&(rOut>in)]||[(in<rIn<out)&&(rOut>in)]||[(in<rIn<out)&&(rOut<in)] (the 3rd part is an empty set)
         * simplified result: rIn<out && rOut>in
         */
    }
}
