/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ATMS.entity.AttrExpressPassEntity;
import ATMS.entity.AttrTicketEntity;
import ATMS.entity.AttractionEntity;
import ATMS.entity.ExpressPassPurchaseEntity;
import ATMS.entity.TicketPurchaseEntity;
import ATMS.session.AttrExpressPassSessionBean;
import ATMS.session.AttractionSessionBean;
import ATMS.session.ExpressPassPurchaseSessionBean;
import ATMS.session.TicketPurchaseSessionBean;
import ATMS.session.TicketSessionBean;
import CRMS.entity.CouponEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.CouponSessionBean;
import CRMS.session.GenerateBarcodeSessionBean;
import CRMS.session.MemberSessionBean;
import CRMS.session.MemberTransactionSessionBean;
import ERMS.session.EmailSessionBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
 * @author Jieqiong
 */
//@WebServlet(name = "ATMSServlet", urlPatterns = {"/ATMSServlet"})
@WebServlet(urlPatterns = {"/ATMSServlet", "/ATMSServlet/*"})
public class ATMSServlet extends HttpServlet {
    @EJB
    private CouponSessionBean couponSessionBean;
    @EJB
    private MemberTransactionSessionBean memberTransactionSessionBean;
    @EJB
    private ExpressPassPurchaseSessionBean expressPassPurchaseSessionBean;
    @EJB
    private AttrExpressPassSessionBean attrExpressPassSessionBean;
    @EJB
    private GenerateBarcodeSessionBean generateBarcodeSessionBean;
    @EJB
    private EmailSessionBean emailSessionBean;
    @EJB
    private MemberSessionBean memberSessionBean;
    @EJB
    private AttractionSessionBean attractionSessionBean;
    @EJB
    private TicketSessionBean ticketSessionBean;
    @EJB
    private TicketPurchaseSessionBean ticketPurchaseSessionBean;
    
    
    
    
    
    AttrTicketEntity ticket;
    AttractionEntity attr;
    List<AttrTicketEntity> tkts;
    TicketPurchaseEntity tp;
    MemberEntity member;
    ExpressPassPurchaseEntity eppurchase;
    List<AttrExpressPassEntity> eps;
    AttrExpressPassEntity ep;
    MemberTransactionEntity mt;
    String message="";
    CouponEntity coupon;
    

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
        
        HttpSession session = request.getSession(true);
        
        
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
                 attr=attractionSessionBean.getAttrById("OT");
                 tkts=new ArrayList<AttrTicketEntity>();
                 tkts=ticketSessionBean.selectTicketForOneAttraction(attr);
                 
                 List<AttrTicketEntity> tickets=ticketSessionBean.getAllTickets();
                 session.setAttribute("tickets", tickets);
                 List<AttrExpressPassEntity> eps=attrExpressPassSessionBean.getAllEPs();
                 session.setAttribute("eps",eps);
                 

