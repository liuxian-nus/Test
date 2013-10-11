/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ATMS.entity.TicketEntity;
import ATMS.entity.TicketPurchaseEntity;
import ATMS.session.TicketPurchaseSessionBean;
import ATMS.session.TicketSessionBean;
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
    private TicketSessionBean ticketSessionBean;
    @EJB
    private TicketPurchaseSessionBean ticketPurchaseSessionBean;
    
    TicketEntity ticket;
    List<TicketEntity> tkts;
    TicketPurchaseEntity tp;

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
                

                request.getRequestDispatcher("/adventureWorld.jsp").forward(request, response);
            }else if("ticketBooking".equals(page)){
                System.out.println("***ticketBooking page***");
                

                request.getRequestDispatcher("/ticketBooking.jsp").forward(request, response);
            }else if("ticketBookingConfirm".equals(page)){
                System.out.println("***ticketBookingConfirm page***");
                
        /*        Long ticketId=Long.parseLong(request.getParameter("OTticket"));
                Integer quantity=Integer.parseInt(request.getParameter("quantity"));*/
                
            /*    String tickets=request.getParameter("OTticket");
                System.out.println("OTticket: "+tickets);*/
                
        /*        Long quantity1=Long.parseLong(request.getParameter("quantity1"));
                Long quantity2=Long.parseLong(request.getParameter("quantity2"));
                Long quantity3=Long.parseLong(request.getParameter("quantity3"));*/
                Integer quantity1=Integer.parseInt(request.getParameter("quantity1"));
                Integer quantity2=Integer.parseInt(request.getParameter("quantity2"));
                Integer quantity3=Integer.parseInt(request.getParameter("quantity3"));
                
                Integer day=Integer.parseInt(request.getParameter("dateDay"));
                Integer month=Integer.parseInt(request.getParameter("dateMonth"));
                Integer year=Integer.parseInt(request.getParameter("dateYear"));
                Date date;
                date = new Date(year - 1900, month - 1, day);
                
                System.out.println("quantity1: "+quantity1);
 
                
                tp=new TicketPurchaseEntity();
                tkts=new ArrayList<TicketEntity>();
                boolean tpInitialised=false;
                Long tpId=ticketPurchaseSessionBean.addTicketPurchase(tp);
                
                if(quantity1!=0){
      //              System.out.println("into if");
                    ticket=ticketSessionBean.getTicketById(Long.parseLong(String.valueOf(1)));
                    System.out.println("ticketName: "+ticket.getTicketName());
                    tkts.add(ticket);
                    System.out.println("ticket added: "+tkts.get(0).getTicketName());
                    ticketPurchaseSessionBean.updateTicketListAndQuantity(tpId, tkts,quantity1);  
                    System.out.println("tpId received: "+tpId);
                    tpInitialised=true;
                    System.out.println("tpInitialised: "+tpInitialised);
                    System.out.println("ticket 1 added into tp");
      //              ticketPurchaseSessionBean.addTicket(ticket);
                }
                
                if(quantity2!=0){
      //              System.out.println("into if");
                    ticket=ticketSessionBean.getTicketById(Long.parseLong(String.valueOf(2)));
                    System.out.println("ticketName: "+ticket.getTicketName());
                    tkts.add(ticket);
                    System.out.println("tkts size: "+tkts.size());
              //      System.out.println("ticket added: "+tkts.get(1).getTicketName());
             /*       if(tpInitialised){
                        System.out.println("tp has been initialised");
                        System.out.println("tpId passed in: "+tpId);
                        tpId=ticketPurchaseSessionBean.updateTicketListAndQuantity(tpId, tkts,quantity2);  
                    }                 
                    else{
                        System.out.println("tp has not been initialised");
                        ticketPurchaseSessionBean.updateTicketListAndQuantity(tkts,quantity2);
                        tpInitialised=true;
                    }*/
                    ticketPurchaseSessionBean.updateTicketListAndQuantity(tpId, tkts,quantity2);
                        
          //          System.out.println("tpInitialised: "+tpInitialised);
                    System.out.println("ticket 2 added into tp");
      //              ticketPurchaseSessionBean.addTicket(ticket);
                }
                if(quantity3!=0){
                    ticket=ticketSessionBean.getTicketById(Long.parseLong(String.valueOf(3)));
                    System.out.println("ticketName: "+ticket.getTicketName());
                    tkts.add(ticket);
                    System.out.println("tkts size: "+tkts.size());
                    ticketPurchaseSessionBean.updateTicketListAndQuantity(tpId, tkts,quantity3);
                    System.out.println("ticket 3 added into tp");
                }
 
                request.getRequestDispatcher("/ticketBookingConfirm.jsp").forward(request, response);
            }else {
                System.out.println("other page");
            }
        } catch (Exception e) {
            System.out.println(e);
            log("Exception in ATMSServlet.processRequest()");
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
        System.out.println("ATMSServlet: destroy()");
    }

   /* @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>*/
}
