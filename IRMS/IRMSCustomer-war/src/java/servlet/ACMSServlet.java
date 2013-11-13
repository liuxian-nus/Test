/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ACMS.entity.ReservationEntity;
import ACMS.session.ReservationSessionBean;
import ACMS.session.RoomPriceSessionBean;
import CRMS.entity.CouponEntity;
import CRMS.entity.CouponTypeEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.PromotionEntity;
import CRMS.session.CouponSessionBean;
import CRMS.session.CouponTypeSessionBean;
import CRMS.session.MemberSessionBean;
import CRMS.session.MemberTransactionSessionBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private RoomPriceSessionBean roomPriceSessionBean;
    @EJB
    private MemberSessionBean memberSessionBean;
    @EJB
    private CouponTypeSessionBean couponTypeSessionBean;
    @EJB
    private CouponSessionBean couponSessionBean;
    @EJB
    private ReservationSessionBean reservationSessionBean;
    @EJB
    private MemberTransactionSessionBean memberTransactionSessionBean;
    ReservationEntity data = new ReservationEntity();
    PromotionEntity promotion = new PromotionEntity();
    boolean isAvailable = false;
    int SessionTime;
    Long reservationId;
    CouponEntity coupon;
    String message = "";
    private String USER_AGENT;
    Double roomPrice;
    Double totalPrice;

    @Override
    public void init() {
        System.out.println("ACMSERVLET: init()");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ACMSERVLET: processRequest()");
        HttpSession session = request.getSession(true);
        SessionTime = (int) session.getMaxInactiveInterval();
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
                SessionTime = (int) session.getMaxInactiveInterval();
                System.out.println("Timeleft" + SessionTime);
                request.setAttribute("SessionTime", SessionTime);
                MemberEntity thisMember = (MemberEntity) session.getAttribute("member");
                if (thisMember != null) {
                    String string = thisMember.getMemberName();
                    String[] parts = string.split(" ");
                    String firstName = parts[0];
                    String lastName = parts[1];
                    System.out.println(firstName);
                    System.out.println(lastName);
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("lastName", lastName);
                }
                if (isAvailable) {
                    String roomType = data.getReservationRoomType();
                    System.out.println("RoomType:" + roomType);
                    roomPrice = roomPriceSessionBean.getPriceValueByType(roomType);
                    int roomCount = data.getReservationRoomCount();
                    totalPrice = roomPrice * roomCount;
                    session.setAttribute("roomPrice", roomPrice);
                    session.setAttribute("totalPrice", totalPrice);
                    request.getRequestDispatcher("/hotelBook.jsp").forward(request, response);
                } else {
                    message = "Sorry,the room is not available on your selected type";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("/hotelSearch.jsp").forward(request, response);
                }
            } else if ("hotelBook".equals(page)) {
                System.out.println("***hotel Book***");
                SessionTime = (int) session.getMaxInactiveInterval();
                System.out.println("Timeleft" + SessionTime);
                request.setAttribute("SessionTime", SessionTime);
                data = (ReservationEntity) session.getAttribute("data");
                request.getRequestDispatcher("/hotelBook.jsp").forward(request, response);
            } else if ("hotelPay".equals(page)) {

                System.out.println("***hotel payment***");
                data = (ReservationEntity) session.getAttribute("data");
                data = continueRead1(request);
                String paymentMethod = request.getParameter("paymentMethod");
                System.out.println("PaymentMethod" + paymentMethod);
                if (paymentMethod.equalsIgnoreCase("Continue with Payment")) {
                    data = continueRead2(request);
                    SessionTime = (int) session.getMaxInactiveInterval();
                    System.out.println("Time left" + SessionTime);
                    request.setAttribute("SessionTime", SessionTime);
                    promotion = (PromotionEntity) session.getAttribute("promotion");
                    request.getRequestDispatcher("/hotelPay.jsp").forward(request, response);
                } else {
                    System.out.println("***hotel payment by coin***");

                    MemberEntity member = (MemberEntity) session.getAttribute("member");
                    boolean affordable = memberTransactionSessionBean.checkCoinAmount(member, totalPrice);
                    if (affordable) {
                        memberTransactionSessionBean.payByCoin(member, totalPrice);
                        reservationSessionBean.addReservationByCoin(data, member);
                        promotion = (PromotionEntity) session.getAttribute("promotion");

                        request.getRequestDispatcher("/hotelPayConfirm.jsp").forward(request, response);
                    } else {
                        request.setAttribute("redeemMessage", "Sorry, your coins are not enough");
                        request.getRequestDispatcher("/hotelBook.jsp").forward(request, response);
                    }
                }

            } else if ("hotelRedeem".equals(page)) {
            } else if ("hotelPayConfirm".equals(page)) {
                System.out.println("***hotel payment confirmation***");
                System.out.println("adding reservation to database....");
                data = (ReservationEntity) session.getAttribute("data");
                data.setRcCreditCardNo(request.getParameter("cardNo"));
                String promotionCode = request.getParameter("promotionCode");
                String payment = request.getParameter("payment");
                System.out.println(payment);
                String cardNo = request.getParameter("cardNo");
                System.out.println(cardNo);
                sendGet();
                try {
                    System.out.println(totalPrice);
                    MemberEntity thisMember = (MemberEntity) session.getAttribute("member");
                    data.setRcMember(thisMember);
                    if (promotionCode == null) {
                        reservationSessionBean.addReservation(data, totalPrice);
                    } else {
                        reservationSessionBean.addReservationWithPromotion(data, promotionCode);
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

                promotion = (PromotionEntity) session.getAttribute("promotion");
                request.getRequestDispatcher("/hotelPayConfirm.jsp").forward(request, response);
            } else if ("hotelModify".equals(page)) {
                reservationId = Long.valueOf(request.getParameter("reservationId"));
                System.out.println(reservationId);
                data = reservationSessionBean.getReservationById(reservationId);
                System.out.println("***hotel modify***");
                request.getRequestDispatcher("/hotelModify.jsp").forward(request, response);
            } else if ("hotelPayPalConfirm".equals(page)) {
                System.out.println("***hotel paypal confirmation***");

                data = (ReservationEntity) session.getAttribute("data");
                data.setRcCreditCardNo(request.getParameter("cardNo"));
                request.getRequestDispatcher("/hotelPayPalConfirm.jsp").forward(request, response);
            } else if ("hotelCancel".equals(page)) {
                reservationSessionBean.cancelReservation(reservationId);
                System.out.println("***hotel cancel***");

                request.getRequestDispatcher("/hotelCancel.jsp").forward(request, response);
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

    private void sendGet() throws Exception {

        String url = "https://api-3t.sandbox.paypal.com/nvp?";
        url += "USER=xinyusoc-facilitator_api1.gmail.com&";
        url += "PWD=1383997852&";
        url += "SIGNATURE=AFcWxV21C7fd0v3bYYYRCpSSRl31A4L4WLmbdOQyA2Nn26.xecMb47ed&";
        url += "METHOD=SetExpressCheckout&";
        url += "VERSION=93&";
        url += "PAYMENTREQUEST_0_PAYMENTACTION=SALE";
        url += "PAYMENTREQUEST_0_AMT=10.00";
        url += "PAYMENTREQUEST_0_CURRENCYCODE=USD";
        url += "cancelUrl=http://is3102.cloudapp.net"; //cancel order
        url += "returnUrl=http://is3102.cloudapp.net/IRMSCustomer-war/irmsServlet/hotelPay";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.err.println(response.toString());

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
            count = 10;
        } else if ((data.getReservationHotelNo() == 2) && (data.getReservationRoomType().equals("Deluxe"))) {
            count = 100;
        } else if ((data.getReservationHotelNo() == 2) && (data.getReservationRoomType().equals("Deluxe Suite"))) {
            count = 60;
        } else if ((data.getReservationHotelNo() == 2) && (data.getReservationRoomType().equals("Chairman Suite"))) {
            count = 8;
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
            if (data == null) {
                System.out.println("data is null");
            }

            if ((re.getRcCheckOutDate() != null) && (re.getRcCheckInDate() != null) && (re.getRcCheckOutDate().after(data.getRcCheckInDate())) && (re.getRcCheckInDate().before(data.getRcCheckOutDate()))) {
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