                request.getRequestDispatcher("/adventureWorld.jsp").forward(request, response);
            }else if("horrorHouse".equals(page)){
                 System.out.println("***horrorHouse page***");
                 attr=attractionSessionBean.getAttrById("IT");
                 tkts=new ArrayList<AttrTicketEntity>();
                 tkts=ticketSessionBean.selectTicketForOneAttraction(attr);
                 
                 List<AttrTicketEntity> tickets=ticketSessionBean.getAllTickets();
                 session.setAttribute("tickets", tickets);
                 List<AttrExpressPassEntity> eps=attrExpressPassSessionBean.getAllEPs();
                 session.setAttribute("eps",eps);
                request.getRequestDispatcher("/horrorHouse.jsp").forward(request, response);
            }else if("museum".equals(page)){
                 System.out.println("***museum page***");
                 attr=attractionSessionBean.getAttrById("MU");
                 tkts=new ArrayList<AttrTicketEntity>();
                 tkts=ticketSessionBean.selectTicketForOneAttraction(attr);
                 
                 List<AttrTicketEntity> tickets=ticketSessionBean.getAllTickets();
                 session.setAttribute("tickets", tickets);
       
                request.getRequestDispatcher("/museum.jsp").forward(request, response);
            }else if("aquarium".equals(page)){
                 System.out.println("***aquarium page***");
                 attr=attractionSessionBean.getAttrById("AQ");
                 tkts=new ArrayList<AttrTicketEntity>();
                 tkts=ticketSessionBean.selectTicketForOneAttraction(attr);
                 
                 List<AttrTicketEntity> tickets=ticketSessionBean.getAllTickets();
                 session.setAttribute("tickets", tickets);
                 
                 request.getRequestDispatcher("/aquarium.jsp").forward(request, response);
            }else if("attrTicketBooking".equals(page)){
                System.out.println("***attrTicketBooking page***");
            /*    List<AttrTicketEntity> tickets=ticketSessionBean.getAllTickets();
                session.setAttribute("tickets", tickets);
                List<AttrExpressPassEntity> eps=attrExpressPassSessionBean.getAllEPs();
                session.setAttribute("eps",eps);*/

                request.getRequestDispatcher("/attrTicketBooking.jsp").forward(request, response);
            }else if("horrorHouseTicketBooking".equals(page)){
                System.out.println("***horrorHouseTicketBooking page***");
              request.getRequestDispatcher("/horrorHouseTicketBooking.jsp").forward(request, response);
            }else if("attrTicketBookingInformation".equals(page)){
                System.out.println("***attrTicketBookingInformation page***");                    
 
                
                Integer quantity1=Integer.parseInt(request.getParameter("quantity1"));
                Integer quantity2=Integer.parseInt(request.getParameter("quantity2"));
                Integer quantity3=Integer.parseInt(request.getParameter("quantity3"));
                Integer quantity4=Integer.parseInt(request.getParameter("quantity4"));
                Integer quantity5=Integer.parseInt(request.getParameter("quantity5"));
                Integer quantity6=Integer.parseInt(request.getParameter("quantity6"));
                Integer quantity7=Integer.parseInt(request.getParameter("quantity7"));
                Integer quantity8=Integer.parseInt(request.getParameter("quantity8"));
                Integer quantity9=Integer.parseInt(request.getParameter("quantity9"));
                             
//                Integer day=Integer.parseInt(request.getParameter("dateDay"));
//                Integer month=Integer.parseInt(request.getParameter("dateMonth"));
//                Integer year=Integer.parseInt(request.getParameter("dateYear"));
//                Date date;
//                date = new Date(year - 1900, month - 1, day);
                SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
                String dateString=request.getParameter("date");
                Date date=sdf.parse(dateString);
                System.out.println("date: "+date);
                session.setAttribute("date",date);
                System.out.println("new date session set");
                
                String attrId=request.getParameter("attrId");
                System.out.println("attrId: "+attrId);
                attr=attractionSessionBean.getAttrById(attrId);
                session.setAttribute("attr", attr);
                System.out.println("session set");
                
                String couponIdString=request.getParameter("couponId");
                if(couponIdString!=""){
                    System.out.println("coupon code entered");
                    Long couponId=Long.parseLong(couponIdString);
/*                    
                }
                Long couponId=Long.parseLong(request.getParameter("couponId"));
                System.out.println("couponId:"+couponId);
                if(couponId!=0){*/
                    System.out.println("start to use coupon");
                    coupon=couponSessionBean.getCouponById(couponId); 
                    if(coupon==null){
                        message="Coupon code is not correct.";  
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("/attrTicketBooking.jsp").forward(request, response); 
                    }
                    else if(coupon.getStatus().equals("Used")){
                        message="The coupon has been used. One coupon can only be used once";  
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("/attrTicketBooking.jsp").forward(request, response); 
                    }
                    else if(coupon.getStatus().equals("Expired")){
                        message="The coupon has expired";  
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("/attrTicketBooking.jsp").forward(request, response); 
                    }  
                }
                session.setAttribute("coupon",coupon);
                System.out.println("coupon session set");
                
                String tpRemarks="";
                String eppurchaseRemarks="";
            
                
         /*       boolean ticketAvailable;
                ticketAvailable=checkTicketAvailability(quantity1, quantity2,date, attr);*/
                               
                tp=new TicketPurchaseEntity();
                tkts=new ArrayList<AttrTicketEntity>();
                List<Integer> quantities=new ArrayList<Integer>();
                double fee=0.0;
                Long tpId=ticketPurchaseSessionBean.addTicketPurchase(tp);
                int i=0;
                
               if(attrId.equals("OT")){
                    System.out.println("attrId is OT");
                    i=1;
                }
                if(attrId.equals("IT")){
                    System.out.println("attrId is IT");
                    i=10;
                }
                if(attrId.equals("MU")){
                    System.out.println("attrId is MU");
                    i=19;
                }
                if(attrId.equals("AQ")){
                    System.out.println("attrId is AQ");
                    i=25;
                }
                    
                if(quantity1!=0){
                    ticket=ticketSessionBean.getTicketById(Long.parseLong(String.valueOf(i)));
                    System.out.println("ticketName: "+ticket.getAttrTicketName());
                    tkts.add(ticket);
                    System.out.println("ticket added: "+tkts.get(0).getAttrTicketName());
                    quantities.add(quantity1);
                    System.out.println("quantities size: "+quantities.size());
                    fee+=ticket.getAttrTicketPrice()*quantity1;
                    System.out.println("fee: "+fee);
                    System.out.println("ticket 1 added into tp");
                }
                
                if(quantity2!=0){
                    ticket=ticketSessionBean.getTicketById(Long.parseLong(String.valueOf(i+1)));
                    System.out.println("ticketName: "+ticket.getAttrTicketName());
                    tkts.add(ticket);
                    quantities.add(quantity2);
                    System.out.println("quantities size: "+quantities.size());
                    System.out.println("tkts size: "+tkts.size());
                    fee+=ticket.getAttrTicketPrice()*quantity2;
                    System.out.println("fee: "+fee);
                    System.out.println("ticket 2 added into tp");
                }
                if(quantity3!=0){
                    ticket=ticketSessionBean.getTicketById(Long.parseLong(String.valueOf(i+2)));
                    System.out.println("ticketName: "+ticket.getAttrTicketName());
                    tkts.add(ticket);
                    quantities.add(quantity3);
                    System.out.println("tkts size: "+tkts.size());
                    System.out.println("quantities size: "+quantities.size());
                    fee+=ticket.getAttrTicketPrice()*quantity3;
                    System.out.println("fee: "+fee);
                    System.out.println("ticket 3 added into tp");
                }
                if(quantity4!=0){
                    ticket=ticketSessionBean.getTicketById(Long.parseLong(String.valueOf(i+3)));
                    System.out.println("ticketName: "+ticket.getAttrTicketName());
                    tkts.add(ticket);
                    quantities.add(quantity4);
                    System.out.println("tkts size: "+tkts.size());
                    System.out.println("quantities size: "+quantities.size());
                    fee+=ticket.getAttrTicketPrice()*quantity4;
                    System.out.println("fee: "+fee);
                    System.out.println("ticket 4 added into tp");
                }
                if(quantity5!=0){
                    ticket=ticketSessionBean.getTicketById(Long.parseLong(String.valueOf(i+4)));
                    System.out.println("ticketName: "+ticket.getAttrTicketName());
                    tkts.add(ticket);
                    quantities.add(quantity5);
                    System.out.println("tkts size: "+tkts.size());
                    System.out.println("quantities size: "+quantities.size());
                    fee+=ticket.getAttrTicketPrice()*quantity5;
                    System.out.println("fee: "+fee);
                    System.out.println("ticket 5 added into tp");
                }
                if(quantity6!=0){
                    ticket=ticketSessionBean.getTicketById(Long.parseLong(String.valueOf(i+5)));
                    System.out.println("ticketName: "+ticket.getAttrTicketName());
                    tkts.add(ticket);
                    quantities.add(quantity6);
                    System.out.println("tkts size: "+tkts.size());
                    System.out.println("quantities size: "+quantities.size());
                    fee+=ticket.getAttrTicketPrice()*quantity6;
                    System.out.println("fee: "+fee);
                    System.out.println("ticket 6 added into tp");
                }
                if(quantity7!=0){
                    ticket=ticketSessionBean.getTicketById(Long.parseLong(String.valueOf(i+6)));
                    System.out.println("ticketName: "+ticket.getAttrTicketName());
                    tkts.add(ticket);
                    quantities.add(quantity7);
                    System.out.println("tkts size: "+tkts.size());
                    System.out.println("quantities size: "+quantities.size());
                    fee+=ticket.getAttrTicketPrice()*quantity7;
                    System.out.println("fee: "+fee);
                    System.out.println("ticket 7 added into tp");
                }
                if(quantity8!=0){
                    ticket=ticketSessionBean.getTicketById(Long.parseLong(String.valueOf(i+7)));
                    System.out.println("ticketName: "+ticket.getAttrTicketName());
                    tkts.add(ticket);
                    quantities.add(quantity8);
                    System.out.println("tkts size: "+tkts.size());
                    System.out.println("quantities size: "+quantities.size());
                    fee+=ticket.getAttrTicketPrice()*quantity8;
                    System.out.println("fee: "+fee);
                    System.out.println("ticket 3 added into tp");
                }
                if(quantity9!=0){
                    ticket=ticketSessionBean.getTicketById(Long.parseLong(String.valueOf(i+8)));
                    System.out.println("ticketName: "+ticket.getAttrTicketName());
                    tkts.add(ticket);
                    quantities.add(quantity9);
                    System.out.println("tkts size: "+tkts.size());
                    System.out.println("quantities size: "+quantities.size());
                    fee+=ticket.getAttrTicketPrice()*quantity9;
                    System.out.println("fee: "+fee);
                    System.out.println("ticket 9 added into tp");
                }
                
                System.out.println("tkts size: "+tkts.size());
                System.out.println("quantities size: "+quantities.size());
                
                if(coupon!=null){
                //    fee*=coupon.getCouponType().getDiscount();
                    fee=couponSessionBean.getDiscountPrice(coupon, fee);
                    System.out.println("fee deducted to "+fee);
                    tpRemarks+="purchase with coupon "+coupon.getCouponId()+"\n";
                }
                ticketPurchaseSessionBean.updatePurchase(tpId, tkts, quantities, date, fee,"In Progress",tpRemarks);
                
                System.out.println("ticket purchase in progress!");
                
                tp=ticketPurchaseSessionBean.getTicketPurchaseById(tpId);
                System.out.println("tp fee: "+tp.getAttrTicketFee());
                session.setAttribute("tp",tp);
                System.out.println("session set!");
                
                System.out.println("start ep purchase");
                Integer epq1=Integer.parseInt(request.getParameter("epq1"));
                Integer epq2=Integer.parseInt(request.getParameter("epq2"));
                Integer epq3=Integer.parseInt(request.getParameter("epq3"));
                Integer epq4=Integer.parseInt(request.getParameter("epq4"));
                Integer epq5=Integer.parseInt(request.getParameter("epq5"));
                Integer epq6=Integer.parseInt(request.getParameter("epq6"));
                Integer epq7=Integer.parseInt(request.getParameter("epq7"));
                Integer epq8=Integer.parseInt(request.getParameter("epq8"));
                Integer epq9=Integer.parseInt(request.getParameter("epq9"));
                
                
                eppurchase=new ExpressPassPurchaseEntity();
                if(epq1==0&&epq2==0&&epq3==0){
                    System.out.println("no ep purchase!");
                    System.out.println(eppurchase);
                    session.setAttribute("eppurchase",eppurchase);
                    System.out.println("session set!");
                }
                else{
                    System.out.println("have ep purchase");
                    
                    ep=new AttrExpressPassEntity();
                    eps=new ArrayList<AttrExpressPassEntity>();
                    List<Integer> epquantities=new ArrayList<Integer>();
                    double epFee=0.0;
   
                    if(epq1!=0){
                        ep=attrExpressPassSessionBean.getEPById(Long.parseLong(String.valueOf(i)));
                        System.out.println("epName: "+ep.getAttrEPName());
                        eps.add(ep);
                        epquantities.add(epq1);
                        epFee+=ep.getAttrEPPrice();
                    }
                    if(epq2!=0){
                        ep=attrExpressPassSessionBean.getEPById(Long.parseLong(String.valueOf(i+1)));
                        System.out.println("epName: "+ep.getAttrEPName());
                        eps.add(ep);
                        epquantities.add(epq2);
                        epFee+=ep.getAttrEPPrice();
                    }
                    if(epq3!=0){
                        ep=attrExpressPassSessionBean.getEPById(Long.parseLong(String.valueOf(i+2)));
                        System.out.println("epName: "+ep.getAttrEPName());
                        eps.add(ep);
                        epquantities.add(epq3);
                        epFee+=ep.getAttrEPPrice();
                    }
                    if(epq4!=0){
                        ep=attrExpressPassSessionBean.getEPById(Long.parseLong(String.valueOf(i+3)));
                        System.out.println("epName: "+ep.getAttrEPName());
                        eps.add(ep);
                        epquantities.add(epq4);
                        epFee+=ep.getAttrEPPrice();
                    }
                    if(epq5!=0){
                        ep=attrExpressPassSessionBean.getEPById(Long.parseLong(String.valueOf(i+4)));
                        System.out.println("epName: "+ep.getAttrEPName());
                        eps.add(ep);
                        epquantities.add(epq5);
                        epFee+=ep.getAttrEPPrice();
                    }
                    if(epq6!=0){
                        ep=attrExpressPassSessionBean.getEPById(Long.parseLong(String.valueOf(i+5)));
                        System.out.println("epName: "+ep.getAttrEPName());
                        eps.add(ep);
                        epquantities.add(epq6);
                        epFee+=ep.getAttrEPPrice();
                    }
                    if(epq7!=0){
                        ep=attrExpressPassSessionBean.getEPById(Long.parseLong(String.valueOf(i+6)));
                        System.out.println("epName: "+ep.getAttrEPName());
                        eps.add(ep);
                        epquantities.add(epq7);
                        epFee+=ep.getAttrEPPrice();
                    }
                    if(epq8!=0){
                        ep=attrExpressPassSessionBean.getEPById(Long.parseLong(String.valueOf(i+7)));
                        System.out.println("epName: "+ep.getAttrEPName());
                        eps.add(ep);
                        epquantities.add(epq8);
                        epFee+=ep.getAttrEPPrice();
                    }
                    if(epq9!=0){
                        ep=attrExpressPassSessionBean.getEPById(Long.parseLong(String.valueOf(i+8)));
                        System.out.println("epName: "+ep.getAttrEPName());
                        eps.add(ep);
                        epquantities.add(epq9);
                        epFee+=ep.getAttrEPPrice();
                    }
                    
                    eppurchase.setAttrEPs(eps);
                    eppurchase.setEpBookDate(date);
                    eppurchase.setEpQuantities(epquantities);
                    eppurchase.setEppStatus("In Progress");
                    if(coupon!=null){
                     //   epFee-= coupon.getCouponType().getDiscount();
                        epFee=couponSessionBean.getDiscountPrice(coupon,epFee);
                        System.out.println("epfee deducted to " + epFee);
                        eppurchaseRemarks+="purchase with coupon "+coupon.getCouponId()+"\n";
                    }
                    eppurchase.setEpFee(epFee);
                    System.out.println("eppurchase configured");
                    
                    expressPassPurchaseSessionBean.addEPPurchase(eppurchase);
                    System.out.println("eppurchase added into database");
                    
                    session.setAttribute("eppurchase",eppurchase);
                    System.out.println("session set!");
                    
                }

                request.getRequestDispatcher("/attrTicketBookingInformation.jsp").forward(request, response);
            }else if("attrTicketBookingPayment".equals(page)){
                System.out.println("***attrTicketBookingPayment page***");
 
                request.getRequestDispatcher("/attrTicketBookingPayment.jsp").forward(request, response);
            }else if("attrTicketBookingConfirmation".equals(page)){
                System.out.println("***attrTicketBookingConfirmation page***");
                Date today=new Date();
                String email=request.getParameter("email");
                System.out.println("email: "+email);
                tp=(TicketPurchaseEntity)session.getAttribute("tp");
                System.out.println("tpStatus:"+tp.getAttrTPStatus());
                attr=(AttractionEntity)session.getAttribute("attr");
                System.out.println("attr: "+attr.getAttrName());
                coupon=(CouponEntity)session.getAttribute("coupon");
                System.out.println("get coupon");
                Boolean coinPay=false;
                Boolean enoughCoin=false;
                Boolean hasEPPurchase=false;
                double totalPrice=tp.getAttrTicketFee();
                System.out.println("total price: "+totalPrice);
                
                String paymentMethod =request.getParameter("paymentMethod");
                if(paymentMethod.equals("Coin")){
                    coinPay=true;
                    System.out.println("pay by coin");
                }
                
                eppurchase=(ExpressPassPurchaseEntity)session.getAttribute("eppurchase");
                if(eppurchase.getAttrEPs().isEmpty()){
                    System.out.println("no eppurchase");
                }
                else{
                    hasEPPurchase=true;
                    totalPrice+=eppurchase.getEpFee();
                    System.out.println("total price with eppurchase: "+totalPrice);
//                    if(member!=null){
//                        eppurchase.setMember(member);                        
//                    }
                }
//                eppurchase.setEppStatus("Purchased");
//                expressPassPurchaseSessionBean.updateEPPurchase(eppurchase);
               
                member=memberSessionBean.getMemberByEmail(email);
                if(member==null&&coinPay==true){
                    message="You must be a member to pay by coins";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("/attrTicketBookingPayment.jsp").forward(request, response);            
                }
                if(member!=null){
                    System.out.println("is member");
                    session.setAttribute("member",member);
                    if(coinPay){
                        enoughCoin=memberTransactionSessionBean.checkCoinAmount(member,totalPrice);
                        if(!enoughCoin){
                            System.out.println("coin is not enough");
                            message = "Coin is not enough.";
                            request.setAttribute("message", message);
                            request.getRequestDispatcher("/attrTicketBookingPayment.jsp").forward(request, response);
                            return;
                        }
                        memberTransactionSessionBean.payByCoin(member,totalPrice);
                        System.out.println("pay by coin successful");
                    }else{
                        System.out.println("not coinpay");
                        memberTransactionSessionBean.addPoint(member, totalPrice);
                        memberTransactionSessionBean.addCoin(member, totalPrice);
                        memberTransactionSessionBean.updateVIP(member);
                    }                  
                    tp.setMember(member);
                    ticketPurchaseSessionBean.updateTicketPurchase(tp);
                    memberSessionBean.updateMemberTicketPurchase(member, tp);
                    System.out.println("add member ticket purchase");
                    if(hasEPPurchase){
                        eppurchase.setMember(member);
                        expressPassPurchaseSessionBean.updateEPPurchase(eppurchase);
                        memberSessionBean.updateMemberExpressPassPurchase(member, eppurchase);
                        System.out.println("add member ep purchase");
                    }
                    mt=new MemberTransactionEntity();
                    Date date=(Date)session.getAttribute("date");
                    mt.setMtDate(date);
                    mt.setMtAmount(totalPrice);
                    mt.setMtDepartment("attraction");
                    mt.setMtMode(true);
                    mt.setPaymentStatus(true);
                    mt.setMemberEmail(email);
                    String description = member.getMemberName() + ": Your purchase of attraction ticket at " + date + " with a total expense of: " + totalPrice;
                    mt.setMtDescription(description);
                    mt.setMtDate(today);
                    mt=memberTransactionSessionBean.addMemberTransaction(mt);
                    System.out.println("member transaction added");
                    Set<MemberTransactionEntity> allMTs=member.getMemberTransactions();
                    allMTs.add(mt);
                    member.setMemberTransactions(allMTs);
                    memberSessionBean.updateMember(member);
                    System.out.println("member updated"); 
                }
               
                tp.setAttrTPStatus("Purchased");
                ticketPurchaseSessionBean.updateTicketPurchase(tp); 
                System.out.println("tp status updated");
                if(coupon!=null){
                    couponSessionBean.useCoupon(coupon, today, "attraction");
//                    coupon.setDepartment("attraction");
//                    coupon.setStatus("Used");
//                    couponSessionBean.updateCoupon(coupon);
                    System.out.println("coupon updated");
                }
                if(hasEPPurchase){
                    eppurchase.setEppStatus("Purchased");
                    expressPassPurchaseSessionBean.updateEPPurchase(eppurchase);
                    System.out.println("epp status updated");
                }
                
               System.out.println("generating barcode");
               System.out.println("tpId" +tp.getTpId());
               generateBarcodeSessionBean.generate(String.valueOf(tp.getTpId()));
               if(hasEPPurchase){
                   generateBarcodeSessionBean.generate(String.valueOf(eppurchase.getEppId()));
               }
               System.out.println("barcode generated");
               
            
                
                emailSessionBean.emailAttractionTicketSingle(email, tp);
                System.out.println("email sent");
                if(hasEPPurchase){
                    System.out.println("start send email for eppurchase");
                    emailSessionBean.emailAttractionTicketExpress(email,eppurchase);
                    System.out.println("email sent for eppurchase");
                }
                
                if(member.isVIP()){
                    System.out.println("member is VIP");
                    eppurchase=expressPassPurchaseSessionBean.prepareEPForVIP();
                    generateBarcodeSessionBean.generate(String.valueOf(eppurchase.getEppId()));
                    emailSessionBean.emailAttractionTicketExpress(email,eppurchase);
                    System.out.println("upgraded express pass sent");
                    
                }
                           
                request.getRequestDispatcher("/attrTicketBookingConfirmation.jsp").forward(request, response);
            }else {
                System.out.println("other page");
            }
        } catch (Exception e) {
            System.out.println(e);
            log("Exception in ATMSServlet.processRequest()");
            //System.out.println(e);
        }       
    }
    
   /* public boolean checkTicketAvailability(int quantity1, int quantity2, Date date, AttractionEntity attr){
        List<TicketPurchaseEntity> tickets=ticketPurchaseSessionBean.getTicketsForAttrDate(attr,date);
    }*/
   
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
