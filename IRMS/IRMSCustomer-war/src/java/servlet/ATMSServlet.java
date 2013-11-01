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
import CRMS.entity.MemberEntity;
import CRMS.session.GenerateBarcodeSessionBean;
import CRMS.session.MemberSessionBean;
import CRMS.session.MemberTransactionSessionBean;
import ERMS.session.EmailSessionBean;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Jieqiong
 */
//@WebServlet(name = "ATMSServlet", urlPatterns = {"/ATMSServlet"})
@WebServlet(urlPatterns = {"/ATMSServlet", "/ATMSServlet/*"})
public class ATMSServlet extends HttpServlet {
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
                System.out.println("generating barcode");
               generateBarcodeSessionBean.generate("1234567");
               System.out.println("barcode generated");
                

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
            }else if("ticketBooking".equals(page)){
                System.out.println("***ticketBooking page***");
            /*    List<AttrTicketEntity> tickets=ticketSessionBean.getAllTickets();
                session.setAttribute("tickets", tickets);
                List<AttrExpressPassEntity> eps=attrExpressPassSessionBean.getAllEPs();
                session.setAttribute("eps",eps);*/
                
                
                

                request.getRequestDispatcher("/ticketBooking.jsp").forward(request, response);
            }else if("ticketBookingInformation".equals(page)){
                System.out.println("***ticketBookingInformation page***");                    
                
                Integer quantity1=Integer.parseInt(request.getParameter("quantity1"));
                Integer quantity2=Integer.parseInt(request.getParameter("quantity2"));
                Integer quantity3=Integer.parseInt(request.getParameter("quantity3"));
                             
                Integer day=Integer.parseInt(request.getParameter("dateDay"));
                Integer month=Integer.parseInt(request.getParameter("dateMonth"));
                Integer year=Integer.parseInt(request.getParameter("dateYear"));
                Date date;
                date = new Date(year - 1900, month - 1, day);
                
                String attrId=request.getParameter("attrId");
                System.out.println("attrId: "+attrId);
                attr=attractionSessionBean.getAttrById(attrId);
                session.setAttribute("attr", attr);
                System.out.println("session set");
                
           //     boolean ticketAvailable;
           //     ticketAvailable=checkTicketAvailability(quantity1, quantity2,date, attr);
                               
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
                
                System.out.println("tkts size: "+tkts.size());
                System.out.println("quantities size: "+quantities.size());
                ticketPurchaseSessionBean.updatePurchase(tpId, tkts, quantities, date, fee,"In Progress");
                
                System.out.println("ticket purchase in progress!");
                
                tp=ticketPurchaseSessionBean.getTicketPurchaseById(tpId);
                System.out.println("tp fee: "+tp.getAttrTicketFee());
                session.setAttribute("tp",tp);
                System.out.println("session set!");
                
                System.out.println("start ep purchase");
                Integer epq1=Integer.parseInt(request.getParameter("epq1"));
                Integer epq2=Integer.parseInt(request.getParameter("epq2"));
                Integer epq3=Integer.parseInt(request.getParameter("epq3"));
                
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
                        ep=attrExpressPassSessionBean.getEPById(Long.parseLong(String.valueOf(1)));
                        System.out.println("epName: "+ep.getAttrEPName());
                        eps.add(ep);
                        epquantities.add(epq1);
                        epFee+=ep.getAttrEPPrice();
                    }
                    if(epq2!=0){
                        ep=attrExpressPassSessionBean.getEPById(Long.parseLong(String.valueOf(2)));
                        System.out.println("epName: "+ep.getAttrEPName());
                        eps.add(ep);
                        epquantities.add(epq2);
                        epFee+=ep.getAttrEPPrice();
                    }
                    if(epq3!=0){
                        ep=attrExpressPassSessionBean.getEPById(Long.parseLong(String.valueOf(3)));
                        System.out.println("epName: "+ep.getAttrEPName());
                        eps.add(ep);
                        epquantities.add(epq2);
                        epFee+=ep.getAttrEPPrice();
                    }
                    
                    eppurchase.setAttrEPs(eps);
                    eppurchase.setEpBookDate(date);
                    eppurchase.setEpQuantities(epquantities);
                    eppurchase.setEppStatus("In Progress");
                    eppurchase.setEpFee(epFee);
                    System.out.println("eppurchase configured");
                    
                    expressPassPurchaseSessionBean.addEPPurchase(eppurchase);
                    System.out.println("eppurchase added into database");
                    
                    session.setAttribute("eppurchase",eppurchase);
                    System.out.println("session set!");
                    
                }


                request.getRequestDispatcher("/ticketBookingInformation.jsp").forward(request, response);
            }else if("ticketBookingPayment".equals(page)){
                System.out.println("***ticketBookingPayment page***");
 
                request.getRequestDispatcher("/ticketBookingPayment.jsp").forward(request, response);
            }else if("ticketBookingConfirmation".equals(page)){
                System.out.println("***ticketBookingConfirmation page***");
                
                String email=request.getParameter("email");
                System.out.println("email: "+email);
                tp=(TicketPurchaseEntity)session.getAttribute("tp");
                System.out.println("tpStatus:"+tp.getAttrTPStatus());
                attr=(AttractionEntity)session.getAttribute("attr");
                System.out.println("attr: "+attr.getAttrName());
               
                member=memberSessionBean.getMemberByEmail(email);
                if(member!=null){
                    System.out.println("is member");
                    tp.setMember(member);
                    ticketPurchaseSessionBean.updateTicketPurchase(tp);
                    memberSessionBean.updateMemberTicketPurchase(member, tp);
//                    System.out.println("add member transaction");
//                    memberTransactionSessionBean.addMemberTransaction(member,tp.getAttrTicketFee(),
//                            tp.getAttrTicketBookDate(),"Attraction","", false);
//                    System.out.println("transaction added");

                }
               
                tp.setAttrTPStatus("Purchased");
                ticketPurchaseSessionBean.updateTicketPurchase(tp); 
                
               System.out.println("generating barcode");
               System.out.println("tpId" +tp.getTpId());
               generateBarcodeSessionBean.generate(String.valueOf(tp.getTpId()));
               System.out.println("barcode generated");
               
              
                  
                eppurchase=(ExpressPassPurchaseEntity)session.getAttribute("eppurchase");
                if(eppurchase.getAttrEPs().isEmpty()){
                    System.out.println("no eppurchase");
                }
                else{
                    if(member!=null){
                        eppurchase.setMember(member);                        
                    }
                }
                eppurchase.setEppStatus("Purchased");
                expressPassPurchaseSessionBean.updateEPPurchase(eppurchase);
                
         //       emailSessionBean.emailAttractionTicketSingle(email, tp);
         //       System.out.println("email sent");
                
//               
                
                request.getRequestDispatcher("/ticketBookingConfirmation.jsp").forward(request, response);
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
