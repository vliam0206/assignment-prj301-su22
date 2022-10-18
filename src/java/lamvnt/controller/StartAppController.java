/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lamvnt.registration.RegistrationDAO;
import lamvnt.registration.RegistrationDTO;
import lamvnt.utils.MyAppConstants;

/**
 *
 * @author LamVo
 */
@WebServlet(name = "StartAppController", urlPatterns = {"/StartAppController"})
public class StartAppController extends HttpServlet {

//    private final String LOGIN_PAGE = "login.html";
//    private final String SEARCH_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        ServletContext context = getServletContext();
        Properties siteMaps= (Properties)context.getAttribute("SITEMAPS");     
        String url = siteMaps.getProperty(MyAppConstants.StartAppFeatures.LOGIN_PAGE);
        try {
            //1. get all cookies
            Cookie[] cookies = request.getCookies(); // Vi co nhieu cookies, nen lay ra ca mang            
            if (cookies != null) {                //                
                //2. get last cookies
                Cookie lastCookie = cookies[cookies.length - 1];
                //3. get username(name) & password(value)
                String username = lastCookie.getName();
                String password = lastCookie.getValue();
                //4. call DAO
                RegistrationDAO dao = new RegistrationDAO();
//                boolean checkLogin = dao.checkLogin(username, password);
                RegistrationDTO checkLogin = dao.checkLogin(username, password);
//5. process result
                if (checkLogin != null) {
                    url = siteMaps.getProperty(MyAppConstants.StartAppFeatures.SEARCH_PAGE);
                }
            }// cookies has existed
        } catch (SQLException ex) {
            log("StartAppController _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("StartAppController _ Naming " + ex.getMessage());
        } finally { // Dung reqDispatcher de che url cung dc, Dung sendredirect cung dc
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
