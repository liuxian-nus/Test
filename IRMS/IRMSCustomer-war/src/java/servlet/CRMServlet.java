package servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import CRMS.entity.MemberEntity;
import CRMS.session.MemberResetPasswordSessionBean;
import FBMS.entity.RestaurantEntity;
import FBMS.session.IndReservationSessionBeanRemote;
import CRMS.session.MemberSessionBean;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lionetdd, Jieqiong
 */


@WebServlet(urlPatterns = {"/CRMServlet", "/CRMServlet/*"})
public class CRMServlet extends HttpServlet {
    @EJB
    private MemberResetPasswordSessionBean memberResetPasswordSessionBean;
    @EJB
    private MemberSessionBean memberSession;
    

  
    @EJB
    private IndReservationSessionBeanRemote indReservationSessionBean;
    private ArrayList data = null;
    private MemberEntity member;

  
   
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
    public void init(){
        System.out.println("CRMSERVLET: init()");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CRMSERVLET: processRequest()");
        
       /* response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();*/
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            
            String temp = request.getServletPath();

            String page = request.getPathInfo();
            page = page.substring(1);
            System.out.println("CRMSServlet page: "+page);
            

             if ("member".equals(page))
            {
                System.out.println("***member page***"); 
                request.getRequestDispatcher("/member.jsp").forward(request, response);

            }
             else if ("memberInfo".equals(page))
            {
                System.out.println("***memberInfo page***");
                
                System.out.println(request.getParameter("email"));
                System.out.println(request.getParameter("password"));
                
                String email=request.getParameter("email");
                String password=request.getParameter("password");
                
                System.out.println(email);
                System.out.println(password);
                
                
                boolean isLogin=memberSession.login(email, password);
                System.out.println(isLogin);
                member=memberSession.getMemberByEmail(email);
                System.out.println(member.getMemberName());
                
                
                
                
                request.getRequestDispatcher("/memberInfo.jsp").forward(request, response);

            }
             else if ("memberRegister".equals(page))
            {
                System.out.println("***memberRegister page***");    
                request.getRequestDispatcher("/memberRegister.jsp").forward(request, response);

            }else if ("memberRegisterResult".equals(page)){
                
                System.out.println("***memberRegisterResult page***");
                
                String userName=request.getParameter("username");
                String email=request.getParameter("e-mail");
                String password1=request.getParameter("password");
                String password2=request.getParameter("password2");
                String mobile=request.getParameter("mobile");
                String nationality=request.getParameter("nationality");
                String day=request.getParameter("dateDay");
                String month=request.getParameter("dateMonth");
                String year=request.getParameter("dateYear");
                String maritalStatus=request.getParameter("marital");
                String gender=request.getParameter("gender");
                Boolean subscribe=Boolean.valueOf(request.getParameter("subscribe"));
                String this_date=day+"-"+month+"-"+year;
                System.out.println(this_date);
                Date date=new SimpleDateFormat("dd-MMMMM-yyyy").parse("01-July-2013");
                System.out.println(date);
                   
                member=memberSession.addMember(email, userName, password1, password2, mobile, gender, nationality, date, maritalStatus, subscribe);
                
                request.getRequestDispatcher("/memberRegisterResult.jsp").forward(request, response);
            }
             
             else if ("memberForgetPassword".equals(page))
            {
                System.out.println("***memberForgetPassword page***");
//                String question=request.getParameter()
                String email=request.getParameter("email");
                memberResetPasswordSessionBean.ResetPassword(email);
           //     memberResetPasswordSessionBean.ResetPassword("leijq369@gmail.com");
                request.getRequestDispatcher("/memberForgetPassword.jsp").forward(request, response);

            }
             else if("memberInfoEdition".equals(page)){
                 System.out.println("***memberInfoEdition page***");
                 
             }
            else{
                System.out.println("other page");
            }
//          
//             
//            dispatcher=servletContext.getNamedDispatcher(page);
//            System.out.println("dispatcher set up");
//            System.out.println(dispatcher);
//            if(dispatcher==null){
//                dispatcher=servletContext.getNamedDispatcher("Error");
//            }
//            System.out.println("Before push content");
//            dispatcher.forward(request, response);
//            System.out.println("After push content");


            
        }catch (Exception e){
            System.out.println(e);
            log("Exception in CRMServlet.processRequest()");
            //System.out.println(e);
        }
    }
    

    private ArrayList makeReservation(HttpServletRequest request) throws ParseException{
        DateFormat formatter =new SimpleDateFormat("dd/MM/yy");
        ArrayList al=new ArrayList();
        Date indReservationDateTime=formatter.parse(request.getParameter("indReservationDateTime"));
        
        return al;
        
    }
            
            /* TODO output your page here. You may use following sample code. */

              
         /* TODO output your page here. You may use following sample code. */

            /*
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet irmsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet irmsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            * */
        /*} finally {            
            out.close();
        }*/
    
    

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
   
    

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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

    private ArrayList searchRestaurant(HttpServletRequest request) {
        
        ArrayList al = new ArrayList();
        System.out.println("method invoked");
        String restNeighbourhood = request.getParameter("restNeighbourhood");
        System.out.println(restNeighbourhood);
        System.out.println("restNeighbourhood retrieved");
        String restTypeOfPlace   = request.getParameter("restTypeOfPlace");
        System.out.println(restTypeOfPlace);
        String restCuisine       = request.getParameter("restCuisine");
        System.out.println(restCuisine);
        String keyword           = request.getParameter("keyword");
        System.out.println(keyword);
        RestaurantEntity re = indReservationSessionBean.createRestaurantEntity(restNeighbourhood, restTypeOfPlace, restCuisine, keyword);
        
        System.out.println(re.getRestNeighbourhood()+re.getRestCuisine()+re.getRestTypeOfPlace());

        Set <RestaurantEntity> res =   indReservationSessionBean.searchRestaurant(re);  


        
        al.addAll(res);
        al.add("Restaurant Search has been performed!");
        
      //  System.out.println(al.get(0));
        System.out.println("CRMServlet: restaurant search has been completed!");



        return al;
        //To change body of generated methods, choose Tools | Templates.
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
    public void destroy(){
        System.out.println("CRMServlet: destroy()");
    }
    
    
}
