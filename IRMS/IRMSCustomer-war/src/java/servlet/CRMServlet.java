package servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import FBMS.entity.RestaurantEntity;
import FBMS.session.IndReservationSessionBeanRemote;
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
 * @author lionetdd
 */


@WebServlet(urlPatterns = {"/CRMServlet", "/CRMServlet/*"})
public class CRMServlet extends HttpServlet {
   @EJB
    private IndReservationSessionBeanRemote indReservationSessionBean;
    private ArrayList data = null;

  
   
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
            System.out.println(page);
            

             if ("member".equals(page))
            {
                System.out.println("***member page***");
       
                request.getRequestDispatcher("/member.jsp").forward(request, response);

            }
             else if ("memberInfo".equals(page))
            {
                System.out.println("***member page***");
                System.out.println(request.getParameter("email"));
                request.getRequestDispatcher("/memberInfo.jsp").forward(request, response);

            }
             else if ("memberRegister".equals(page))
            {
                System.out.println("***member page***");
                request.getRequestDispatcher("/memberRegister.jsp").forward(request, response);

            }
             else if ("memberForgetPassword".equals(page))
            {
                System.out.println("***memberForgetPassword page***");
                request.getRequestDispatcher("/memberForgetPassword.jsp").forward(request, response);

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